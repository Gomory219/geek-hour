<template>
  <div class="page-course-detail">
    <!-- 加载状态 -->
    <div v-if="loading" class="loading-container">
      <n-spin size="large" description="加载中..." />
    </div>

    <!-- 主内容 -->
    <div v-else class="course-detail-layout" :class="{ 'viewing-tutorial': activeView === 'tutorial' }">
      <!-- 左侧：目录树 -->
      <CourseOutline
        :filtered-tree="filteredTree"
        :active-tutorial="activeTutorial"
        :model-tab="activeTab"
        @select="selectTutorial"
        @tab-change="handleTabChange"
      />

      <!-- 中间：概览或教程内容 -->
      <div class="main-content">
        <!-- 返回按钮（教程视图时显示） -->
        <div v-if="activeView === 'tutorial'" class="back-bar">
          <n-button text @click="backToOverview">
            <template #icon>
              <n-icon><ArrowBackOutline /></n-icon>
            </template>
            返回课程概览
          </n-button>
        </div>

        <!-- 课程概览 -->
        <CourseOverview
          v-if="activeView === 'overview'"
          :course="course"
          @start-learning="startLearning"
        />

        <!-- 教程内容 -->
        <TutorialContentViewer
          v-else
          :tutorial-detail="tutorialContent"
          :loading="contentLoading"
        />
      </div>

      <!-- 右侧：推荐课程（仅概览视图显示） -->
      <CourseRecommendSidebar
        v-if="activeView === 'overview'"
        :courses="recommendedCourses"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import { NIcon, NButton, NSpin } from 'naive-ui'
import { ArrowBackOutline } from '@vicons/ionicons5'
import { useCourseDetail } from '@/composables/useCourseDetail'
import CourseOutline from '@/components/course/CourseOutline.vue'
import CourseOverview from '@/components/course/CourseOverview.vue'
import TutorialContentViewer from '@/components/course/TutorialContentViewer.vue'
import CourseRecommendSidebar from '@/components/course/CourseRecommendSidebar.vue'

// 使用 composable
const {
  course,
  filteredTree,
  recommendedCourses,
  activeTutorial,
  tutorialContent,
  activeTab,
  activeView,
  loading,
  contentLoading,
  selectTutorial,
  handleTabChange,
  startLearning,
  backToOverview
} = useCourseDetail()
</script>

<style scoped lang="scss">
.page-course-detail {
  // 突破 DefaultLayout 的 max-width: 1200px 容器限制，占满视口宽度
  width: 100vw;
  margin-left: calc(-50vw + 50%);
}

.loading-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 400px;
  padding: 20px;
}

.course-detail-layout {
  display: grid;
  grid-template-columns: 280px 1fr 280px;
  gap: 20px;
  padding: 20px;
  max-width: 100%;
  align-items: start;

  &.viewing-tutorial {
    grid-template-columns: 280px 1fr;
  }
}

.main-content {
  min-width: 0;
}

.back-bar {
  padding: $spacing-sm 0;
  margin-bottom: $spacing-md;
}

// 响应式设计
@media (max-width: 1024px) {
  .course-detail-layout {
    grid-template-columns: 240px 1fr;
  }
}

@media (max-width: 768px) {
  .course-detail-layout {
    grid-template-columns: 1fr;
  }
}
</style>
