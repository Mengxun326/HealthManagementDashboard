package org.jeecg.modules.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 过往病史实体
 */
@Data
@TableName("medical_history")
public class MedicalHistory {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String patientId;

    private String diseaseName;

    private LocalDate diagnosedDate;

    private String status;

    private String hospitalName;

    private String doctorName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer delFlag;
}
