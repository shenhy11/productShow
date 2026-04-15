<template>
  <div class="milestones-page container">
    <div class="page-header">
      <h1>Company Milestones</h1>
      <p>A journey of continuous innovation</p>
    </div>
    
    <div class="timeline">
      <div v-if="pending" class="loading">Loading milestones...</div>
      <div v-else-if="milestones.length === 0" class="no-data">No milestones available.</div>
      <div v-else class="timeline-item" v-for="ms in milestones" :key="ms.id">
        <div class="timeline-year">{{ ms.year }}</div>
        <div class="timeline-content">
          <h3>{{ locale === 'zh' ? ms.titleZh : ms.titleEn }}</h3>
          <p>{{ locale === 'zh' ? ms.descriptionZh : ms.descriptionEn }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const { locale } = useI18n()
const { request } = useHttp()

const { data, pending } = request('/api/brand/milestone/list', {
  lazy: true,
  query: { orderByColumn: 'year', isAsc: 'desc', status: 'published' }
})

const milestones = computed(() => {
  if (data.value && data.value.rows) return data.value.rows
  return [
    { id: 1, year: '2026', titleZh: '发布 Wi-Fi 7 旗舰产品', titleEn: 'Released Wi-Fi 7 Flagship Product', descriptionZh: '开创下一代无线网络新纪元。', descriptionEn: 'A new era of next-gen wireless networks.' },
    { id: 2, year: '2024', titleZh: '建立全球云管理平台', titleEn: 'Established Global Cloud Management Platform', descriptionZh: '实现千万级设备在线管理。', descriptionEn: 'Managed millions of devices online.' },
    { id: 3, year: '2019', titleZh: '成立越南制造基地', titleEn: 'Setup Vietnam Manufacturing Base', descriptionZh: '进一步扩展全球产能。', descriptionEn: 'Expanded global production capacity.' },
    { id: 4, year: '1999', titleZh: 'ZIONCOM 成立', titleEn: 'ZIONCOM Founded', descriptionZh: '在深圳开启网络设备研发之旅。', descriptionEn: 'Started networking device R&D in Shenzhen.' }
  ]
})

useHead({ title: 'Milestones' })
</script>

<style scoped>
.milestones-page {
  padding: 40px 15px;
  max-width: 800px;
}
.page-header {
  text-align: center;
  margin-bottom: 50px;
}
.page-header h1 {
  font-size: 2.5rem;
  color: var(--color-primary);
  margin-bottom: 10px;
}
.timeline {
  position: relative;
  padding: 20px 0;
}
.timeline::before {
  content: '';
  position: absolute;
  top: 0;
  bottom: 0;
  left: 120px;
  width: 2px;
  background: var(--color-primary);
}
.timeline-item {
  display: flex;
  margin-bottom: 40px;
  position: relative;
}
.timeline-year {
  width: 120px;
  flex-shrink: 0;
  font-size: 2rem;
  font-weight: bold;
  color: var(--color-primary);
  padding-right: 30px;
  text-align: right;
  position: relative;
}
.timeline-year::after {
  content: '';
  position: absolute;
  top: 15px;
  right: -9px;
  width: 16px;
  height: 16px;
  background: #fff;
  border: 4px solid var(--color-primary);
  border-radius: 50%;
  box-sizing: border-box;
}
.timeline-content {
  flex: 1;
  padding-left: 30px;
  padding-top: 10px;
}
.timeline-content h3 {
  margin: 0 0 10px 0;
  font-size: 1.5rem;
  color: #333;
}
.timeline-content p {
  margin: 0;
  color: #666;
  line-height: 1.6;
}

@media screen and (max-width: 767px) {
  .timeline::before {
    left: 20px;
  }
  .timeline-year {
    width: auto;
    font-size: 1.5rem;
    padding-right: 0;
    text-align: left;
    margin-bottom: 10px;
  }
  .timeline-year::after {
    top: 5px;
    left: 14px;
    right: auto;
  }
  .timeline-item {
    flex-direction: column;
    padding-left: 40px;
  }
  .timeline-content {
    padding-left: 0;
  }
}
</style>
