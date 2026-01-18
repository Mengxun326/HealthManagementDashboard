package org.jeecg.modules.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.health.entity.MedicalHistory;
import org.jeecg.modules.health.mapper.MedicalHistoryMapper;
import org.jeecg.modules.health.service.MedicalHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 过往病史服务实现
 */
@Service
public class MedicalHistoryServiceImpl extends ServiceImpl<MedicalHistoryMapper, MedicalHistory> implements MedicalHistoryService {

    @Override
    public List<MedicalHistory> getList(String patientId) {
        LambdaQueryWrapper<MedicalHistory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MedicalHistory::getPatientId, patientId)
                .eq(MedicalHistory::getDelFlag, 0)
                .orderByDesc(MedicalHistory::getDiagnosedDate);
        return this.list(wrapper);
    }
}
