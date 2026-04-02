<template>
  <aside class="course-recommend-sidebar">
    <div class="sidebar-header">
      <h3 class="sidebar-title">推荐教程</h3>
    </div>

    <div class="sidebar-content">
      <!-- 推荐课程列表 -->
      <div v-for="course in courses" :key="course.id" class="recommend-item" @click="goToCourse(course.id!)">
        <div class="recommend-cover">
          <img
            v-if="course.coverImage"
            :src="course.coverImage"
            :alt="course.name"
            class="cover-image"
          />
          <div v-else class="cover-placeholder">
            {{ course.name?.charAt(0) || '?' }}
          </div>
        </div>

        <div class="recommend-info">
          <h4 class="recommend-name">{{ course.name }}</h4>
          <div class="recommend-meta">
            <span class="recommend-views">{{ formatCount(course.viewCount) }} 人学习</span>
            <span class="recommend-price">
              <template v-if="course.isFree">免费</template>
              <template v-else-if="course.price">¥{{ course.price }}</template>
              <template v-else>暂无定价</template>
            </span>
          </div>
        </div>
      </div>

      <!-- 空状态 -->
      <n-empty v-if="courses.length === 0" description="暂无推荐" size="small" />
    </div>
  </aside>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { NEmpty } from 'naive-ui'
import type { CourseVO } from '@/api'

interface Props {
  courses: CourseVO[]
}

defineProps<Props>()

const router = useRouter()

function goToCourse(courseId: string) {
  router.push(`/course/${courseId}`)
}

function formatCount(count?: number): string {
  if (!count) return '0'
  if (count >= 10000) {
    return (count / 10000).toFixed(1) + '万'
  }
  return count.toString()
}
</script>

<style scoped lang="scss">
.course-recommend-sidebar {
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

.sidebar-header {
  padding: $spacing-md $spacing-lg;
  border-bottom: 1px solid $border-extra-light;
}

.sidebar-title {
  font-size: $font-size-base;
  font-weight: 600;
  color: $text-primary;
  margin: 0;
}

.sidebar-content {
  padding: $spacing-md;
}

.recommend-item {
  display: flex;
  gap: $spacing-md;
  padding: $spacing-sm;
  border-radius: $border-radius-base;
  cursor: pointer;
  transition: all 0.2s;
  margin-bottom: $spacing-sm;

  &:hover {
    background: $bg-color;
    transform: translateX(2px);
  }

  &:last-child {
    margin-bottom: 0;
  }
}

.recommend-cover {
  flex-shrink: 0;
  width: 120px;
  height: 68px;
  border-radius: $border-radius-base;
  overflow: hidden;
  background: $bg-color;

  .cover-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .cover-placeholder {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    background: linear-gradient(135deg, $primary-color, lighten($primary-color, 20%));
    color: rgba(255, 255, 255, 0.8);
    font-weight: bold;
    font-size: $font-size-large;
  }
}

.recommend-info {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  min-width: 0;
}

.recommend-name {
  font-size: $font-size-small;
  font-weight: 500;
  color: $text-primary;
  margin: 0;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  line-height: 1.4;
}

.recommend-meta {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: $font-size-small;
}

.recommend-views {
  color: $text-secondary;
}

.recommend-price {
  color: $danger-color;
  font-weight: 500;
}
</style>
