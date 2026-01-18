-- 医疗健康管理系统数据库表结构

-- 创建数据库
CREATE DATABASE IF NOT EXISTS `health_management` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `health_management`;

-- 1. 患者信息表
CREATE TABLE IF NOT EXISTS `health_patient` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `user_id` VARCHAR(32) NOT NULL COMMENT '用户ID（关联sys_user）',
  `name` VARCHAR(50) NOT NULL COMMENT '姓名',
  `gender` TINYINT(1) DEFAULT 0 COMMENT '性别：0-未知，1-男，2-女',
  `birth_date` DATE COMMENT '出生日期',
  `phone` VARCHAR(20) COMMENT '手机号',
  `id_card` VARCHAR(18) COMMENT '身份证号',
  `address` VARCHAR(200) COMMENT '地址',
  `avatar_url` VARCHAR(255) COMMENT '头像URL',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` TINYINT(1) DEFAULT 0 COMMENT '删除标志：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='患者信息表';

-- 2. 健康指标记录表
CREATE TABLE IF NOT EXISTS `health_metric` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `patient_id` VARCHAR(32) NOT NULL COMMENT '患者ID',
  `metric_type` VARCHAR(20) NOT NULL COMMENT '指标类型：BLOOD_PRESSURE-血压，BLOOD_SUGAR-血糖，CHOLESTEROL-血脂，BMI-BMI',
  `metric_value` DECIMAL(10,2) NOT NULL COMMENT '指标值',
  `record_date` DATE NOT NULL COMMENT '记录日期',
  `normal_range_min` DECIMAL(10,2) COMMENT '正常范围最小值',
  `normal_range_max` DECIMAL(10,2) COMMENT '正常范围最大值',
  `status` VARCHAR(10) DEFAULT 'NORMAL' COMMENT '状态：NORMAL-正常，ABNORMAL-异常',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` TINYINT(1) DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_patient_id` (`patient_id`),
  KEY `idx_metric_type` (`metric_type`),
  KEY `idx_record_date` (`record_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='健康指标记录表';

