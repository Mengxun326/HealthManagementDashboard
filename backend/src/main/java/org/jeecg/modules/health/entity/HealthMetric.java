package org.jeecg.modules.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 健康指标实体
 */
@Data
@TableName("health_metric")
public class HealthMetric {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String patientId;

    private String metricType;

    private BigDecimal metricValue;

    private LocalDate recordDate;

    private BigDecimal normalRangeMin;

    private BigDecimal normalRangeMax;

    private String status;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer delFlag;
}
