<template>
  <div class="products-page container">
    <div class="page-header">
      <h1>{{ searchKeyword ? `Search Results for "${searchKeyword}"` : $t('nav.products') }}</h1>
      <div class="search-bar">
        <input type="text" v-model="searchInput" @keyup.enter="handleSearch" :placeholder="$t('common.search')" />
        <button @click="handleSearch">{{ $t('common.search') }}</button>
      </div>
    </div>
    
    <div class="layout-main">
      <aside class="sidebar">
        <h3>Categories</h3>
        <ul class="category-list">
          <li :class="{ active: !selectedCategory }" @click="setCategory(null)">All Products</li>
          <li 
            v-for="cat in categories" 
            :key="cat.id" 
            :class="{ active: selectedCategory === cat.id }"
            @click="setCategory(cat.id)"
          >
            {{ locale === 'zh' ? cat.nameZh : cat.nameEn }}
          </li>
        </ul>
      </aside>
      
      <div class="product-content">
        <div v-if="pending" class="loading">Loading products...</div>
        <div v-else-if="products.length === 0" class="no-data">No products found.</div>
        <div v-else class="product-grid">
          <ProductCard v-for="item in products" :key="item.id" :product="item" />
        </div>
        
        <!-- 分页占位 -->
        <div class="pagination" v-if="total > 0">
           <span>Total {{ total }} items</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const route = useRoute()
const router = useRouter()
const { locale } = useI18n()
const localePath = useLocalePath()
const { request } = useHttp()

const searchInput = ref(route.query.keyword || '')
const searchKeyword = computed(() => route.query.keyword || '')
const selectedCategory = computed(() => route.query.category ? Number(route.query.category) : null)

// 获取分类
const { data: catData } = await request('/api/product/category/list')
const categories = computed(() => catData.value?.data || [
  { id: 1, nameZh: '无线路由器', nameEn: 'Wireless Router' },
  { id: 2, nameZh: '无线适配器', nameEn: 'Wireless Adapter' }
])

// 获取产品列表
const { data: prodData, pending, refresh } = await request('/api/product/product/list', {
  query: { 
    keyword: searchKeyword,
    categoryId: selectedCategory,
    status: 'published'
  }
})

const products = computed(() => {
  if (prodData.value?.rows) return prodData.value.rows
  // Mock data fallback
  return [
    { id: 101, model: 'WR1200', nameZh: '千兆双频无线路由器', nameEn: 'AC1200 Dual-Band Router', summaryZh: '1200Mbps 高速网络', summaryEn: '1200Mbps High Speed' },
    { id: 102, model: 'AX3000', nameZh: 'Wi-Fi 6 无线路由器', nameEn: 'Wi-Fi 6 Router', summaryZh: '最新一代Wi-Fi标准', summaryEn: 'Next-gen Wi-Fi 6 standard' }
  ]
})
const total = computed(() => prodData.value?.total || 0)

function handleSearch() {
  const query = { ...route.query }
  if (searchInput.value.trim()) {
    query.keyword = searchInput.value.trim()
  } else {
    delete query.keyword
  }
  router.push({ query })
}

function setCategory(id) {
  const query = { ...route.query }
  if (id) {
    query.category = id
  } else {
    delete query.category
  }
  router.push({ query })
}

watch(() => route.query, () => {
  refresh()
}, { deep: true })

useHead({ title: 'Products' })
</script>

<style scoped>
.products-page {
  padding: 40px 15px;
}
.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 40px;
  border-bottom: 1px solid #eaeaea;
  padding-bottom: 20px;
}
.page-header h1 {
  margin: 0;
}
.search-bar {
  display: flex;
}
.search-bar input {
  padding: 8px 15px;
  border: 1px solid #ddd;
  border-radius: 4px 0 0 4px;
  outline: none;
}
.search-bar button {
  padding: 8px 20px;
  background: var(--color-primary);
  color: #fff;
  border: none;
  border-radius: 0 4px 4px 0;
  cursor: pointer;
}
.layout-main {
  display: flex;
  gap: 40px;
}
.sidebar {
  width: 250px;
  flex-shrink: 0;
}
.sidebar h3 {
  margin-top: 0;
  border-bottom: 2px solid #eaeaea;
  padding-bottom: 10px;
}
.category-list {
  list-style: none;
  padding: 0;
  margin: 0;
}
.category-list li {
  padding: 10px 15px;
  cursor: pointer;
  border-bottom: 1px solid #f5f5f5;
  transition: background 0.3s;
}
.category-list li:hover {
  background: #f9f9f9;
}
.category-list li.active {
  color: var(--color-primary);
  font-weight: bold;
  background: #f0f7ff;
  border-left: 3px solid var(--color-primary);
}
.product-content {
  flex: 1;
}
.product-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 30px;
}
.pagination {
  margin-top: 40px;
  text-align: center;
  color: #666;
}

@media screen and (max-width: 767px) {
  .layout-main {
    flex-direction: column;
  }
  .sidebar {
    width: 100%;
  }
  .page-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 15px;
  }
}
</style>
