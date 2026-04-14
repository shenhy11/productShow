## ADDED Requirements

### Requirement: 固件版本管理
系统 SHALL 支持按产品型号管理固件版本，每个固件记录包含：关联产品、版本号、文件（上传至 OSS）、Release Note、发布日期、状态。

#### Scenario: 上传新固件版本
- **WHEN** 管理员为某产品上传新的固件文件并填写版本号和 Release Note
- **THEN** 系统创建固件记录，文件上传至 OSS，前台该产品详情页展示最新固件下载链接

#### Scenario: 查看固件历史版本
- **WHEN** 用户在前台产品详情页点击「固件下载」
- **THEN** 系统展示该产品的所有固件版本列表（按版本号倒序），用户可下载任意版本

### Requirement: 用户手册与 Datasheet 管理
系统 SHALL 支持为产品关联用户手册和 Datasheet PDF 文档，支持上传、替换、删除。

#### Scenario: 上传产品手册
- **WHEN** 管理员为产品上传 PDF 格式的用户手册
- **THEN** 文件上传至 OSS，前台产品详情页展示手册下载链接

### Requirement: 型号精准搜索下载
系统 SHALL 在前台 Download Center 提供按型号搜索的固件/文档精准查找功能。

#### Scenario: 按型号搜索固件
- **WHEN** 用户在 Download Center 输入完整或部分产品型号（如 "A3002RU"）
- **THEN** 系统返回匹配产品的固件和文档列表，可直接下载
