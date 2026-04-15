<template>
  <div class="articles-page container">
    <div class="page-header">
      <h1>Events</h1>
      <p>Join us at upcoming exhibitions and online webinars.</p>
    </div>
    
    <div v-if="pending" class="loading">Loading events...</div>
    <div v-else-if="articles.length === 0" class="no-data">No events available.</div>
    <div v-else class="article-grid">
      <ContentArticleCard v-for="item in articles" :key="item.id" :article="item" basePath="events" />
    </div>
  </div>
</template>

<script setup>
const { request } = useHttp()

const { data, pending } = request('/api/content/article/list', {
  lazy: true,
  query: { type: 'event', status: 'published' }
})

const articles = computed(() => {
  if (data.value && data.value.rows) return data.value.rows
  return [
    { id: 3, titleZh: '诚邀共赴 2026 汉诺威展', titleEn: 'Welcome to Hannover Messe 2026', summaryZh: '...', publishDate: '2026-04-01' }
  ]
})

useHead({ title: 'Events' })
</script>

<style scoped>
.articles-page {
  padding: 40px 15px;
}
.page-header {
  text-align: center;
  margin-bottom: 50px;
}
.page-header h1 {
  font-size: 2.5rem;
  color: #333;
  margin-bottom: 15px;
}
.page-header p {
  color: #666;
  font-size: 1.1rem;
}
.article-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 30px;
}
</style>
