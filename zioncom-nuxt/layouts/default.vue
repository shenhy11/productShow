<template>
  <div class="layout-wrapper">
    <!-- 极简毛玻璃 Header -->
    <header class="header">
      <div class="header-container">
        <div class="logo">
          <NuxtLink :to="localePath('/')">
            <span class="gradient-text" style="font-weight:800; font-family:'Outfit';">ZIONCOM</span>
          </NuxtLink>
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
          <span class="divider"></span>
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
        <div class="footer-grid">
          <div class="footer-brand">
            <h2 class="gradient-text">ZIONCOM</h2>
            <p>{{ $t('footer.slogan') }}</p>
          </div>
        </div>
        <div class="footer-bottom">
          <p>{{ $t('common.copyright') }}</p>
        </div>
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

/* 高级毛玻璃顶部导航 */
.header {
  position: sticky;
  top: 0;
  z-index: 1000;
  background: rgba(255, 255, 255, 0.75);
  backdrop-filter: blur(20px);
  -webkit-backdrop-filter: blur(20px);
  border-bottom: 1px solid rgba(255,255,255,0.4);
  box-shadow: 0 4px 30px rgba(0, 0, 0, 0.03);
  padding: 12px 0;
  transition: all 0.3s ease;
}

.header-container {
  max-width: var(--container-max-width);
  margin: 0 auto;
  padding: 0 24px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo a {
  font-size: 26px;
  letter-spacing: 0.5px;
}

/* 有流动光圈下划线的导航菜单 */
.nav-menu {
  display: flex;
  gap: 36px;
}

.nav-menu a {
  position: relative;
  font-size: 1.05rem;
  font-weight: 500;
  color: var(--color-text-muted);
  padding: 8px 0;
}

.nav-menu a::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 0;
  height: 2px;
  background: var(--color-primary);
  transition: width 0.3s ease;
  border-radius: 2px;
}

.nav-menu a:hover, 
.nav-menu a.router-link-active {
  color: var(--color-primary-dark);
}

.nav-menu a:hover::after,
.nav-menu a.router-link-active::after {
  width: 100%;
}

/* 语言切换控制 */
.lang-switch {
  display: flex;
  align-items: center;
  gap: 12px;
  background: var(--color-bg-base);
  padding: 6px 16px;
  border-radius: 30px;
  border: 1px solid var(--color-border);
}

.lang-switch span {
  cursor: pointer;
  font-size: 0.9rem;
  font-weight: 600;
  color: var(--color-text-muted);
  transition: color 0.2s;
}

.lang-switch span:not(.divider):hover {
  color: var(--color-primary);
}

.lang-switch span.active {
  color: var(--color-primary-dark);
}

.divider {
  width: 1px;
  height: 14px;
  background: #cbd5e1;
}

.main-content {
  flex: 1;
}

/* Footer 美化 */
.footer {
  background: #0f172a;
  color: #94a3b8;
  padding: 60px 0 20px;
  margin-top: 80px;
  border-top: 1px solid #1e293b;
}

.footer-container {
  max-width: var(--container-max-width);
  margin: 0 auto;
  padding: 0 24px;
}

.footer-brand h2 {
  margin-bottom: 12px;
}

.footer-bottom {
  margin-top: 40px;
  padding-top: 20px;
  border-top: 1px solid #1e293b;
  text-align: center;
  font-size: 0.9rem;
}
</style>
