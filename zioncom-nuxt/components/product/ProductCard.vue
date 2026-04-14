<template>
  <NuxtLink :to="localePath(`/products/${product.id}`)" class="product-card">
    <div class="product-img" :style="{ backgroundImage: `url(${product.coverImage || 'https://picsum.photos/300/300?random=' + product.id})` }"></div>
    <div class="product-info">
      <div class="product-model">{{ product.model }}</div>
      <h3 class="product-name">{{ locale === 'zh' ? product.nameZh : product.nameEn }}</h3>
      <p class="product-summary">{{ locale === 'zh' ? product.summaryZh : product.summaryEn }}</p>
    </div>
  </NuxtLink>
</template>

<script setup>
const localePath = useLocalePath()
const { locale } = useI18n()
defineProps({
  product: {
    type: Object,
    required: true
  }
})
</script>

<style scoped>
.product-card {
  display: block;
  border: 1px solid #eaeaea;
  border-radius: 8px;
  overflow: hidden;
  text-decoration: none;
  color: #333;
  transition: transform 0.3s, box-shadow 0.3s;
  background: #fff;
}
.product-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0,0,0,0.1);
  border-color: var(--color-primary);
}
.product-img {
  width: 100%;
  padding-bottom: 75%; /* 4:3 Aspect Ratio */
  background-size: cover;
  background-position: center;
  background-color: #f7f7f7;
}
.product-info {
  padding: 20px;
}
.product-model {
  font-size: 0.9rem;
  color: var(--color-primary);
  font-weight: bold;
  margin-bottom: 8px;
}
.product-name {
  margin: 0 0 10px 0;
  font-size: 1.2rem;
  line-height: 1.4;
  height: 2.8em;
  overflow: hidden;
}
.product-summary {
  font-size: 0.95rem;
  color: #666;
  margin: 0;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
