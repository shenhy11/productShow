## Context

吉翁电子（Zioncom）是一家深圳的网络通信设备制造商，现有官网 www.zioncom.net 基于传统技术栈构建，前后端耦合、SEO 表现差、缺乏结构化内容管理。本次重构采用前后端分离架构，以 Nuxt 3 + RuoYi (Spring Boot) 为核心技术栈，实现现代化、高性能、易维护的企业官网和管理后台。

**利益相关方**：
- 运营团队：需要便捷的后台管理产品信息、新闻、固件
- 开发团队：需要清晰的模块分工和自动化部署流程
- 终端用户：需要快速加载的前台、精准的型号搜索和固件下载
- 海外经销商：需要多语言支持和全球渠道展示

**约束条件**：
- 部署环境为单台云主机（4核8G），需优化资源占用
- 团队熟悉 Java 和 Vue 技术栈
- 内容更新频率不大，适合 SSG/ISR 策略

## Goals / Non-Goals

**Goals:**
- 实现前后端完全分离，前台（Nuxt SSR）、管理后台（RuoYi-Vue3 SPA）、API（Spring Boot）独立部署
- SEO 友好：产品详情页和新闻页可被 Google/Bing 等搜索引擎正确索引
- 支持中英文双语，URL 前缀模式（/en/、/zh/）
- Docker Compose 一键部署，GitHub Actions / GitLab CI 自动化流水线
- 统一文件存储：所有固件、图片、手册通过云 OSS 管理

**Non-Goals:**
- 不做电商交易功能（无购物车、无在线支付）
- 不做实时聊天或在线客服系统
- 不做移动端 App（仅做响应式 Web）
- 不做多租户 SaaS 架构
- 第一阶段不做全文搜索引擎（如 Elasticsearch），使用 MySQL LIKE 查询

## Decisions

### 决策 1：前端框架 — Nuxt 3 (Vue 3)

**选择**：Nuxt 3

**替代方案考虑**：
| 方案 | 优势 | 劣势 |
|------|------|------|
| Next.js (React) | 生态最大、Vercel 部署方便 | 团队更熟悉 Vue |
| 纯 Vite SPA | 最轻量 | SEO 需额外处理 |
| **Nuxt 3 (Vue)** | **团队熟悉 Vue、SSR/SSG/ISR 混合、i18n 成熟** | 社区比 React 小 |

**理由**：团队已有 Vue 经验（RuoYi 后台也是 Vue），统一技术栈降低学习成本。Nuxt 3 的 `routeRules` 支持按路由配置渲染策略，完美匹配本项目需求。

### 决策 2：渲染策略 — SSG + ISR + SSR 混合模式

| 路由 | 渲染模式 | 理由 |
|------|----------|------|
| `/`, `/about/**`, `/support/faq` | SSG | 内容变化频率低，静态生成最快 |
| `/products/:id` | ISR (SWR) | SEO 关键且需要更新，增量再生成 |
| `/products`, `/news/**` | SSR | 需要实时筛选和分页 |
| `/admin/**` | SPA (CSR) | 管理后台无需 SEO |

### 决策 3：后端底座 — RuoYi Spring Boot

**选择**：基于 RuoYi 后端底座扩展业务模块

**替代方案考虑**：
- 从零搭建 Spring Boot：灵活但用户/角色/权限/日志等基础设施需全部自建，工作量大
- NestJS (Node.js)：前后端统一 TS，但团队 Java 经验更丰富
- **RuoYi**：自带用户管理、角色权限、菜单管理、操作日志、代码生成器，开箱即用

**理由**：RuoYi 已提供完整的 RBAC 权限体系和运维基础设施，我们只需聚焦业务模块（product、content、support、oss）的开发。

### 决策 4：Admin 后台 — 独立 RuoYi-Vue3 项目

**选择**：Admin 后台作为独立前端项目部署在 admin.zioncom.net

**替代方案考虑**：
- 在 Nuxt 内嵌 /admin 路由：代码耦合，Admin UI 与官网 UI 风格差异大
- **独立项目**：解耦部署、独立迭代、利用 RuoYi 完整的前端模板

### 决策 5：文件存储 — 云厂商 OSS

**选择**：阿里云 OSS / 腾讯云 COS

**替代方案考虑**：
- 自建 MinIO：需自行运维、占用云主机存储和带宽
- **云 OSS**：免运维、自带 CDN 加速、按量计费、高可用

