<template>
  <div class="quick-links container">
    <div class="link-card search-card">
      <h3>Looking for a Product?</h3>
      <p>Search by model or category</p>
      <div class="search-box">
        <input type="text" placeholder="e.g. WR1200" v-model="keyword" @keyup.enter="handleSearch" />
        <button @click="handleSearch">Search</button>
      </div>
    </div>
    
    <div class="link-card oem-card">
      <h3>OEM/ODM Service</h3>
      <p>Partner with us to create value</p>
      <NuxtLink :to="localePath('/about')" class="btn-outline">Learn More</NuxtLink>
    </div>
  </div>
</template>

<script setup>
const localePath = useLocalePath()
const router = useRouter()
const keyword = ref('')

function handleSearch() {
  if (keyword.value.trim()) {
    router.push(localePath(`/products?keyword=${encodeURIComponent(keyword.value.trim())}`))
  }
}
</script>

<style scoped>
.quick-links {
  display: flex;
  gap: 20px;
  margin-top: -50px; /* Overlap hero banner */
  position: relative;
  z-index: 10;
}
.link-card {
  flex: 1;
  background: #fff;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
  text-align: center;
}
.search-card {
  border-top: 4px solid var(--color-primary);
}
.oem-card {
  border-top: 4px solid var(--color-secondary);
}
.link-card h3 {
  margin-top: 0;
  font-size: 1.5rem;
  color: #333;
}
.link-card p {
  color: #666;
  margin-bottom: 20px;
}
.search-box {
  display: flex;
  justify-content: center;
}
.search-box input {
  padding: 10px 15px;
  border: 1px solid #ccc;
  border-radius: 4px 0 0 4px;
  outline: none;
  width: 60%;
}
.search-box button {
  padding: 10px 20px;
  border: none;
  background: var(--color-primary);
  color: #fff;
  cursor: pointer;
  border-radius: 0 4px 4px 0;
  transition: background 0.3s;
}
.search-box button:hover {
  background: var(--color-secondary);
}
.btn-outline {
  display: inline-block;
  padding: 10px 30px;
  border: 1px solid var(--color-secondary);
  color: var(--color-secondary);
  text-decoration: none;
  border-radius: 4px;
  transition: all 0.3s;
}
.btn-outline:hover {
  background: var(--color-secondary);
  color: #fff;
}

@media screen and (max-width: 767px) {
  .quick-links {
    flex-direction: column;
    margin-top: 20px;
  }
}
</style>
