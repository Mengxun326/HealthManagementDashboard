package org.jeecg.modules.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 健康课程实体
 */
@Data
@TableName("health_course")
public class HealthCourse {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String courseTitle;

    private String courseContent;

    private String courseUrl;

    private String courseType;

    private String coverImageUrl;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer delFlag;
}