**理由**：固件文件（可达几十 MB）和产品图片访问量分散，云 OSS 的 CDN 分发可显著改善全球用户的下载体验。

### 决策 6：多语言方案 — 前端 i18n + 后端双字段

- 前端静态文本：`@nuxtjs/i18n` 模块 + JSON 语言包
- 动态内容（产品名、新闻标题等）：数据库存储 `name_en` / `name_zh` 双字段
- URL 模式：/en/products/xxx、/zh/products/xxx
- API 支持 `?lang=en` 参数按语言返回对应字段

### 决策 7：部署方案 — Docker Compose + Nginx

单台云主机部署完整技术栈：

```
Docker Compose Services:
├── nginx         (80/443) → SSL 终止 + 反向代理
├── nuxt-app      (3000)   → Nuxt SSR 服务
├── spring-api    (8080)   → RuoYi Spring Boot API
├── mysql         (3306)   → 主数据库
└── redis         (6379)   → 缓存/会话

Admin 前端打包为静态文件，由 Nginx 直接 serve
```

### 决策 8：CI/CD — GitHub Actions

Pipeline 设计：
- `feature/*` 分支 → 仅 Build + Test
- `develop` 分支 → 自动部署测试环境
- `main` 分支 → 自动部署生产环境
- 产物：Docker 镜像推送至云厂商容器镜像仓库（ACR/TCR）
- 部署：SSH 到云主机执行 docker-compose pull && up -d

### 决策 9：Monorepo 项目结构

```
zioncom-website/
├── zioncom-nuxt/          # Nuxt 3 官网前台
├── zioncom-admin/         # RuoYi-Vue3 管理后台前端
├── zioncom-server/        # RuoYi Spring Boot 后端
│   ├── ruoyi-admin/       # 启动入口
│   ├── ruoyi-common/
│   ├── ruoyi-framework/
│   ├── ruoyi-system/
│   ├── zioncom-product/   # 产品业务模块
│   ├── zioncom-content/   # 内容业务模块
│   ├── zioncom-support/   # 技术支持模块
│   └── zioncom-oss/       # 文件服务模块
├── docker-compose.yml
├── nginx/nginx.conf
└── .github/workflows/
```

## Risks / Trade-offs

| 风险 | 等级 | 缓解措施 |
|------|------|----------|
| 单台云主机承载全部服务，资源瓶颈 | 中 | → 合理限制每个 Docker 容器的 CPU/内存；SSG 页面用 Nginx 缓存分流；后期可水平扩展为多机 |
| Nuxt SSR 服务 Node.js 进程内存泄漏 | 中 | → Docker 容器设置内存上限 + 自动重启策略；使用 PM2 管理 Node 进程 |
| RuoYi 版本升级与自定义模块冲突 | 低 | → 业务模块完全独立于 RuoYi 框架模块，遵循 RuoYi 扩展约定 |
| 云 OSS 费用超预期 | 低 | → 固件下载量有限，启用 OSS 生命周期规则自动清理旧版本 |
| 多语言内容同步维护成本 | 低 | → 后台 Admin 提供双语并排编辑界面，减轻运营负担 |

## Migration Plan

1. **阶段一：基础搭建**（Week 1-2）
   - 初始化 Monorepo 项目结构
   - 搭建 RuoYi 后端框架 + 新增业务模块骨架
   - 搭建 Nuxt 3 前端框架 + 基础路由和布局

2. **阶段二：核心业务**（Week 3-5）
   - 产品管理 CRUD（后端 API + Admin 页面 + 前台展示）
   - 内容管理 CRUD（新闻、事件）
   - OSS 文件上传/下载集成

3. **阶段三：完善功能**（Week 6-7）
   - 固件版本管理
   - FAQ 和技术支持门户
   - 多语言 i18n 集成
   - 品牌展示页面（About、Milestones、Factory）

4. **阶段四：部署上线**（Week 8）
   - Docker Compose 编排和 Nginx 配置
   - CI/CD 流水线搭建
   - DNS 切换和灰度发布

**回滚策略**：保留原网站域名指向不变，新站使用临时域名验证。正式切换后保留原站 1 个月用于紧急回退。

## Open Questions

- 是否需要集成 Google Analytics / 百度统计等数据分析工具？
- Worldwide 页面的全球渠道商数据来源是 Excel 导入还是后台手动维护？
- 产品图片是否需要自动裁剪/压缩生成多尺寸缩略图？
- 是否需要邮件通知功能（如用户提交技术支持请求后自动通知运营）？
