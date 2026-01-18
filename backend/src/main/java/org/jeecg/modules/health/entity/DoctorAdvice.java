package org.jeecg.modules.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 医嘱实体
 */
@Data
@TableName("doctor_advice")
public class DoctorAdvice {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String patientId;

    private String doctorId;

    private String adviceContent;

    private LocalDate adviceDate;

    private String status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer delFlag;
}
