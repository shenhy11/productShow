## ADDED Requirements

### Requirement: 关于我们页面
系统 SHALL 提供品牌介绍页面，展示公司简介、企业愿景、核心优势等信息，内容支持后台管理编辑。

#### Scenario: 访问关于我们页面
- **WHEN** 用户访问 /about 页面
- **THEN** 系统展示公司品牌介绍的 SSG 静态页面，包含企业简介、核心技术优势

### Requirement: 里程碑时间线
系统 SHALL 在 About 区域提供企业发展里程碑时间线展示，按年份倒序展示关键事件。

#### Scenario: 查看里程碑
- **WHEN** 用户访问 /about/milestones 页面
- **THEN** 系统展示企业发展历程的时间线视图，每个节点包含年份、事件标题和描述

### Requirement: 工厂与实验室展示
系统 SHALL 提供工厂和研发实验室的图文展示页面，支持高质量图片画廊。

#### Scenario: 浏览工厂与实验室
- **WHEN** 用户访问 /about/factory-lab 页面
- **THEN** 系统展示 SMT 产线、实验室等场景的图片画廊和文字说明

### Requirement: 全球渠道商展示
系统 SHALL 在 Worldwide 页面通过交互式地图展示全球各地区的分销商/代理商据点。

#### Scenario: 查看全球渠道分布
- **WHEN** 用户访问 /worldwide 页面
- **THEN** 系统展示交互式世界地图，标注各地区渠道商位置，点击标记可查看详细信息

### Requirement: 联系我们页面
系统 SHALL 提供联系方式页面，展示公司地址、电话、邮箱，并提供联系表单。

#### Scenario: 提交联系表单
- **WHEN** 用户在联系页面填写姓名、邮箱、留言内容并提交
- **THEN** 系统保存联系记录到数据库，后台可查看联系记录列表
