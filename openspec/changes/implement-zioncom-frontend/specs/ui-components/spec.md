## 新增需求 (ADDED Requirements)

### 需求: 视觉资产与 Design Token (Design Token Setup)
系统必须建立一套集中式的、响应式 CSS 架构中心（CSS Variables），定义包括品牌标识色（热焰红/星空黑）、排版基准大小，以及透明玻璃拟态（Glassmorphism）涉及的参数（例如泛高光阴影和柔性高斯模糊参数），以精准重塑甚至超越原始 zioncom.net。

#### 场景: UI 元件运用全局原子 Token (Using tokens on a component)
- **当 (WHEN)** 开发者为一个具有 `.card` 类名的组件块施加了 `var(--color-primary)` 特性时
- **那么 (THEN)** 渲染树就会精准地将其渲染成 Zioncom 的官方调色版色准。

### 需求: 统一产品缩略展示卡片组件 (Product Card Component)
通用型的 `ZProductCard` 卡片组件应当整齐展示产品的核心图片、精炼名称以及卖点副标题（Subtitle），并在被交互（如悬停）时提供对应的高级动效视觉体验。

#### 场景: 将光标悬停进入卡片范围 (Hovering product card)
- **当 (WHEN)** 用户鼠标精准悬停（Hovering）产品展示区卡片面板时
- **那么 (THEN)** 系统即时调用平滑 CSS transform (`scale-up / translateY`) 放大并叠加深色盒子阴影增强质感。

### 需求: 高兼容度动态面包屑 (Breadcrumb Component)
系统必须封装出一套自研动态深层次面包屑组件 `ZBreadcrumb`，来承载错综复杂难以记忆的子级路由链路。

#### 场景: 用户正在深度审阅某特殊路由型号 (Viewing a deep product details page)
- **当 (WHEN)** 用户深入产品页面，如 URL 等级 `products/wireless/x6100ua`
- **那么 (THEN)** 面包屑组件将严格折射渲染出 `首页 > 产品库 > 无线路由器 > X6100UA` 的完整可逆向跳转通道。
