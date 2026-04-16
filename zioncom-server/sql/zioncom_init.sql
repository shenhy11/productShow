SET NAMES utf8mb4;
-- ============================================================
-- Zioncom 主初始化入口脚本
-- 按顺序执行各模块的初始化脚本
-- 使用方式：在 MySQL 中执行 source zioncom_init.sql
-- ============================================================

SET NAMES utf8mb4;

-- 创建数据库（如不存在）
CREATE DATABASE IF NOT EXISTS `zioncom` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `zioncom`;

-- 执行各模块脚本（Docker 启动时由 entrypoint 按文件名顺序自动加载）
SOURCE zioncom_product.sql;
SOURCE zioncom_content.sql;
SOURCE zioncom_support.sql;
SOURCE zioncom_brand.sql;
SOURCE zioncom_oss.sql;

SELECT '✅ Zioncom 数据库初始化完成' AS message;
