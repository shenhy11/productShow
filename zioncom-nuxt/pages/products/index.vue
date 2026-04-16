<template>
  <div class="products-page container">
    <div class="page-header">
      <h1>{{ searchKeyword ? $t('product.searchResultsFor', { keyword: searchKeyword }) : $t('nav.products') }}</h1>
      <div class="search-bar">
        <input type="text" v-model="searchInput" @keyup.enter="handleSearch" :placeholder="$t('common.search')" />
        <button @click="handleSearch">{{ $t('common.search') }}</button>
      </div>
    </div>
    
    <div class="layout-main">
      <aside class="sidebar">
        <h3>{{ $t('product.categories') }}</h3>
        <ul class="category-list">
          <li :class="{ active: !selectedCategory }" @click="setCategory(null)">{{ $t('product.allProducts') }}</li>
          <li 
            v-for="cat in categories" 
            :key="cat.id" 
            :class="{ active: selectedCategory === cat.id }"
            @click="setCategory(cat.id)"
          >
            {{ locale === 'zh' ? cat.nameZh : (cat.nameEn || cat.name) }}
          </li>
        </ul>
      </aside>
      
      <div class="product-content">
        <div v-if="pending" class="loading">{{ $t('product.loadingProducts') }}</div>
        <div v-else-if="products.length === 0" class="no-data">{{ $t('product.noProducts') }}</div>
        <div v-else class="product-grid">
          <ProductCard v-for="item in products" :key="item.id" :product="item" />
        </div>
        
        <!-- 分页占位 -->
        <div class="pagination" v-if="total > 0">
           <span>{{ $t('product.totalItems', { count: total }) }}</span>
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
const selectedCategory = computed(() => route.query.category ? String(route.query.category) : null)

// 获取分类
const { data: catData } = useFetch<Array<{id: string, name: string, icon: string}>>('/mock/categories.json', { lazy: true })
const categories = computed(() => catData.value || [])

// 获取静态产品列表来进行模拟
const { data: allProdData, pending } = useFetch('/mock/products.json', { lazy: true })

const products = computed(() => {
  if (!allProdData.value || !allProdData.value.data) return []
  
  let list = allProdData.value.data.rows
  
  if (selectedCategory.value) {
    list = list.filter(p => p.categoryId === selectedCategory.value)
  }
  
  if (searchKeyword.value) {
    const kw = searchKeyword.value.toLowerCase()
    list = list.filter(p => 
      p.model.toLowerCase().includes(kw) || 
      p.nameZh.toLowerCase().includes(kw) || 
      p.nameEn.toLowerCase().includes(kw)
    )
  }
  
  return list
})

const total = computed(() => products.value.length)

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

// Auto reactivity through computed props removes the need for explicit watch refresh of useFetch in this mock scenario

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
