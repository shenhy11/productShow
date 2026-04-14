## ADDED Requirements

### Requirement: CI 构建与测试
系统 SHALL 在代码推送时自动触发 CI 流水线，分别构建后端（Maven）、前台（Nuxt）和 Admin（Vue）三个子项目，并运行单元测试。

#### Scenario: 推送代码触发 CI
- **WHEN** 开发者向 feature/* 或 develop 分支推送代码
- **THEN** CI 流水线自动执行：后端 mvn package + 前端 pnpm build，测试失败时阻止合并

### Requirement: Docker 镜像构建与推送
系统 SHALL 在 CI 构建成功后，为各服务构建 Docker 镜像并推送至云厂商容器镜像仓库（ACR/TCR）。

#### Scenario: 构建并推送镜像
- **WHEN** develop 或 main 分支的 CI 构建通过
- **THEN** 系统自动构建 zioncom-api、zioncom-web、zioncom-admin 三个 Docker 镜像，打 tag 并推送到镜像仓库

### Requirement: 自动化部署
系统 SHALL 支持 main 分支合并后自动部署到生产环境云主机。

#### Scenario: 生产环境自动部署
- **WHEN** 代码合并到 main 分支
- **THEN** CI/CD 通过 SSH 连接云主机，执行 docker-compose pull && docker-compose up -d，部署完成后执行健康检查

#### Scenario: 部署失败回滚
- **WHEN** 部署后健康检查失败
- **THEN** 自动执行 docker-compose up -d --force-recreate 回滚到上一个可用镜像版本
