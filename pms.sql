/*
 Navicat Premium Data Transfer

 Source Server         : 81.71.38.103
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 81.71.38.103:3306
 Source Schema         : pms

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 13/01/2021 22:54:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `admin_role` enum('VISITOR','ROOT','FILE_ADMIN','COMMENT_ADMIN','CLIENT_ADMIN') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'VISITOR',
  `admin_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE,
  UNIQUE INDEX `admin_name`(`admin_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_client
-- ----------------------------
DROP TABLE IF EXISTS `t_client`;
CREATE TABLE `t_client`  (
  `client_id` int NOT NULL AUTO_INCREMENT COMMENT '用户号',
  `client_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `client_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户邮箱号',
  `client_created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '用户账号创建时间',
  `client_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `client_status` enum('DELETED','BAN','NORMAL') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NORMAL' COMMENT '用户账号的状态',
  PRIMARY KEY (`client_id`) USING BTREE,
  UNIQUE INDEX `client_email`(`client_email`) USING BTREE,
  UNIQUE INDEX `client_name`(`client_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic STATS_PERSISTENT = 1;

-- ----------------------------
-- Table structure for t_client_project
-- ----------------------------
DROP TABLE IF EXISTS `t_client_project`;
CREATE TABLE `t_client_project`  (
  `project_id` int NOT NULL COMMENT '项目号',
  `client_id` int NOT NULL COMMENT '用户号',
  PRIMARY KEY (`project_id`, `client_id`) USING BTREE,
  INDEX `client_id`(`client_id`) USING BTREE,
  CONSTRAINT `t_client_project_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `t_client` (`client_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_client_project_ibfk_3` FOREIGN KEY (`project_id`) REFERENCES `t_project` (`project_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `comment_id` int NOT NULL AUTO_INCREMENT COMMENT '评论唯一标识',
  `comment_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '评论时间',
  `comment_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论的内容',
  `comment_publisher` int NOT NULL COMMENT '评论的发布者',
  `comment_task` int NOT NULL COMMENT '评论针对的任务对象',
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `comment_publisher`(`comment_publisher`) USING BTREE,
  INDEX `comment_task`(`comment_task`) USING BTREE,
  CONSTRAINT `t_comment_ibfk_1` FOREIGN KEY (`comment_publisher`) REFERENCES `t_client` (`client_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_comment_ibfk_2` FOREIGN KEY (`comment_task`) REFERENCES `t_task` (`task_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file`  (
  `file_id` int NOT NULL AUTO_INCREMENT COMMENT '文件号',
  `file_time` datetime(0) NOT NULL COMMENT '文件发布时间',
  `file_publisher` int NOT NULL COMMENT '文件发布者',
  `file_project` int NOT NULL COMMENT '文件发布针对的项目',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件名',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件路径',
  PRIMARY KEY (`file_id`) USING BTREE,
  INDEX `file_publisher`(`file_publisher`) USING BTREE,
  INDEX `file_project`(`file_project`) USING BTREE,
  CONSTRAINT `t_file_ibfk_1` FOREIGN KEY (`file_publisher`) REFERENCES `t_client` (`client_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_file_ibfk_2` FOREIGN KEY (`file_project`) REFERENCES `t_project` (`project_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`  (
  `log_id` int NOT NULL AUTO_INCREMENT COMMENT '日志号',
  `log_client` int NOT NULL COMMENT '操作者',
  `log_project` int NOT NULL COMMENT '操作项目',
  `log_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `log_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作的具体内容',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `log_client`(`log_client`) USING BTREE,
  INDEX `log_project`(`log_project`) USING BTREE,
  CONSTRAINT `t_log_ibfk_1` FOREIGN KEY (`log_client`) REFERENCES `t_client` (`client_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_log_ibfk_2` FOREIGN KEY (`log_project`) REFERENCES `t_project` (`project_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project`  (
  `project_id` int NOT NULL AUTO_INCREMENT COMMENT '项目号',
  `project_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目标题或项目名',
  `project_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目描述信息',
  `project_root` int NOT NULL COMMENT '项目超级负责人 ',
  `project_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邀请码',
  `project_created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '项目创建时间',
  PRIMARY KEY (`project_id`) USING BTREE,
  INDEX `project_root`(`project_root`) USING BTREE,
  CONSTRAINT `t_project_ibfk_1` FOREIGN KEY (`project_root`) REFERENCES `t_client` (`client_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task`  (
  `task_id` int NOT NULL AUTO_INCREMENT COMMENT '任务号',
  `task_status` enum('UNFINISHED','FINISHED','OVERDUE_FINISHED','OVERDUE_UNFINISHED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'UNFINISHED' COMMENT '任务状态',
  `task_principle` int NULL DEFAULT NULL COMMENT '任务的负责人',
  `task_project` int NOT NULL COMMENT '任务所在项目',
  `task_deadline` datetime(0) NULL DEFAULT NULL COMMENT '任务截止时间',
  `task_finished_time` datetime(0) NULL DEFAULT NULL COMMENT '任务的完成时间',
  `task_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任务名',
  `task_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务详情',
  PRIMARY KEY (`task_id`) USING BTREE,
  INDEX `task_project`(`task_project`) USING BTREE,
  INDEX `task_principle`(`task_principle`) USING BTREE,
  CONSTRAINT `t_task_ibfk_1` FOREIGN KEY (`task_project`) REFERENCES `t_project` (`project_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_task_ibfk_2` FOREIGN KEY (`task_principle`) REFERENCES `t_client` (`client_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for v_admin_show_all
-- ----------------------------
DROP VIEW IF EXISTS `v_admin_show_all`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_admin_show_all` AS select `t_admin`.`admin_id` AS `admin_id`,`t_admin`.`admin_name` AS `admin_name`,`t_admin`.`admin_role` AS `admin_role` from `t_admin`;

-- ----------------------------
-- View structure for v_client_exists
-- ----------------------------
DROP VIEW IF EXISTS `v_client_exists`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_client_exists` AS select `t_client`.`client_id` AS `client_id`,`t_client`.`client_name` AS `client_name`,`t_client`.`client_email` AS `client_email`,`t_client`.`client_created_time` AS `client_created_time`,`t_client`.`client_password` AS `client_password`,`t_client`.`client_status` AS `client_status` from `t_client` where (`t_client`.`client_status` <> 'DELETED');

-- ----------------------------
-- View structure for v_client_show_all
-- ----------------------------
DROP VIEW IF EXISTS `v_client_show_all`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_client_show_all` AS select `t_client`.`client_id` AS `client_id`,`t_client`.`client_name` AS `client_name`,`t_client`.`client_email` AS `client_email`,`t_client`.`client_status` AS `client_status`,`t_client`.`client_created_time` AS `client_created_time` from `t_client`;

-- ----------------------------
-- View structure for v_client_show_exists
-- ----------------------------
DROP VIEW IF EXISTS `v_client_show_exists`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_client_show_exists` AS select `t_client`.`client_id` AS `client_id`,`t_client`.`client_name` AS `client_name`,`t_client`.`client_email` AS `client_email`,`t_client`.`client_created_time` AS `client_created_time`,`t_client`.`client_status` AS `client_status` from `t_client` where (`t_client`.`client_status` <> 'DELETED');

-- ----------------------------
-- View structure for v_client_show_normal
-- ----------------------------
DROP VIEW IF EXISTS `v_client_show_normal`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_client_show_normal` AS select `t_client`.`client_id` AS `client_id`,`t_client`.`client_name` AS `client_name`,`t_client`.`client_email` AS `client_email`,`t_client`.`client_created_time` AS `client_created_time`,`t_client`.`client_status` AS `client_status` from `t_client` where (`t_client`.`client_status` = 'NORMAL');

-- ----------------------------
-- View structure for v_client_task
-- ----------------------------
DROP VIEW IF EXISTS `v_client_task`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_client_task` AS select `t_client_project`.`client_id` AS `client_id`,`t_task`.`task_id` AS `task_id` from (`t_client_project` join `t_task`) where (`t_task`.`task_project` = `t_client_project`.`project_id`);

-- ----------------------------
-- Triggers structure for table t_project
-- ----------------------------
DROP TRIGGER IF EXISTS `before_delete_t_project`;
delimiter ;;
CREATE TRIGGER `before_delete_t_project` BEFORE DELETE ON `t_project` FOR EACH ROW begin
# 删除所有该项目的成员记录
	delete from t_client_project
	where t_client_project.project_id=old.project_id;
	delete from t_task
	where t_task.task_project=old.project_id;
	DELETE from t_file
	where t_file.file_project=old.project_id;
	delete from t_log
	where t_log.log_project=old.project_id;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table t_project
-- ----------------------------
DROP TRIGGER IF EXISTS `after_insert_t_project`;
delimiter ;;
CREATE TRIGGER `after_insert_t_project` AFTER INSERT ON `t_project` FOR EACH ROW begin
	insert into t_client_project(client_id,project_id)
	values(new.project_root,new.project_id);
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table t_task
-- ----------------------------
DROP TRIGGER IF EXISTS `before_delete_t_task`;
delimiter ;;
CREATE TRIGGER `before_delete_t_task` BEFORE DELETE ON `t_task` FOR EACH ROW begin
	delete from t_comment 
	where t_comment.comment_task=old.task_id;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
