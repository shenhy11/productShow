<template>
  <div class="hero-banner">
    <div class="banner-slide" style="background-image: url('https://picsum.photos/1920/600?random=1')">
      <div class="banner-content">
        <h1>Welcome to Zioncom</h1>
        <p>Your Trusted Partner in Network Communication Solutions</p>
        <NuxtLink :to="localePath('/products')" class="btn-primary">Explore Products</NuxtLink>
      </div>
    </div>
  </div>
</template>

<script setup>
const localePath = useLocalePath()
</script>

<style scoped>
.hero-banner {
  width: 100%;
  height: 600px;
  overflow: hidden;
  position: relative;
}
.banner-slide {
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
  display: flex;
  align-items: center;
  justify-content: center;
}
.banner-content {
  text-align: center;
  color: #fff;
  background: rgba(0, 0, 0, 0.4);
  padding: 40px;
  border-radius: 8px;
}
.banner-content h1 {
  font-size: 3rem;
  margin-bottom: 20px;
}
.banner-content p {
  font-size: 1.5rem;
  margin-bottom: 30px;
}
.btn-primary {
  display: inline-block;
  padding: 12px 30px;
  background-color: var(--color-primary);
  color: #fff;
  text-decoration: none;
  border-radius: 4px;
  font-size: 1.2rem;
  transition: background-color 0.3s;
}
.btn-primary:hover {
  background-color: var(--color-secondary);
}
</style>
