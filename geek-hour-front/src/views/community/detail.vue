<template>
  <div class="page-community-detail">
    <h1>社区详情</h1>
  </div>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'

const route = useRoute()
console.log('社区 ID:', route.params.id)
</script>

<style scoped lang="scss">
.page-community-detail {
  padding: 20px;
}
</style>
