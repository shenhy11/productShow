## ADDED Requirements

### Requirement: Nuxt SSG 静态页面
系统 SHALL 使用 Nuxt 3 的 SSG 模式预渲染首页、About Us、FAQ 等内容变化频率低的页面，构建时生成静态 HTML。

#### Scenario: 首页静态渲染
- **WHEN** 用户访问网站首页 /
- **THEN** Nginx 返回预构建的静态 HTML 页面，响应时间 < 100ms，包含完整 SEO 元信息

### Requirement: Nuxt ISR 产品详情页
系统 SHALL 使用 ISR（增量静态再生成）模式渲染产品详情页，首次访问生成静态缓存，产品信息更新后在下次请求时自动再生成。

#### Scenario: 产品详情页首次访问
- **WHEN** 用户首次访问一个产品详情页 /products/:id
- **THEN** Nuxt SSR 渲染页面并缓存为静态 HTML，后续请求直接返回缓存版本

#### Scenario: 产品信息更新后页面再生成
- **WHEN** 管理员在后台更新了产品信息，用户再次访问该产品页
- **THEN** 缓存超时后（SWR 策略），Nuxt 在后台重新渲染页面，用户获取最新内容

### Requirement: Nuxt SSR 动态列表页
系统 SHALL 使用 SSR 模式渲染产品列表页和新闻列表页，每次请求实时查询后端 API 并生成 HTML。

#### Scenario: 产品列表页渲染
- **WHEN** 用户访问 /products 并应用分类筛选
- **THEN** Nuxt 服务端请求 Spring Boot API 获取产品列表，渲染完整 HTML 返回客户端

### Requirement: 响应式布局
系统 SHALL 实现完全响应式设计，支持桌面（≥1200px）、平板（768-1199px）、手机（<768px）三种断点的自适应布局。

#### Scenario: 移动端浏览产品
- **WHEN** 用户在手机上访问产品列表页
- **THEN** 页面自动切换为单列布局，导航变为汉堡菜单，产品卡片纵向排列
