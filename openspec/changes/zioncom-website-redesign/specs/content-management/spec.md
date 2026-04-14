## ADDED Requirements

### Requirement: 文章管理 CRUD
系统 SHALL 支持新闻和事件两种文章类型的完整管理，包括创建、编辑、发布、撤回、删除。文章信息包含：标题（中英文）、类型（News/Event）、封面图、正文富文本、发布日期、状态（草稿/已发布）、SEO 元信息。

#### Scenario: 创建文章草稿
- **WHEN** 管理员创建新文章并保存
- **THEN** 系统创建状态为「草稿」的文章记录，不在前台展示

#### Scenario: 发布文章
- **WHEN** 管理员将文章状态从「草稿」切换为「已发布」
- **THEN** 文章在前台对应栏目（News 或 Events）中按发布日期倒序展示

#### Scenario: 撤回已发布文章
- **WHEN** 管理员将已发布的文章状态切回「草稿」
- **THEN** 文章从前台隐藏，后台仍可编辑

### Requirement: 文章分类展示
系统 SHALL 在前台区分 News 和 Events 两个栏目，各自独立展示并支持分页浏览。Product Launch 作为 News 的子分类展示。

#### Scenario: 前台浏览新闻列表
- **WHEN** 用户访问 /news 页面
- **THEN** 系统展示所有已发布的 News 类型文章，按发布日期倒序，每页 10 条

#### Scenario: 前台浏览事件列表
- **WHEN** 用户访问 /events 页面
- **THEN** 系统展示所有已发布的 Event 类型文章，按发布日期倒序

### Requirement: 富文本编辑器
系统 SHALL 为文章和产品描述提供富文本编辑功能，支持文本格式化、图片插入（上传到 OSS）、视频嵌入。

#### Scenario: 编辑器插入图片
- **WHEN** 管理员在富文本编辑器中上传图片
- **THEN** 图片自动上传至 OSS，返回 URL 插入到编辑器内容中
