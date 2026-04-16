SET NAMES utf8mb4;
-- ============================================================
-- Zioncom 官网数据库初始化脚本 - 内容模块
-- 表：文章分类、文章
-- ============================================================

SET NAMES utf8mb4;

-- ----------------------------
-- 文章表（新闻 + 事件，通过 type 字段区分）
-- ----------------------------
CREATE TABLE IF NOT EXISTS `zc_article` (
  `id`            bigint       NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `type`          varchar(20)  NOT NULL DEFAULT 'news' COMMENT '文章类型（news新闻 event事件）',
  `sub_type`      varchar(20)           DEFAULT NULL COMMENT '子类型（product_launch产品发布）',
  `title_zh`      varchar(300) NOT NULL COMMENT '文章标题（中文）',
  `title_en`      varchar(300) NOT NULL COMMENT '文章标题（英文）',
  `cover_image`   varchar(500)          DEFAULT NULL COMMENT '封面图URL',
  `summary_zh`    varchar(500)          DEFAULT NULL COMMENT '文章摘要（中文）',
  `summary_en`    varchar(500)          DEFAULT NULL COMMENT '文章摘要（英文）',
  `content_zh`    longtext              DEFAULT NULL COMMENT '文章内容富文本（中文）',
  `content_en`    longtext              DEFAULT NULL COMMENT '文章内容富文本（英文）',
  `publish_date`  date                  DEFAULT NULL COMMENT '发布日期',
  `status`        varchar(10)  NOT NULL DEFAULT 'draft' COMMENT '状态（published已发布 draft草稿）',
  `view_count`    int          NOT NULL DEFAULT 0 COMMENT '浏览次数',
  `seo_title_zh`  varchar(200)          DEFAULT NULL COMMENT 'SEO标题（中文）',
  `seo_title_en`  varchar(200)          DEFAULT NULL COMMENT 'SEO标题（英文）',
  `seo_desc_zh`   varchar(500)          DEFAULT NULL COMMENT 'SEO描述（中文）',
  `seo_desc_en`   varchar(500)          DEFAULT NULL COMMENT 'SEO描述（英文）',
  `author`        varchar(100)          DEFAULT NULL COMMENT '作者',
  `sort_order`    int          NOT NULL DEFAULT 0 COMMENT '显示排序',
  `create_by`     varchar(64)           DEFAULT NULL COMMENT '创建者',
  `create_time`   datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by`     varchar(64)           DEFAULT NULL COMMENT '更新者',
  `update_time`   datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag`      char(1)      NOT NULL DEFAULT '0' COMMENT '删除标志（0正常 2删除）',
  PRIMARY KEY (`id`),
  KEY `idx_type_status` (`type`, `status`),
  KEY `idx_publish_date` (`publish_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表（新闻/事件）';
