## 新增需求 (ADDED Requirements)

### 需求: 全局极尽的中文/国际化切换彻底支持 (Absolute Multilingual Support)
站点的任何主要层级结构包含并不仅限于：全局导航头（Nav）、全局页脚（Footer）、内部多层级侧边栏、搜索按钮字词等，其英文字符均不容许硬编码暴露在代码中，**必须全量剥离**替换为 `$t('xxx')` 指向的国际化字段。

#### 场景: 进行全站的中英文一键切变断言 (Toggle language test)
- **当 (WHEN)** 用户在多语言选择框击下偏好设定 "简体中文 / zh"
- **那么 (THEN)** 所有原生的 `Search`、`Contact`、`Home` 字眼都必须无死角替换为中文对应的翻译项。
