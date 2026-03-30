<template>
  <div class="page-home">
    <!-- Hero Banner -->
    <section class="hero">
      <div class="hero-overlay"></div>
      <div class="hero-content">
        <h1 class="hero-title">Geek Hour</h1>
        <p class="hero-subtitle">探索技术的无限可能，与极客一起成长</p>
        <div class="hero-actions">
          <n-button type="primary" size="large" @click="$router.push('/course')">
            开始学习
          </n-button>
          <n-button size="large" ghost @click="$router.push('/community')">
            加入社区
          </n-button>
        </div>
      </div>
      <!-- 底部渐变过渡遮罩 -->
      <div class="hero-fade"></div>
    </section>

    <!-- 统计卡片 - 悬浮在 Hero 与内容区交界处 -->
    <div class="hero-stats-bridge">
      <div class="hero-stats">
        <div class="stat-item">
          <span class="stat-num">{{ courseCount }}+</span>
          <span class="stat-label">精品课程</span>
        </div>
        <div class="stat-item">
          <span class="stat-num">{{ articleCount }}+</span>
          <span class="stat-label">技术文章</span>
        </div>
        <div class="stat-item">
          <span class="stat-num">1000+</span>
          <span class="stat-label">活跃学员</span>
        </div>
      </div>
    </div>

    <!-- 精选课程 -->
    <section class="section">
      <div class="section-header">
        <h2 class="section-title">精选课程</h2>
        <n-button text type="primary" @click="$router.push('/course')">
          查看全部 →
        </n-button>
      </div>
      <div class="category-section">
        <!-- 一级分类 tab -->
        <div class="category-primary">
          <div
            v-for="cat in primaryCategories"
            :key="cat.id"
            class="primary-item"
            :class="{ active: activePrimaryId === cat.id }"
            @click="selectPrimary(cat)"
          >
            {{ cat.name }}
          </div>
        </div>
        <!-- 内容区：左侧二级分类 + 右侧课程 -->
        <div class="category-content">
          <div v-if="subCategories.length" class="category-secondary">
            <div
              v-for="sub in subCategories"
              :key="sub.id"
              class="secondary-item"
              :class="{ active: activeSubId === sub.id }"
              @click="selectSub(sub)"
            >
              {{ sub.name }}
            </div>
          </div>
          <div v-if="loadingCourses" class="loading-wrapper">
            <n-spin size="large" />
          </div>
          <div v-else-if="featuredCourses.length" class="course-grid">
            <div
              v-for="course in featuredCourses"
              :key="course.id"
              class="course-card"
              @click="$router.push(`/course/${course.id}`)"
            >
              <div class="course-cover">
                <img v-if="course.coverImage" :src="course.coverImage" :alt="course.name" />
                <div v-else class="course-cover-placeholder" :style="{ background: getCardColor(course.id) }">
                  {{ course.name?.charAt(0) }}
                </div>
                <span v-if="course.isVip" class="course-badge vip">VIP免费</span>
                <span v-if="course.isFeature" class="course-badge feature">精选</span>
              </div>
              <div class="course-info">
                <h3 class="course-name">{{ course.name }}</h3>
                <div class="course-meta">
                  <span v-if="course.level" class="course-level">{{ course.level }}</span>
                  <span class="course-views">{{ formatCount(course.viewCount) }} 人学习</span>
                </div>
                <div class="course-footer">
                  <span class="course-price">
                    <template v-if="course.isFree">免费</template>
                    <template v-else-if="course.price">¥{{ course.price }}</template>
                    <template v-else>暂无定价</template>
                  </span>
                  <span class="course-likes">
                    <n-icon size="16"><heart-outline /></n-icon>
                    {{ course.likes || 0 }}
                  </span>
                </div>
              </div>
            </div>
          </div>
          <div v-else-if="!loadingCourses" class="no-result">暂无课程</div>
        </div>
      </div>
    </section>

    <!-- 精选文章 -->
    <section class="section">
      <div class="section-header">
        <h2 class="section-title">精选文章</h2>
        <n-button text type="primary" @click="$router.push('/community')">
          更多文章 →
        </n-button>
      </div>
      <div v-if="loadingArticles" class="loading-wrapper">
        <n-spin size="large" />
      </div>
      <div v-else-if="featuredArticles.length" class="article-list">
        <div
          v-for="article in featuredArticles"
          :key="article.id"
          class="article-card"
          @click="$router.push(`/community/${article.id}`)"
        >
          <div v-if="article.coverImage" class="article-cover">
            <img :src="article.coverImage" :alt="article.title" />
          </div>
          <div class="article-info">
            <h3 class="article-title">{{ article.title }}</h3>
            <p class="article-summary">{{ article.summary }}</p>
            <div class="article-meta">
              <span class="article-status" :class="article.status?.toLowerCase()">
                {{ statusLabel(article.status) }}
              </span>
              <span>{{ formatDate(article.publishDateTime || article.createDateTime) }}</span>
              <span>{{ formatCount(article.viewCount) }} 阅读</span>
              <span>{{ formatCount(article.likeCount) }} 点赞</span>
            </div>
          </div>
        </div>
      </div>
      <n-empty v-else description="暂无精选文章" />
    </section>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import { NButton, NEmpty, NSpin, NIcon } from 'naive-ui'
