<template>
  <div class="hero-banner">
    <!-- 动态发光背景球 -->
    <div class="glow-orb orb-1"></div>
    <div class="glow-orb orb-2"></div>
    
    <div class="banner-slide">
      <div class="banner-content glass-card">
        <h1 class="gradient-text">Welcome to Zioncom</h1>
        <p>Your Trusted Partner in Network Communication Solutions.</p>
        <div class="btn-group">
          <NuxtLink :to="localePath('/products')" class="btn-primary">Explore Products</NuxtLink>
          <NuxtLink :to="localePath('/about')" class="btn-outline">Learn More</NuxtLink>
        </div>
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
  height: 65vh;
  min-height: 500px;
  position: relative;
  overflow: hidden;
  background-color: #030712;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 动态流光背景 */
.glow-orb {
  position: absolute;
  border-radius: 50%;
  filter: blur(80px);
  opacity: 0.6;
  z-index: 1;
}

.orb-1 {
  width: 500px;
  height: 500px;
  background: var(--color-primary);
  top: -100px;
  right: -50px;
  animation: float 8s ease-in-out infinite alternate;
}

.orb-2 {
  width: 400px;
  height: 400px;
  background: var(--color-accent);
  bottom: -150px;
  left: 10%;
  animation: float 12s ease-in-out infinite alternate-reverse;
}

@keyframes float {
  0% { transform: translateY(0) scale(1); }
  100% { transform: translateY(50px) scale(1.1); }
}

.banner-slide {
  position: relative;
  z-index: 10;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0 24px;
}

.banner-content {
  text-align: center;
  padding: 60px 40px;
  max-width: 800px;
  background: rgba(255, 255, 255, 0.05);
  border-color: rgba(255, 255, 255, 0.1);
  box-shadow: 0 24px 40px -12px rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(24px);
  -webkit-backdrop-filter: blur(24px);
}

.banner-content h1 {
  font-size: 3.5rem;
  margin-bottom: 24px;
  line-height: 1.1;
  color: #fff;
  /* 如果 global 的 gradient-text 生效则表现为渐变 */
}

.banner-content p {
  font-size: 1.25rem;
  margin-bottom: 40px;
  color: #cbd5e1;
  line-height: 1.6;
}

.btn-group {
  display: flex;
  gap: 20px;
  justify-content: center;
}

.btn-outline {
  border-color: rgba(255, 255, 255, 0.4);
  color: #fff;
}

.btn-outline:hover {
  background: rgba(255, 255, 255, 0.1);
  border-color: #fff;
}

@media screen and (max-width: 768px) {
  .banner-content h1 { font-size: 2.5rem; }
  .btn-group { flex-direction: column; }
}
</style>
