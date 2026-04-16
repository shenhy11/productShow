<template>
  <NuxtLink :to="localePath(`/products/${product.id}`)" class="product-card">
    <div class="product-img" :style="{ backgroundImage: `url(${product.image || product.coverImage || 'https://picsum.photos/300/300?random=' + product.id})` }"></div>
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
  display: flex;
  flex-direction: column;
  text-decoration: none;
  color: var(--color-gray-900);
  background: var(--glass-bg);
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border: var(--glass-border);
  box-shadow: 0 4px 6px rgba(0,0,0,0.02);
  border-radius: var(--radius-lg);
  overflow: hidden;
  transition: transform var(--transition-normal), box-shadow var(--transition-normal), border-color var(--transition-normal);
}
.product-card:hover {
  transform: translateY(-8px);
  box-shadow: var(--glass-shadow-dark);
  border-color: var(--color-primary);
}
.product-img {
  width: 100%;
  padding-bottom: 75%; /* 4:3 Aspect Ratio */
  background-size: cover;
  background-position: center;
  background-color: var(--color-white);
  border-bottom: var(--glass-border);
}
.product-info {
  padding: var(--spacing-4);
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}
.product-model {
  font-size: var(--text-sm);
  color: var(--color-primary);
  font-weight: var(--font-bold);
  margin-bottom: var(--spacing-2);
  text-transform: uppercase;
  letter-spacing: 0.05em;
}
.product-name {
  margin: 0 0 var(--spacing-2) 0;
  font-size: var(--text-lg);
  font-weight: var(--font-semibold);
  line-height: 1.4;
  height: 2.8em;
  overflow: hidden;
}
.product-summary {
  font-size: var(--text-sm);
  color: #666;
  margin: 0;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
