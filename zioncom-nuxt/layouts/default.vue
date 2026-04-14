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
        </nav>
        <div class="lang-switch">
          <span @click="setLocale('en')" :class="{ active: locale === 'en' }">EN</span>
          <span> | </span>
          <span @click="setLocale('zh')" :class="{ active: locale === 'zh' }">中文</span>
        </div>
      </div>
    </header>

    <!-- 页面内容 -->
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
}
.header-container {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.logo a {
  font-size: 24px;
  font-weight: bold;
  text-decoration: none;
  color: #333;
}
.nav-menu a {
  margin: 0 15px;
  text-decoration: none;
  color: #666;
}
.nav-menu a:hover, .nav-menu a.router-link-active {
  color: #0056b3;
}
.lang-switch span {
  cursor: pointer;
  color: #666;
}
.lang-switch span.active {
  color: #0056b3;
  font-weight: bold;
}
.main-content {
  flex: 1;
}
.footer {
  background: #f8f9fa;
  padding: 20px 0;
  text-align: center;
  border-top: 1px solid #eaeaea;
  color: #666;
}
</style>
