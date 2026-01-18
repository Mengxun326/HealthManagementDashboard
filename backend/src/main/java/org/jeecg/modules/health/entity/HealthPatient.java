package org.jeecg.modules.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 患者信息实体
 */
@Data
@TableName("health_patient")
public class HealthPatient {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String userId;

    private String name;

    private Integer gender;

    private LocalDate birthDate;

    private String phone;

    private String idCard;

    private String address;

    private String avatarUrl;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer delFlag;
}
