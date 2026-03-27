<template>
  <div class="page-login">
    <n-card title="登录">
      <n-form>
        <n-form-item label="用户名">
          <n-input placeholder="请输入用户名" />
        </n-form-item>
        <n-form-item label="密码">
          <n-input type="password" placeholder="请输入密码" />
        </n-form-item>
        <n-form-item>
          <n-button type="primary" block>登录</n-button>
        </n-form-item>
      </n-form>
      <p class="register-link">
        还没有账号？<router-link to="/register">立即注册</router-link>
      </p>
    </n-card>
  </div>
</template>

<script setup lang="ts">
import { NCard, NForm, NFormItem, NInput, NButton } from 'naive-ui'
</script>

<style scoped lang="scss">
.page-login {
  .register-link {
    text-align: center;
    margin-top: 20px;
    color: #fff;

    a {
      color: #fff;
      text-decoration: underline;
    }
  }
}
</style>
