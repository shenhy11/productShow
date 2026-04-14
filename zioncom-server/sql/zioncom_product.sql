-- ============================================================
-- Zioncom 官网数据库初始化脚本 - 产品模块
-- 表：产品分类、产品、产品规格参数
-- ============================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 产品分类表（支持多级树形结构）
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_product_category` (
  `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `parent_id`   bigint       NOT NULL DEFAULT 0 COMMENT '父分类ID，0表示顶级分类',
  `name_zh`     varchar(100) NOT NULL COMMENT '分类名称（中文）',
  `name_en`     varchar(100) NOT NULL COMMENT '分类名称（英文）',
  `icon`        varchar(255)          DEFAULT NULL COMMENT '分类图标URL',
  `sort_order`  int          NOT NULL DEFAULT 0 COMMENT '显示排序，数字越小越靠前',
  `status`      tinyint      NOT NULL DEFAULT 1 COMMENT '状态（1正常 0停用）',
  `create_by`   varchar(64)           DEFAULT NULL COMMENT '创建者',
  `create_time` datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by`   varchar(64)           DEFAULT NULL COMMENT '更新者',
  `update_time` datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag`    char(1)      NOT NULL DEFAULT '0' COMMENT '删除标志（0正常 2删除）',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品分类表';

-- ----------------------------
-- 产品表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_product` (
  `id`            bigint       NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `category_id`   bigint       NOT NULL COMMENT '所属分类ID',
  `model`         varchar(100) NOT NULL COMMENT '产品型号（如 WR1200）',
  `name_zh`       varchar(200) NOT NULL COMMENT '产品名称（中文）',
  `name_en`       varchar(200) NOT NULL COMMENT '产品名称（英文）',
  `summary_zh`    text                  DEFAULT NULL COMMENT '产品摘要（中文）',
  `summary_en`    text                  DEFAULT NULL COMMENT '产品摘要（英文）',
  `detail_zh`     longtext              DEFAULT NULL COMMENT '产品详情富文本（中文）',
  `detail_en`     longtext              DEFAULT NULL COMMENT '产品详情富文本（英文）',
  `cover_image`   varchar(500)          DEFAULT NULL COMMENT '封面图URL（OSS路径）',
  `banner_images` varchar(2000)         DEFAULT NULL COMMENT '轮播图URL列表，逗号分隔',
  `status`        varchar(10)  NOT NULL DEFAULT 'draft' COMMENT '状态（published上架 draft下架 discontinued停产）',
  `seo_title_zh`  varchar(200)          DEFAULT NULL COMMENT 'SEO标题（中文）',
  `seo_title_en`  varchar(200)          DEFAULT NULL COMMENT 'SEO标题（英文）',
  `seo_desc_zh`   varchar(500)          DEFAULT NULL COMMENT 'SEO描述（中文）',
  `seo_desc_en`   varchar(500)          DEFAULT NULL COMMENT 'SEO描述（英文）',
  `sort_order`    int          NOT NULL DEFAULT 0 COMMENT '分类内排序',
  `create_by`     varchar(64)           DEFAULT NULL COMMENT '创建者',
  `create_time`   datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by`     varchar(64)           DEFAULT NULL COMMENT '更新者',
  `update_time`   datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag`      char(1)      NOT NULL DEFAULT '0' COMMENT '删除标志（0正常 2删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_model` (`model`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品表';

-- ----------------------------
-- 产品规格参数表（动态 key-value 键值对）
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_product_spec` (
  `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '规格ID',
  `product_id`  bigint       NOT NULL COMMENT '产品ID',
  `spec_group`  varchar(100)          DEFAULT NULL COMMENT '规格分组（如 WiFi规格、接口规格）',
  `spec_key`    varchar(100) NOT NULL COMMENT '规格名称（如 Wi-Fi Standard）',
  `spec_value`  varchar(500) NOT NULL COMMENT '规格值（如 Wi-Fi 6 (802.11ax)）',
  `sort_order`  int          NOT NULL DEFAULT 0 COMMENT '显示排序',
  `create_time` datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品规格参数表';

-- ----------------------------
-- 初始化产品分类数据（对应原网站16个分类）
-- ----------------------------
INSERT INTO `zc_product_category` (`id`, `parent_id`, `name_zh`, `name_en`, `sort_order`) VALUES
(1,  0, '无线路由器',     'Wireless Router',          1),
(2,  0, '无线USB适配器',  'Wireless USB Adapter',     2),
(3,  0, '5G/4G Wi-Fi路由', '5G/4G Wi-Fi Router',      3),
(4,  0, 'ADSL',           'ADSL',                     4),
(5,  0, '信号放大器',     'Range Extender',            5),
(6,  0, '电力线适配器',   'Powerline Adapter',         6),
(7,  0, 'PCI/PCI-E适配器','PCI/PCI-E Adapter',        7),
(8,  0, '室外无线',       'Outdoor Wireless',          8),
(9,  0, '吸顶/面板AP',    'Ceiling AP & In-Wall AP',  9),
(10, 0, '网络适配器',     'Network Adapter',           10),
(11, 0, '交换机',         'Switch',                    11),
(12, 0, '移动电源',       'Portable Power Bank',       12),
(13, 0, 'IOT',            'IOT',                       13),
(14, 0, '智能会议系统',   'Smart Conference System',   14),
(15, 0, '智能存储',       'Smart Storage',             15),
(16, 0, '配件',           'Accessory',                 16),
(17, 0, '停产产品',       'Discontinued Model',        17);

SET FOREIGN_KEY_CHECKS = 1;
