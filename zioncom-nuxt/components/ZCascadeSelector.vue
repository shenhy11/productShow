<template>
  <div class="cascade-selector flex flex-col md:flex-row gap-4 w-full">
    <!-- Category 1 -->
    <div class="select-wrapper flex-1">
      <select v-model="selectedLevel1" @change="onLevel1Change" class="form-select">
        <option value="" disabled>{{ placeholder1 }}</option>
        <option v-for="item in level1Options" :key="item.value" :value="item.value">
          {{ item.label }}
        </option>
      </select>
    </div>

    <!-- Category 2 -->
    <div class="select-wrapper flex-1">
      <select v-model="selectedLevel2" @change="onLevel2Change" :disabled="!selectedLevel1 || level2Options.length === 0" class="form-select">
        <option value="" disabled>{{ placeholder2 }}</option>
        <option v-for="item in level2Options" :key="item.value" :value="item.value">
          {{ item.label }}
        </option>
      </select>
    </div>

    <!-- Category 3 -->
    <div class="select-wrapper flex-1">
      <select v-model="selectedLevel3" @change="onLevel3Change" :disabled="!selectedLevel2 || level3Options.length === 0" class="form-select">
        <option value="" disabled>{{ placeholder3 }}</option>
        <option v-for="item in level3Options" :key="item.value" :value="item.value">
          {{ item.label }}
        </option>
      </select>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, watch } from 'vue'

interface CascadeNode {
  label: string
  value: string
  children?: CascadeNode[]
}

const props = defineProps<{
  options: CascadeNode[]
  placeholder1?: string
  placeholder2?: string
  placeholder3?: string
  modelValue?: [string, string, string] // [level1, level2, level3]
}>()

const emit = defineEmits(['update:modelValue', 'change'])

const selectedLevel1 = ref(props.modelValue?.[0] || '')
const selectedLevel2 = ref(props.modelValue?.[1] || '')
const selectedLevel3 = ref(props.modelValue?.[2] || '')

const level1Options = computed(() => props.options || [])

const level2Options = computed(() => {
  const parent = level1Options.value.find(opt => opt.value === selectedLevel1.value)
  return parent?.children || []
})

const level3Options = computed(() => {
  const parent = level2Options.value.find(opt => opt.value === selectedLevel2.value)
  return parent?.children || []
})

function onLevel1Change() {
  selectedLevel2.value = ''
  selectedLevel3.value = ''
  emitChange()
}

function onLevel2Change() {
  selectedLevel3.value = ''
  emitChange()
}

function onLevel3Change() {
  emitChange()
}

function emitChange() {
  const val = [selectedLevel1.value, selectedLevel2.value, selectedLevel3.value]
  emit('update:modelValue', val)
  emit('change', val)
}

// Watch incoming value to reset if needed
watch(() => props.modelValue, (newVal) => {
  if (newVal) {
    if (selectedLevel1.value !== newVal[0]) selectedLevel1.value = newVal[0]
    if (selectedLevel2.value !== newVal[1]) selectedLevel2.value = newVal[1]
    if (selectedLevel3.value !== newVal[2]) selectedLevel3.value = newVal[2]
  }
}, { deep: true })
</script>

<style scoped>
.cascade-selector {
  width: 100%;
}
.select-wrapper {
  position: relative;
}
.form-select {
  width: 100%;
  padding: 12px 16px;
  background-color: var(--color-white);
  border: 1px solid var(--color-gray-200);
  border-radius: var(--radius-md);
  font-size: var(--text-sm);
  color: var(--color-gray-900);
  outline: none;
  transition: border-color var(--transition-fast), box-shadow var(--transition-fast);
  appearance: none;
  background-image: url("data:image/svg+xml;charset=US-ASCII,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%22292.4%22%20height%3D%22292.4%22%3E%3Cpath%20fill%3D%22%23333333%22%20d%3D%22M287%2069.4a17.6%2017.6%200%200%200-13-5.4H18.4c-5%200-9.3%201.8-12.9%205.4A17.6%2017.6%200%200%200%200%2082.2c0%205%201.8%209.3%205.4%2012.9l128%20127.9c3.6%203.6%207.8%205.4%2012.8%205.4s9.2-1.8%2012.8-5.4L287%2095c3.5-3.5%205.4-7.8%205.4-12.8%200-5-1.9-9.2-5.5-12.8z%22%2F%3E%3C%2Fsvg%3E");
  background-repeat: no-repeat;
  background-position: right 1rem top 50%;
  background-size: 0.65rem auto;
}

.form-select:focus {
  border-color: var(--color-primary);
  box-shadow: 0 0 0 3px rgba(230, 0, 18, 0.1);
}

.form-select:disabled {
  background-color: var(--color-gray-100);
  cursor: not-allowed;
  opacity: 0.7;
}
</style>
