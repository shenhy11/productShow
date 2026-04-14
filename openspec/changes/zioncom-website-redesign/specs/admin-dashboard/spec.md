## ADDED Requirements

### Requirement: RuoYi 权限体系复用
系统 SHALL 复用 RuoYi 内置的 RBAC 权限体系，通过角色和菜单权限控制后台各业务模块的访问。

#### Scenario: 角色权限分配
- **WHEN** 超级管理员为"运营编辑"角色分配"产品管理"和"内容管理"菜单权限
- **THEN** 该角色用户登录后仅可见被授权的菜单和操作按钮

### Requirement: 产品管理后台页面
系统 SHALL 在 Admin 后台提供产品管理模块，包含产品列表（分页/搜索/筛选）、产品新增/编辑表单、分类管理树、规格参数管理。

#### Scenario: 管理员管理产品列表
- **WHEN** 管理员访问后台产品管理页面
- **THEN** 系统展示产品表格（型号、名称、分类、状态、创建时间），支持按分类筛选和关键词搜索

### Requirement: 内容管理后台页面
系统 SHALL 在 Admin 后台提供内容管理模块，包含文章列表、文章编辑（富文本）、分类标签管理。

#### Scenario: 管理员编辑文章
- **WHEN** 管理员在后台编辑一篇新闻文章
- **THEN** 编辑页面提供标题（中英文）、分类选择、封面图上传、富文本正文编辑器、SEO 元信息配置

### Requirement: SEO 配置管理
系统 SHALL 在 Admin 后台提供 SEO 配置功能，支持为产品和文章自定义 SEO title、meta description、OG 标签。

#### Scenario: 配置产品 SEO 信息
- **WHEN** 管理员在产品编辑页填写 SEO title 和 meta description
- **THEN** 前台该产品详情页的 HTML head 输出对应的 title 标签和 meta description 标签
