## ADDED Requirements

### Requirement: OSS 文件上传
系统 SHALL 提供统一的文件上传接口，将文件上传至云厂商 OSS（阿里云 OSS / 腾讯云 COS），返回可访问的文件 URL。

#### Scenario: 上传图片文件
- **WHEN** 管理员通过后台上传产品封面图（支持 jpg/png/webp 格式，单文件不超过 5MB）
- **THEN** 系统将图片上传至 OSS 指定 bucket 的 images/ 前缀路径下，返回 CDN 加速的访问 URL

#### Scenario: 上传固件文件
- **WHEN** 管理员上传固件压缩包（支持 zip/bin/img 格式，单文件不超过 100MB）
- **THEN** 系统将文件上传至 OSS 的 firmware/ 前缀路径下，返回文件 URL

### Requirement: 文件下载（预签名 URL）
系统 SHALL 支持生成 OSS 预签名 URL，供前台用户下载固件和文档，避免直接暴露 OSS bucket 地址。

#### Scenario: 生成固件下载链接
- **WHEN** 用户在前台点击固件下载按钮
- **THEN** 系统生成有效期为 1 小时的预签名 URL，浏览器开始下载文件

### Requirement: 文件管理
系统 SHALL 在后台提供文件管理界面，展示已上传文件列表，支持按类型筛选和删除操作。

#### Scenario: 查看已上传文件
- **WHEN** 管理员访问文件管理页面
- **THEN** 系统展示所有已上传文件列表，包含文件名、大小、类型、上传时间、URL

#### Scenario: 删除文件
- **WHEN** 管理员删除已上传的文件
- **THEN** 系统从 OSS 删除该文件，并清除数据库中对应的引用记录
