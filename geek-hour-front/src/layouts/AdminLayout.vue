<template>
  <div class="admin-layout">
    <aside class="sidebar">
      <div class="sidebar-header">
        <h2>管理后台</h2>
      </div>
      <nav class="sidebar-nav">
        <router-link to="/admin">
          <icon-ion-home-outline /> 概览
        </router-link>
        <router-link to="/admin/users">
          <icon-ion-person-outline /> 用户管理
        </router-link>
        <router-link to="/admin/posts-review">
          <icon-ion-document-text-outline /> 帖子审核
        </router-link>
        <router-link to="/admin/comments-review">
          <icon-ion-chatbubble-outline /> 评论审核
        </router-link>
        <router-link to="/admin/reports">
          <icon-ion-flag-outline /> 举报管理
        </router-link>
        <router-link to="/admin/violations">
          <icon-ion-warning-outline /> 违规记录
        </router-link>
        <router-link to="/admin/sensitive-words">
          <icon-ion-warning /> 敏感词
        </router-link>
        <router-link to="/admin/courses">
          <icon-ion-book-outline /> 课程管理
        </router-link>
        <router-link to="/admin/recommendations">
          <icon-ion-star-outline /> 推荐管理
        </router-link>
        <router-link to="/admin/reviews">
          <icon-ion-thumbs-up-outline /> 评价管理
        </router-link>
        <router-link to="/admin/instructors">
          <icon-ion-person-add-outline /> 讲师管理
        </router-link>
        <router-link to="/admin/coupons">
          <icon-ion-pricetag-outline /> 优惠券
        </router-link>
        <router-link to="/admin/activities">
          <icon-ion-calendar-outline /> 活动管理
        </router-link>
        <router-link to="/admin/memberships">
          <icon-ion-card-outline /> 会员管理
        </router-link>
        <router-link to="/admin/promotions">
          <icon-ion-gift-outline /> 促销活动
        </router-link>
        <router-link to="/admin/notifications">
          <icon-ion-notifications-outline /> 通知管理
        </router-link>
        <router-link to="/admin/permissions">
          <icon-ion-lock-closed-outline /> 权限管理
        </router-link>
        <router-link to="/admin/logs">
          <icon-ion-list-outline /> 操作日志
        </router-link>
        <router-link to="/admin/settings">
          <icon-ion-settings-outline /> 系统设置
        </router-link>
        <router-link to="/admin/anti-spam">
          <icon-ion-shield-checkmark-outline /> 反垃圾
        </router-link>
      </nav>
    </aside>
    <main class="main-content">
      <header class="main-header">
        <h1>{{ pageTitle }}</h1>
        <div class="header-actions">
          <span>管理员</span>
          <a @click="logout">退出</a>
        </div>
      </header>
      <div class="main-body">
        <router-view />
      </div>
    </main>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import {
  HomeOutline as IconIonHomeOutline,
  PersonOutline as IconIonPersonOutline,
  DocumentTextOutline as IconIonDocumentTextOutline,
  ChatbubbleOutline as IconIonChatbubbleOutline,
  FlagOutline as IconIonFlagOutline,
  WarningOutline as IconIonWarningOutline,
  Warning as IconIonWarning,
  BookOutline as IconIonBookOutline,
  StarOutline as IconIonStarOutline,
  ThumbsUpOutline as IconIonThumbsUpOutline,
  PersonAddOutline as IconIonPersonAddOutline,
  PricetagOutline as IconIonPricetagOutline,
  CalendarOutline as IconIonCalendarOutline,
  CardOutline as IconIonCardOutline,
  GiftOutline as IconIonGiftOutline,
  NotificationsOutline as IconIonNotificationsOutline,
  LockClosedOutline as IconIonLockClosedOutline,
  ListOutline as IconIonListOutline,
  SettingsOutline as IconIonSettingsOutline,
  ShieldCheckmarkOutline as IconIonShieldCheckmarkOutline
} from '@vicons/ionicons5'
import { useUserStore } from '@/stores/user'

const route = useRoute()
const router = useRouter()
const userStore = useUserStore()

const pageTitle = computed(() => {
  const nameMap: Record<string, string> = {
    Admin: '概览',
    AdminUsers: '用户管理',
    AdminUserDetail: '用户详情',
    AdminPostsReview: '帖子审核',
    AdminCommentsReview: '评论审核',
    AdminReports: '举报管理',
    AdminViolations: '违规记录',
    AdminSensitiveWords: '敏感词管理',
    AdminCourses: '课程管理',
    AdminCourseEdit: '编辑课程',
    AdminRecommendations: '推荐管理',
    AdminReviews: '评价管理',
    AdminInstructors: '讲师管理',
    AdminCoupons: '优惠券管理',
    AdminActivities: '活动管理',
    AdminMemberships: '会员管理',
    AdminPromotions: '促销活动',
    AdminNotifications: '通知管理',
    AdminPermissions: '权限管理',
    AdminLogs: '操作日志',
    AdminSettings: '系统设置',
    AdminAntiSpam: '反垃圾'
  }
  return nameMap[route.name as string] || '管理后台'
})

const logout = () => {
  userStore.logout()
  router.push('/admin/login')
}
</script>

<style scoped lang="scss">
.admin-layout {
  display: flex;
  min-height: 100vh;
}

.sidebar {
  width: $sidebar-width;
  background-color: #2c3e50;
  color: #fff;
  position: fixed;
  height: 100vh;
  overflow-y: auto;

  .sidebar-header {
    padding: 20px;
    border-bottom: 1px solid rgba(255, 255, 255, 0.1);

    h2 {
      margin: 0;
      font-size: 18px;
    }
  }

  .sidebar-nav {
    padding: 10px 0;

    a {
      display: flex;
      align-items: center;
      gap: 10px;
      padding: 12px 20px;
      color: rgba(255, 255, 255, 0.7);
      transition: all 0.3s;

      &:hover,
      &.router-link-active {
        background-color: rgba(255, 255, 255, 0.1);
        color: #fff;
      }
    }
  }
}

.main-content {
  flex: 1;
  margin-left: $sidebar-width;
  display: flex;
  flex-direction: column;
}

.main-header {
  background-color: #fff;
  padding: 20px 30px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: space-between;
  align-items: center;

  h1 {
    margin: 0;
    font-size: 20px;
  }

  .header-actions {
    display: flex;
    gap: 20px;
    align-items: center;

    a {
      cursor: pointer;
      color: $primary-color;

      &:hover {
        text-decoration: underline;
      }
    }
  }
}

.main-body {
  flex: 1;
  padding: 30px;
}
</style>
