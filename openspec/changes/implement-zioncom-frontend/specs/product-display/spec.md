## 新增需求 (ADDED Requirements)

### 需求: 多产品类目展示页面 (Multi-category Display Page)
系统必须能够正确渲染主产品索引页面，该页面需要展示基于侧边栏或顶部选项卡过滤的 14 个以上（14+）的产品层级类目标签。

#### 场景: 在目录列表中切换分类栏目 (Switching categories on the list)
- **当 (WHEN)** 用户选中了 `无线网卡 / Wireless USB Adapter` 这个分类时
- **那么 (THEN)** 产品网格必须即时刷新以仅展示属于该子类的产品（支持 SSR 模式渲染并拥有独立 URL）。

### 需求: 高保真产品全景视图 (Detailed Product View)
系统必须能够在产品专属详情页全方位挂载并展示高质量主图、核心硬件参数规格矩阵参数以及对应的硬件周边固件下挂源节点链接。

#### 场景: 深入访问具体产品信息详情视图 (Accessing product details)
- **当 (WHEN)** 页面路由跳转导向至 `/products/[id]` 时
- **那么 (THEN)** 视图界面平滑加载并在客户端抓取完整的参数表（包含中英文摘要及富文本主体）以构建页面。
