## 1. 项目初始化与基础搭建

- [x] 1.1 创建 Monorepo 根目录结构（zioncom-nuxt/、zioncom-admin/、zioncom-server/、docker/、nginx/、.github/workflows/）
- [x] 1.2 初始化 RuoYi-Vue3 后端项目（clone RuoYi 脚手架），配置 MySQL 数据源和 Redis 连接
- [x] 1.3 初始化 RuoYi-Vue3 前端 Admin 项目，验证默认登录和权限管理功能可用
- [x] 1.4 初始化 Nuxt 3 前台项目（npx nuxi init），配置 SSR 模式和基础 nuxt.config.ts
- [x] 1.5 创建 docker-compose.yml，编排 mysql、redis、spring-api、nuxt-app、nginx 服务
- [x] 1.6 配置 Nginx 反向代理规则（www → Nuxt:3000、admin → 静态文件、api → Spring:8080）
- [x] 1.7 验证本地 Docker Compose 一键启动全部服务

## 2. 数据库设计与基础业务模块

- [x] 2.1 设计并创建产品相关表（product、product_category、product_spec）含双语字段
- [x] 2.2 设计并创建内容相关表（article、article_category）含双语字段
- [x] 2.3 设计并创建固件管理表（firmware、document）
- [x] 2.4 设计并创建技术支持表（faq、faq_category、tech_video、contact_message、support_request）
- [x] 2.5 设计并创建品牌展示表（milestone、worldwide_partner）
- [x] 2.6 设计并创建 OSS 文件记录表（sys_oss）
- [x] 2.7 编写 SQL 初始化脚本，包含示例数据

## 3. 后端 - OSS 文件服务模块 (zioncom-oss)

- [x] 3.1 集成云厂商 OSS SDK（阿里云 OSS / 腾讯云 COS），编写 OssConfig 配置类
- [x] 3.2 实现 OssService：文件上传（按类型放入 images/ / firmware/ / docs/ 前缀）、删除、生成预签名 URL
- [x] 3.3 实现 OssController：POST /api/oss/upload（通用上传）、GET /api/oss/download/{id}（预签名下载）
- [x] 3.4 实现文件管理 CRUD API（列表/筛选/删除）
- [x] 3.5 限制上传文件大小（图片 5MB、固件 100MB）和格式校验

## 4. 后端 - 产品管理模块 (zioncom-product)

- [x] 4.1 实现产品分类管理 API（树形 CRUD：新增/编辑/删除/排序/获取分类树）
- [x] 4.2 实现产品 CRUD API（新增/编辑/上下架/删除/列表查询/详情查询）
- [x] 4.3 实现产品规格参数管理 API（按产品 ID 批量保存/查询 key-value 键值对）
- [x] 4.4 实现产品搜索 API（按关键词搜索型号/名称，按分类筛选，分页排序）
- [x] 4.5 实现产品 API 多语言支持（?lang=en 参数返回对应语言字段）

## 5. 后端 - 内容管理模块 (zioncom-content)

- [x] 5.1 实现文章 CRUD API（新增/编辑/发布/撤回/删除，按类型筛选 News/Event）
- [x] 5.2 实现文章列表 API（分页、按类型筛选、按发布日期排序）
- [x] 5.3 实现文章详情 API（含 SEO 元信息返回）
- [x] 5.4 实现文章多语言支持

## 6. 后端 - 固件与技术支持模块 (zioncom-support)

- [x] 6.1 实现固件版本管理 API（按产品上传固件、版本列表、Release Note）
- [x] 6.2 实现用户手册/Datasheet 管理 API（关联产品上传 PDF）
- [x] 6.3 实现 Download Center 搜索 API（按型号搜索固件和文档）
- [x] 6.4 实现 FAQ 管理 CRUD API（问答对、分类、排序）
- [x] 6.5 实现技术视频管理 API（标题、描述、视频嵌入 URL、关联产品）
- [x] 6.6 实现联系表单/技术支持请求提交 API（保存到数据库）
- [x] 6.7 实现 Web 模拟器管理 API（产品型号、模拟器链接）

## 7. 后端 - 品牌展示模块

- [x] 7.1 实现里程碑管理 API（年份、事件标题、描述、CRUD）
- [x] 7.2 实现全球渠道商管理 API（地区、名称、联系方式、经纬度、CRUD）
- [x] 7.3 实现联系我们后台查看 API（联系记录列表、已读标记）

## 8. Admin 后台前端 (RuoYi-Vue3)

- [x] 8.1 新增产品分类管理页面（树形表格、新增/编辑弹窗）
- [x] 8.2 新增产品管理页面（列表表格 + 搜索筛选 + 新增/编辑表单含双语字段、封面图上传、富文本编辑器、规格参数动态表单、SEO 配置）
- [x] 8.3 新增文章管理页面（列表 + 类型筛选 + 编辑表单含双语标题、富文本、封面图、SEO 配置）
- [x] 8.4 新增固件管理页面（按产品筛选固件列表 + 上传固件表单含版本号和 Release Note）
- [x] 8.5 新增文档管理页面（按产品管理手册和 Datasheet PDF）
- [x] 8.6 新增 FAQ 管理页面（分类管理 + 问答对列表 + 编辑）
- [x] 8.7 新增技术视频管理页面
- [x] 8.8 新增里程碑管理页面（时间线数据编辑）
- [x] 8.9 新增全球渠道商管理页面
- [x] 8.10 新增联系记录 / 支持请求查看页面
- [x] 8.11 新增文件管理页面（OSS 文件列表、筛选、删除）
- [x] 8.12 配置 RuoYi 菜单和权限（为新增业务模块添加菜单项和按钮权限）

