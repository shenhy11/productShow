## 实施背景 (Context)

在重构后的基础版本发布后，我们审查了 Zioncom 的多语言体验与重要模块。发现首页缺乏产品焦点的轮播推荐功能，`products/index.vue` 页面的列表缺乏具体的业务产品数据展示；另外，硬编码文字在深层嵌套的组件内没有经过 `$t` 国际化过滤，使得中英文切换名存实亡。本设计文档旨在从工程角度明确此功能漏洞的填补细节。

## 目标与非目标 (Goals / Non-Goals)

**目标：**
- 利用 `crawl_output` 提供的内容矩阵，在 `zioncom-nuxt/locales/` 构建全站统一且完备的 JSON i18n 字典对。
- 在首页使用第三方轻量级轮播组件或自写 `CSS Scroll Snap` 组件插入 `ZProductCarousel.vue`。
- 将 `mock/products.json` 的深层内容打通行列绑定，渲染真实图片与核心卖点至页面。

**非目标：**
- 更换国际化底层技术（完全遵循现有的 `@nuxtjs/i18n` 结构即可）。
- 创建后台动态管理轮播图片的 API 接口（这阶段依然依赖静态或内置映射资源与文件配置模拟）。

## 设计决策 (Decisions)

- **国际化清理方案 (i18n Clean-up)**: 通过全局正则/搜索排查现有组件（特别是 Header, Footer 面包屑）的中文和英文词根，在 `en.json` 与 `zh.json` 中配置如 `nav.home`, `support.download`, `product.specifications` 这样的树形结构，并将模板全部改写为双花括号 `{{ $t('key') }}` 形式。
- **轮播图技术 (Carousel Tech)**: 避免引入超级庞大的外部库（如 Swiper），优先在 `ZProductCarousel.vue` 中利用原生的 CSS flex `overflow-x: auto; scroll-snap-type: x mandatory;` 特性叠加 Vue 3 原生 `setInterval` 进行极简丝滑轮播控制。
- **模拟数据深耕 (Deep Mocking)**: 根据分析出的 `crawl_results.json` 中的产品系列（如 AX3000, X6100UA 等），将其高清封面图配以特色功能抽取为独立的 JSON 结构填充到 `products` 的数据源流中。

## 潜在风险与取舍 (Risks / Trade-offs)

- [风险] CSS 原生轮播的兼容性与交互功能（例如分页器和小圆点）手工开发成本可能略高且容错低。 → 策略：只实现自动轮播与核心的左右焦点控制即可满足 Zioncom 等级视觉诉求，无需多余功能干涉。
- [风险] JSON 字典极度膨胀导致包体增大或查询降效。 → 策略：@nuxtjs/i18n 已配置为按需分块打包加载（`lazy: true`），完全能承受目前万字量级的国际化词典构建而不会拖累首屏 SSR。
