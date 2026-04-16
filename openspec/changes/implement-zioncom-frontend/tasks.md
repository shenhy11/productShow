## 1. 基础配置与样式 Token 设置

- [x] 1.1 创建 `assets/css/main.css`，实现品牌核心颜色（红色 `#E60012`、黑色）以及玻璃拟态（Glassmorphism）CSS 变量（投影、模糊、边框）。
- [x] 1.2 在 `nuxt.config.ts` 或 `app.vue` 中全局导入 `main.css`。
- [x] 1.3 创建全局通用的响应式布局与样式响应类（container, flex, grid 等），对齐 Tailwind 风格至纯 CSS 变量。

## 2. 核心 UI 组件开发

- [x] 2.1 开发 `ZProductCard.vue`，展示产品主图、标题及简介，集成悬停放大及盒子阴影（box-shadow）变量。
- [x] 2.2 开发 `ZBreadcrumb.vue` 面包屑组件，利用 `vue-router` 的路由匹配自动生成动态导航链接。
- [x] 2.3 开发 `ZDataGrid.vue` 数据表格增强组件，以优雅的列表样式渲染固件、文档等数据。
- [x] 2.4 测试并确保 `ZProductCard` 在手机/平板等移动端布局的响应式适配。

## 3. 产品展示与产品目录

- [x] 3.1 提取爬虫结果 `crawl_output/crawl_results.json` 中的 14 级产品分类树，作为静态 Mock 数据存在 `public/mock/categories.json`。
- [x] 3.2 在 `pages/products/index.vue` 产品中心引入异步请求，通过 `useFetch` 读取 `mock/categories.json` 分类树。
- [x] 3.3 在产品首页构建动态侧边栏过滤机制，通过 URL query 控制右侧 `ZProductCard` 分类数据的独立展示。

## 4. 技术支持服务与深度交互

- [x] 4.1 开发 `ZCascadeSelector.vue` 树形级联选择器组件，绑定深度嵌套的下拉数据支撑下载中心三级联动（类目>子类>型号）。
- [x] 4.2 在下载中心 `pages/support/download.vue` 中接入级联器，利用 `lodash-es` 的防抖函数 (Debounce) 在用户选定最后级型号时长效缓冲触发真实/Mock API 数据调取。
- [x] 4.3 更新邮件支持模块 `pages/support/email-support.vue`，编写原生的表单正则校验以防御空标题、非法长短与格式不对的邮箱提交。

## 5. 国际化 (i18n) 全站深度重构

- [ ] 5.1 审查并迁移全局 Header 与 Footer (Layouts)：收集所有导航链、产品与公司的外部直链接英文字段，统一注册入 `locales/en.json` 与 `zh.json`，并将源码节点替换为 `$t('nav.xxx')`。
- [ ] 5.2 清洗 `pages/index.vue` (首页外露文案)：寻找 'Global Brand', 'Read More', 'Support Center' 等遗留硬编码字段进行翻译双绑重写。
- [ ] 5.3 修复面包屑及动态侧边栏的隐性英文字符映射翻译逻辑。

## 6. 首页视觉轮播图 (Home Carousel) 开发

- [ ] 6.1 编排构建 `ZProductCarousel.vue` 组件，基于原生 CSS 构建水平卷轴滚动（`scroll-snap`），保证移动端触控划屏流畅。
- [ ] 6.2 组建由核心热门产品素材（从 `crawl_output` 获取两三幅图）构成的焦点幻灯片模拟数据数组。
- [ ] 6.3 在 `pages/index.vue` 主页 Banner 槽位挂载组件，并添加一个基于 Vue 生命周期函数的定时器实现数秒一次的自动无缝轮接效果。

## 7. 产品字典及丰富化目录 (Product Detail Explosion)

- [ ] 7.1 改造当前极为单薄的 `mock/categories.json` / `mock/products.json` 引流模式，人工根据抓取的 `crawl_results.json` 伪造一组至少有 5~8 个产品的高保真 JSON 数据。
- [ ] 7.2 修缮 `pages/products/index.vue` 产品中心的网格列表排列，让它真正读取刚才拓展后的列表长度并以密集的组件式铺展开来。
- [ ] 7.3 验证详情页内挂载这些更丰富数据的连通性。
