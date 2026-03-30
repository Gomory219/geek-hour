<template>
  <div class="admin-login-page">
    <div class="login-card">
      <h2 class="title">Geek Hour 管理后台</h2>
      <n-form ref="formRef" :model="formModel" :rules="rules">
        <n-form-item path="account">
          <n-input
            v-model:value="formModel.account"
            placeholder="请输入管理员账号"
            size="large"
          />
        </n-form-item>
        <n-form-item path="password">
          <n-input
            v-model:value="formModel.password"
            type="password"
            show-password-on="click"
            placeholder="请输入密码"
            size="large"
            @keyup.enter="handleLogin"
          />
        </n-form-item>
        <n-form-item>
          <n-button type="primary" block size="large" :loading="loading" @click="handleLogin">
            登录
          </n-button>
        </n-form-item>
      </n-form>
      <p class="back-link">
        <router-link to="/">返回用户端</router-link>
      </p>
    </div>
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
  account: '',
  password: ''
})

const rules: FormRules = {
  account: [
    { required: true, message: '请输入管理员账号', trigger: 'blur' }
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
    const userInfo = await userStore.login(formModel.account, formModel.password)
    if (userInfo?.role !== 'ADMIN') {
      userStore.logout()
      message.error('该账号无管理员权限')
      return
    }
    router.push('/admin')
  } catch (err: any) {
    message.error(err.message || '登录失败，请检查账号密码')
  } finally {
    loading.value = false
  }
}
</script>

<style scoped lang="scss">
.admin-login-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%);

  .login-card {
    width: 400px;
    padding: 40px;
    background: rgba(255, 255, 255, 0.95);
    border-radius: 12px;
    box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);

    .title {
      text-align: center;
      margin: 0 0 30px;
      font-size: 22px;
      font-weight: 600;
      color: #1a1a2e;
    }

    .back-link {
      text-align: center;
      margin-top: 20px;
      font-size: 14px;

      a {
        color: #666;
        text-decoration: none;

        &:hover {
          color: #409eff;
        }
      }
    }
  }
}
</style>
