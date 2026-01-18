package org.jeecg.modules.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.health.entity.AppointmentReminder;

/**
 * 复诊提醒服务接口
 */
public interface AppointmentReminderService extends IService<AppointmentReminder> {

    /**
     * 分页查询复诊提醒列表
     */
    IPage<AppointmentReminder> getPageList(Page<AppointmentReminder> page, String patientId);

    /**
     * 标记为已读
     */
    boolean markAsRead(String id);
}
