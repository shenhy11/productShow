# Zioncom 企业官网及后台管理系统 (Vue3 + Nuxt3 + RuoYi)

## 架构说明
本项目采用前后端分离架构，主要由三部分组成：
1. **zioncom-server (后端)**: 基于 RuoYi-Vue3 核心的 Spring Boot 应用。管理产品、内容、支持、品牌等模块业务与 API 数据服务。
2. **zioncom-admin (后台前端)**: 基于 RuoYi Vue3 架构的前端管理面板。用于后台管理人员维护所有官网内容数据。
3. **zioncom-nuxt (前台页面)**: 基于 Nuxt 3 实现。支持 SSR/ISR/SSG 混合渲染，提升 SEO，内建多语言 (i18n) 以及响应式排版。

## 快速运行 (开发环境)

### 1. 运行环境要求
- JDK 8+
- Node.js 18+ (推荐 20)
- MySQL 5.7+ / 8.0+
- Redis (默认本机的 6379 端口)
- pnpm (或 npm) 用于前端项目拉取包

### 2. 后端服务 (`zioncom-server`)
1. 初始化数据库，导入 `sql/ry_vue_*.sql`，并将我们生成的 `zioncom_menu.sql` 及相关表结构一并导入。
2. 配置 `ruoyi-admin/src/main/resources/application-druid.yml` 里面 `mysql` 的账密。
3. 进入 `zioncom-server`，运行 `mvn clean install`。
4. 运行 `RuoYiApplication.java`。后端默认监听 8080 端口。

### 3. 后台前端 (`zioncom-admin`)
1. 进入目录：`cd zioncom-admin`
2. 安装依赖：`npm install`
3. 运行项目：`npm run dev`
4. 访问：http://localhost:80

### 4. 官网前台 (`zioncom-nuxt`)
1. 进入目录：`cd zioncom-nuxt`
2. 安装依赖：`npm install`
3. 启动开发服务器：`npm run dev`
4. 访问：http://localhost:3000

## 部署说明 (生产环境)
我们提供了完整的 Docker Compose 和 GitHub Actions 部署方案：
- 将代码提交至 `main` 分支后，由 `.github/workflows/deploy.yml` 自动打包镜像并发布到指定 Registry。
- 然后利用 `docker-compose.prod.yml` 实施线上部署和配置。

详情可参考 `.github/workflows` 和 Dockerfile 相关设定。

## 模块清单
- 产品中心（品类、商品、规格参数）
- 内容模块（新闻、事件、轮播图）
- 技术支持（FAQ分类与问答、相关固件及文档下载、视频指南、模拟器链接、表单工单）
- 品牌渠道（关于我们里程碑、全球渠道经销商分布）
