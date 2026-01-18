package org.jeecg.modules.health.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jeecg.modules.health.common.Result;
import org.jeecg.modules.health.entity.AppointmentReminder;
import org.jeecg.modules.health.entity.MedicationReminder;
import org.jeecg.modules.health.service.AppointmentReminderService;
import org.jeecg.modules.health.service.MedicationReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 消息提醒控制器
 */
@Tag(name = "消息提醒管理")
@RestController
@RequestMapping("/api/reminder")
public class ReminderController {

    @Autowired
    private MedicationReminderService medicationReminderService;

    @Autowired
    private AppointmentReminderService appointmentReminderService;

    @Operation(summary = "获取用药提醒列表")
    @GetMapping("/medication")
    public Result<IPage<MedicationReminder>> getMedicationReminders(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestAttribute("userId") String userId) {
        Page<MedicationReminder> page = new Page<>(current, size);
        // TODO: 根据userId获取patientId
        String patientId = userId;
        IPage<MedicationReminder> result = medicationReminderService.getPageList(page, patientId);
        return Result.success(result);
    }

    @Operation(summary = "获取复诊提醒列表")
    @GetMapping("/appointment")
    public Result<IPage<AppointmentReminder>> getAppointmentReminders(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestAttribute("userId") String userId) {
        Page<AppointmentReminder> page = new Page<>(current, size);
        // TODO: 根据userId获取patientId
        String patientId = userId;
        IPage<AppointmentReminder> result = appointmentReminderService.getPageList(page, patientId);
        return Result.success(result);
    }

    @Operation(summary = "标记提醒为已读")
    @PutMapping("/{id}/read")
    public Result<Void> markAsRead(@PathVariable String id, @RequestParam String type) {
        boolean success = false;
        if ("medication".equals(type)) {
            success = medicationReminderService.markAsRead(id);
        } else if ("appointment".equals(type)) {
            success = appointmentReminderService.markAsRead(id);
        }
        if (success) {
            return Result.success("标记成功");
        } else {
            return Result.error("标记失败");
        }
    }
}
