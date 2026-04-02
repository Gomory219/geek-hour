/**
 * Naive UI 离散 API
 * 用于在拦截器、store 等非组件上下文中显示消息提示
 * 不需要 <n-message-provider> 祖先组件
 */
import { createDiscreteApi } from 'naive-ui'

const { message } = createDiscreteApi(['message'])

export const discreteMessage = message
