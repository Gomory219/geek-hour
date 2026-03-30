<template>
  <div class="page-login">
    <n-card title="登录">
      <n-form ref="formRef" :model="formModel" :rules="rules">
        <n-form-item label="账号/手机号" path="account">
          <n-input v-model:value="formModel.account" placeholder="请输入账号或手机号" />
        </n-form-item>
        <n-form-item label="密码" path="password">
          <n-input
            v-model:value="formModel.password"
            type="password"
            show-password-on="click"
            placeholder="请输入密码"
            @keyup.enter="handleLogin"
          />
        </n-form-item>
        <n-form-item>
          <n-button type="primary" block :loading="loading" @click="handleLogin">登录</n-button>
        </n-form-item>
      </n-form>
      <p class="register-link">
        还没有账号？<router-link to="/register">立即注册</router-link>
      </p>
    </n-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useMessage } from 'naive-ui'
import type { FormInst, FormRules } from 'naive-ui'
import { useUserStore } from '@/stores/user'

const formRef = ref<FormInst | null>(null)
const loading = ref(false)
const message = useMessage()
const router = useRouter()
const route = useRoute()
const userStore = useUserStore()

const formModel = reactive({
  account: '',
  password: ''
})

const rules: FormRules = {
  account: [
    { required: true, message: '请输入账号或手机号', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码至少6个字符', trigger: 'blur' }
  ]
}

const handleLogin = async () => {
  try {
    await formRef.value?.validate()
  } catch {
    return
  }

  loading.value = true
  try {
    await userStore.login(formModel.account, formModel.password)
    const redirect = (route.query.redirect as string) || '/'
    router.push(redirect)
  } catch (err: any) {
    message.error(err.message || '登录失败，请检查账号密码')
  } finally {
    loading.value = false
  }
}
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
