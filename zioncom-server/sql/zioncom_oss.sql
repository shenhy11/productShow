SET NAMES utf8mb4;
-- ============================================================
-- Zioncom 官网数据库初始化脚本 - OSS文件记录模块
-- 表：OSS文件记录
-- ============================================================

SET NAMES utf8mb4;

-- ----------------------------
-- OSS文件记录表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_oss_file` (
  `id`           bigint       NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `file_name`    varchar(200) NOT NULL COMMENT '原始文件名',
  `object_key`   varchar(500) NOT NULL COMMENT 'OSS对象Key（如 images/2024/product-cover.jpg）',
  `file_url`     varchar(500) NOT NULL COMMENT '文件访问URL（CDN地址）',
  `file_size`    bigint                DEFAULT NULL COMMENT '文件大小（字节）',
  `file_type`    varchar(50)           DEFAULT NULL COMMENT '文件MIME类型（如 image/jpeg）',
  `category`     varchar(50)  NOT NULL DEFAULT 'other' COMMENT '文件分类（image图片 firmware固件 document文档 other其他）',
  `create_by`    varchar(64)           DEFAULT NULL COMMENT '上传者',
  `create_time`  datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_object_key` (`object_key`),
  KEY `idx_category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='OSS文件记录表';
