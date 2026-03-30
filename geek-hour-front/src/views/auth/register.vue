<template>
  <div class="page-register">
    <n-card title="注册">
      <n-form ref="formRef" :model="formModel" :rules="rules">
        <n-form-item label="手机号" path="phone">
          <n-input v-model:value="formModel.phone" placeholder="请输入手机号" />
        </n-form-item>
        <n-form-item label="用户名" path="username">
          <n-input v-model:value="formModel.username" placeholder="请输入用户名" />
        </n-form-item>
        <n-form-item label="密码" path="password">
          <n-input
            v-model:value="formModel.password"
            type="password"
            show-password-on="click"
            placeholder="请输入密码"
          />
        </n-form-item>
        <n-form-item label="确认密码" path="confirmPassword">
          <n-input
            v-model:value="formModel.confirmPassword"
            type="password"
            show-password-on="click"
            placeholder="请再次输入密码"
            @keyup.enter="handleRegister"
          />
        </n-form-item>
        <n-form-item>
          <n-button type="primary" block :loading="loading" @click="handleRegister">注册</n-button>
        </n-form-item>
      </n-form>
      <p class="login-link">
        已有账号？<router-link to="/login">立即登录</router-link>
      </p>
    </n-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useMessage } from 'naive-ui'
import type { FormInst, FormRules } from 'naive-ui'
import { useUserStore } from '@/stores/user'

const formRef = ref<FormInst | null>(null)
const loading = ref(false)
const message = useMessage()
const router = useRouter()
const userStore = useUserStore()

const formModel = reactive({
  phone: '',
  username: '',
  password: '',
  confirmPassword: ''
})

const rules: FormRules = {
  phone: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
    { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur' }
  ],
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 20, message: '用户名长度为2-20个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度为6-20个字符', trigger: 'blur' }
  ],
  confirmPassword: [
    { required: true, message: '请确认密码', trigger: 'blur' },
    {
      validator: (_rule, value) => {
        if (value !== formModel.password) {
          return new Error('两次输入的密码不一致')
        }
        return true
      },
      trigger: 'blur'
    }
  ]
}

const handleRegister = async () => {
  try {
    await formRef.value?.validate()
  } catch {
    return
  }

  loading.value = true
  try {
    await userStore.register({
      phone: formModel.phone,
      username: formModel.username,
      password: formModel.password,
      confirmPassword: formModel.confirmPassword
    })
    message.success('注册成功，请登录')
    router.push('/login')
  } catch (err: any) {
    message.error(err.message || '注册失败，请稍后重试')
  } finally {
    loading.value = false
  }
}
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
