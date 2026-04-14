import { useNuxtApp, useRuntimeConfig } from '#app'

export const useHttp = () => {
  const { $i18n } = useNuxtApp()
  const config = useRuntimeConfig()

  /**
   * 封装的请求函数，自动附加当前的 lang 参数
   */
  const request = async (url: string, options: any = {}) => {
    // 获取当前语言环境，如 en 或 zh
    const currentLang = $i18n.locale.value

    // 默认附带到 query 参数中
    const defaults = {
      query: { lang: currentLang },
      // 如果后端需要，可加入自定义 headers，如：
      // headers: { 'Accept-Language': currentLang }
    }

    // 深度合并 fetch 配置
    const finalOptions = {
      ...defaults,
      ...options,
      query: {
        ...defaults.query,
        ...(options.query || {})
      }
    }

    return await useFetch(url, finalOptions)
  }

  return { request }
}
