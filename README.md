# Zioncom Website Redesign

吉翁电子（Zioncom）官网重构项目 — 前后端分离架构

## 项目结构

```
zioncom-website/
├── zioncom-nuxt/          # Nuxt 3 官网前台 (SSR/SSG)
├── zioncom-admin/         # RuoYi-Vue3 管理后台前端
├── zioncom-server/        # RuoYi Spring Boot 后端 API
├── nginx/                 # Nginx 配置
├── docker/                # Docker 相关配置
├── .github/workflows/     # CI/CD 流水线
├── docker-compose.yml     # 本地开发环境编排
└── docker-compose.prod.yml # 生产环境编排
```

## 技术栈

| 层级 | 技术 |
|------|------|
| 前台前端 | Nuxt 3 (Vue 3) + @nuxtjs/i18n |
| 管理后台 | RuoYi-Vue3 (Vue 3 + Element Plus) |
| 后端 API | Spring Boot 2.x (RuoYi 底座) |
| 数据库 | MySQL 8.x + Redis |
| 文件存储 | 阿里云 OSS / 腾讯云 COS |
| 容器化 | Docker + Docker Compose |
| CI/CD | GitHub Actions |
| 反向代理 | Nginx |

## 快速启动

```bash
# 启动全部服务（开发环境）
docker-compose up -d

# 服务访问地址
# 官网前台: http://localhost:3000
# 管理后台: http://localhost:8080/admin
# API:      http://localhost:8080/api
```

## 开发文档

详见 `openspec/changes/zioncom-website-redesign/` 目录下的规划文档。
