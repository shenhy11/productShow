<template>
  <div class="layout-wrapper">
    <!-- 全局 Header -->
    <header class="header">
      <div class="header-container">
        <div class="logo">
          <NuxtLink :to="localePath('/')">ZIONCOM</NuxtLink>
        </div>
        <nav class="nav-menu">
          <NuxtLink :to="localePath('/')">{{ $t('nav.home') }}</NuxtLink>
          <NuxtLink :to="localePath('/products')">{{ $t('nav.products') }}</NuxtLink>
          <NuxtLink :to="localePath('/support')">{{ $t('nav.support') }}</NuxtLink>
          <NuxtLink :to="localePath('/news')">{{ $t('nav.news') }}</NuxtLink>
          <NuxtLink :to="localePath('/about')">{{ $t('nav.about') }}</NuxtLink>
        </nav>
        <div class="lang-switch">
          <span @click="setLocale('en')" :class="{ active: locale === 'en' }">EN</span>
          <span> | </span>
          <span @click="setLocale('zh')" :class="{ active: locale === 'zh' }">中文</span>
        </div>
      </div>
    </header>

    <!-- 页面内容 & 动画 -->
    <main class="main-content">
      <slot />
    </main>

    <!-- 全局 Footer -->
    <footer class="footer">
      <div class="footer-container">
        <p>{{ $t('common.copyright') }}</p>
      </div>
    </footer>
  </div>
</template>

<script setup>
const { locale, setLocale } = useI18n()
const localePath = useLocalePath()
</script>

<style scoped>
.layout-wrapper {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}
.header {
  border-bottom: 1px solid #eaeaea;
  padding: 15px 0;
  box-shadow: 0 2px 10px rgba(0,0,0,0.05);
  position: sticky;
  top: 0;
  background: rgba(255, 255, 255, 0.95);
  z-index: 1000;
  backdrop-filter: blur(5px);
}
.header-container {
  max-width: var(--container-max-width, 1200px);
  margin: 0 auto;
  padding: 0 15px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.logo a {
  font-size: 24px;
  font-weight: bold;
  text-decoration: none;
  color: var(--color-primary);
  letter-spacing: 1px;
}
.nav-menu a {
  margin: 0 15px;
  text-decoration: none;
  color: #555;
  font-weight: 500;
  transition: color 0.3s;
}
.nav-menu a:hover, .nav-menu a.router-link-active {
  color: var(--color-primary);
}
.lang-switch span {
  cursor: pointer;
  color: #666;
  transition: color 0.3s;
}
.lang-switch span:hover {
  color: var(--color-primary);
}
.lang-switch span.active {
  color: var(--color-primary);
  font-weight: bold;
}
.main-content {
  flex: 1;
}
.footer {
  background: #f8f9fa;
  padding: 30px 0;
  text-align: center;
  border-top: 1px solid #eaeaea;
  color: #666;
}
</style>

<style>
/* 页面切换动画 */
.page-enter-active,
.page-leave-active {
  transition: all 0.4s ease;
}
.page-enter-from,
.page-leave-to {
  opacity: 0;
  transform: translateY(10px);
}
</style>
