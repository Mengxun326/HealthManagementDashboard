package org.jeecg.modules.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.health.entity.FamilyMember;

import java.util.List;

/**
 * 亲友关系服务接口
 */
public interface FamilyMemberService extends IService<FamilyMember> {

    /**
     * 获取亲友列表
     */
    List<FamilyMember> getList(String patientId);
}
