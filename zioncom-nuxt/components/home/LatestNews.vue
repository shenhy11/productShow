<template>
  <div class="latest-news container">
    <div class="section-header">
      <h2>{{ $t('nav.news') }}</h2>
      <NuxtLink :to="localePath('/news')" class="view-all">{{ $t('common.viewMore') }}</NuxtLink>
    </div>
    
    <div v-if="pending" class="loading">{{ $t('common.loading') }}</div>
    <div v-else class="news-grid">
      <!-- 整个卡片作为 NuxtLink，点击任意位置都能跳转 -->
      <NuxtLink
        class="news-card"
        v-for="news in newsList"
        :key="news.id"
        :to="localePath(`/news/${news.id}`)"
      >
        <div class="news-img" :style="{ backgroundImage: `url(${news.coverImage || 'https://picsum.photos/400/200?random=' + news.id})` }"></div>
        <div class="news-info">
          <span class="news-date">{{ formatDate(news.publishDate) }}</span>
          <h4 class="news-title">{{ locale === 'zh' ? news.titleZh : news.titleEn }}</h4>
          <span class="read-more">{{ $t('home.readMore') }} &rarr;</span>
        </div>
      </NuxtLink>
    </div>
  </div>
</template>

<script setup>
const localePath = useLocalePath()
const { locale } = useI18n()
const { request } = useHttp()

// 请求最新新闻接口 (实际部署时使用正式接口)
const { data, pending } = request('/api/content/article/list', {
  lazy: true,
  query: { pageSize: 3, status: 'published' }
})

// 为避免没有数据暂用 mock 数据
const newsList = computed(() => {
  if (data.value && data.value.rows && data.value.rows.length > 0) {
    return data.value.rows
  }
  return [
    { id: 1, titleZh: 'ZIONCOM Launches Next-Gen Wi-Fi 7 Router', publishDate: '2026-03-15' },
    { id: 2, titleZh: 'Global Partners Summit 2026 Concluded Successfully', publishDate: '2026-02-28' },
    { id: 3, titleZh: 'New Smart Home IoT Series Unveiled', publishDate: '2026-01-10' }
  ]
})

function formatDate(dateStr) {
  if (!dateStr) return ''
  const date = new Date(dateStr)
  return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`
}
</script>

<style scoped>
.latest-news {
  padding: 60px 15px;
}
.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}
.section-header h2 {
  margin: 0;
  font-size: 2rem;
  color: #333;
}
.view-all {
  color: var(--color-primary);
  text-decoration: none;
}
.view-all:hover {
  text-decoration: underline;
}
.news-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 30px;
}
.news-card {
  display: block;
  text-decoration: none;
  color: inherit;
  border: 1px solid #eaeaea;
  border-radius: 8px;
  overflow: hidden;
  transition: transform 0.3s, box-shadow 0.3s;
  background: #fff;
}
.news-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0,0,0,0.1);
}
.news-img {
  width: 100%;
  height: 200px;
  background-size: cover;
  background-position: center;
}
.news-info {
  padding: 20px;
}
.news-date {
  font-size: 0.9rem;
  color: #999;
  display: block;
  margin-bottom: 10px;
}
.news-title {
  margin: 0 0 15px 0;
  font-size: 1.2rem;
  color: #333;
  line-height: 1.4;
  height: 2.8em;
  overflow: hidden;
}
.read-more {
  color: var(--color-primary);
  text-decoration: none;
  font-weight: bold;
}
.read-more:hover {
  color: var(--color-secondary);
}
</style>