## 9. Nuxt 前台 - 基础框架与布局

- [x] 9.1 配置 Nuxt 3 routeRules（SSG/ISR/SSR 混合渲染策略）
- [x] 9.2 安装并配置 @nuxtjs/i18n 模块（中英文语言包、URL 前缀模式、hreflang 标签）
- [x] 9.3 创建全局布局组件（Header 导航栏含多级菜单 + 语言切换、Footer 版权信息）
- [x] 9.4 配置 API 请求 composable（useFetch 封装，自动携带 lang 参数）
- [x] 9.5 配置全局 SEO 默认值（title 模板、meta description、OG 标签）
- [x] 9.6 实现响应式断点适配（桌面/平板/手机三档媒体查询）

## 10. Nuxt 前台 - 首页

- [x] 10.1 实现 Hero 区域（全屏大图/轮播，展示主推产品）
- [x] 10.2 实现快速通道区域（产品搜索入口、OEM/ODM 能力介绍入口）
- [x] 10.3 实现最新动态区域（最新新闻和事件卡片）
- [x] 10.4 实现产品分类快速导航区域

## 11. Nuxt 前台 - 产品模块

- [x] 11.1 实现产品列表页（/products）：分类侧边栏 + 产品卡片网格 + 分页
- [x] 11.2 实现产品详情页（/products/:id）：产品大图、摘要、规格参数表、相关固件下载、相关文档下载
- [x] 11.3 实现产品搜索功能（搜索框 + 搜索结果页）

## 12. Nuxt 前台 - 新闻与事件

- [x] 12.1 实现新闻列表页（/news）：文章卡片列表 + 分页
- [x] 12.2 实现事件列表页（/events）：事件卡片列表 + 分页
- [x] 12.3 实现文章详情页（/news/:id、/events/:id）：标题、封面图、正文富文本、发布日期

## 13. Nuxt 前台 - 品牌展示

- [x] 13.1 实现关于我们页面（/about）：企业简介、核心优势
- [x] 13.2 实现里程碑页面（/about/milestones）：时间线组件，按年份展示
- [x] 13.3 实现工厂与实验室页面（/about/factory-lab）：图片画廊 + 文字介绍
- [x] 13.4 实现全球渠道页面（/worldwide）：交互式世界地图（可用 Leaflet/Mapbox）标注渠道商
- [x] 13.5 实现联系我们页面（/about/contact）：联系信息 + 表单提交

## 14. Nuxt 前台 - 技术支持

- [x] 14.1 实现 Download Center 页面（/support/download）：型号搜索 + 固件/文档结果列表 + 下载按钮
- [x] 14.2 实现 FAQ 页面（/support/faq）：分类分组 + 折叠/展开问答
- [x] 14.3 实现 Web 模拟器列表页（/support/web-emulators）
- [x] 14.4 实现技术视频页面（/support/tech-videos）：视频卡片网格
- [x] 14.5 实现邮件技术支持页面（/support/email-support）：提交表单

## 15. UI 设计与视觉优化

- [x] 15.1 确定设计系统（配色方案、字体、间距、圆角等 Design Tokens）
- [x] 15.2 实现前台全局 CSS 变量和基础样式
- [x] 15.3 实现交互动效（页面过渡动画、Hover 效果、滚动触发动画）
- [x] 15.4 适配暗色模式（可选，建议后期迭代）

## 16. CI/CD 与部署

- [x] 16.1 编写后端 Dockerfile（基于 JDK 镜像，mvn package 多阶段构建）
- [x] 16.2 编写 Nuxt 前台 Dockerfile（基于 Node 镜像，pnpm build）
- [x] 16.3 编写 Admin 前台 Dockerfile（基于 Node 构建 + Nginx serve 静态文件）
- [x] 16.4 编写 docker-compose.prod.yml（生产环境配置覆写，环境变量外置）
- [x] 16.5 配置 GitHub Actions CI workflow（Build + Test，针对 feature/develop/main 分支）
- [x] 16.6 配置 GitHub Actions Deploy workflow（main 分支自动推送镜像到 ACR/TCR + SSH 部署）
- [ ] 16.7 配置 Nginx SSL 证书（Let's Encrypt 或云厂商免费证书）
- [ ] 16.8 端到端部署验证：全流程从代码推送到生产环境可访问

## 17. 测试与上线

- [ ] 17.1 后端各模块 API 接口测试（可使用 Postman/IDEA HTTP Client）
- [ ] 17.2 前台各页面功能验证（SSG 页面加载、ISR 缓存刷新、SSR 列表分页）
- [ ] 17.3 Admin 后台各管理页面 CRUD 操作验证
- [ ] 17.4 多语言切换功能验证（前台中英文切换、API lang 参数）
- [ ] 17.5 响应式布局验证（桌面、平板、手机三种设备）
- [ ] 17.6 SEO 验证（Google Search Console 提交 sitemap、检查 SSR 页面源码）
- [ ] 17.7 DNS 切换至新站，保留旧站 1 个月用于紧急回退
