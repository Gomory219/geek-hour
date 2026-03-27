<template>
  <div class="page-community-post">
    <h1>帖子详情</h1>
  </div>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'

const route = useRoute()
console.log('帖子 ID:', route.params.id)
</script>

<style scoped lang="scss">
.page-community-post {
  padding: 20px;
}
</style>
