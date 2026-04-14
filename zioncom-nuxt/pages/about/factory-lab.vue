<template>
  <div class="factory-lab-page container">
    <div class="page-header">
      <h1>Factory & Laboratory</h1>
      <p>State-of-the-art facilities ensuring world-class product quality.</p>
    </div>
    
    <div class="content-section">
      <div class="text-block">
        <h2>Advanced Manufacturing</h2>
        <p>ZIONCOM operates two major manufacturing bases located in Shenzhen, China and Vietnam. Our factories span over 100,000 square meters, equipped with advanced SMT lines, automated testing systems, and strict quality control processes.</p>
      </div>
      
      <div class="gallery-grid">
        <div class="gallery-item" v-for="i in 6" :key="'img'+i">
          <img :src="`https://picsum.photos/400/300?random=factory${i}`" alt="Factory Image" loading="lazy" />
        </div>
      </div>
    </div>
    
    <div class="content-section pd-top">
      <div class="text-block">
        <h2>Leading R&D Laboratory</h2>
        <p>Our dedicated R&D laboratory is equipped with industry-leading testing equipment for reliability, RF performance, environmental, and safety tests. Every product undergoes rigorous testing to meet strict international standards.</p>
      </div>
      
      <div class="gallery-grid">
        <div class="gallery-item" v-for="i in 3" :key="'lab'+i">
          <img :src="`https://picsum.photos/400/300?random=lab${i}`" alt="Lab Image" loading="lazy" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
useHead({ title: 'Factory & Lab' })
</script>

<style scoped>
.factory-lab-page {
  padding: 40px 15px;
}
.page-header {
  text-align: center;
  margin-bottom: 50px;
}
.page-header h1 {
  font-size: 2.5rem;
  color: #333;
  margin-bottom: 15px;
}
.page-header p {
  color: #666;
  font-size: 1.1rem;
}
.content-section {
  margin-bottom: 60px;
}
.pd-top {
  border-top: 1px solid #eaeaea;
  padding-top: 60px;
}
.text-block h2 {
  font-size: 1.8rem;
  color: var(--color-primary);
  margin-bottom: 20px;
}
.text-block p {
  font-size: 1.1rem;
  line-height: 1.8;
  color: #444;
  margin-bottom: 30px;
  max-width: 900px;
}
.gallery-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
}
.gallery-item {
  overflow: hidden;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0,0,0,0.05);
}
.gallery-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s ease;
  display: block;
}
.gallery-item:hover img {
  transform: scale(1.05);
}
</style>
