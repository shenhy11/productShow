<template>
  <div class="download-page container">
    <div class="page-header">
      <h1>Download Center</h1>
      <p>Search by product model to find firmware and manuals</p>
      
      <div class="search-box">
        <input type="text" v-model="searchInput" @keyup.enter="handleSearch" placeholder="Enter product model (e.g. WR1200)" />
        <button @click="handleSearch">Search</button>
      </div>
    </div>
    
    <div v-if="hasSearched">
      <div v-if="pending" class="loading">Searching...</div>
      <div v-else-if="!productId" class="no-data">No product found for "{{ searchKeyword }}".</div>
      <div v-else>
        <h2>Results for "{{ searchKeyword }}"</h2>
        
        <div class="result-section">
          <h3>Firmware</h3>
          <ul class="download-list" v-if="firmwares.length > 0">
            <li v-for="fw in firmwares" :key="'fw-'+fw.id">
              <div class="info">
                <strong>{{ fw.version }}</strong>
                <span class="date">{{ fw.releaseDate }}</span>
              </div>
              <a :href="fw.fileUrl" target="_blank" class="btn-download">Download</a>
            </li>
          </ul>
          <p v-else class="text-muted">No firmware available.</p>
        </div>
        
        <div class="result-section mt-4">
          <h3>Documents</h3>
          <ul class="download-list" v-if="documents.length > 0">
            <li v-for="doc in documents" :key="'doc-'+doc.id">
              <div class="info">
                <strong>{{ locale === 'zh' ? doc.titleZh : doc.titleEn }}</strong>
              </div>
              <a :href="doc.fileUrl" target="_blank" class="btn-download">Download</a>
            </li>
          </ul>
          <p v-else class="text-muted">No documents available.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const { locale } = useI18n()
const { request } = useHttp()

const searchInput = ref('')
const searchKeyword = ref('')
const hasSearched = ref(false)
const pending = ref(false)

const productId = ref(null)
const firmwares = ref([])
const documents = ref([])

async function handleSearch() {
  if (!searchInput.value.trim()) return
  
  searchKeyword.value = searchInput.value.trim()
  hasSearched.value = true
  pending.value = true
  productId.value = null
  firmwares.value = []
  documents.value = []
  
  try {
    // 1. 根据 model 搜索 product 找到 productId (也可以由后端直接提供聚合接口)
    const { data: prodRes } = await request('/api/product/product/list', {
      query: { keyword: searchKeyword.value }
    })
    
    const pList = prodRes.value?.rows
    if (pList && pList.length > 0) {
      // 默认取第一个匹配的产品
      productId.value = pList[0].id
      
      // 2. 查固件
      const { data: fwRes } = await request('/api/support/firmware/list', {
        query: { productId: productId.value, status: 'published' }
      })
      if (fwRes.value?.rows) firmwares.value = fwRes.value.rows
      
      // 3. 查文档
      const { data: docRes } = await request('/api/support/document/list', {
        query: { productId: productId.value, status: 'published' }
      })
      if (docRes.value?.rows) documents.value = docRes.value.rows
    }
  } catch (err) {
    console.error(err)
  } finally {
    pending.value = false
  }
}

useHead({ title: 'Download Center' })
</script>

<style scoped>
.download-page {
  padding: 40px 15px;
  max-width: 800px;
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
.search-box {
  display: flex;
  max-width: 500px;
  margin: 30px auto;
}
.search-box input {
  flex: 1;
  padding: 12px 15px;
  border: 1px solid #ccc;
  border-radius: 4px 0 0 4px;
  font-size: 1rem;
  outline: none;
}
.search-box button {
  padding: 12px 25px;
  background: var(--color-primary);
  color: #fff;
  border: none;
  font-size: 1rem;
  border-radius: 0 4px 4px 0;
  cursor: pointer;
}
.result-section h3 {
  border-bottom: 2px solid var(--color-primary);
  padding-bottom: 10px;
  margin-bottom: 20px;
  color: var(--color-primary);
}
.download-list {
  list-style: none;
  padding: 0;
  margin: 0;
}
.download-list li {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  border: 1px solid #eaeaea;
  margin-bottom: 15px;
  border-radius: 4px;
  background: #fdfdfd;
}
.info strong {
  display: block;
  font-size: 1.1rem;
  margin-bottom: 5px;
}
.date {
  color: #999;
  font-size: 0.9rem;
}
.btn-download {
  padding: 8px 20px;
  background: var(--color-primary);
  color: #fff;
  text-decoration: none;
  border-radius: 4px;
  transition: background 0.3s;
}
.btn-download:hover {
  background: var(--color-secondary);
}
.mt-4 {
  margin-top: 40px;
}
.text-muted {
  color: #999;
}
</style>
