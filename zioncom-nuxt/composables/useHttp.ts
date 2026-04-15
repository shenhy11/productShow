import { useNuxtApp } from '#app'
import { isRef, unref } from 'vue'

export const useHttp = () => {
  const { $i18n } = useNuxtApp()

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

    // 生成唯一 key 避免 Nuxt 共享全局相同的 useFetch key。
    // 注：绝对不能用 JSON.stringify 序列化 finalOptions.query，
    // 因为这会引发 ComputedRef 渲染时的循环引用（Circular structure to JSON）死机！
    if (!finalOptions.key) {
      const qs = Object.entries(finalOptions.query || {})
        .map(([k, v]) => `${k}=${String(isRef(v) ? unref(v) : v)}`)
        .join('&')
      finalOptions.key = `${url}?${qs}`
    }

    // 添加侦听：当语言环境或 query 中的响应式依赖变化时主动重新请求
    if (!finalOptions.watch) {
      finalOptions.watch = [$i18n.locale]
    }

    return useFetch(url, finalOptions)
  }

  return { request }
}
