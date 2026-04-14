<template>
  <div class="about-page container">
    <div class="page-header">
      <h1>{{ $t('nav.about') }}</h1>
      <p>Your Trusted Partner in Network Communication Solutions</p>
    </div>
    
    <div class="about-content">
      <div class="text-section">
        <h2>Company Profile</h2>
        <p>ZIONCOM Electronics (Shenzhen) Co., Ltd. is a leading manufacturer of network communication products. We specialize in the research, development, and production of reliable wireless routers, network adapters, and intelligent switches.</p>
        <p>With years of experience in OEM and ODM services, we have built a solid reputation among our global partners. Quality and innovation are the pillars of our success.</p>
        
        <h2>Core Advantages</h2>
        <ul class="advantages-list">
          <li><strong>R&D Excellence:</strong> Over 100 dedicated engineers.</li>
          <li><strong>Quality Control:</strong> ISO9001 certified manufacturing facilities.</li>
          <li><strong>Global Reach:</strong> Products sold in over 50 countries.</li>
          <li><strong>Customer Focus:</strong> 24/7 localized technical support.</li>
        </ul>
      </div>
      
      <div class="image-section">
        <img src="https://picsum.photos/600/400?random=about" alt="ZIONCOM Office" class="about-img" />
      </div>
    </div>
    
    <div class="about-nav">
      <h3>Explore More</h3>
      <div class="nav-links">
        <NuxtLink :to="localePath('/about/milestones')" class="btn-outline">Milestones</NuxtLink>
        <NuxtLink :to="localePath('/about/factory-lab')" class="btn-outline">Factory & Lab</NuxtLink>
        <NuxtLink :to="localePath('/about/contact')" class="btn-outline">Contact Us</NuxtLink>
      </div>
    </div>
  </div>
</template>

<script setup>
useHead({ title: 'About Us' })
</script>

<style scoped>
.about-page {
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
.about-content {
  display: flex;
  gap: 50px;
  align-items: center;
  margin-bottom: 60px;
}
.text-section {
  flex: 1;
}
.text-section h2 {
  font-size: 1.8rem;
  color: var(--color-primary);
  margin-bottom: 20px;
}
.text-section p {
  font-size: 1.1rem;
  line-height: 1.8;
  color: #555;
  margin-bottom: 20px;
}
.advantages-list {
  list-style: none;
  padding: 0;
}
.advantages-list li {
  margin-bottom: 15px;
  font-size: 1.1rem;
  color: #444;
}
.advantages-list li::before {
  content: '✓';
  color: var(--color-primary);
  margin-right: 10px;
  font-weight: bold;
}
.image-section {
  flex: 1;
}
.about-img {
  width: 100%;
  border-radius: 8px;
  box-shadow: 0 10px 20px rgba(0,0,0,0.1);
}
.about-nav {
  text-align: center;
  border-top: 1px solid #eaeaea;
  padding-top: 40px;
}
.about-nav h3 {
  margin-bottom: 30px;
}
.nav-links {
  display: flex;
  justify-content: center;
  gap: 20px;
}
.btn-outline {
  display: inline-block;
  padding: 12px 30px;
  border: 2px solid var(--color-primary);
  color: var(--color-primary);
  text-decoration: none;
  border-radius: 4px;
  font-weight: bold;
  transition: all 0.3s;
}
.btn-outline:hover {
  background: var(--color-primary);
  color: #fff;
}

@media screen and (max-width: 767px) {
  .about-content {
    flex-direction: column;
  }
  .nav-links {
    flex-direction: column;
  }
}
</style>
