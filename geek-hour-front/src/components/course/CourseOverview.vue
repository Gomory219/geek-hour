<template>
  <div class="course-overview">
    <!-- 课程封面 -->
    <div class="course-cover">
      <img
        v-if="course?.coverImage"
        :src="course.coverImage"
        :alt="course.name"
        class="cover-image"
      />
      <div v-else class="cover-placeholder">
        <span class="placeholder-text">{{ course?.name?.charAt(0) || '?' }}</span>
      </div>

      <!-- 标签 -->
      <div v-if="course" class="course-badges">
        <span v-if="course.isVip" class="badge vip">VIP免费</span>
        <span v-if="course.isFeature" class="badge feature">精选</span>
        <span v-if="course.isFree" class="badge free">免费</span>
      </div>
    </div>

    <!-- 课程信息 -->
    <div class="course-info">
      <h1 class="course-name">{{ course?.name || '加载中...' }}</h1>

      <!-- 简介 -->
      <p v-if="course?.profile" class="course-profile">{{ course.profile }}</p>

      <!-- 元信息 -->
      <div class="course-meta">
        <span class="meta-item">
          <n-icon size="14"><EyeOutline /></n-icon>
          {{ formatCount(course?.viewCount) }} 人学习
        </span>
        <span v-if="course?.level" class="meta-item">
          <n-icon size="14"><SchoolOutline /></n-icon>
          {{ levelMap[course.level] }}
        </span>
        <span v-if="course?.updateTime" class="meta-item">
          <n-icon size="14"><TimeOutline /></n-icon>
          {{ formatDate(course.updateTime) }}
        </span>
      </div>

      <!-- 价格 -->
      <div class="course-price-section">
        <span v-if="course?.isFree" class="price-free">免费</span>
        <span v-else-if="course?.isVip" class="price-vip">VIP免费</span>
        <span v-else-if="course?.price" class="price">¥{{ course.price }}</span>
        <span v-else class="price-unknown">暂无定价</span>
      </div>

      <!-- 介绍（Markdown 渲染） -->
      <div v-if="course?.introduction" class="course-introduction">
        <h3 class="section-title">课程介绍</h3>
        <MdPreview
          :model-value="course.introduction"
          theme="light"
          preview-theme="default"
        />
      </div>

      <!-- 操作按钮 -->
      <div class="course-actions">
        <n-button type="primary" size="large" @click="handleStartLearning">
          <template #icon>
            <n-icon><PlayCircleOutline /></n-icon>
          </template>
          开始学习
        </n-button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { NIcon, NButton } from 'naive-ui'
import { EyeOutline, SchoolOutline, TimeOutline, PlayCircleOutline } from '@vicons/ionicons5'
import { MdPreview } from 'md-editor-v3'
import 'md-editor-v3/lib/preview.css'
import type { CourseDetailVO } from '@/api'

interface Props {
  course: CourseDetailVO | null
}

interface Emits {
  (e: 'start-learning'): void
}

defineProps<Props>()
const emit = defineEmits<Emits>()

// 难度等级映射
const levelMap: Record<string, string> = {
  JUNIOR: '初级',
  MIDDLE: '中级',
  SENIOR: '高级'
}

// 格式化数字
function formatCount(count?: number): string {
  if (!count) return '0'
  if (count >= 10000) {
    return (count / 10000).toFixed(1) + '万'
  }
  return count.toString()
}

// 格式化日期
function formatDate(dateStr?: string): string {
  if (!dateStr) return ''
  const date = new Date(dateStr)
  return date.toLocaleDateString('zh-CN', { year: 'numeric', month: 'long', day: 'numeric' })
}

function handleStartLearning() {
  emit('start-learning')
}
</script>

<style scoped lang="scss">
.course-overview {
  background: $bg-white;
  border-radius: $border-radius-large;
  border: 1px solid $border-light;
  overflow: hidden;
}

.course-cover {
  position: relative;
  width: 100%;
  padding-top: 56.25%; // 16:9
  background: $bg-color;
  overflow: hidden;

  .cover-image {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .cover-placeholder {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    background: linear-gradient(135deg, $primary-color, lighten($primary-color, 20%));

    .placeholder-text {
      font-size: 80px;
      font-weight: bold;
      color: rgba(255, 255, 255, 0.8);
    }
  }

  .course-badges {
    position: absolute;
    top: $spacing-md;
    left: $spacing-md;
    display: flex;
    gap: $spacing-xs;
  }

  .badge {
    padding: $spacing-xs $spacing-sm;
    font-size: $font-size-small;
    border-radius: $border-radius-small;
    font-weight: 500;

    &.vip {
      background: linear-gradient(135deg, #ff9500, #ffb347);
      color: #fff;
    }

    &.feature {
      background: linear-gradient(135deg, #ff4757, #ff6b81);
      color: #fff;
    }

    &.free {
      background: linear-gradient(135deg, $success-color, lighten($success-color, 10%));
      color: #fff;
    }
  }
}

.course-info {
  padding: $spacing-lg;
}

.course-name {
  font-size: $font-size-large;
  font-weight: 600;
  color: $text-primary;
  margin: 0 0 $spacing-md 0;
  line-height: 1.4;
}

.course-profile {
  font-size: $font-size-base;
  color: $text-secondary;
  margin: 0 0 $spacing-md 0;
  line-height: 1.6;
}

.course-meta {
  display: flex;
  flex-wrap: wrap;
  gap: $spacing-md;
  margin-bottom: $spacing-md;
}

.meta-item {
  display: flex;
  align-items: center;
  gap: $spacing-xs;
  font-size: $font-size-small;
  color: $text-secondary;

  .n-icon {
    color: $text-placeholder;
  }
}

.course-price-section {
  padding: $spacing-md 0;
  border-top: 1px solid $border-extra-light;
  border-bottom: 1px solid $border-extra-light;
  margin-bottom: $spacing-lg;
}

.price-free,
.price-vip {
  font-size: $font-size-large;
  font-weight: 600;
  color: $success-color;
}

.price {
  font-size: $font-size-large;
  font-weight: 600;
  color: $danger-color;
}

.price-unknown {
  font-size: $font-size-base;
  color: $text-secondary;
}

.course-introduction {
  margin-bottom: $spacing-lg;
}

.section-title {
  font-size: $font-size-base;
  font-weight: 600;
  color: $text-primary;
  margin: 0 0 $spacing-md 0;
}

.course-actions {
  display: flex;
  gap: $spacing-md;
}
</style>