import { HeartOutline } from '@vicons/ionicons5'
import { CourseControllerService, CategoryControllerService } from '@/api/generated/gkh-course'
import { ArticleControllerService } from '@/api/generated/gkh-blog'
import type { CourseVO } from '@/api/generated/gkh-course/models/CourseVO'
import type { CategoryVO } from '@/api/generated/gkh-course/models/CategoryVO'
import type { Article } from '@/api/generated/gkh-blog/models/Article'

const featuredCourses = ref<CourseVO[]>([])
const primaryCategories = ref<CategoryVO[]>([])
const subCategories = ref<CategoryVO[]>([])
const featuredArticles = ref<Article[]>([])
const activePrimaryId = ref<number | undefined>(undefined)
const activeSubId = ref<number | undefined>(undefined)
const loadingCourses = ref(false)
const loadingArticles = ref(false)

const courseCount = computed(() => featuredCourses.value.length)
const articleCount = computed(() => featuredArticles.value.length)

const cardColors = ['#409eff', '#67c23a', '#e6a23c', '#f56c6c', '#909399', '#9b59b6', '#1abc9c', '#e74c3c']
const getCardColor = (id?: string) => {
  const num = Number(id) || 0
  return `${cardColors[num % cardColors.length]}33`
}

const statusLabel = (status?: Article.status) => {
  const map: Record<string, string> = {
    DRAFT: '草稿',
    PENDING_REVIEW: '审核中',
    PUBLISHED: '已发布'
  }
  return status ? map[status] || status : ''
}

const formatCount = (count?: number) => {
  if (!count) return '0'
  if (count >= 10000) return (count / 10000).toFixed(1) + '万'
  if (count >= 1000) return (count / 1000).toFixed(1) + 'k'
  return String(count)
}

