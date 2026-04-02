import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { UserControllerService } from '@/api/generated/gkh-user'
import { apiRequest } from '@/utils/request'

export interface UserInfo {
  id?: string
  account?: string
  userName?: string
  phone?: string
  avatarImage?: string
  role?: 'ADMIN' | 'USER' | 'VIP'
}

export const useUserStore = defineStore('user', () => {
  const token = ref<string>(localStorage.getItem('token') || '')
  const userInfo = ref<UserInfo | null>(null)
  const isLoggedIn = ref<boolean>(!!token.value)

  const isAdmin = computed(() => userInfo.value?.role === 'ADMIN')

  // 设置 Token
  const setToken = (newToken: string) => {
    token.value = newToken
    localStorage.setItem('token', newToken)
    isLoggedIn.value = true
  }

  // 清除 Token
  const clearToken = () => {
    token.value = ''
    localStorage.removeItem('token')
    isLoggedIn.value = false
    userInfo.value = null
  }

  // 设置用户信息
  const setUserInfo = (info: UserInfo) => {
    userInfo.value = info
  }

  // 获取当前登录用户信息
  const fetchUserInfo = async (): Promise<UserInfo | null> => {
    const data = await apiRequest(
      UserControllerService.loginUser(),
      { showToast: false, skipAuthRedirect: true }
    )
    const info: UserInfo = {
      id: data?.id,
      account: data?.account,
      userName: data?.userName,
      phone: data?.phone,
      avatarImage: data?.avatarImage,
      role: data?.role
    }
    userInfo.value = info
    return info
  }

  // 登录
  const login = async (account: string, password: string): Promise<UserInfo | null> => {
    const tokenStr = await apiRequest(
      UserControllerService.login({ account, password }),
      { showToast: false }
    )
    setToken(tokenStr || '')
    return await fetchUserInfo()
  }

  // 注册
  const register = async (data: {
    phone: string
    username: string
    password: string
    confirmPassword: string
  }) => {
    return await apiRequest(
      UserControllerService.register({
        phone: data.phone,
        username: data.username,
        password: data.password,
        confirmPassword: data.confirmPassword
      }),
      { showToast: false }
    )
  }

  // 登出
  const logout = () => {
    clearToken()
  }

  return {
    token,
    userInfo,
    isLoggedIn,
    isAdmin,
    setToken,
    clearToken,
    setUserInfo,
    fetchUserInfo,
    login,
    register,
    logout
  }
})
