package org.jeecg.modules.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 亲友关系实体
 */
@Data
@TableName("family_member")
public class FamilyMember {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String patientId;

    private String memberName;

    private String relation;

    private String memberPhone;

    private String permissionLevel;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer delFlag;
}
