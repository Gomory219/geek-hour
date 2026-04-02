import axios, { type AxiosInstance, type AxiosError, type InternalAxiosRequestConfig, type AxiosResponse } from 'axios'
import { OpenAPI as UserOpenAPI, UserControllerService } from './generated/gkh-user'
import { OpenAPI as ResourceOpenAPI, FileControllerService, HealthControllerService as ResourceHealthControllerService } from './generated/gkh-resource'
import { OpenAPI as CourseOpenAPI, CourseControllerService, CategoryControllerService } from './generated/gkh-course'
import { OpenAPI as BlogOpenAPI, ArticleControllerService } from './generated/gkh-blog'
import { discreteMessage } from '@/utils/discrete'

// 创建全局axios实例
const http: AxiosInstance = axios.create({
  baseURL: '/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
})

// 请求拦截器：注入Token
http.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    const token = localStorage.getItem('token')
    if (token && config.headers) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error: AxiosError) => {
    return Promise.reject(error)
  }
)

// 响应拦截器：统一错误处理
http.interceptors.response.use(
  (response: AxiosResponse) => {
    // 后端返回 BaseResponse<T> 结构，这里可以统一处理
    const { data } = response
    if (data?.code !== undefined && data.code !== 0) {
      // 业务错误 - code 应该是 0 表示成功
      return Promise.reject(new Error(data.message || '请求失败'))
    }
    return response
  },
  (error: AxiosError) => {
    // HTTP错误
    if (error.response) {
      const status = error.response.status
      const messages: Record<number, string> = {
        401: '登录已过期，请重新登录',
        403: '无权限访问',
        404: '请求的资源不存在',
        500: '服务器错误，请稍后重试',
      }
      const msg = messages[status] || `请求失败 (${status})`
      discreteMessage.error(msg)

      if (status === 401) {
        localStorage.removeItem('token')
        window.location.href = '/login'
      }
    } else if (error.request) {
      discreteMessage.error('网络错误，请检查网络连接')
    } else {
      discreteMessage.error('请求配置错误')
    }
    return Promise.reject(error)
  }
)

// 配置生成的API代码
// 各服务的 BASE 已在生成时设置为正确的网关路由前缀
// gkh-user: /api/user, gkh-resource: /api/resource
UserOpenAPI.TOKEN = async () => localStorage.getItem('token') || ''
UserOpenAPI.WITH_CREDENTIALS = false

ResourceOpenAPI.TOKEN = async () => localStorage.getItem('token') || ''
ResourceOpenAPI.WITH_CREDENTIALS = false

CourseOpenAPI.TOKEN = async () => localStorage.getItem('token') || ''
CourseOpenAPI.WITH_CREDENTIALS = false

BlogOpenAPI.TOKEN = async () => localStorage.getItem('token') || ''
BlogOpenAPI.WITH_CREDENTIALS = false

// 导出axios实例供其他场景使用
export { http }

// 导出各服务API模块
export { UserControllerService }
export { FileControllerService }
export { CourseControllerService, CategoryControllerService }
export { ArticleControllerService }

// 导出Health服务（两个服务都有health接口）
export const HealthService = ResourceHealthControllerService

// 导出常用类型
export type { BaseResponseString, UserRegisterDTO } from './generated/gkh-user'
export type { CourseVO, CategoryVO } from './generated/gkh-course'
export type { Article } from './generated/gkh-blog'
