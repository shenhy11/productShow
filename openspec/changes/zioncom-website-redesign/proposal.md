## Why

吉翁电子（Zioncom）现有官方网站 (www.zioncom.net) 采用传统服务端渲染技术，存在以下核心问题：
1. **技术栈老化**：前后端耦合严重，无法独立迭代，维护成本高
2. **SEO 表现不佳**：产品详情页和新闻页缺乏搜索引擎优化，影响海外市场获客
3. **内容管理低效**：产品信息、新闻动态、固件版本等内容缺少结构化管理后台，运营人员依赖开发支持
4. **用户体验落后**：响应式适配不足，移动端体验差，页面加载速度慢

当前正是 Zioncom 拓展全球市场的关键时期，需要一个现代化、高性能、易维护的企业官网来支撑品牌展示和技术服务。

## What Changes

- 采用 **Nuxt 3 (Vue 3) SSR/SSG 混合模式** 重建官网前台，实现 SEO 友好的服务端渲染
- 基于 **RuoYi-Vue3 + Spring Boot** 搭建自建 Admin 管理后台，覆盖产品、内容、固件、FAQ 等全业务管理
- 以 RuoYi 后端为底座，**扩展 zioncom 业务模块**（产品、内容、技术支持、OSS 文件管理）
- 集成 **云厂商 OSS** 作为固件包 / 产品图片 / 手册文档的统一文件存储
- 实现 **@nuxtjs/i18n 多语言架构**，支持中英文双语切换
- 通过 **Docker Compose + CI/CD** 实现容器化部署和自动化发布流水线
- 采用 Nginx 反向代理实现 www / admin / api 三域名分发

## Capabilities

### New Capabilities
- `product-catalog`: 产品目录展示与管理，包含 16+ 产品分类的层级管理、产品详情页（规格参数表）、产品搜索与筛选
- `content-management`: 新闻动态与事件管理 CMS，支持富文本编辑、封面图、发布状态控制、SEO 元信息配置
- `firmware-management`: 固件版本管理，按产品型号关联固件包、自动版本历史、用户手册与 Datasheet 下载
- `oss-file-service`: 统一文件服务，对接阿里云/腾讯云 OSS SDK，覆盖图片上传、固件上传、文件预签名 URL 下载
- `i18n-multilingual`: 前端 Nuxt i18n 多语言 + 后端数据库双语字段，支持 URL 前缀模式 (/en/, /zh/)
- `nuxt-ssr-frontend`: Nuxt 3 官网前台，SSG 静态页面（首页/About/FAQ）+ ISR 产品详情页 + SSR 动态列表页
- `admin-dashboard`: 基于 RuoYi-Vue3 的自建管理后台，涵盖产品/内容/固件/FAQ/SEO 配置等全业务管理
- `cicd-pipeline`: GitHub Actions / GitLab CI 自动化流水线，覆盖构建、测试、Docker 镜像推送、SSH 部署
- `brand-showcase`: 品牌展示模块（关于我们、里程碑时间线、工厂与实验室、全球渠道商交互地图）
- `support-portal`: 技术支持门户（FAQ 管理、Web 模拟器入口、技术视频管理、邮件支持表单）

### Modified Capabilities
<!-- 无已有 capability 需要修改，这是全新项目 -->

## Impact

- **代码库**：全新前后端项目（Monorepo 结构），包含 zioncom-nuxt、zioncom-admin、zioncom-server 三个子项目
- **技术依赖**：Nuxt 3、Vue 3、Spring Boot 2.x (RuoYi)、MySQL 8.x、Redis、Docker、Nginx
- **外部服务**：云厂商 OSS（阿里云/腾讯云）、云主机 ECS、域名 DNS 解析
- **基础设施**：需要 4 核 8G 起步的云主机，配置 Docker Compose 环境
- **运维流程**：引入 CI/CD 自动化部署，开发团队需熟悉 Git 分支策略和 Docker 基础操作
