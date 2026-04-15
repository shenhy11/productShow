<template>
  <div class="product-categories container">
    <div class="section-header">
      <h2>{{ $t('nav.products') }}</h2>
      <NuxtLink :to="localePath('/products')" class="view-all">{{ $t('common.viewMore') }}</NuxtLink>
    </div>
    
    <div v-if="pending" class="loading">Loading...</div>
    <div v-else class="category-grid">
      <NuxtLink 
        v-for="cat in categoryList" 
        :key="cat.id" 
        :to="localePath(`/products?category=${cat.id}`)"
        class="category-card"
      >
        <div class="cat-icon">
          <img :src="cat.icon || 'https://picsum.photos/100/100?random=' + cat.id" :alt="cat.nameZh">
        </div>
        <div class="cat-name">{{ cat.nameZh }}</div>
      </NuxtLink>
    </div>
  </div>
</template>

<script setup>
const localePath = useLocalePath()
const { request } = useHttp()

// 查询分类接口 (默认展示前8个热门分类)
const { data, pending } = request('/api/product/category/list', {
  lazy: true,
  query: { pageSize: 8 }
})

const categoryList = computed(() => {
  if (data.value && data.value.data) {
    return data.value.data.slice(0, 8)
  }
  // Mock fallback
  return [
    { id: 1, nameZh: '无线路由器', icon: '' },
    { id: 2, nameZh: '无线适配器', icon: '' },
    { id: 3, nameZh: '中继器', icon: '' },
    { id: 4, nameZh: '智能路由', icon: '' }
  ]
})
</script>

<style scoped>
.product-categories {
  padding: 60px 15px;
  background-color: #f9f9f9;
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
.category-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
}
.category-card {
  background: #fff;
  border-radius: 8px;
  padding: 30px 20px;
  text-align: center;
  text-decoration: none;
  color: #333;
  transition: transform 0.3s, box-shadow 0.3s;
  border: 1px solid #eaeaea;
}
.category-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0,0,0,0.05);
  border-color: var(--color-primary);
}
.cat-icon img {
  width: 80px;
  height: 80px;
  object-fit: contain;
  margin-bottom: 15px;
}
.cat-name {
  font-weight: 500;
  font-size: 1.1rem;
}
</style>
