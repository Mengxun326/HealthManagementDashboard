package org.jeecg.modules.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 慢病管理实体
 */
@Data
@TableName("chronic_disease")
public class ChronicDisease {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String patientId;

    private String diseaseType;

    private LocalDate diagnosedDate;

    private String status;

    private String hospitalName;

    private String doctorName;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer delFlag;
}
