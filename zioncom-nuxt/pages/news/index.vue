<template>
  <div class="articles-page container">
    <div class="page-header">
      <h1>{{ $t('nav.news') }}</h1>
      <p>Stay updated with our latest announcements and product releases.</p>
    </div>
    
    <div v-if="pending" class="loading">Loading news...</div>
    <div v-else-if="articles.length === 0" class="no-data">No news available.</div>
    <div v-else class="article-grid">
      <ContentArticleCard v-for="item in articles" :key="item.id" :article="item" basePath="news" />
    </div>
  </div>
</template>

<script setup>
const { request } = useHttp()

const { data, pending } = request('/api/content/article/list', {
  lazy: true,
  query: { type: 'news', status: 'published' }
})

const articles = computed(() => {
  if (data.value && data.value.rows) return data.value.rows
  return [
    { id: 1, titleZh: '千兆双频路由器新品上市', titleEn: 'New Gigabit Dual-Band Router Released', summaryZh: '为您的高带宽需求...', publishDate: '2026-03-01' },
    { id: 2, titleZh: 'Wi-Fi 7 时代即将到来', titleEn: 'The Era of Wi-Fi 7 is Coming', summaryZh: 'ZIONCOM 已经准备好迎接最新标准...', publishDate: '2026-02-15' }
  ]
})

useHead({ title: 'News' })
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
