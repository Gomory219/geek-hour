import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('@/layouts/DefaultLayout.vue'),
    children: [
      {
        path: '',
        name: 'Home',
        component: () => import('@/views/home/index.vue')
      },
      {
        path: 'course',
        name: 'Course',
        component: () => import('@/views/course/index.vue')
      },
      {
        path: 'course/:id',
        name: 'CourseDetail',
        component: () => import('@/views/course/detail.vue')
      },
      {
        path: 'community',
        name: 'Community',
        component: () => import('@/views/community/index.vue')
      },
      {
        path: 'community/:id',
        name: 'CommunityDetail',
        component: () => import('@/views/community/detail.vue')
      },
      {
        path: 'community/post/:id',
        name: 'CommunityPost',
        component: () => import('@/views/community/post.vue')
      },
      {
        path: 'search',
        name: 'Search',
        component: () => import('@/views/search/index.vue')
      },
      {
        path: 'user/profile',
        name: 'UserProfile',
        component: () => import('@/views/user/profile.vue')
      },
      {
        path: 'user/learning',
        name: 'UserLearning',
        component: () => import('@/views/user/learning.vue')
      },
      {
        path: 'user/content',
        name: 'UserContent',
        component: () => import('@/views/user/content.vue')
      },
      {
        path: 'user/messages',
        name: 'UserMessages',
        component: () => import('@/views/user/messages.vue')
      },
      {
        path: 'user/settings',
        name: 'UserSettings',
        component: () => import('@/views/user/settings.vue')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/layouts/AuthLayout.vue'),
    children: [
      {
        path: '',
        name: 'Login',
        component: () => import('@/views/auth/login.vue')
      }
    ]
  },
  {
    path: '/register',
    component: () => import('@/layouts/AuthLayout.vue'),
    children: [
      {
        path: '',
        name: 'Register',
        component: () => import('@/views/auth/register.vue')
      }
    ]
  },
  {
    path: '/admin',
    component: () => import('@/layouts/AdminLayout.vue'),
    children: [
      {
        path: '',
        name: 'Admin',
        component: () => import('@/views/admin/index.vue')
      },
      {
        path: 'users',
        name: 'AdminUsers',
        component: () => import('@/views/admin/users.vue')
      },
      {
        path: 'users/:id',
        name: 'AdminUserDetail',
        component: () => import('@/views/admin/user-detail.vue')
      },
      {
        path: 'posts-review',
        name: 'AdminPostsReview',
        component: () => import('@/views/admin/posts-review.vue')
      },
      {
        path: 'comments-review',
        name: 'AdminCommentsReview',
        component: () => import('@/views/admin/comments-review.vue')
      },
      {
        path: 'reports',
        name: 'AdminReports',
        component: () => import('@/views/admin/reports.vue')
      },
      {
        path: 'violations',
        name: 'AdminViolations',
        component: () => import('@/views/admin/violations.vue')
      },
      {
        path: 'sensitive-words',
        name: 'AdminSensitiveWords',
        component: () => import('@/views/admin/sensitive-words.vue')
      },
      {
        path: 'courses',
        name: 'AdminCourses',
        component: () => import('@/views/admin/courses.vue')
      },
      {
        path: 'courses/edit/:id?',
        name: 'AdminCourseEdit',
        component: () => import('@/views/admin/course-edit.vue')
      },
      {
        path: 'recommendations',
        name: 'AdminRecommendations',
        component: () => import('@/views/admin/recommendations.vue')
      },
      {
        path: 'reviews',
        name: 'AdminReviews',
        component: () => import('@/views/admin/reviews.vue')
      },
      {
        path: 'instructors',
        name: 'AdminInstructors',
        component: () => import('@/views/admin/instructors.vue')
      },
      {
        path: 'coupons',
        name: 'AdminCoupons',
        component: () => import('@/views/admin/coupons.vue')
      },
      {
        path: 'activities',
        name: 'AdminActivities',
        component: () => import('@/views/admin/activities.vue')
      },
      {
        path: 'memberships',
        name: 'AdminMemberships',
        component: () => import('@/views/admin/memberships.vue')
      },
      {
        path: 'promotions',
        name: 'AdminPromotions',
        component: () => import('@/views/admin/promotions.vue')
      },
      {
        path: 'notifications',
        name: 'AdminNotifications',
        component: () => import('@/views/admin/notifications.vue')
      },
      {
        path: 'permissions',
        name: 'AdminPermissions',
        component: () => import('@/views/admin/permissions.vue')
      },
      {
        path: 'logs',
        name: 'AdminLogs',
        component: () => import('@/views/admin/logs.vue')
      },
      {
        path: 'settings',
        name: 'AdminSettings',
        component: () => import('@/views/admin/settings.vue')
      },
      {
        path: 'anti-spam',
        name: 'AdminAntiSpam',
        component: () => import('@/views/admin/anti-spam.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
