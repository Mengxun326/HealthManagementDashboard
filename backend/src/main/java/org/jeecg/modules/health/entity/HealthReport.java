package org.jeecg.modules.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 体检报告实体
 */
@Data
@TableName("health_report")
public class HealthReport {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String patientId;

    private String reportType;

    private LocalDate reportDate;

    private String hospitalName;

    private String reportFileUrl;

    private String aiSummary;

    private Integer aiScore;

    private String status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer delFlag;
}
