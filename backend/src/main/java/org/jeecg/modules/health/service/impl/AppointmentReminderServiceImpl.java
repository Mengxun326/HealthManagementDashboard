package org.jeecg.modules.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.health.entity.AppointmentReminder;
import org.jeecg.modules.health.mapper.AppointmentReminderMapper;
import org.jeecg.modules.health.service.AppointmentReminderService;
import org.springframework.stereotype.Service;

/**
 * 复诊提醒服务实现
 */
@Service
public class AppointmentReminderServiceImpl extends ServiceImpl<AppointmentReminderMapper, AppointmentReminder> implements AppointmentReminderService {

    @Override
    public IPage<AppointmentReminder> getPageList(Page<AppointmentReminder> page, String patientId) {
        LambdaQueryWrapper<AppointmentReminder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AppointmentReminder::getPatientId, patientId)
                .eq(AppointmentReminder::getDelFlag, 0)
                .eq(AppointmentReminder::getStatus, "PENDING")
                .orderByAsc(AppointmentReminder::getAppointmentDate);
        return this.page(page, wrapper);
    }

    @Override
    public boolean markAsRead(String id) {
        AppointmentReminder reminder = this.getById(id);
        if (reminder == null) {
            return false;
        }
        reminder.setIsRead(1);
        return this.updateById(reminder);
    }
}
