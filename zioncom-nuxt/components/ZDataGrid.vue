<template>
  <div class="data-grid glass">
    <div v-if="title" class="data-grid-header">
      <h2 class="data-grid-title">{{ title }}</h2>
      <slot name="actions"></slot>
    </div>
    
    <div class="data-grid-table-wrapper">
      <table class="data-grid-table">
        <thead>
          <tr>
            <th v-for="col in columns" :key="col.key" :style="{ width: col.width }">
              {{ col.label }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-if="!data || data.length === 0" class="empty-row">
            <td :colspan="columns.length" class="text-center p-8">
              No data available
            </td>
          </tr>
          <tr v-for="(row, idx) in data" :key="idx" class="data-row">
            <td v-for="col in columns" :key="col.key">
              <slot :name="col.key" :row="row">
                {{ row[col.key] }}
              </slot>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup lang="ts">
interface Column {
  key: string
  label: string
  width?: string
}

withDefaults(defineProps<{
  title?: string
  columns: Column[]
  data?: any[]
}>(), {
  title: '',
  data: () => []
})
</script>

<style scoped>
.data-grid {
  width: 100%;
  overflow: hidden;
}

.data-grid-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: var(--spacing-4);
  border-bottom: 1px solid rgba(0, 0, 0, 0.05);
}

.data-grid-title {
  margin: 0;
  font-size: var(--text-lg);
  font-weight: var(--font-semibold);
  color: var(--color-gray-900);
}

.data-grid-table-wrapper {
  overflow-x: auto;
}

.data-grid-table {
  width: 100%;
  border-collapse: collapse;
  text-align: left;
}

.data-grid-table th {
  padding: var(--spacing-4);
  font-weight: var(--font-semibold);
  color: var(--color-gray-800);
  background: var(--color-gray-100);
  border-bottom: 1px solid var(--color-gray-200);
  white-space: nowrap;
}

.data-grid-table td {
  padding: var(--spacing-4);
  color: var(--color-gray-900);
  border-bottom: 1px solid var(--color-gray-200);
  vertical-align: top;
}

.data-row:hover td {
  background: rgba(0, 0, 0, 0.02);
}

.empty-row td {
  color: #999;
  font-style: italic;
}
</style>
