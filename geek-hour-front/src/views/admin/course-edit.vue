<template>
  <div class="page-admin-course-edit">
    <h1>编辑课程</h1>
  </div>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'

const route = useRoute()
console.log('课程 ID:', route.params.id)
</script>

<style scoped lang="scss">
.page-admin-course-edit {
}
</style>
