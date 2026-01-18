package org.jeecg.modules.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.health.entity.AllergyHistory;
import org.jeecg.modules.health.mapper.AllergyHistoryMapper;
import org.jeecg.modules.health.service.AllergyHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 过敏史服务实现
 */
@Service
public class AllergyHistoryServiceImpl extends ServiceImpl<AllergyHistoryMapper, AllergyHistory> implements AllergyHistoryService {

    @Override
    public List<AllergyHistory> getList(String patientId) {
        LambdaQueryWrapper<AllergyHistory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AllergyHistory::getPatientId, patientId)
                .eq(AllergyHistory::getDelFlag, 0)
                .orderByDesc(AllergyHistory::getRecordedDate);
        return this.list(wrapper);
    }
}
