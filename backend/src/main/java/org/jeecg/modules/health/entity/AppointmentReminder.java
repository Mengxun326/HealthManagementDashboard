package org.jeecg.modules.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 复诊提醒实体
 */
@Data
@TableName("appointment_reminder")
public class AppointmentReminder {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String patientId;

    private LocalDateTime appointmentDate;

    private String hospitalName;

    private String department;

    private String doctorName;

    private String status;

    private Integer isRead;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer delFlag;
}
