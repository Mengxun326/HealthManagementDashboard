package org.jeecg.modules.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 过敏史实体
 */
@Data
@TableName("allergy_history")
public class AllergyHistory {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String patientId;

    private String allergen;

    private String reaction;

    private String severity;

    private LocalDate recordedDate;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer delFlag;
}
