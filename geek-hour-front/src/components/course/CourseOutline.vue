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
      <div v-for="chapter in filteredTree" :key="chapter.id" class="outline-chapter">
        <!-- 章节标题 -->
        <div class="chapter-header" @click="toggleChapter(chapter.id!)">
          <n-icon size="16" class="chapter-icon" :class="{ expanded: expandedChapters.has(chapter.id!) }">
            <ChevronForwardOutline />
          </n-icon>
          <span class="chapter-title">{{ chapter.title }}</span>
        </div>

        <!-- 章节下的教程列表 -->
        <div v-show="expandedChapters.has(chapter.id!)" class="chapter-lessons">
          <div
            v-for="lesson in chapter.children || []"
            :key="lesson.id"
            class="lesson-item"
            :class="{
              active: activeTutorial?.id === lesson.id,
              [`process-${getProcessClass(lesson.processType)}`]: lesson.processType
            }"
            @click="$emit('select', lesson)"
          >
            <n-icon size="16" class="lesson-icon">
              <DocumentTextOutline v-if="lesson.type === 'TEXT'" />
              <PlayCircleOutline v-else />
            </n-icon>
            <span class="lesson-title">{{ lesson.title }}</span>

            <!-- 进度图标 -->
            <n-icon v-if="lesson.processType === 'LEARNED'" size="14" class="process-icon learned">
              <CheckmarkCircle />
            </n-icon>
            <n-icon v-else-if="lesson.processType === 'IN_PROGRESS'" size="14" class="process-icon in-progress">
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
import { ref, computed } from 'vue'
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

// Tab 配置
const tabs = [
  { label: '全部', value: 'ALL' as const },
  { label: '文字教程', value: 'TEXT' as const },
  { label: '视频教程', value: 'VIDEO' as const }
]

// 展开的章节
const expandedChapters = ref<Set<string>>(new Set())

// 默认展开所有章节
const initializeExpanded = () => {
  const set = new Set<string>()
  const addToSet = (nodes: TutorialBriefVO[]) => {
    nodes.forEach(node => {
      if (node.children && node.children.length > 0) {
        set.add(node.id!)
        addToSet(node.children)
      }
    })
  }
  addToSet(props.filteredTree)
  expandedChapters.value = set
}

// 初始化展开状态
initializeExpanded()

// 切换章节展开/折叠
function toggleChapter(chapterId: string) {
  const newSet = new Set(expandedChapters.value)
  if (newSet.has(chapterId)) {
    newSet.delete(chapterId)
  } else {
    newSet.add(chapterId)
  }
  expandedChapters.value = newSet
}

// 获取进度样式类
function getProcessClass(processType?: string): string {
  switch (processType) {
    case 'LEARNED':
      return 'learned'
    case 'IN_PROGRESS':
      return 'in-progress'
    default:
      return 'unlearned'
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

  &::-webkit-scrollbar {
    width: 4px;
  }
  &::-webkit-scrollbar-thumb {
    background: $border-base;
    border-radius: 2px;
  }
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

  &:hover {
    color: $primary-color;
  }

  &.active {
    color: $primary-color;
    background: $bg-white;
    border-bottom-color: $primary-color;
  }
}

.outline-tree {
  padding: $spacing-sm 0;
}

.outline-chapter {
  border-bottom: 1px solid $border-extra-light;

  &:last-child {
    border-bottom: none;
  }
}

.chapter-header {
  display: flex;
  align-items: center;
  gap: $spacing-xs;
  padding: $spacing-sm $spacing-md;
  cursor: pointer;
  user-select: none;
  transition: background 0.2s;

  &:hover {
    background: $bg-color;
  }

  .chapter-icon {
    transition: transform 0.2s;

    &.expanded {
      transform: rotate(90deg);
    }
  }

  .chapter-title {
    flex: 1;
    font-size: $font-size-small;
    font-weight: 500;
    color: $text-primary;
  }
}

.chapter-lessons {
  background: $bg-color;
}

.lesson-item {
  display: flex;
  align-items: center;
  gap: $spacing-xs;
  padding: $spacing-sm $spacing-md $spacing-sm calc($spacing-md + $spacing-xs + 16px);
  cursor: pointer;
  transition: all 0.2s;
  position: relative;
  border-left: 3px solid transparent;

  &:hover {
    background: darken($bg-color, 2%);
  }

  &.active {
    background: rgba($primary-color, 0.08);
    border-left-color: $primary-color;

    .lesson-title {
      color: $primary-color;
      font-weight: 500;
    }
  }

  .lesson-icon {
    color: $text-secondary;
    flex-shrink: 0;
  }

  .lesson-title {
    flex: 1;
    font-size: $font-size-small;
    color: $text-regular;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .process-icon {
    flex-shrink: 0;

    &.learned {
      color: $success-color;
    }

    &.in-progress {
      color: $warning-color;
    }
  }

  // 进度状态样式
  &.process-unlearned {
    opacity: 0.8;
  }

  &.process-in-progress {
    .lesson-title {
      color: $warning-color;
    }
  }

  &.process-learned {
    opacity: 0.6;

    .lesson-icon {
      color: $success-color;
    }
  }
}
</style>
