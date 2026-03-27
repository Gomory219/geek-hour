<template>
  <div class="page-admin-user-detail">
    <h1>用户详情</h1>
  </div>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'

const route = useRoute()
console.log('用户 ID:', route.params.id)
</script>

<style scoped lang="scss">
.page-admin-user-detail {
}
</style>
