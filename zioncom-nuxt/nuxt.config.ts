// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: '2024-11-01',
  devtools: { enabled: false },
  typescript: { typeCheck: false },

  app: {
    pageTransition: { name: 'page', mode: 'out-in' },
    head: {
      titleTemplate: '%s - Zioncom',
      title: 'Zioncom Electronics',
      meta: [
        { charset: 'utf-8' },
        { name: 'viewport', content: 'width=device-width, initial-scale=1' },
        { hid: 'description', name: 'description', content: 'Zioncom is a global manufacturer of network communication products, specializing in Wi-Fi routers, adapters, and switches.' },
        { property: 'og:title', content: 'Zioncom Electronics' },
        { property: 'og:description', content: 'Zioncom is a global manufacturer of network communication products.' },
        { property: 'og:type', content: 'website' }
      ]
    }
  },

  css: [
    '~/assets/css/main.css'
  ],

  // 配置 SSG/ISR/SSR 混合渲染策略
  routeRules: {
    '/': { isr: 3600 },             // 首页缓存 1 小时
    '/products/**': { isr: 3600 },  // 产品页缓存 1 小时
    '/news/**': { isr: 1800 },      // 新闻页缓存 30 分钟
    '/support/**': { ssr: true },   // 技术支持页面动态渲染 SSR
    '/api/**': { proxy: 'http://zioncom-server:8080/api/**' } // API 代理转发到 Spring Boot
  },

  modules: [
    '@nuxtjs/i18n'
  ],

  i18n: {
    restructureDir: false,
    locales: [
      { code: 'en', iso: 'en-US', file: 'en.json', name: 'English' },
      { code: 'zh', iso: 'zh-CN', file: 'zh.json', name: '简体中文' }
    ],
    lazy: true,
    langDir: 'locales/',
    defaultLocale: 'en',
    strategy: 'prefix_except_default',
    detectBrowserLanguage: {
      useCookie: true,
      cookieKey: 'i18n_redirected',
      redirectOn: 'root', 
    }
  }
})
