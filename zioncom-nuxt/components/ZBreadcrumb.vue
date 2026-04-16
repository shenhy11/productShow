<template>
  <nav class="breadcrumb-container" aria-label="Breadcrumb">
    <ol class="breadcrumb-list">
      <li class="breadcrumb-item">
        <NuxtLink :to="localePath('/')" class="breadcrumb-link">{{ $t('nav.home') || 'Home' }}</NuxtLink>
      </li>
      
      <li v-for="(crumb, index) in crumbs" :key="index" class="breadcrumb-item">
        <span class="separator">/</span>
        <NuxtLink 
          v-if="index < crumbs.length - 1" 
          :to="crumb.to" 
          class="breadcrumb-link"
        >
          {{ crumb.label }}
        </NuxtLink>
        <span v-else class="breadcrumb-active" aria-current="page">
          {{ crumb.label }}
        </span>
      </li>
    </ol>
  </nav>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const localePath = useLocalePath()

const crumbs = computed(() => {
  const pathArray = route.path.split('/').filter(p => p && p !== 'en' && p !== 'zh')
  
  return pathArray.map((pathSegment, index) => {
    // Generate the URL for this segment
    const segmentPath = '/' + pathArray.slice(0, index + 1).join('/')
    
    // Attempt basic capitalizing logic or mapping here 
    // In a real scenario we might map 'wireless-router' -> 'Wireless Router'
    const formatLabel = (str: string) => {
      let raw = str.replace(/-/g, ' ')
      return raw.charAt(0).toUpperCase() + raw.slice(1)
    }

    return {
      to: localePath(segmentPath),
      label: formatLabel(pathSegment)
    }
  })
})
</script>

<style scoped>
.breadcrumb-container {
  padding: var(--spacing-4) 0;
  margin-bottom: var(--spacing-4);
}

.breadcrumb-list {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  list-style: none;
  padding: 0;
  margin: 0;
  font-size: var(--text-sm);
}

.breadcrumb-item {
  display: inline-flex;
  align-items: center;
}

.breadcrumb-link {
  color: #666;
  text-decoration: none;
  transition: color var(--transition-fast);
}

.breadcrumb-link:hover {
  color: var(--color-primary);
  text-decoration: underline;
}

.separator {
  margin: 0 var(--spacing-2);
  color: #ccc;
}

.breadcrumb-active {
  color: var(--color-gray-900);
  font-weight: var(--font-semibold);
}
</style>