-- 3. 体检报告表
CREATE TABLE IF NOT EXISTS `health_report` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `patient_id` VARCHAR(32) NOT NULL COMMENT '患者ID',
  `report_type` VARCHAR(50) NOT NULL COMMENT '报告类型',
  `report_date` DATE NOT NULL COMMENT '报告日期',
  `hospital_name` VARCHAR(100) COMMENT '医院名称',
  `report_file_url` VARCHAR(500) COMMENT '报告文件URL',
  `ai_summary` TEXT COMMENT 'AI解读摘要',
  `ai_score` INT COMMENT 'AI健康评分（0-100）',
  `status` VARCHAR(20) DEFAULT 'PENDING' COMMENT '状态：PENDING-待解读，ANALYZED-已解读',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` TINYINT(1) DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_patient_id` (`patient_id`),
  KEY `idx_report_date` (`report_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='体检报告表';

-- 4. 病历表
CREATE TABLE IF NOT EXISTS `medical_record` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `patient_id` VARCHAR(32) NOT NULL COMMENT '患者ID',
  `record_date` DATE NOT NULL COMMENT '病历日期',
  `hospital_name` VARCHAR(100) COMMENT '医院名称',
  `doctor_name` VARCHAR(50) COMMENT '医生姓名',
  `diagnosis` TEXT COMMENT '诊断',
  `treatment` TEXT COMMENT '治疗方案',
  `record_file_url` VARCHAR(500) COMMENT '病历文件URL',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` TINYINT(1) DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_patient_id` (`patient_id`),
  KEY `idx_record_date` (`record_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='病历表';

-- 5. 慢病管理表
CREATE TABLE IF NOT EXISTS `chronic_disease` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `patient_id` VARCHAR(32) NOT NULL COMMENT '患者ID',
  `disease_type` VARCHAR(50) NOT NULL COMMENT '疾病类型：HYPERTENSION-高血压，DIABETES-高血糖，HYPERLIPIDEMIA-高血脂等',
  `diagnosed_date` DATE COMMENT '诊断日期',
  `status` VARCHAR(20) DEFAULT 'TREATING' COMMENT '状态：TREATING-治疗中，CURED-已治愈',
  `hospital_name` VARCHAR(100) COMMENT '医院名称',
  `doctor_name` VARCHAR(50) COMMENT '医生姓名',
  `description` VARCHAR(500) COMMENT '描述',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` TINYINT(1) DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_patient_id` (`patient_id`),
  KEY `idx_disease_type` (`disease_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='慢病管理表';

-- 6. 用药提醒表
CREATE TABLE IF NOT EXISTS `medication_reminder` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `patient_id` VARCHAR(32) NOT NULL COMMENT '患者ID',
  `medication_name` VARCHAR(100) NOT NULL COMMENT '药物名称',
  `dosage` VARCHAR(50) COMMENT '剂量',
  `frequency` VARCHAR(50) COMMENT '频率',
  `reminder_time` TIME COMMENT '提醒时间',
  `start_date` DATE COMMENT '开始日期',
  `end_date` DATE COMMENT '结束日期',
  `status` VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '状态：ACTIVE-有效，COMPLETED-已完成，CANCELLED-已取消',
  `is_read` TINYINT(1) DEFAULT 0 COMMENT '是否已读：0-未读，1-已读',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` TINYINT(1) DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_patient_id` (`patient_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用药提醒表';

-- 7. 复诊提醒表
CREATE TABLE IF NOT EXISTS `appointment_reminder` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `patient_id` VARCHAR(32) NOT NULL COMMENT '患者ID',
  `appointment_date` DATETIME NOT NULL COMMENT '预约时间',
  `hospital_name` VARCHAR(100) COMMENT '医院名称',
  `department` VARCHAR(50) COMMENT '科室',
  `doctor_name` VARCHAR(50) COMMENT '医生姓名',
  `status` VARCHAR(20) DEFAULT 'PENDING' COMMENT '状态：PENDING-待就诊，COMPLETED-已完成，CANCELLED-已取消',
  `is_read` TINYINT(1) DEFAULT 0 COMMENT '是否已读：0-未读，1-已读',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` TINYINT(1) DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_patient_id` (`patient_id`),
  KEY `idx_appointment_date` (`appointment_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='复诊提醒表';

-- 8. 过敏史表
CREATE TABLE IF NOT EXISTS `allergy_history` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `patient_id` VARCHAR(32) NOT NULL COMMENT '患者ID',
  `allergen` VARCHAR(100) NOT NULL COMMENT '过敏原',
  `reaction` VARCHAR(500) COMMENT '反应症状',
  `severity` VARCHAR(20) DEFAULT 'MILD' COMMENT '严重程度：MILD-轻度，MODERATE-中度，SEVERE-严重',
  `recorded_date` DATE COMMENT '记录日期',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` TINYINT(1) DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_patient_id` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='过敏史表';

-- 9. 过往病史表
CREATE TABLE IF NOT EXISTS `medical_history` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `patient_id` VARCHAR(32) NOT NULL COMMENT '患者ID',
  `disease_name` VARCHAR(100) NOT NULL COMMENT '疾病名称',
  `diagnosed_date` DATE COMMENT '诊断日期',
  `status` VARCHAR(20) DEFAULT 'TREATING' COMMENT '状态：TREATING-治疗中，CURED-已治愈',
  `hospital_name` VARCHAR(100) COMMENT '医院名称',
  `doctor_name` VARCHAR(50) COMMENT '医生姓名',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` TINYINT(1) DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_patient_id` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='过往病史表';

-- 10. 亲友关系表
CREATE TABLE IF NOT EXISTS `family_member` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `patient_id` VARCHAR(32) NOT NULL COMMENT '患者ID',
  `member_name` VARCHAR(50) NOT NULL COMMENT '亲友姓名',
  `relation` VARCHAR(20) NOT NULL COMMENT '关系：SPOUSE-配偶，CHILD-子女，PARENT-父母，OTHER-其他',
  `member_phone` VARCHAR(20) COMMENT '亲友手机号',
  `permission_level` VARCHAR(20) DEFAULT 'VIEW' COMMENT '权限级别：VIEW-查看，MANAGE-管理',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` TINYINT(1) DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_patient_id` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='亲友关系表';

-- 11. 医嘱表
CREATE TABLE IF NOT EXISTS `doctor_advice` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `patient_id` VARCHAR(32) NOT NULL COMMENT '患者ID',
  `doctor_id` VARCHAR(32) COMMENT '医生ID',
  `advice_content` TEXT NOT NULL COMMENT '医嘱内容',
  `advice_date` DATE NOT NULL COMMENT '医嘱日期',
  `status` VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '状态：ACTIVE-有效，COMPLETED-已完成',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` TINYINT(1) DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_patient_id` (`patient_id`),
  KEY `idx_doctor_id` (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医嘱表';

-- 12. 健康课程表
CREATE TABLE IF NOT EXISTS `health_course` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `course_title` VARCHAR(200) NOT NULL COMMENT '课程标题',
  `course_content` TEXT COMMENT '课程内容',
  `course_url` VARCHAR(500) COMMENT '课程URL',
  `course_type` VARCHAR(50) COMMENT '课程类型',
  `cover_image_url` VARCHAR(500) COMMENT '封面图片URL',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` TINYINT(1) DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `idx_course_type` (`course_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='健康课程表';
