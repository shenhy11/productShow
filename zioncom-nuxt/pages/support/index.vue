<template>
  <div class="support-page container">
    <div class="page-header">
      <h1>Support Center</h1>
      <p>How can we help you today?</p>
    </div>
    
    <div class="support-grid">
      <NuxtLink :to="localePath('/support/download')" class="support-card">
        <h3>Download Center</h3>
        <p>Find firmware, manuals, and drivers for your products.</p>
      </NuxtLink>
      
      <NuxtLink :to="localePath('/support/faq')" class="support-card">
        <h3>FAQ</h3>
        <p>Find answers to frequently asked questions.</p>
      </NuxtLink>
      
      <NuxtLink :to="localePath('/support/web-emulators')" class="support-card">
        <h3>Web Emulators</h3>
        <p>Experience the web interface of our products online.</p>
      </NuxtLink>
      
      <NuxtLink :to="localePath('/support/tech-videos')" class="support-card">
        <h3>Tech Videos</h3>
        <p>Watch setup guides and feature introductions.</p>
      </NuxtLink>
      
      <NuxtLink :to="localePath('/support/email-support')" class="support-card">
        <h3>Email Support</h3>
        <p>Submit a ticket if you need further assistance.</p>
      </NuxtLink>
    </div>
  </div>
</template>

<script setup>
useHead({ title: 'Support Center' })
</script>

<style scoped>
.support-page {
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
.support-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 30px;
}
.support-card {
  display: block;
  padding: 30px;
  background: #fdfdfd;
  border: 1px solid #eaeaea;
  border-radius: 8px;
  text-decoration: none;
  color: #333;
  text-align: center;
  transition: transform 0.3s, box-shadow 0.3s;
}
.support-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0,0,0,0.05);
  border-color: var(--color-primary);
}
.support-card h3 {
  margin-top: 0;
  font-size: 1.5rem;
  color: var(--color-primary);
  margin-bottom: 15px;
}
.support-card p {
  color: #666;
  margin: 0;
}
</style>
