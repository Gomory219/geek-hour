import { defineStore } from 'pinia'
import { ref } from 'vue'

export interface UserInfo {
  id: number
  username: string
  nickname: string
  email: string
  avatar?: string
}

export const useUserStore = defineStore('user', () => {
  const token = ref<string>(localStorage.getItem('token') || '')
  const userInfo = ref<UserInfo | null>(null)
  const isLoggedIn = ref<boolean>(!!token.value)

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

  // 登录
  const login = async (username: string, password: string) => {
    // TODO: 调用登录 API
    // const res = await UserControllerService.login({ username, password })
    // setToken(res.data.token)
    // setUserInfo(res.data.user)
    console.log('登录:', username, password)
  }

  // 登出
  const logout = () => {
    clearToken()
    // TODO: 跳转到登录页
  }

  return {
    token,
    userInfo,
    isLoggedIn,
    setToken,
    clearToken,
    setUserInfo,
    login,
    logout
  }
})
