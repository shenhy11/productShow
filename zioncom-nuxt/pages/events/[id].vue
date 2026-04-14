<template>
  <div class="article-detail container">
    <div v-if="pending" class="loading">Loading event...</div>
    <div v-else-if="!article" class="no-data">Event not found.</div>
    <div v-else>
      <div class="article-header">
        <h1>{{ locale === 'zh' ? article.titleZh : article.titleEn }}</h1>
        <div class="meta">
          <span class="date">{{ formatDate(article.publishDate) }}</span>
          <span class="author" v-if="article.author">By {{ article.author }}</span>
        </div>
      </div>
      
      <div class="article-cover" v-if="article.coverImage">
        <img :src="article.coverImage" :alt="locale === 'zh' ? article.titleZh : article.titleEn" />
      </div>
      
      <div class="article-body rich-text" v-html="locale === 'zh' ? article.contentZh : article.contentEn"></div>
    </div>
  </div>
</template>

<script setup>
const route = useRoute()
const { locale } = useI18n()
const { request } = useHttp()
const articleId = route.params.id

const { data: res, pending } = await request(`/api/content/article/${articleId}`)

const article = computed(() => {
  if (res.value?.data) return res.value.data
  return { 
    id: articleId, 
    titleZh: 'ZIONCOM 参加欧洲展会', 
    titleEn: 'ZIONCOM attends EU Exhibition', 
    publishDate: '2026-04-01',
    author: 'Marketing',
    contentZh: '<p>这里是正文</p>',
    contentEn: '<p>Body text here</p>'
  }
})

function formatDate(dateStr) {
  if (!dateStr) return ''
  const date = new Date(dateStr)
  return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`
}

useHead(() => ({
  title: locale.value === 'zh' ? article.value?.titleZh : article.value?.titleEn
}))
</script>

<style scoped>
.article-detail {
  padding: 40px 15px;
  max-width: 900px;
  margin: 0 auto;
}
.article-header {
  text-align: center;
  margin-bottom: 40px;
}
.article-header h1 {
  font-size: 2.2rem;
  color: #333;
  margin-bottom: 15px;
}
.meta {
  color: #999;
  font-size: 0.95rem;
}
.meta span {
  margin: 0 10px;
}
.article-cover img {
  width: 100%;
  border-radius: 8px;
  margin-bottom: 40px;
}
.article-body {
  font-size: 1.1rem;
  line-height: 1.8;
  color: #444;
}
</style>
