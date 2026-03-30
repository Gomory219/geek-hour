<template>
  <div class="default-layout">
    <header class="header">
      <div class="header-content">
        <router-link to="/" class="logo">
          <img src="/logo-text.svg" alt="Geek Hour" />
        </router-link>
        <nav class="nav">
          <router-link to="/" class="nav-link">首页</router-link>
          <router-link to="/course" class="nav-link">课程</router-link>
          <router-link to="/community" class="nav-link">社区</router-link>
          <router-link to="/search" class="nav-link">搜索</router-link>
        </nav>
        <div class="user-actions">
          <template v-if="userStore.isLoggedIn">
            <n-dropdown :options="userMenuOptions" @select="handleUserMenu">
              <div class="user-avatar-wrapper">
                <n-avatar
                  v-if="userStore.userInfo?.avatarImage"
                  :src="userStore.userInfo.avatarImage"
                  :size="32"
                  round
                />
                <n-avatar v-else :size="32" round>
                  {{ userStore.userInfo?.userName?.charAt(0) || 'U' }}
                </n-avatar>
                <span class="user-name">{{ userStore.userInfo?.userName || '用户' }}</span>
              </div>
            </n-dropdown>
          </template>
          <template v-else>
            <n-button type="primary" size="small" @click="$router.push('/login')">登录</n-button>
            <n-button size="small" @click="$router.push('/register')">注册</n-button>
          </template>
        </div>
      </div>
    </header>
    <main class="main">
      <router-view />
    </main>
    <footer class="footer">
      <div class="footer-content">
        <p class="footer-text">&copy; 2024 Geek Hour. All rights reserved.</p>
      </div>
    </footer>
  </div>
</template>

<script setup lang="ts">
import { h } from 'vue'
import { useRouter } from 'vue-router'
import { NButton, NDropdown, NAvatar } from 'naive-ui'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()

const userMenuOptions = [
  { label: '个人中心', key: 'profile' },
  { label: '我的学习', key: 'learning' },
  { label: '账号设置', key: 'settings' },
  { type: 'divider', key: 'd1' },
  { label: '退出登录', key: 'logout' }
]

const handleUserMenu = (key: string) => {
  switch (key) {
    case 'profile':
      router.push('/user/profile')
      break
    case 'learning':
      router.push('/user/learning')
      break
    case 'settings':
      router.push('/user/settings')
      break
    case 'logout':
      userStore.logout()
      router.push('/')
      break
  }
}
</script>

<style scoped lang="scss">
.default-layout {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.header {
  background-color: #fff;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.08);
  position: sticky;
  top: 0;
  z-index: 100;

  .header-content {
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
    height: $header-height;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .logo {
    display: block;
    text-decoration: none;

    img {
      width: 180px;
      height: auto;
      display: block;
    }
  }

  .nav {
    display: flex;
    gap: 8px;
    margin-left: -60px;
  }

  .nav-link {
    padding: 6px 16px;
    border-radius: 6px;
    color: $text-regular;
    font-size: 15px;
    transition: all 0.2s;
    text-decoration: none;

    &:hover {
      color: $primary-color;
      background: rgba($primary-color, 0.06);
    }

    &.router-link-active {
      color: $primary-color;
      font-weight: 600;
    }

    &.router-link-exact-active {
      background: rgba($primary-color, 0.08);
    }
  }

  .user-actions {
    display: flex;
    align-items: center;
    gap: 10px;
  }

  .user-avatar-wrapper {
    display: flex;
    align-items: center;
    gap: 8px;
    cursor: pointer;
    padding: 4px 8px;
    border-radius: 20px;
    transition: background 0.2s;

    &:hover {
      background: $bg-color;
    }
  }

  .user-name {
    font-size: 14px;
    color: $text-regular;
    max-width: 80px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
}

.main {
  flex: 1;
  max-width: 1200px;
  width: 100%;
  margin: 0 auto;
  padding: 20px;
}

.footer {
  background-color: #f8f9fb;
  border-top: 1px solid $border-lighter;
  padding: 24px 20px;

  .footer-content {
    max-width: 1200px;
    margin: 0 auto;
    text-align: center;
  }

  .footer-text {
    color: $text-secondary;
    font-size: 13px;
  }
}
</style>
