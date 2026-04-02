<template>
  <aside class="course-outline">
    <div class="outline-tabs">
      <button
        v-for="tab in tabs"
        :key="tab.value"
        class="outline-tab"
        :class="{ active: modelTab === tab.value }"
        @click="$emit('tab-change', tab.value)"
      >
        {{ tab.label }}
      </button>
    </div>

    <div class="outline-tree">
      <div v-for="item in filteredTree" :key="item.id" class="outline-item">
        <!-- 条目行 -->
        <div
          class="item-row"
          :class="{
            active: activeTutorial?.id === item.id,
            [`process-${getProcessClass(item.processType)}`]: item.processType
          }"
        >
          <!-- 展开箭头（仅有子教程时显示） -->
          <n-icon
            v-if="hasChildren(item)"
            size="16"
            class="expand-icon"
            :class="{ expanded: expandedSet.has(item.id!) }"
            @click.stop="toggleExpand(item.id!)"
          >
            <ChevronForwardOutline />
          </n-icon>
          <span v-else class="expand-placeholder" />

          <!-- 类型图标 -->
          <n-icon size="16" class="item-type-icon">
            <DocumentTextOutline v-if="item.type === 'TEXT'" />
            <PlayCircleOutline v-else />
          </n-icon>

          <!-- 标题（点击获取教程详情） -->
          <span class="item-title" @click="$emit('select', item)">{{ item.title }}</span>

          <!-- 进度图标 -->
          <n-icon v-if="item.processType === 'LEARNED'" size="14" class="process-icon learned">
            <CheckmarkCircle />
          </n-icon>
          <n-icon v-else-if="item.processType === 'IN_PROGRESS'" size="14" class="process-icon in-progress">
            <TimeOutline />
          </n-icon>
        </div>

        <!-- 子教程列表 -->
        <div v-if="hasChildren(item)" v-show="expandedSet.has(item.id!)" class="item-children">
          <div
            v-for="child in item.children || []"
            :key="child.id"
            class="item-row child-row"
            :class="{
              active: activeTutorial?.id === child.id,
              [`process-${getProcessClass(child.processType)}`]: child.processType
            }"
            @click="$emit('select', child)"
          >
            <span class="expand-placeholder" />
            <n-icon size="16" class="item-type-icon">
              <DocumentTextOutline v-if="child.type === 'TEXT'" />
              <PlayCircleOutline v-else />
            </n-icon>
            <span class="item-title">{{ child.title }}</span>
            <n-icon v-if="child.processType === 'LEARNED'" size="14" class="process-icon learned">
              <CheckmarkCircle />
            </n-icon>
            <n-icon v-else-if="child.processType === 'IN_PROGRESS'" size="14" class="process-icon in-progress">
              <TimeOutline />
            </n-icon>
          </div>
        </div>
      </div>

      <!-- 空状态 -->
      <n-empty v-if="filteredTree.length === 0" description="暂无教程" size="small" />
    </div>
  </aside>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { NIcon, NEmpty } from 'naive-ui'
import { ChevronForwardOutline, DocumentTextOutline, PlayCircleOutline, CheckmarkCircle, TimeOutline } from '@vicons/ionicons5'
import type { TutorialBriefVO } from '@/api'
import type { ActiveTab } from '@/composables/useCourseDetail'

interface Props {
  filteredTree: TutorialBriefVO[]
  activeTutorial: TutorialBriefVO | null
  modelTab: ActiveTab
}

interface Emits {
  (e: 'select', tutorial: TutorialBriefVO): void
  (e: 'tab-change', tab: ActiveTab): void
}

const props = defineProps<Props>()
defineEmits<Emits>()

const tabs = [
  { label: '文字教程', value: 'TEXT' as const },
  { label: '视频教程', value: 'VIDEO' as const }
]

const expandedSet = ref<Set<string>>(new Set())

// 判断是否有子教程
function hasChildren(item: TutorialBriefVO): boolean {
  return !!(item.children && item.children.length > 0)
}

// 默认展开所有有子教程的条目
function initExpanded(nodes: TutorialBriefVO[]) {
  const set = new Set<string>()
  const walk = (list: TutorialBriefVO[]) => {
    list.forEach(node => {
      if (hasChildren(node)) {
        set.add(node.id!)
        walk(node.children!)
      }
    })
  }
  walk(nodes)
  expandedSet.value = set
}
initExpanded(props.filteredTree)

function toggleExpand(id: string) {
  const set = new Set(expandedSet.value)
  if (set.has(id)) {
    set.delete(id)
  } else {
    set.add(id)
  }
  expandedSet.value = set
}

function getProcessClass(processType?: string): string {
  switch (processType) {
    case 'LEARNED': return 'learned'
    case 'IN_PROGRESS': return 'in-progress'
    default: return 'unlearned'
  }
}
</script>

<style scoped lang="scss">
.course-outline {
  background: $bg-white;
  border-radius: $border-radius-large;
  border: 1px solid $border-light;
  overflow: hidden;
  position: sticky;
  top: calc($header-height + $spacing-md);
  max-height: calc(100vh - $header-height - $spacing-xl);
  overflow-y: auto;

  &::-webkit-scrollbar { width: 4px; }
  &::-webkit-scrollbar-thumb { background: $border-base; border-radius: 2px; }
}

.outline-tabs {
  display: flex;
  border-bottom: 1px solid $border-light;
  background: $bg-color;
}

.outline-tab {
  flex: 1;
  padding: $spacing-sm $spacing-md;
  font-size: $font-size-small;
  color: $text-regular;
  background: transparent;
  border: none;
  cursor: pointer;
  transition: all 0.2s;
  border-bottom: 2px solid transparent;

  &:hover { color: $primary-color; }
  &.active {
    color: $primary-color;
    background: $bg-white;
    border-bottom-color: $primary-color;
  }
}

.outline-tree {
  padding: $spacing-sm 0;
}

.outline-item {
  border-bottom: 1px solid $border-extra-light;
  &:last-child { border-bottom: none; }
}

.item-row {
  display: flex;
  align-items: center;
  gap: $spacing-sm;
  padding: $spacing-sm $spacing-md;
  cursor: pointer;
  transition: all 0.2s;
  border-left: 3px solid transparent;

  &:hover { background: $bg-color; }

  &.active {
    background: rgba($primary-color, 0.08);
    border-left-color: $primary-color;
    .item-title { color: $primary-color; font-weight: 500; }
  }

  // 进度
  &.process-learned { opacity: 0.6; .item-type-icon { color: $success-color; } }
  &.process-in-progress { .item-title { color: $warning-color; } }
  &.process-unlearned { opacity: 0.8; }
}

.expand-icon {
  flex-shrink: 0;
  color: $text-placeholder;
  cursor: pointer;
  transition: transform 0.2s;

  &.expanded { transform: rotate(90deg); }
  &:hover { color: $primary-color; }
}

.expand-placeholder {
  width: 16px;
  flex-shrink: 0;
}

.item-type-icon {
  color: $text-secondary;
  flex-shrink: 0;
}

.item-title {
  flex: 1;
  font-size: $font-size-small;
  color: $text-primary;
  font-weight: 500;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.process-icon {
  flex-shrink: 0;
  &.learned { color: $success-color; }
  &.in-progress { color: $warning-color; }
}

.item-children {
  background: $bg-color;
}

.child-row {
  padding-left: calc($spacing-md + 16px + $spacing-xs);

  .item-title {
    font-weight: 400;
    color: $text-regular;
  }
}
</style>
