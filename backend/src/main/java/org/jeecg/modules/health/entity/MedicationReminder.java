package org.jeecg.modules.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 用药提醒实体
 */
@Data
@TableName("medication_reminder")
public class MedicationReminder {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String patientId;

    private String medicationName;

    private String dosage;

    private String frequency;

    private LocalTime reminderTime;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private Integer isRead;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer delFlag;
}
