## ADDED Requirements

### Requirement: 前端多语言切换
系统 SHALL 通过 @nuxtjs/i18n 支持中英文双语切换，URL 采用前缀模式（/en/、/zh/）。

#### Scenario: 切换语言
- **WHEN** 用户点击前台语言切换按钮从中文切换到英文
- **THEN** 页面 URL 从 /zh/products 变为 /en/products，所有静态文本切换为英文，动态内容展示英文字段

#### Scenario: 搜索引擎索引多语言页面
- **WHEN** 搜索引擎爬虫访问产品页
- **THEN** 页面 HTML head 包含 hreflang 标签，指向对应的中英文版本 URL

### Requirement: 后端动态内容多语言
系统 SHALL 在数据库中为产品名、分类名、文章标题等动态内容字段存储中英文双字段（name_en/name_zh），API 支持按语言参数返回对应字段。

#### Scenario: API 按语言返回内容
- **WHEN** 前端请求 GET /api/products?lang=en
- **THEN** API 返回产品列表中 name 字段使用 name_en 值，summary 字段使用 summary_en 值

#### Scenario: 后台双语编辑
- **WHEN** 管理员在后台编辑产品信息
- **THEN** 编辑表单同时展示中文和英文输入框，支持并排编辑
