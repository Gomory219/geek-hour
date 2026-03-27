<template>
  <div class="page-register">
    <n-card title="注册">
      <n-form>
        <n-form-item label="用户名">
          <n-input placeholder="请输入用户名" />
        </n-form-item>
        <n-form-item label="邮箱">
          <n-input placeholder="请输入邮箱" />
        </n-form-item>
        <n-form-item label="密码">
          <n-input type="password" placeholder="请输入密码" />
        </n-form-item>
        <n-form-item label="确认密码">
          <n-input type="password" placeholder="请再次输入密码" />
        </n-form-item>
        <n-form-item>
          <n-button type="primary" block>注册</n-button>
        </n-form-item>
      </n-form>
      <p class="login-link">
        已有账号？<router-link to="/login">立即登录</router-link>
      </p>
    </n-card>
  </div>
</template>

<script setup lang="ts">
import { NCard, NForm, NFormItem, NInput, NButton } from 'naive-ui'
</script>

<style scoped lang="scss">
.page-register {
  .login-link {
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