const formatDate = (dateStr?: string) => {
  if (!dateStr) return ''
  const d = new Date(dateStr)
  return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}-${String(d.getDate()).padStart(2, '0')}`
}

// 选中一级分类 → 加载二级分类 + 课程
const selectPrimary = async (cat: CategoryVO) => {
  activePrimaryId.value = cat.id
  activeSubId.value = undefined
  await loadSubCategories(cat.id)
  loadCourses()
}

// 选中二级分类 → 刷新课程（点同一项则取消选中）
const selectSub = (sub: CategoryVO) => {
  activeSubId.value = activeSubId.value === sub.id ? undefined : sub.id
  loadCourses()
}

const loadCourses = async () => {
  loadingCourses.value = true
  try {
    const categoryId = activeSubId.value || activePrimaryId.value
    const res = await CourseControllerService.listByCategory(categoryId)
    if (res.code === 0 && res.data) {
      featuredCourses.value = res.data
    }
  } catch {
    // 静默处理
  } finally {
    loadingCourses.value = false
  }
}

const loadPrimaryCategories = async () => {
  try {
    const res = await CategoryControllerService.list()
    if (res.code === 0 && res.data) {
      primaryCategories.value = res.data
      // 默认选中第一个
      if (res.data.length) {
        const first = res.data[0]
        activePrimaryId.value = first.id
        await loadSubCategories(first.id)
      }
    }
  } catch {
    // 静默处理
  }
}

const loadSubCategories = async (baseCategoryId?: number) => {
  try {
    const res = await CategoryControllerService.list(baseCategoryId)
    if (res.code === 0 && res.data) {
      subCategories.value = res.data
    }
  } catch {
    subCategories.value = []
  }
}

const loadArticles = async () => {
  loadingArticles.value = true
  try {
    const res = await ArticleControllerService.getFeaturedArticles(1, 6)
    if (Array.isArray(res)) {
      featuredArticles.value = res
    }
  } catch {
    // 静默处理
  } finally {
    loadingArticles.value = false
  }
}

onMounted(async () => {
  await loadPrimaryCategories()
  loadCourses()
  loadArticles()
})
</script>

<style scoped lang="scss">
.page-home {
  padding: 0;
  margin: -20px;
}

/* ===== Hero Banner - 视觉过渡方案 ===== */
@keyframes hero-slide-up {
  from {
    opacity: 0;
    transform: translateY(40px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes glow-pulse {
  0%, 100% { opacity: 0.6; }
  50% { opacity: 1; }
}

.hero {
  position: relative;
  min-height: 500px;
  background: #0f172a;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;

  // 微弱网格背景
  &::before {
    content: '';
    position: absolute;
    inset: 0;
    background-image:
      linear-gradient(rgba(56, 189, 248, 0.025) 1px, transparent 1px),
      linear-gradient(90deg, rgba(56, 189, 248, 0.025) 1px, transparent 1px);
    background-size: 60px 60px;
    pointer-events: none;
    z-index: 1;
  }

  // 深邃渐变底层（稍微提亮）
  &::after {
    content: '';
    position: absolute;
    inset: 0;
    background:
      radial-gradient(ellipse 120% 80% at 50% 120%, rgba(30, 64, 175, 0.3) 0%, transparent 60%),
      radial-gradient(ellipse 80% 60% at 20% 20%, rgba(30, 58, 138, 0.4) 0%, transparent 50%),
      radial-gradient(ellipse 60% 50% at 80% 30%, rgba(30, 64, 175, 0.3) 0%, transparent 50%),
      linear-gradient(180deg, #0f172a 0%, #1e293b 40%, #334155 70%, #475569 100%);
    pointer-events: none;
    z-index: 0;
  }

  .hero-overlay {
    position: absolute;
    inset: 0;
    z-index: 2;
    pointer-events: none;
    overflow: hidden;

    &::before {
      content: '';
      position: absolute;
      width: 500px;
      height: 500px;
      top: -10%;
      left: -5%;
      background: radial-gradient(circle, rgba(56, 189, 248, 0.08) 0%, transparent 70%);
      animation: glow-pulse 6s ease-in-out infinite;
    }

    &::after {
      content: '';
      position: absolute;
      width: 400px;
      height: 400px;
      bottom: -15%;
      right: -5%;
      background: radial-gradient(circle, rgba(99, 102, 241, 0.06) 0%, transparent 70%);
      animation: glow-pulse 8s ease-in-out 2s infinite;
    }
  }

  // 底部渐变过渡遮罩
  .hero-fade {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    height: 120px;
    z-index: 3;
    pointer-events: none;
    background: linear-gradient(180deg, transparent 0%, rgba(248, 250, 252, 0.3) 50%, rgba(248, 250, 252, 0.8) 100%);
  }

  .hero-content {
    position: relative;
    z-index: 3;
    text-align: center;
    color: #e0f2fe;
    padding: 60px 20px 80px;
    animation: hero-slide-up 1s cubic-bezier(0.16, 1, 0.3, 1) both;
  }

  .hero-title {
    font-size: 56px;
    font-weight: 800;
    letter-spacing: 2px;
    margin-bottom: 16px;
    animation: hero-slide-up 1s cubic-bezier(0.16, 1, 0.3, 1) 0.1s both;

    background: linear-gradient(135deg, #ffffff 0%, #bae6fd 30%, #7dd3fc 50%, #38bdf8 70%, #ffffff 100%);
    background-size: 200% auto;
    -webkit-background-clip: text;
    background-clip: text;
    -webkit-text-fill-color: transparent;
    position: relative;

    &::after {
      content: 'Geek Hour';
      position: absolute;
      inset: 0;
      background: none;
      -webkit-text-fill-color: transparent;
      text-shadow: 0 0 50px rgba(56, 189, 248, 0.3);
      z-index: -1;
    }
  }

  .hero-subtitle {
    font-size: 18px;
    opacity: 0.85;
    margin-bottom: 36px;
    font-weight: 300;
    letter-spacing: 3px;
    color: #94a3b8;
    animation: hero-slide-up 1s cubic-bezier(0.16, 1, 0.3, 1) 0.2s both;
  }

  .hero-actions {
    display: flex;
    gap: 16px;
    justify-content: center;
    animation: hero-slide-up 1s cubic-bezier(0.16, 1, 0.3, 1) 0.35s both;

    :deep(.n-button) {
      border-radius: 10px;
      font-weight: 600;
      padding: 0 28px;
      height: 46px;
      font-size: 15px;
      transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
    }

    :deep(.n-button--primary-type) {
      background: linear-gradient(135deg, #06b6d4 0%, #0ea5e9 40%, #3b82f6 100%);
      border: none;
      box-shadow: 0 4px 18px rgba(6, 182, 212, 0.3), inset 0 1px 0 rgba(255, 255, 255, 0.15);
      position: relative;
      overflow: hidden;

      &::before {
        content: '';
        position: absolute;
        top: 0;
        left: -100%;
        width: 100%;
        height: 100%;
        background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.15), transparent);
        transition: left 0.5s ease;
      }

      &:hover {
        transform: translateY(-2px) scale(1.02);
        box-shadow: 0 6px 24px rgba(6, 182, 212, 0.4), 0 0 0 1px rgba(56, 189, 248, 0.2);

        &::before {
          left: 100%;
        }
      }

      &:active {
        transform: translateY(0) scale(0.98);
      }
    }

    :deep(.n-button--default-type) {
      color: #cbd5e1;
      border: 1px solid rgba(255, 255, 255, 0.12);
      background: rgba(255, 255, 255, 0.05);
      backdrop-filter: blur(10px);
      -webkit-backdrop-filter: blur(10px);

      &:hover {
        color: #fff;
        border-color: rgba(255, 255, 255, 0.25);
        background: rgba(255, 255, 255, 0.1);
        transform: translateY(-2px);
        box-shadow: 0 6px 20px rgba(0, 0, 0, 0.25);
      }

      &:active {
        transform: translateY(0);
      }
    }
  }
}

/* ===== 统计卡片桥接层 - 悬浮在深浅交界处 ===== */
.hero-stats-bridge {
  position: relative;
  z-index: 10;
  margin-top: -85px;
  display: flex;
  justify-content: center;
  padding: 0 20px;
}

.hero-stats {
  display: flex;
  gap: 56px;
  justify-content: center;
  padding: 28px 64px;
  background: #fff;
  border-radius: 20px;
  box-shadow:
    0 10px 40px rgba(0, 0, 0, 0.08),
    0 2px 12px rgba(0, 0, 0, 0.04),
    inset 0 1px 0 rgba(255, 255, 255, 0.8);
  animation: hero-slide-up 1s cubic-bezier(0.16, 1, 0.3, 1) 0.5s both;
  position: relative;
  overflow: hidden;

  // 顶部装饰线
  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 15%;
    right: 15%;
    height: 3px;
    background: linear-gradient(90deg, transparent, #3b82f6, #06b6d4, transparent);
    border-radius: 0 0 3px 3px;
  }
}

.stat-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
  z-index: 1;
}

.stat-num {
  font-size: 32px;
  font-weight: 700;
  color: #1e3a8a;
  letter-spacing: 1px;
  text-shadow: 0 2px 8px rgba(59, 130, 246, 0.15);
}

.stat-label {
  font-size: 13px;
  color: #64748b;
  margin-top: 6px;
  letter-spacing: 2px;
  font-weight: 500;
}

/* ===== 通用 Section ===== */
.section {
  padding: 48px 20px;
  max-width: 1200px;
  margin: 0 auto;
  // 淡灰背景，缩小与 Hero 的对比度
  background: #f8fafc;
  border-radius: 16px;
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
}

.section-title {
  font-size: 24px;
  font-weight: 700;
  color: $text-primary;
  position: relative;
  padding-left: 16px;

  &::before {
    content: '';
    position: absolute;
    left: 0;
    top: 4px;
    bottom: 4px;
    width: 4px;
    background: $primary-color;
    border-radius: 2px;
  }
}

.loading-wrapper {
  display: flex;
  justify-content: center;
  padding: 60px 0;
}

/* ===== 课程分类区域（原型样式） ===== */
.category-section {
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
}

/* 一级分类 tab */
.category-primary {
  display: flex;
  border-bottom: 1px solid #eee;
}

.primary-item {
  flex: 1;
  padding: 16px 10px;
  text-align: center;
  cursor: pointer;
  font-size: 15px;
  color: #555;
  transition: all 0.15s;
  position: relative;
  white-space: nowrap;
  user-select: none;

  &:hover {
    color: $primary-color;
    background: #fafafa;
  }

  &.active {
    color: $primary-color;
    font-weight: 600;

    &::after {
      content: '';
      position: absolute;
      bottom: 0;
      left: 50%;
      transform: translateX(-50%);
      width: 40px;
      height: 3px;
      background: $primary-color;
      border-radius: 2px;
    }
  }
}

/* 内容区：左侧二级 + 右侧课程 */
.category-content {
  display: flex;
  align-items: stretch;
  height: 520px;
  overflow: hidden;
  background: #fff;
}

/* 二级分类 */
.category-secondary {
  width: 140px;
  flex-shrink: 0;
  border-right: 1px solid #eee;
  padding: 12px 0;
  display: flex;
  flex-direction: column;
  overflow-y: auto;

  /* 隐藏滚动条 */
  &::-webkit-scrollbar {
    width: 0;
  }
}

.secondary-item {
  padding: 12px 16px;
  cursor: pointer;
  font-size: 14px;
  color: #666;
  transition: all 0.15s;
  user-select: none;
  border-left: 3px solid transparent;

  &:hover {
    color: $primary-color;
    background: #f5f7fa;
  }

  &.active {
    color: $primary-color;
    background: #ecf5ff;
    border-left-color: $primary-color;
    font-weight: 500;
  }
}

/* 课程网格 */
.course-grid {
  flex: 1;
  padding: 24px;
  padding-bottom: 40px;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
  grid-auto-rows: max-content;
  gap: 24px;
  align-items: start;
  align-content: start;
  overflow-y: auto;
  height: 100%;
  box-sizing: border-box;

  /* 美化滚动条 */
  &::-webkit-scrollbar {
    width: 6px;
  }
  &::-webkit-scrollbar-thumb {
    background: #e0e0e0;
    border-radius: 3px;
  }
  &::-webkit-scrollbar-thumb:hover {
    background: $primary-color;
  }
}

.no-result {
  color: #999;
  font-size: 14px;
  padding: 40px;
  text-align: center;
  flex: 1;
}

.course-card {
  display: flex;
  flex-direction: column;
  background: #fff;
  border: 1px solid #f0f0f0;
  border-radius: 12px;
  overflow: hidden;
  cursor: pointer;
  transition: all 0.3s ease;
  height: auto;
  min-height: min-content;

  &:hover {
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    transform: translateY(-4px);
  }
}

.course-cover {
  position: relative;
  width: 100%;
  aspect-ratio: 16 / 9;
  flex-shrink: 0;
  overflow: hidden;
  background: #f5f7fa;

  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .course-cover-placeholder {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 48px;
    font-weight: 800;
    color: #fff;
    text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
}

.course-badge {
  position: absolute;
  top: 10px;
  padding: 2px 10px;
  border-radius: 6px;
  font-size: 11px;
  font-weight: bold;
  backdrop-filter: blur(4px);

  &.free {
    background: rgba(103, 194, 58, 0.9);
    left: 10px;
  }
  &.vip {
    background: rgba(230, 162, 60, 0.9);
    left: 10px;
  }
  &.feature {
    background: rgba(245, 108, 108, 0.9);
    right: 10px;
  }
}

.course-info {
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 12px;
  background: #fff;
  flex-shrink: 0;
  flex-grow: 1;
}

.course-name {
  font-size: 15px;
  font-weight: 600;
  color: #333;
  line-height: 1.5;
  min-height: 45px;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.course-meta {
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 12px;
  color: #999;
}

.course-level {
  font-size: 11px;
  padding: 2px 6px;
  border-radius: 3px;
  background: #ecf5ff;
  color: $primary-color;
}

.course-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.course-price {
  font-size: 16px;
  color: $danger-color;
  font-weight: bold;

  &:empty::after {
    content: '暂无定价';
    font-size: 13px;
    color: $text-secondary;
    font-weight: 400;
  }
}

.course-likes {
  display: flex;
  align-items: center;
  gap: 4px;
  font-size: 13px;
  color: $text-secondary;
}

/* ===== 文章列表 ===== */
.article-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.article-card {
  display: flex;
  gap: 20px;
  background: #fff;
  border-radius: $border-radius-large;
  overflow: hidden;
  cursor: pointer;
  transition: box-shadow 0.25s;
  box-shadow: $box-shadow-base;
  padding: 16px;

  &:hover {
    box-shadow: $box-shadow-light;
  }
}

.article-cover {
  flex-shrink: 0;
  width: 200px;
  height: 130px;
  border-radius: $border-radius-base;
  overflow: hidden;

  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
}

.article-info {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  min-width: 0;
}

.article-title {
  font-size: 17px;
  font-weight: 600;
  color: $text-primary;
  line-height: 1.5;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  margin-bottom: 8px;
}

.article-summary {
  font-size: 14px;
  color: $text-regular;
  line-height: 1.6;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  margin-bottom: 12px;
}

.article-meta {
  display: flex;
  gap: 16px;
  font-size: 13px;
  color: $text-secondary;
  align-items: center;
}

.article-status {
  padding: 1px 8px;
  border-radius: 3px;
  font-size: 12px;

  &.published {
    background: rgba($success-color, 0.1);
    color: $success-color;
  }
  &.draft {
    background: rgba($info-color, 0.1);
    color: $info-color;
  }
  &.pending_review {
    background: rgba($warning-color, 0.1);
    color: $warning-color;
  }
}

@media (max-width: 768px) {
  .article-card {
    flex-direction: column;
  }
  .article-cover {
    width: 100%;
    height: 180px;
  }
  .hero {
    min-height: 420px;

    &::before {
      opacity: 0.5;
    }

    &::after {
      background:
        radial-gradient(ellipse 120% 80% at 50% 120%, rgba(30, 64, 175, 0.25) 0%, transparent 60%),
        linear-gradient(180deg, #0f172a 0%, #1e293b 50%, #334155 100%);
    }

    .hero-overlay {
      background: linear-gradient(180deg, rgba(15, 23, 42, 0.2) 0%, rgba(15, 23, 42, 0.1) 100%);

      &::before,
      &::after {
        display: none;
      }
    }

    .hero-content {
      padding: 40px 16px 60px;
    }

    .hero-title {
      font-size: 32px;
      letter-spacing: 1px;
    }

    .hero-subtitle {
      font-size: 14px;
      letter-spacing: 2px;
      margin-bottom: 28px;
    }

    .hero-actions {
      gap: 12px;

      :deep(.n-button) {
        height: 42px;
        padding: 0 20px;
        font-size: 14px;
        border-radius: 10px;
      }
    }

    .hero-fade {
      height: 80px;
    }
  }

  .hero-stats-bridge {
    margin-top: -55px;
    padding: 0 16px;
  }

  .hero-stats {
    gap: 32px;
    padding: 20px 36px;
    border-radius: 16px;
  }

  .stat-num {
    font-size: 24px;
  }

  .stat-label {
    font-size: 12px;
  }
  .category-content {
    flex-direction: column;
    height: auto;
    max-height: 800px;
  }
  .category-secondary {
    width: 100%;
    flex-direction: row;
    flex-wrap: wrap;
    border-right: none;
    border-bottom: 1px solid #eee;
    padding: 8px 12px;
    gap: 4px;
  }
  .secondary-item {
    border-left: none;
    padding: 6px 12px;
    border-radius: 16px;
    &.active {
      background: $primary-color;
      color: #fff;
      border-left-color: transparent;
    }
  }
  /* 移动端课程网格改为2列 */
  .course-grid {
    grid-template-columns: repeat(2, 1fr);
    overflow-y: visible;
    height: auto;
  }
}

@media (max-width: 480px) {
  /* 超小屏幕改为1列 */
  .course-grid {
    grid-template-columns: 1fr;
  }
}
</style>
