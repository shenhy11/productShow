<template>
  <NuxtLink :to="localePath(`/${basePath}/${article.id}`)" class="article-card">
    <div class="article-img" :style="{ backgroundImage: `url(${article.coverImage || 'https://picsum.photos/400/250?random=' + article.id})` }"></div>
    <div class="article-info">
      <span class="article-date">{{ formatDate(article.publishDate) }}</span>
      <h3 class="article-title">{{ locale === 'zh' ? article.titleZh : article.titleEn }}</h3>
      <p class="article-summary">{{ locale === 'zh' ? article.summaryZh : article.summaryEn }}</p>
    </div>
  </NuxtLink>
</template>

<script setup>
const localePath = useLocalePath()
const { locale } = useI18n()

defineProps({
  article: {
    type: Object,
    required: true
  },
  basePath: {
    type: String,
    default: 'news' // or 'events'
  }
})

function formatDate(dateStr) {
  if (!dateStr) return ''
  const date = new Date(dateStr)
  return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`
}
</script>

<style scoped>
.article-card {
  display: block;
  border: 1px solid #eaeaea;
  border-radius: 8px;
  overflow: hidden;
  text-decoration: none;
  background: #fff;
  transition: transform 0.3s, box-shadow 0.3s;
}
.article-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0,0,0,0.1);
  border-color: var(--color-primary);
}
.article-img {
  width: 100%;
  height: 200px;
  background-size: cover;
  background-position: center;
  background-color: #f7f7f7;
}
.article-info {
  padding: 20px;
}
.article-date {
  font-size: 0.85rem;
  color: #999;
  display: block;
  margin-bottom: 10px;
}
.article-title {
  color: #333;
  margin: 0 0 15px 0;
  font-size: 1.25rem;
  line-height: 1.4;
  height: 2.8em;
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.article-summary {
  font-size: 0.95rem;
  color: #666;
  margin: 0;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
