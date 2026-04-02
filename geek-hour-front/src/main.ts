import { createApp } from 'vue'
import { createPinia } from 'pinia'
import router from './router'
import App from './App.vue'

// Naive UI
import naive from 'naive-ui'

// 全局样式
import '@/assets/styles/global.scss'

// 错误处理
import { BusinessError } from '@/types/error'
import { discreteMessage } from '@/utils/discrete'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(naive)

// 全局错误处理器 - 兜底未处理的 BusinessError
app.config.errorHandler = (err, instance, info) => {
  console.error('[Global Error Handler]', err, info)
  if (err instanceof BusinessError) {
    discreteMessage.error(err.message)
  }
}

app.mount('#app')
