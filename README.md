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

## 部署说明 (生产环境/阿里云更新流程)
如果当前线上有新功能合并至 `feature-login`（或 `main`）分支，需要在阿里云服务器 (8.166.113.25) 上拉去最新代码并重新构建部署 Docker 容器，请使用 SSH 登入服务器并依次执行以下流程：

```bash
# 1. 登录云服务器后，进入挂载代码的主工作区
cd ~/productShow

# 2. 从 Github 远端拉取刚才推送的最新应用代码更新（确保当前分支无冲突）
git fetch --all
git pull origin feature-login

# 3. 切换到部署编排目录
cd deploy

# 4. 根据最新代码重新打包构建，并剔除旧容器后台启动运行，重建整个前台或后端架构
docker-compose -f docker-compose.yml up -d --build
```
> **提示**：如果仅更新且不需要更新依赖缓存，以上四步是最稳健也是最自动化的方案。构建和重启期间 `Nginx` 将能够自动完成切流。

## 🌐 环境访问指引 (当前线上云服务器节点)

在基于 Docker Compose 的整体部署架构下，以下为当前服务器 (`8.166.113.25`) 分配服务使用的端口网络矩阵与统一访问入口：

### 1. 前台官网门户 (面向全球用户)
* **正式访问入口（推荐）**：`http://8.166.113.25/`
  * *说明：基于 Nginx 的代理出口，内部封装优化防重载等。支持多语言后缀如 `/zh/` 或 `/en/`。*
* **Nuxt 直连通道（预留诊断）**：`http://8.166.113.25:3000/`
  * *说明：绕开 Nginx 服务器直接访问内层 Node 渲染结果，调试 502/缓存时推荐使用。*

### 2. 业务运营系统 (内部员工端)
* **若依企业管理后台**：`http://8.166.113.25:8081/`
  * *说明：基于 Vue3 的运营管理面板应用。在此端配置所有多语言支持、产品、参数与图片管理等。*

### 3. 服务器核心接口与数据库资源
* **纯享核心 API URL (Spring)**：`http://8.166.113.25:8080/` 或者借助前置转发访问 `http://8.166.113.25/api/`
* **Swagger 接口白皮书文档**：`http://8.166.113.25:8080/swagger-ui/index.html`
* **主业务数据库 (MySQL 8.0)**：映射外网端口 `3306` (通过 Navicat 等客户端连接维护)
* **对象高速缓存 (Redis)**：映射外网端口 `6379`

## 模块清单
- 产品中心（品类、商品、规格参数）
- 内容模块（新闻、事件、轮播图）
- 技术支持（FAQ分类与问答、相关固件及文档下载、视频指南、模拟器链接、表单工单）
- 品牌渠道（关于我们里程碑、全球渠道经销商分布）
