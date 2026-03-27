<template>
  <div class="auth-layout">
    <div class="auth-container">
      <div class="auth-header">
        <h1 class="logo">Geek Hour</h1>
      </div>
      <router-view />
    </div>
  </div>
</template>

<script setup lang="ts">
// 认证布局（登录/注册）
</script>

<style scoped lang="scss">
.auth-layout {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.auth-container {
  width: 100%;
  max-width: 400px;
  padding: 20px;
}

.auth-header {
  text-align: center;
  margin-bottom: 30px;

  .logo {
    font-size: 32px;
    font-weight: bold;
    color: #fff;
    margin: 0;
  }
}
</style>
