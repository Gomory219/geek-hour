<template>
  <div class="tutorial-content-viewer">
    <!-- 加载状态 -->
    <div v-if="loading" class="loading-container">
      <n-spin size="large" />
    </div>

    <!-- 教程内容 -->
    <div v-else-if="tutorialDetail" class="tutorial-content">
      <!-- 教程头部信息 -->
      <div class="tutorial-header">
        <h1 class="tutorial-title">{{ tutorialDetail.title }}</h1>

        <!-- 作者信息 -->
        <div v-if="tutorialDetail.authorName" class="tutorial-author">
          <n-avatar
            v-if="tutorialDetail.authorAvatarUrl"
            :src="tutorialDetail.authorAvatarUrl"
            :size="32"
            round
          />
          <span class="author-name">{{ tutorialDetail.authorName }}</span>
        </div>

        <!-- 统计信息 -->
        <div class="tutorial-stats">
          <span class="stat-item">
            <n-icon size="14"><EyeOutline /></n-icon>
            {{ formatCount(tutorialDetail.viewCount) }} 浏览
          </span>
          <span class="stat-item">
            <n-icon size="14"><HeartOutline /></n-icon>
            {{ formatCount(tutorialDetail.likes) }} 点赞
          </span>
        </div>
      </div>

      <!-- 文字教程 -->
      <div v-if="tutorialDetail.type === 'TEXT'" class="text-content">
        <MdPreview
          :model-value="tutorialDetail.content || ''"
          theme="light"
          preview-theme="default"
        />
      </div>

      <!-- 视频教程 -->
      <div v-else-if="tutorialDetail.type === 'VIDEO'" class="video-content">
        <div ref="artRef" class="video-container"></div>
      </div>
    </div>

    <!-- 空状态 -->
    <n-empty v-else description="暂无教程内容" />
  </div>
</template>

<script setup lang="ts">
import { ref, watchEffect, onUnmounted } from 'vue'
import { NSpin, NEmpty, NIcon, NAvatar } from 'naive-ui'
import { EyeOutline, HeartOutline } from '@vicons/ionicons5'
import { MdPreview } from 'md-editor-v3'
import ArtPlayer from 'artplayer'
import type { ArtplayerOptions } from 'artplayer'
import type { TutorialDetailVO } from '@/api'
import 'md-editor-v3/lib/preview.css'

interface Props {
  tutorialDetail: TutorialDetailVO | null
  loading: boolean
}

const props = defineProps<Props>()

const artRef = ref<HTMLDivElement>()
let artPlayer: ArtPlayer | null = null

// 初始化视频播放器
watchEffect(() => {
  if (props.tutorialDetail?.type === 'VIDEO' && props.tutorialDetail.videoUrl && artRef.value) {
    // 销毁旧实例
    if (artPlayer) {
      artPlayer.destroy()
      artPlayer = null
    }

    // 创建新实例
    const options: ArtplayerOptions = {
      container: artRef.value,
      url: props.tutorialDetail.videoUrl,
      volume: 0.7,
      isLive: false,
      muted: false,
      autoplay: false,
      pip: true,
      autoSize: true,
      autoMini: true,
      screenshot: true,
      setting: true,
      loop: false,
      flip: true,
      playbackRate: true,
      aspectRatio: true,
      fullscreen: true,
      fullscreenWeb: true,
      miniProgressBar: true,
      theme: '#409eff'
    }

    artPlayer = new ArtPlayer(options)
  }
})

// 清理
onUnmounted(() => {
  if (artPlayer) {
    artPlayer.destroy()
    artPlayer = null
  }
})

// 格式化数字
function formatCount(count?: number): string {
  if (!count) return '0'
  if (count >= 10000) {
    return (count / 10000).toFixed(1) + '万'
  }
  return count.toString()
}
</script>

<style scoped lang="scss">
.tutorial-content-viewer {
  background: $bg-white;
  border-radius: $border-radius-large;
  border: 1px solid $border-light;
  overflow: hidden;
}

.loading-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 400px;
}

.tutorial-content {
  padding: $spacing-lg;
}

.tutorial-header {
  padding-bottom: $spacing-lg;
  border-bottom: 1px solid $border-extra-light;
  margin-bottom: $spacing-lg;
}

.tutorial-title {
  font-size: $font-size-large;
  font-weight: 600;
  color: $text-primary;
  margin: 0 0 $spacing-md 0;
  line-height: 1.4;
}

.tutorial-author {
  display: flex;
  align-items: center;
  gap: $spacing-sm;
  margin-bottom: $spacing-md;

  .author-name {
    font-size: $font-size-base;
    color: $text-regular;
  }
}

.tutorial-stats {
  display: flex;
  gap: $spacing-lg;
}

.stat-item {
  display: flex;
  align-items: center;
  gap: $spacing-xs;
  font-size: $font-size-small;
  color: $text-secondary;

  .n-icon {
    color: $text-placeholder;
  }
}

.text-content {
  :deep(.md-preview) {
    background: transparent;
  }

  :deep(.md-preview-content) {
    font-size: $font-size-base;
    line-height: 1.8;
    color: $text-primary;
  }
}

.video-content {
  .video-container {
    width: 100%;
    aspect-ratio: 16 / 9;
    background: #000;
    border-radius: $border-radius-base;
    overflow: hidden;
  }
}
</style>
