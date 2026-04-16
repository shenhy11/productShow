## 变更原因 (Why)

Zioncom 目前这套 Nuxt 3 前端仍处于基础脚手架状态，极度缺乏生产环境企业官网应具备的稳健 UI 组件、业务专有视图以及详实的内容保真度。为了将其从原型外壳升级为拥有全球服务能力、良好 SEO 表现的系统，我们必须将先前在官网 `zioncom.net` 获取到并提纯的设计资产与组件真实数据结构（详情查阅 `Zioncom_Architecture_Design.md`）体系化地落地实现。

## 涉及改动点 (What Changes)

- 利用新鲜出炉的设计范式重建站点的视觉主心骨（涵盖 玻璃拟态、高级暗夜科技感 以及微动效等特征）。
- 创建稳健严谨的核心 UI 自定义组件群（如 `ZBreadcrumb`，`ZProductCard`，`ZCascadeSelector`，`ZDataGrid`）将其深度集成至 Vue 3 核心 Composable 链路与 Nuxt 3 的布局内。
- 设计、绘制并组织异常复杂的 14 大级产品 `Products` 分类网格呈现架构。
- 搭建 `Support`（支持中心）系列组件池，尤其着重依靠动态的三阶复合防抖级联数据交互设计出企业版下载中心检索能力。
- 全面挂载早前爬取解析到的 JSON 结构数据至前端充当前后解耦联调前的主测试 Mock 数据，以便即时验证界面。

## 能力解构 (Capabilities)

### 新增能力区块
- `ui-components`: 专门面向 Zioncom 专属框架体系创作的可重用的原子 UI 组件体系封装。
- `product-display`: 利用 Nuxt 3 底层构建的多重层级关系罗列、多态过滤及单个产品信息渲染机制引擎。
- `support-services`: 研发涉及强阻断多级下载搜寻器、支持服务中心和基于异步状态更新的表格矩阵交互面板。

### 修改的能力区块
- 暂无

## 影响面 (Impact)

- 核心组件重构 `zioncom-nuxt/components/*`
- 业务聚合页重构 `zioncom-nuxt/pages/*`
- 顶层视图及入口 `zioncom-nuxt/layouts/*`
- 彻底刷新 Nuxt 纯前端 CSS 全局视觉体系与样式绑定。
- 大幅扩展并完善 Mock 体系以及增强网络库 `useHttp` 参数传递模型适配性。
