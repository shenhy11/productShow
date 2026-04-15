// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: '2024-11-01',
  devtools: { enabled: false },
  typescript: { typeCheck: false },

  app: {
    // pageTransition: { name: 'page', mode: 'out-in' }, // 移除全局过渡动画，解决路由冻结 bug
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

  // 渲染策略：移除 ISR 避免首次渲染失败被缓存（ISR cache poison），产品/新闻/支持页改为 CSR
  routeRules: {
    '/products/**': { ssr: false },
    '/news/**':     { ssr: false },
    '/support/**':  { ssr: false },
    // API 代理：硬编码 spring-api:8080（Docker 内部服务名）
    // nuxt.config.ts 在 Build Time 运行，process.env 此时不可靠，不能用环境变量
    '/api/**': { proxy: 'http://spring-api:8080/' }
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
