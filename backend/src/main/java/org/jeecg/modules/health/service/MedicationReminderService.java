package org.jeecg.modules.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.health.entity.MedicationReminder;

/**
 * 用药提醒服务接口
 */
public interface MedicationReminderService extends IService<MedicationReminder> {

    /**
     * 分页查询用药提醒列表
     */
    IPage<MedicationReminder> getPageList(Page<MedicationReminder> page, String patientId);

    /**
     * 标记为已读
     */
    boolean markAsRead(String id);
}
