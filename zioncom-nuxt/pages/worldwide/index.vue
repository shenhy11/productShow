<template>
  <div class="worldwide-page container">
    <div class="page-header">
      <h1>Worldwide Partners</h1>
      <p>Find ZIONCOM products and services around the globe.</p>
    </div>
    
    <div v-if="pending" class="loading">Loading partners...</div>
    <div v-else-if="partners.length === 0" class="no-data">No partners found.</div>
    <div v-else class="regions-container">
      <div v-for="(partnersInRegion, regionName) in groupedPartners" :key="regionName" class="region-block">
        <h2 class="region-title">{{ regionName }}</h2>
        <div class="partners-grid">
          <div class="partner-card" v-for="partner in partnersInRegion" :key="partner.id">
            <h3>{{ partner.name }}</h3>
            <p class="country"><i class="el-icon-location"></i> {{ partner.country }}</p>
            <p v-if="partner.contactEmail" class="contact">Email: <a :href="`mailto:${partner.contactEmail}`">{{ partner.contactEmail }}</a></p>
            <p v-if="partner.contactPhone" class="contact">Phone: {{ partner.contactPhone }}</p>
            <p v-if="partner.website" class="contact">Web: <a :href="partner.website" target="_blank">{{ partner.website }}</a></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const { request } = useHttp()

const { data, pending } = request('/api/brand/partner/list', {
  lazy: true,
  query: { status: 'published' }
})

const partners = computed(() => {
  if (data.value && data.value.rows) return data.value.rows
  return [
    { id: 1, region: 'Asia', country: 'Vietnam', name: 'Zioncom Vietnam Co.,Ltd', contactEmail: 'sales@zioncom.net', website: 'https://www.zioncom.net' },
    { id: 2, region: 'Asia', country: 'Korea', name: 'ZionKorea', contactEmail: 'korea@zioncom.net', website: 'https://korea.zioncom.net' },
    { id: 3, region: 'Europe', country: 'Germany', name: 'EU Tech Distributors', contactEmail: 'eu@example.com' },
    { id: 4, region: 'America', country: 'USA', name: 'US Net Solutions', contactEmail: 'us@example.com' },
  ]
})

const groupedPartners = computed(() => {
  const groups = {}
  partners.value.forEach(p => {
    const region = p.region || 'Others'
    if (!groups[region]) {
      groups[region] = []
    }
    groups[region].push(p)
  })
  return groups
})

useHead({ title: 'Worldwide Partners' })
</script>

<style scoped>
.worldwide-page {
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
.region-block {
  margin-bottom: 50px;
}
.region-title {
  font-size: 1.8rem;
  color: var(--color-primary);
  border-bottom: 2px solid #eaeaea;
  padding-bottom: 15px;
  margin-bottom: 30px;
}
.partners-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 30px;
}
.partner-card {
  background: #fdfdfd;
  border: 1px solid #eaeaea;
  padding: 25px;
  border-radius: 8px;
  transition: transform 0.3s, box-shadow 0.3s;
}
.partner-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0,0,0,0.05);
  border-color: var(--color-primary);
}
.partner-card h3 {
  margin: 0 0 15px 0;
  font-size: 1.3rem;
  color: #333;
}
.country {
  font-weight: bold;
  color: #555;
  margin-bottom: 15px;
}
.contact {
  font-size: 0.95rem;
  color: #666;
  margin: 5px 0;
}
.contact a {
  color: var(--color-primary);
  text-decoration: none;
}
.contact a:hover {
  text-decoration: underline;
}
</style>
