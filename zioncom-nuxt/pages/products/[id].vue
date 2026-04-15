<template>
  <div class="product-detail container">
    <div v-if="pending" class="loading">Loading product details...</div>
    <div v-else-if="!product" class="no-data">Product not found.</div>
    <div v-else>
      <div class="product-top">
        <div class="product-gallery">
          <img :src="product.coverImage || 'https://picsum.photos/600/600?random=' + product.id" :alt="product.model">
        </div>
        <div class="product-info">
          <h1>{{ product.model }}</h1>
          <h2>{{ locale === 'zh' ? product.nameZh : product.nameEn }}</h2>
          <p class="summary">{{ locale === 'zh' ? product.summaryZh : product.summaryEn }}</p>
          
          <div class="quick-actions">
            <a href="#specs" class="action-btn">Specifications</a>
            <a href="#downloads" class="action-btn outline">Download Center</a>
          </div>
        </div>
      </div>
      
      <div class="product-tabs">
        <div class="tabs-header">
          <div :class="{ active: activeTab === 'overview' }" @click="activeTab = 'overview'">Overview</div>
          <div :class="{ active: activeTab === 'specs' }" @click="activeTab = 'specs'" id="specs">Specifications</div>
          <div :class="{ active: activeTab === 'downloads' }" @click="activeTab = 'downloads'" id="downloads">Downloads</div>
        </div>
        
        <div class="tab-content" v-show="activeTab === 'overview'">
          <div class="rich-text" v-html="locale === 'zh' ? product.detailZh : product.detailEn"></div>
        </div>
        
        <div class="tab-content" v-show="activeTab === 'specs'">
          <table class="spec-table">
            <tbody>
              <tr v-for="(spec, index) in specs" :key="index">
                <td class="spec-key">{{ spec.specKey }}</td>
                <td class="spec-val">{{ spec.specValue }}</td>
              </tr>
            </tbody>
          </table>
          <p v-if="!specs.length" class="text-muted">No specifications available.</p>
        </div>
        
        <div class="tab-content" v-show="activeTab === 'downloads'">
          <h3>Firmware</h3>
          <ul class="download-list">
            <li v-for="fw in firmwares" :key="'fw-'+fw.id">
              <span>{{ fw.version }} <small>({{ fw.releaseDate }})</small></span>
              <a :href="fw.fileUrl" target="_blank" class="download-btn">Download</a>
            </li>
          </ul>
          <p v-if="!firmwares.length" class="text-muted">No firmware available.</p>
          
          <h3 class="mt-4">Documents</h3>
          <ul class="download-list">
            <li v-for="doc in documents" :key="'doc-'+doc.id">
              <span>{{ locale === 'zh' ? doc.titleZh : doc.titleEn }}</span>
              <a :href="doc.fileUrl" target="_blank" class="download-btn">Download</a>
            </li>
          </ul>
          <p v-if="!documents.length" class="text-muted">No documents available.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const route = useRoute()
const { locale } = useI18n()
const { request } = useHttp()
const productId = route.params.id

const activeTab = ref('overview')

const { data: prodRes, pending } = request(`/api/product/product/${productId}`, { lazy: true })
const product = computed(() => {
  if (prodRes.value?.data) return prodRes.value.data
  return { id: productId, model: 'WR1200', nameZh: '千兆双频路由', nameEn: 'AC1200 Router', summaryZh: '1200M', detailZh: '<p>Overview</p>' }
})

// Specs 列表接口（由于这里生成的是标准CRUD，故手动调list接口带productId参数）
const { data: specRes } = request('/api/product/spec/list', { lazy: true, query: { productId } })
const specs = computed(() => specRes.value?.rows || [
  { specKey: 'Standards', specValue: 'IEEE 802.11ac, IEEE 802.11n, IEEE 802.11g, IEEE 802.11b, IEEE 802.11a' },
  { specKey: 'Ports', specValue: '4*1000Mbps LAN Ports, 1*1000Mbps WAN Port' }
])

// 固件接口
const { data: fwRes } = request('/api/support/firmware/list', { lazy: true, query: { productId } })
const firmwares = computed(() => fwRes.value?.rows || [
  { id: 1, version: 'v1.0.2', releaseDate: '2026-01-10', fileUrl: '#' }
])

// 文档接口
const { data: docRes } = request('/api/support/document/list', { lazy: true, query: { productId } })
const documents = computed(() => docRes.value?.rows || [
  { id: 1, titleZh: '快速安装指南', titleEn: 'Quick Installation Guide', fileUrl: '#' }
])

useHead(() => ({
  title: product.value?.model || 'Product Details'
}))
</script>

<style scoped>
.product-detail {
  padding: 40px 15px;
}
.product-top {
  display: flex;
  gap: 50px;
  margin-bottom: 50px;
}
.product-gallery {
  flex: 1;
  max-width: 500px;
}
.product-gallery img {
  width: 100%;
  border-radius: 8px;
  border: 1px solid #eaeaea;
}
.product-info {
  flex: 1;
}
.product-info h1 {
  font-size: 2.5rem;
  color: var(--color-primary);
  margin-top: 0;
}
.product-info h2 {
  font-size: 1.5rem;
  color: #333;
  margin-bottom: 20px;
}
.summary {
  font-size: 1.1rem;
  line-height: 1.6;
  color: #666;
  margin-bottom: 40px;
}
.action-btn {
  display: inline-block;
  padding: 12px 30px;
  background: var(--color-primary);
  color: #fff;
  text-decoration: none;
  font-weight: bold;
  border-radius: 4px;
  margin-right: 15px;
  transition: all 0.3s;
}
.action-btn.outline {
  background: transparent;
  color: var(--color-primary);
  border: 2px solid var(--color-primary);
  padding: 10px 28px;
}
.action-btn:hover {
  background: var(--color-secondary);
  color: #fff;
}
.product-tabs {
  margin-top: 60px;
}
.tabs-header {
  display: flex;
  border-bottom: 2px solid #eaeaea;
  margin-bottom: 30px;
}
.tabs-header div {
  padding: 15px 30px;
  cursor: pointer;
  font-size: 1.2rem;
  font-weight: 500;
  color: #666;
  border-bottom: 2px solid transparent;
  margin-bottom: -2px;
}
.tabs-header div.active {
  color: var(--color-primary);
  border-bottom-color: var(--color-primary);
}
.tab-content {
  min-height: 300px;
}
.spec-table {
  width: 100%;
  border-collapse: collapse;
}
.spec-table td {
  padding: 15px;
  border-bottom: 1px solid #eaeaea;
}
.spec-key {
  width: 30%;
  font-weight: bold;
  background: #f9f9f9;
  color: #333;
}
.download-list {
  list-style: none;
  padding: 0;
}
.download-list li {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  border-bottom: 1px solid #eaeaea;
}
.download-btn {
  color: var(--color-primary);
  text-decoration: none;
  font-weight: bold;
}
.download-btn:hover {
  text-decoration: underline;
}
.mt-4 {
  margin-top: 30px;
}
.text-muted {
  color: #999;
}

@media screen and (max-width: 767px) {
  .product-top {
    flex-direction: column;
  }
  .tabs-header {
    overflow-x: auto;
    white-space: nowrap;
  }
}
</style>
