package org.jeecg.modules.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.health.entity.FamilyMember;
import org.jeecg.modules.health.mapper.FamilyMemberMapper;
import org.jeecg.modules.health.service.FamilyMemberService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 亲友关系服务实现
 */
@Service
public class FamilyMemberServiceImpl extends ServiceImpl<FamilyMemberMapper, FamilyMember> implements FamilyMemberService {

    @Override
    public List<FamilyMember> getList(String patientId) {
        LambdaQueryWrapper<FamilyMember> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FamilyMember::getPatientId, patientId)
                .eq(FamilyMember::getDelFlag, 0)
                .orderByDesc(FamilyMember::getCreateTime);
        return this.list(wrapper);
    }
}
