/**
 * 统一 API 请求包装函数
 * 处理 BaseResponse 解包、业务错误检测、用户反馈
 */
import { ErrorCode, BusinessError, getErrorMessage } from '@/types/error'
import { discreteMessage } from '@/utils/discrete'
import router from '@/router'

export interface RequestOptions {
  /** 是否自动显示错误提示 toast。默认: true */
  showToast?: boolean
  /** 是否跳过 40100 自动跳转登录页。默认: false */
  skipAuthRedirect?: boolean
}

const DEFAULT_OPTIONS: RequestOptions = {
  showToast: true,
  skipAuthRedirect: false,
}

// 防止多个 40100 同时触发多次跳转
let isRedirecting = false

/**
 * 包装生成 API 调用，统一处理错误
 *
 * @param apiCall - 生成 API 服务方法调用，返回 BaseResponse<T> 或裸数据
 * @param options - 配置选项
 * @returns 解包后的数据 T
 * @throws {BusinessError} 业务错误
 *
 * @example
 * // 自动显示错误提示
 * const data = await apiRequest(UserControllerService.loginUser())
 *
 * @example
 * // 不自动显示错误，由 view 层处理
 * const data = await apiRequest(UserControllerService.login({ account, password }), { showToast: false })
 */
export async function apiRequest<T>(
  apiCall: Promise<any>,
  options?: RequestOptions
): Promise<T> {
  const opts = { ...DEFAULT_OPTIONS, ...options }

  try {
    const response = await apiCall

    // 判断是否有 code 字段（区分 BaseResponse<T> 和裸数据返回）
    if (response && typeof response === 'object' && 'code' in response) {
      // BaseResponse<T> 结构
      if (response.code === ErrorCode.SUCCESS) {
        return response.data as T
      }

      // 业务错误
      const message = getErrorMessage(response.code!, response.message)
      const error = new BusinessError(response.code!, message)
      handleBusinessError(error, opts)
      throw error
    }

    // 无 code 字段，直接返回（如 ArticleControllerService 返回裸数组）
    return response as T
  } catch (err) {
    if (err instanceof BusinessError) {
      throw err
    }

    // 网络错误、超时、ApiError 等
    const message = err instanceof Error ? err.message : '网络异常，请检查网络连接'
    const error = new BusinessError(ErrorCode.SYSTEM_ERROR, message)
    if (opts.showToast) {
      discreteMessage.error(message)
    }
    throw error
  }
}

/**
 * 处理业务错误（40100 跳转、显示提示）
 */
function handleBusinessError(error: BusinessError, options: RequestOptions): void {
  // 40100: 未登录 - 清除 token 并跳转登录页
  if (error.code === ErrorCode.NOT_LOGIN_ERROR && !options.skipAuthRedirect) {
    localStorage.removeItem('token')

    if (!isRedirecting) {
      isRedirecting = true
      const currentPath = window.location.pathname + window.location.search

      // 避免在登录页重复跳转
      if (currentPath !== '/login' && currentPath !== '/admin/login') {
        router.push({ path: '/login', query: { redirect: currentPath } })
      }

      // 1秒后重置防跳转标志
      setTimeout(() => {
        isRedirecting = false
      }, 1000)
    }
  }

  if (options.showToast) {
    discreteMessage.error(error.message)
  }
}
