# Zioncom 企业级官网前端产品架构与实施指南

> 本架构文档基于对 `zioncom.net` 官网全站拓扑结构的深度挖掘生成，旨在为基于 Nuxt 3 重新构建一套达到**“生产可交付级别”**的企业级电商展示项目提供清晰的蓝图。

## 1. 业务目标与项目愿景

- **业务目标**：从现有单一页面、路由缺失的原型项目，升级为结构完整、交互流畅、数据结构复杂的全球化品牌官网体系。
- **技术定位**：服务端渲染 (SSR/ISR) 以支持强力 SEO，支持多端自适应 (Responsive Design)，国际化多语言无缝切换 (i18n)。

---

## 2. 网站信息结构树 (Information Architecture)

爬取数据揭示全站采用的是典型的`[扁平化头部导航 + 深层树状分类]`逻辑。

### 📌 2.1 首页 (Home)
- **Top Bar / NavBar**：深色背景玻璃拟物态 / 品牌红黑配色，粘性定位 (Sticky)。
- **Hero Banner**：全屏轮播 (Swiper)，高亮展示旗舰级设备（如 Omni X6100UA 等）。
- **Quick Links / Feature Blocks**：引导进入 News、Events、核心场景。
- **Footer**：紧凑的版权与 ICP 备案声明区。

### 📌 2.2 产品展厅 (PRODUCTS)
产品体系是最复杂的部分，拥有多达 **14 个**主线分类：
- **分类矩阵**：*Wireless Router, Wireless USB Adapter, 5G/4G Wi-Fi Router, ADSL, Range Extender, Switch, Ceiling AP & In-Wall AP, Outdoor Wireless* 等。
- **产品瀑布流 / 分区展示**：
  - **New Products**（新品速递）
  - **Popular Products**（爆款推荐）
  - **Featured Products**（特色型号）
- **产品详情 (PDP)**：需集成技术规格说明、产品高清大图轮播、相关购买/支持链接及文档跳转。

### 📌 2.3 技术支持中心 (SUPPORT)
具有很强的工具属性和强交互表单请求要求：
- **Download Center (下载中心)**：强交互。三级联动过滤表单：`Product Category` -> `Optional Category` -> `Select Model`，附带搜素框直达，以及按地区（中国大陆外销售）的红字警告提示。
- **WEB Emulators (固件模拟器)**：按型号划分模块（如 A6004NS, N200RE-V2 等），帮助用户通过前端体验路由器后台。
- **FAQs (常见问题)**：表格/列表混合布局，带有关键字全检索功能，包含 `TOP10 FAQ`。
- **Tech Tip Video**：嵌入式视频教学播放区域。
- **E-mail Tech Support**：复杂的反馈表单（包含 Subject, Name, Tel, System 等多项 Select / Input）。

### 📌 2.4 关于我们 (ABOUT US) & 新闻 (NEWS)
- **About Us**：包含多门店、多地区的联络明细（香港总部、深圳研发、越南工厂、台湾等）。
- **Milestones**：垂直时间轴，标记如 2023 进入汽车电子领域、2015 设立越南分支等历史。
- **Factory & Lab**：图文组合宣传页。
- **News**：支持 TAB 切换，涵盖 *Corporate News*, *Product Launch*, *Events*。

### 📌 2.5 全球战略 (WORLDWIDE)
- **Map Selector**：深景界光影地图，划分为 *Asia-pacific, Europe, North America, Latin America, Middle East*，并配备中国大陆及台湾区域直达语言版本入口。

---

## 3. 页面核心组件提取 (Component Design)

为维持 Nuxt3 项目结构的整洁和极致可复用性，以下基础组件应当优先实现：

1. **`ZBreadcrumb.vue`**: 处理支持页面极深的嵌套层级回退。
2. **`ZProductCard.vue`**: 支持悬浮发光、点击整卡跳转，包含大图、Model 标题与其 Sub-title 特性概述。
3. **`ZCascadeSelector.vue`**: 支撑支持页面的多级联动下拉菜单查询逻辑。
4. **`ZTimeLine.vue`**: 用于 Milestones 页面的时间轴呈现组件。
5. **`ZDataGrid.vue`**: 用于 FAQs 页面的高亮数据列表。

---

## 4. API 数据交互规范约定 (Mock & REST API)

由于目前是前后端分离并行开发，对新接口提报如下结构标准：

### 4.1 通用分类树聚合接口
**GET** `/api/categories`
响应结构示例：
```json
{
  "code": 200,
  "data": [
    {
      "id": "c1",
      "name": "Wireless Router",
      "slug": "wireless-router",
      "children": [...]
    }
  ]
}
```

### 4.2 支持中心型号联动及固件搜索
**GET** `/api/support/downloads?category=xxx&model=A8000RU`
前端必须采用 debounce （防抖）与动态 `useFetch` Key 进行请求检索。

### 4.3 新闻/产品列表分页处理
**GET** `/api/news?type=events&page=1&size=10`
需使用基于 `useAsyncData` 或已修复的 `useHttp` 配合 SSR 路由预取，确保首屏不白屏并兼顾 SEO。

---

## 5. UI/UX 与工程化实践推荐

- **视觉风格 (Premium Design)**：大量使用背景暗色深空隐喻，融入极具科技感的光斑渐变效果；卡片背景采用“轻微透明 + 毛玻璃过滤器 (Glassmorphism)” (`backdrop-filter: blur()`)。
- **动画控制**：
  - 页面路由切换开启过渡动画 (Vue `<Transition>` with Nuxt `pageTransition`)。
  - 卡片图表出现运用 IntersectionObserver + CSS `@keyframes` 的微动效 (Slide-up & Fade-in)。
- **性能规范**：
  - 首屏全副图片（Hero Banner）必须配置 `fetchpriority="high"` 和 WebP 格式。
  - 其他产品截图一律采用 `loading="lazy"` 进行视窗外懒加载。
  - 对于深层多图表分类，开启 Nuxt 的页面缓存渲染规则 (ISR / swr caching)。

---

## 6. 后续迭代实施路线图 (Implementation Roadmap)

1. **Phase 1: 基础设施骨架建设 (W1)**
   - 完善基于路由的顶级文件夹结构 (`pages/products`, `pages/support/download`, `pages/about/milestones`)。
   - 实现全局布局框架 (Header 玻璃化、Footer 规范化及多语言下钻支持)。
2. **Phase 2: 核心产品与业务流展示 (W2)**
   - 开发并注入大量产品 Mock 数据。
   - 完善产品阵列渲染（Product Cascade 与 Detail Page）。
3. **Phase 3: 工具属性落地 (W3)**
   - 构建高复杂表单页（Download 联动与 Email Tech Form 校验）。
   - 实现 FAQ 与模拟器清单数据的分页/即时检索加载。
4. **Phase 4: 细节动效与 SEO 上线优化 (W4)**
   - Meta 标签动态补齐（`useHead`, `useSeoMeta`）。
   - SSR 水合性能与体积压榨，正式发布交付。
