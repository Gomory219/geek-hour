<template>
  <div class="page-course-detail">
    <h1>课程详情</h1>
  </div>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'

const route = useRoute()
console.log('课程 ID:', route.params.id)
</script>

<style scoped lang="scss">
.page-course-detail {
  padding: 20px;
}
</style>
