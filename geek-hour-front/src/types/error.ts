/**
 * 错误码枚举 - 镜像后端 cn.edu.sxu.exception.ErrorCode
 */
export enum ErrorCode {
  SUCCESS = 0,
  PARAMS_ERROR = 40000,
  NOT_LOGIN_ERROR = 40100,
  NO_AUTH_ERROR = 40101,
  NOT_FOUND_ERROR = 40400,
  FORBIDDEN_ERROR = 40300,
  SYSTEM_ERROR = 50000,
  OPERATION_ERROR = 50001,
}

/**
 * 已知错误码的友好中文提示
 * 当后端返回的 message 不可用时使用此映射
 */
const ERROR_MESSAGES: Record<number, string> = {
  [ErrorCode.PARAMS_ERROR]: '请求参数错误，请检查输入',
  [ErrorCode.NOT_LOGIN_ERROR]: '登录已过期，请重新登录',
  [ErrorCode.NO_AUTH_ERROR]: '您没有权限执行此操作',
  [ErrorCode.NOT_FOUND_ERROR]: '请求的数据不存在',
  [ErrorCode.FORBIDDEN_ERROR]: '禁止访问',
  [ErrorCode.SYSTEM_ERROR]: '系统内部异常，请稍后重试',
  [ErrorCode.OPERATION_ERROR]: '操作失败，请稍后重试',
}

/**
 * 获取错误提示消息
 * @param code 错误码
 * @param fallback 后端返回的错误消息（优先使用）
 * @returns 用户友好的错误提示
 */
export function getErrorMessage(code: number, fallback?: string): string {
  return fallback || ERROR_MESSAGES[code] || '请求失败'
}

/**
 * 业务错误类
 * 当后端返回 code !== 0 时抛出
 */
export class BusinessError extends Error {
  readonly code: number

  constructor(code: number, message: string) {
    super(message)
    this.name = 'BusinessError'
    this.code = code
  }
}
