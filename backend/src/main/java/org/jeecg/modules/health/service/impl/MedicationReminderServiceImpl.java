package org.jeecg.modules.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.health.entity.MedicationReminder;
import org.jeecg.modules.health.mapper.MedicationReminderMapper;
import org.jeecg.modules.health.service.MedicationReminderService;
import org.springframework.stereotype.Service;

/**
 * 用药提醒服务实现
 */
@Service
public class MedicationReminderServiceImpl extends ServiceImpl<MedicationReminderMapper, MedicationReminder> implements MedicationReminderService {

    @Override
    public IPage<MedicationReminder> getPageList(Page<MedicationReminder> page, String patientId) {
        LambdaQueryWrapper<MedicationReminder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MedicationReminder::getPatientId, patientId)
                .eq(MedicationReminder::getDelFlag, 0)
                .eq(MedicationReminder::getStatus, "ACTIVE")
                .orderByDesc(MedicationReminder::getCreateTime);
        return this.page(page, wrapper);
    }

    @Override
    public boolean markAsRead(String id) {
        MedicationReminder reminder = this.getById(id);
        if (reminder == null) {
            return false;
        }
        reminder.setIsRead(1);
        return this.updateById(reminder);
    }
}
