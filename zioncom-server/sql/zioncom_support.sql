SET NAMES utf8mb4;
-- ============================================================
-- Zioncom 官网数据库初始化脚本 - 固件/技术支持模块
-- 表：固件版本、产品文档、FAQ分类、FAQ、技术视频、Web模拟器、支持请求
-- ============================================================

SET NAMES utf8mb4;

-- ----------------------------
-- 固件版本表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_firmware` (
  `id`            bigint       NOT NULL AUTO_INCREMENT COMMENT '固件ID',
  `product_id`    bigint       NOT NULL COMMENT '关联产品ID',
  `version`       varchar(50)  NOT NULL COMMENT '固件版本号（如 v1.2.3）',
  `file_url`      varchar(500) NOT NULL COMMENT '固件文件OSS URL',
  `file_name`     varchar(200)          DEFAULT NULL COMMENT '固件文件原始名称',
  `file_size`     bigint                DEFAULT NULL COMMENT '文件大小（字节）',
  `release_note_zh` text               DEFAULT NULL COMMENT '更新说明（中文）',
  `release_note_en` text               DEFAULT NULL COMMENT '更新说明（英文）',
  `release_date`  date                  DEFAULT NULL COMMENT '发布日期',
  `status`        tinyint      NOT NULL DEFAULT 1 COMMENT '状态（1正常 0禁用）',
  `create_by`     varchar(64)           DEFAULT NULL COMMENT '创建者',
  `create_time`   datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`   datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag`      char(1)      NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_release_date` (`release_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='固件版本表';

-- ----------------------------
-- 产品文档表（用户手册、Datasheet、快速安装指南等）
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_document` (
  `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '文档ID',
  `product_id`  bigint       NOT NULL COMMENT '关联产品ID',
  `doc_type`    varchar(50)  NOT NULL DEFAULT 'manual' COMMENT '文档类型（manual用户手册 datasheet规格书 quickguide快速指南 other其他）',
  `title_zh`    varchar(200) NOT NULL COMMENT '文档标题（中文）',
  `title_en`    varchar(200) NOT NULL COMMENT '文档标题（英文）',
  `file_url`    varchar(500) NOT NULL COMMENT '文档文件OSS URL',
  `file_name`   varchar(200)          DEFAULT NULL COMMENT '文件原始名称',
  `file_size`   bigint                DEFAULT NULL COMMENT '文件大小（字节）',
  `sort_order`  int          NOT NULL DEFAULT 0 COMMENT '显示排序',
  `status`      tinyint      NOT NULL DEFAULT 1 COMMENT '状态（1正常 0禁用）',
  `create_by`   varchar(64)           DEFAULT NULL COMMENT '创建者',
  `create_time` datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag`    char(1)      NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品文档表（手册/Datasheet）';

-- ----------------------------
-- FAQ分类表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_faq_category` (
  `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name_zh`     varchar(100) NOT NULL COMMENT '分类名称（中文）',
  `name_en`     varchar(100) NOT NULL COMMENT '分类名称（英文）',
  `sort_order`  int          NOT NULL DEFAULT 0 COMMENT '显示排序',
  `status`      tinyint      NOT NULL DEFAULT 1 COMMENT '状态（1正常 0停用）',
  `create_time` datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='FAQ分类表';

-- ----------------------------
-- FAQ问答表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_faq` (
  `id`           bigint       NOT NULL AUTO_INCREMENT COMMENT 'FAQ ID',
  `category_id`  bigint                DEFAULT NULL COMMENT 'FAQ分类ID',
  `question_zh`  text         NOT NULL COMMENT '问题（中文）',
  `question_en`  text         NOT NULL COMMENT '问题（英文）',
  `answer_zh`    longtext     NOT NULL COMMENT '回答（中文）',
  `answer_en`    longtext     NOT NULL COMMENT '回答（英文）',
  `sort_order`   int          NOT NULL DEFAULT 0 COMMENT '显示排序',
  `status`       tinyint      NOT NULL DEFAULT 1 COMMENT '状态（1显示 0隐藏）',
  `create_by`    varchar(64)           DEFAULT NULL COMMENT '创建者',
  `create_time`  datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`  datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag`     char(1)      NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='FAQ问答表';

-- ----------------------------
-- 技术视频表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_tech_video` (
  `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '视频ID',
  `product_id`  bigint                DEFAULT NULL COMMENT '关联产品ID（可为空表示通用视频）',
  `title_zh`    varchar(200) NOT NULL COMMENT '视频标题（中文）',
  `title_en`    varchar(200) NOT NULL COMMENT '视频标题（英文）',
  `desc_zh`     text                  DEFAULT NULL COMMENT '视频描述（中文）',
  `desc_en`     text                  DEFAULT NULL COMMENT '视频描述（英文）',
  `video_url`   varchar(500) NOT NULL COMMENT '视频嵌入URL（YouTube/Bilibili嵌入链接）',
  `cover_image` varchar(500)          DEFAULT NULL COMMENT '视频封面图URL',
  `duration`    varchar(20)           DEFAULT NULL COMMENT '时长（如 05:30）',
  `sort_order`  int          NOT NULL DEFAULT 0 COMMENT '显示排序',
  `status`      tinyint      NOT NULL DEFAULT 1 COMMENT '状态（1显示 0隐藏）',
  `create_by`   varchar(64)           DEFAULT NULL COMMENT '创建者',
  `create_time` datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='技术视频表';

-- ----------------------------
-- Web模拟器表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_web_emulator` (
  `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '模拟器ID',
  `product_id`  bigint                DEFAULT NULL COMMENT '关联产品ID',
  `model`       varchar(100) NOT NULL COMMENT '适用产品型号',
  `title_zh`    varchar(200) NOT NULL COMMENT '模拟器标题（中文）',
  `title_en`    varchar(200) NOT NULL COMMENT '模拟器标题（英文）',
  `url`         varchar(500) NOT NULL COMMENT '模拟器访问URL',
  `sort_order`  int          NOT NULL DEFAULT 0 COMMENT '显示排序',
  `status`      tinyint      NOT NULL DEFAULT 1 COMMENT '状态（1显示 0隐藏）',
  `create_time` datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Web模拟器表';

-- ----------------------------
-- 技术支持请求表（邮件技术支持表单）
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_support_request` (
  `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '请求ID',
  `name`        varchar(100) NOT NULL COMMENT '用户姓名',
  `email`       varchar(200) NOT NULL COMMENT '用户邮箱',
  `product_model` varchar(100)        DEFAULT NULL COMMENT '产品型号',
  `subject`     varchar(300)          DEFAULT NULL COMMENT '问题主题',
  `message`     text         NOT NULL COMMENT '问题描述',
  `status`      varchar(20)  NOT NULL DEFAULT 'pending' COMMENT '处理状态（pending待处理 processing处理中 resolved已解决）',
  `remark`      varchar(500)          DEFAULT NULL COMMENT '内部备注',
  `create_time` datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  `update_time` datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='技术支持请求表';

-- 初始化FAQ分类
INSERT INTO `zc_faq_category` (`name_zh`, `name_en`, `sort_order`) VALUES
('无线连接问题', 'Wireless Connection', 1),
('路由器设置', 'Router Setup', 2),
('固件升级', 'Firmware Upgrade', 3),
('产品兼容性', 'Product Compatibility', 4),
('其他问题', 'Others', 5);
