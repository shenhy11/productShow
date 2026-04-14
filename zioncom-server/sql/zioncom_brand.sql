-- ============================================================
-- Zioncom 官网数据库初始化脚本 - 品牌展示模块
-- 表：里程碑、全球渠道商、联系消息
-- ============================================================

SET NAMES utf8mb4;

-- ----------------------------
-- 里程碑时间线表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_milestone` (
  `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '里程碑ID',
  `year`        int          NOT NULL COMMENT '年份',
  `title_zh`    varchar(200) NOT NULL COMMENT '事件标题（中文）',
  `title_en`    varchar(200) NOT NULL COMMENT '事件标题（英文）',
  `desc_zh`     text                  DEFAULT NULL COMMENT '事件描述（中文）',
  `desc_en`     text                  DEFAULT NULL COMMENT '事件描述（英文）',
  `image_url`   varchar(500)          DEFAULT NULL COMMENT '配图URL',
  `sort_order`  int          NOT NULL DEFAULT 0 COMMENT '同年内排序',
  `status`      tinyint      NOT NULL DEFAULT 1 COMMENT '状态（1显示 0隐藏）',
  `create_by`   varchar(64)           DEFAULT NULL COMMENT '创建者',
  `create_time` datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_year` (`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='里程碑时间线表';

-- ----------------------------
-- 全球渠道商表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_worldwide_partner` (
  `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '渠道商ID',
  `region`      varchar(100) NOT NULL COMMENT '所在区域（如 Asia Pacific、Europe）',
  `country`     varchar(100)          DEFAULT NULL COMMENT '国家/地区',
  `name`        varchar(200) NOT NULL COMMENT '渠道商名称',
  `website`     varchar(300)          DEFAULT NULL COMMENT '官网URL',
  `email`       varchar(200)          DEFAULT NULL COMMENT '联系邮箱',
  `phone`       varchar(50)           DEFAULT NULL COMMENT '联系电话',
  `address`     varchar(500)          DEFAULT NULL COMMENT '地址',
  `longitude`   decimal(10,6)         DEFAULT NULL COMMENT '经度（用于地图标注）',
  `latitude`    decimal(10,6)         DEFAULT NULL COMMENT '纬度（用于地图标注）',
  `logo_url`    varchar(500)          DEFAULT NULL COMMENT '渠道商Logo URL',
  `sort_order`  int          NOT NULL DEFAULT 0 COMMENT '显示排序',
  `status`      tinyint      NOT NULL DEFAULT 1 COMMENT '状态（1显示 0隐藏）',
  `create_by`   varchar(64)           DEFAULT NULL COMMENT '创建者',
  `create_time` datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_region` (`region`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='全球渠道商表';

-- ----------------------------
-- 联系消息表（联系我们表单提交记录）
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_contact_message` (
  `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `name`        varchar(100) NOT NULL COMMENT '姓名',
  `email`       varchar(200) NOT NULL COMMENT '邮箱',
  `company`     varchar(200)          DEFAULT NULL COMMENT '公司名称',
  `phone`       varchar(50)           DEFAULT NULL COMMENT '联系电话',
  `subject`     varchar(300)          DEFAULT NULL COMMENT '主题',
  `message`     text         NOT NULL COMMENT '留言内容',
  `is_read`     tinyint      NOT NULL DEFAULT 0 COMMENT '是否已读（0未读 1已读）',
  `create_time` datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  PRIMARY KEY (`id`),
  KEY `idx_is_read` (`is_read`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='联系消息表';

-- 初始化里程碑示例数据
INSERT INTO `zc_milestone` (`year`, `title_zh`, `title_en`, `desc_zh`, `desc_en`, `sort_order`) VALUES
(2005, '公司成立', 'Company Founded',
  '吉翁电子（深圳）有限公司正式成立，专注于网络通信设备的研发与制造。',
  'Zioncom Electronics (Shenzhen) Co., Ltd. was established, focusing on R&D and manufacturing of network communication devices.', 1),
(2010, '进入全球市场', 'Entering Global Markets',
  '产品销售网络扩展至欧洲、北美等主要市场，海外业务占比突破50%。',
  'Sales network expanded to Europe, North America and other major markets, with overseas business exceeding 50%.', 1),
(2018, '推出Wi-Fi 5系列产品', 'Launching Wi-Fi 5 Product Line',
  '率先推出802.11ac Wave 2全系列路由产品，获得多项国际认证。',
  'Launched a full range of 802.11ac Wave 2 routing products, earning multiple international certifications.', 1),
(2022, '推出Wi-Fi 6旗舰产品', 'Wi-Fi 6 Flagship Launch',
  '发布Wi-Fi 6（802.11ax）全系列产品，覆盖家用、企业及运营商多个市场细分。',
  'Released a full Wi-Fi 6 (802.11ax) product range covering home, enterprise and carrier market segments.', 1);
