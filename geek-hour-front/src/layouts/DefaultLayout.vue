<template>
  <div class="default-layout">
    <header class="header">
      <div class="header-content">
        <h1 class="logo">Geek Hour</h1>
        <nav class="nav">
          <router-link to="/">首页</router-link>
          <router-link to="/course">课程</router-link>
          <router-link to="/community">社区</router-link>
          <router-link to="/search">搜索</router-link>
        </nav>
        <div class="user-actions">
          <template v-if="userStore.isLoggedIn">
            <router-link to="/user/profile">个人中心</router-link>
            <a @click="handleLogout">退出</a>
          </template>
          <template v-else>
            <router-link to="/login">登录</router-link>
            <router-link to="/register">注册</router-link>
          </template>
        </div>
      </div>
    </header>
    <main class="main">
      <router-view />
    </main>
    <footer class="footer">
      <p>&copy; 2024 Geek Hour. All rights reserved.</p>
    </footer>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()

const handleLogout = () => {
  userStore.logout()
  router.push('/')
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
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
    font-size: 24px;
    font-weight: bold;
    color: $primary-color;
    margin: 0;
  }

  .nav {
    display: flex;
    gap: 30px;

    a {
      color: $text-regular;
      transition: color 0.3s;

      &:hover,
      &.router-link-active {
        color: $primary-color;
      }
    }
  }

  .user-actions {
    display: flex;
    gap: 20px;

    a {
      color: $text-regular;
      cursor: pointer;

      &:hover {
        color: $primary-color;
      }
    }
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
  background-color: #f5f5f5;
  padding: 20px;
  text-align: center;
  color: $text-secondary;
}
</style>
