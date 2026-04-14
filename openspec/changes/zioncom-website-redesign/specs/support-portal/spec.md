## ADDED Requirements

### Requirement: FAQ 管理与展示
系统 SHALL 支持 FAQ 问答对的后台管理（CRUD）和前台分类展示。

#### Scenario: 管理 FAQ
- **WHEN** 管理员在后台创建 FAQ 条目（问题、回答、分类、排序）
- **THEN** FAQ 条目在前台 /support/faq 页面按分类分组展示，支持折叠/展开

#### Scenario: 前台浏览 FAQ
- **WHEN** 用户访问 /support/faq 页面
- **THEN** 系统展示 FAQ 列表，按分类分组，点击问题展开显示答案

### Requirement: Web 模拟器入口
系统 SHALL 在技术支持页面提供产品 Web 模拟器的外部链接入口，按产品型号分类列出可用的模拟器。

#### Scenario: 访问 Web 模拟器列表
- **WHEN** 用户访问 /support/web-emulators 页面
- **THEN** 系统展示可用的 Web 模拟器列表，每个条目包含产品型号和模拟器链接

### Requirement: 技术视频管理
系统 SHALL 支持后台管理技术指导视频（Tech Tip Video），前台按分类展示视频列表。

#### Scenario: 管理技术视频
- **WHEN** 管理员在后台添加技术视频（标题、描述、视频嵌入 URL、所属产品）
- **THEN** 视频在前台 /support/tech-videos 页面展示

### Requirement: 邮件技术支持表单
系统 SHALL 提供邮件技术支持表单，用户可提交技术问题，系统保存记录供后台查看。

#### Scenario: 提交技术支持请求
- **WHEN** 用户在 /support/email-support 页面填写产品型号、问题描述、联系邮箱并提交
- **THEN** 系统保存支持请求记录到数据库，后台可查看和处理
