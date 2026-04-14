<template>
  <div>
    <HomeHeroBanner />
    <HomeQuickLinks />
    <HomeProductCategories />
    <HomeLatestNews />
  </div>
</template>

<script setup>
// import 结构说明：Nuxt3 默认开启自动导入 components 的规则
// 由于我们将其放置在 /components/home/HeroBanner.vue
// 在模板中你可以直接使用 <HomeHeroBanner />

useHead({
  title: 'Home',
  meta: [
    { name: 'description', content: 'Zioncom Home - Leading provider of networking devices.' }
  ]
})
</script>

<style scoped>
</style>
