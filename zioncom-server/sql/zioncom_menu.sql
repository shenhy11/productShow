SET NAMES utf8mb4;
-- ============================================================
-- Zioncom Menu SQL - 为 RuoYi 系统添加左侧路由菜单
-- 涵盖：产品、内容、支持、品牌、OSS 模块
-- ============================================================

-- 父菜单
INSERT INTO `sys_menu` VALUES (2000, 'Zioncom 官网', 0, 1, 'zioncom', null, '', 1, 0, 'M', '0', '0', '', 'dict', 'admin', sysdate(), '', null, 'Zioncom业务目录');

-- 产品模块
INSERT INTO `sys_menu` VALUES (2001, '产品管理', 2000, 1, 'product', 'product/index', '', 1, 0, 'C', '0', '0', 'product:list', 'tree', 'admin', sysdate(), '', null, '产品模块');
INSERT INTO `sys_menu` VALUES (2002, '产品分类', 2000, 2, 'category', 'product/category/index', '', 1, 0, 'C', '0', '0', 'category:list', 'tree-table', 'admin', sysdate(), '', null, '产品分类模块');

-- 内容模块
INSERT INTO `sys_menu` VALUES (2003, '新闻事件', 2000, 3, 'article', 'content/article/index', '', 1, 0, 'C', '0', '0', 'article:list', 'form', 'admin', sysdate(), '', null, '文章模块');

-- 支持模块
INSERT INTO `sys_menu` VALUES (2004, '固件管理', 2000, 4, 'firmware', 'support/firmware/index', '', 1, 0, 'C', '0', '0', 'firmware:list', 'download', 'admin', sysdate(), '', null, '固件');
INSERT INTO `sys_menu` VALUES (2005, '文档管理', 2000, 5, 'document', 'support/document/index', '', 1, 0, 'C', '0', '0', 'document:list', 'documentation', 'admin', sysdate(), '', null, '文档');
INSERT INTO `sys_menu` VALUES (2006, 'FAQ管理', 2000, 6, 'faq', 'support/faq/index', '', 1, 0, 'C', '0', '0', 'faq:list', 'question', 'admin', sysdate(), '', null, 'FAQ');
INSERT INTO `sys_menu` VALUES (2007, '技术视频', 2000, 7, 'video', 'support/techVideo/index', '', 1, 0, 'C', '0', '0', 'video:list', 'video', 'admin', sysdate(), '', null, '技术视频');
INSERT INTO `sys_menu` VALUES (2008, '模拟器', 2000, 8, 'emulator', 'support/webEmulator/index', '', 1, 0, 'C', '0', '0', 'emulator:list', 'monitor', 'admin', sysdate(), '', null, '网页模拟器');
INSERT INTO `sys_menu` VALUES (2009, '支持请求', 2000, 9, 'request', 'support/supportRequest/index', '', 1, 0, 'C', '0', '0', 'request:list', 'message', 'admin', sysdate(), '', null, '支持请求');

-- 品牌模块
INSERT INTO `sys_menu` VALUES (2010, '发展历程', 2000, 10, 'milestone', 'brand/milestone/index', '', 1, 0, 'C', '0', '0', 'milestone:list', 'date', 'admin', sysdate(), '', null, '历程');
INSERT INTO `sys_menu` VALUES (2011, '全球渠道', 2000, 11, 'partner', 'brand/partner/index', '', 1, 0, 'C', '0', '0', 'partner:list', 'international', 'admin', sysdate(), '', null, '渠道');
INSERT INTO `sys_menu` VALUES (2012, '联系记录', 2000, 12, 'contact', 'brand/contact/index', '', 1, 0, 'C', '0', '0', 'contact:list', 'email', 'admin', sysdate(), '', null, '联系');

-- OSS
INSERT INTO `sys_menu` VALUES (2013, '文件引擎', 2000, 13, 'oss', 'oss/file/index', '', 1, 0, 'C', '0', '0', 'oss:list', 'server', 'admin', sysdate(), '', null, 'OSS');
