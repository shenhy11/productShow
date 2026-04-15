import { useNuxtApp, useRuntimeConfig } from '#app'

export const useHttp = () => {
  const { $i18n } = useNuxtApp()
  const config = useRuntimeConfig()

  /**
   * 封装的请求函数，自动附加当前的 lang 参数
   */
  const request = (url: string, options: any = {}) => {
    // 获取当前语言环境，如 en 或 zh
    const currentLang = $i18n.locale.value

    // 默认附带到 query 参数中
    const defaults = {
      query: { lang: currentLang },
    }

    // 深度合并 fetch 配置
    const finalOptions: any = {
      ...defaults,
      ...options,
      query: {
        ...defaults.query,
        ...(options.query || {})
      }
    }

    // 修复 Nuxt 3 中封装 useFetch 导致的自动分配全局相同 key 的问题
    // 手动基于 url 和带有语言的参数生成独立的 key
    if (!finalOptions.key) {
      finalOptions.key = `${url}-${JSON.stringify(finalOptions.query)}`
    }
    
    // 添加侦听，当语言环境变化时主动重新请求
    if (!finalOptions.watch) {
      finalOptions.watch = [ $i18n.locale ]
    }

    return useFetch(url, finalOptions)
  }

  return { request }
}
