<template>
  <div class="emulators-page container">
    <div class="page-header">
      <h1>Web Emulators</h1>
      <p>Experience the web interface of our products before you buy, or use them as a reference for configuration.</p>
    </div>
    
    <div v-if="pending" class="loading">Loading emulators...</div>
    <div v-else-if="emulators.length === 0" class="no-data">No emulators available right now.</div>
    <div v-else class="emulators-grid">
      <div class="emulator-card" v-for="em in emulators" :key="em.id">
        <div class="emulator-icon">
          <i class="el-icon-monitor"></i>
        </div>
        <div class="emulator-info">
          <h3>{{ em.model }}</h3>
          <p>{{ locale === 'zh' ? em.titleZh : em.titleEn }}</p>
          <a :href="em.emulatorUrl" target="_blank" class="btn-primary">Try Emulator</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const { locale } = useI18n()
const { request } = useHttp()

const { data, pending } = request('/api/support/webEmulator/list', {
  lazy: true,
  query: { status: 'published' }
})

const emulators = computed(() => {
  if (data.value && data.value.rows) return data.value.rows
  return [
    { id: 1, model: 'WR1200', titleZh: '千兆双频路由 Web 界面', titleEn: 'AC1200 Web Interface', emulatorUrl: '#' },
    { id: 2, model: 'AX3000', titleZh: 'Wi-Fi 6 路由 Web 界面', titleEn: 'AX3000 Web Interface', emulatorUrl: '#' }
  ]
})

useHead({ title: 'Web Emulators' })
</script>

<style scoped>
.emulators-page {
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
.emulators-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 30px;
}
.emulator-card {
  display: flex;
  align-items: center;
  background: #fdfdfd;
  border: 1px solid #eaeaea;
  padding: 25px;
  border-radius: 8px;
  transition: transform 0.3s, box-shadow 0.3s;
}
.emulator-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0,0,0,0.05);
  border-color: var(--color-primary);
}
.emulator-icon {
  width: 60px;
  height: 60px;
  background: #f0f7ff;
  color: var(--color-primary);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 2rem;
  margin-right: 20px;
  flex-shrink: 0;
}
.emulator-info h3 {
  margin: 0 0 10px 0;
  font-size: 1.3rem;
  color: #333;
}
.emulator-info p {
  color: #666;
  margin-bottom: 20px;
  font-size: 0.95rem;
}
.btn-primary {
  display: inline-block;
  padding: 8px 20px;
  background: var(--color-primary);
  color: #fff;
  text-decoration: none;
  border-radius: 4px;
  font-size: 0.95rem;
  transition: background 0.3s;
}
.btn-primary:hover {
  background: var(--color-secondary);
}
</style>
