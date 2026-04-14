<template>
  <div class="tech-videos-page container">
    <div class="page-header">
      <h1>Tech Videos</h1>
      <p>Watch step-by-step setup guides, feature introductions, and more.</p>
    </div>
    
    <div v-if="pending" class="loading">Loading videos...</div>
    <div v-else-if="videos.length === 0" class="no-data">No technical videos available.</div>
    <div v-else class="video-grid">
      <div class="video-card" v-for="video in videos" :key="video.id">
        <div class="video-wrapper">
          <iframe 
            :src="video.videoUrl" 
            frameborder="0" 
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
            allowfullscreen
            v-if="video.videoUrl"
          ></iframe>
          <div class="video-placeholder" v-else>
            No Video Link
          </div>
        </div>
        <div class="video-info">
          <h3>{{ locale === 'zh' ? video.titleZh : video.titleEn }}</h3>
          <p class="duration">Duration: {{ video.duration || 'Unknown' }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const { locale } = useI18n()
const { request } = useHttp()

const { data, pending } = await request('/api/support/techVideo/list', {
  query: { status: 'published' }
})

const videos = computed(() => {
  if (data.value && data.value.rows) return data.value.rows
  return [
    { id: 1, titleZh: '如何快速设置上网', titleEn: 'Quick Internet Setup Guide', videoUrl: 'https://www.youtube.com/embed/dQw4w9WgXcQ', duration: '3:45' },
    { id: 2, titleZh: 'Wi-Fi 7 技术详解', titleEn: 'Wi-Fi 7 Technology Explained', videoUrl: 'https://www.youtube.com/embed/dQw4w9WgXcQ', duration: '5:20' }
  ]
})

useHead({ title: 'Tech Videos' })
</script>

<style scoped>
.tech-videos-page {
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
.video-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 30px;
}
.video-card {
  background: #fff;
  border: 1px solid #eaeaea;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 4px 6px rgba(0,0,0,0.02);
}
.video-wrapper {
  position: relative;
  width: 100%;
  padding-bottom: 56.25%; /* 16:9 Aspect Ratio */
  background: #000;
}
.video-wrapper iframe {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
.video-placeholder {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  font-size: 1.2rem;
}
.video-info {
  padding: 15px 20px;
}
.video-info h3 {
  margin: 0 0 10px 0;
  font-size: 1.15rem;
  color: #333;
}
.duration {
  font-size: 0.9rem;
  color: #888;
  margin: 0;
}
</style>
