package org.jeecg.modules.health.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jeecg.modules.health.common.Result;
import org.jeecg.modules.health.entity.HealthPatient;
import org.jeecg.modules.health.service.HealthPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 患者管理控制器
 */
@Tag(name = "患者管理")
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private HealthPatientService healthPatientService;

    @Operation(summary = "获取患者信息")
    @GetMapping("/info")
    public Result<HealthPatient> getInfo(@RequestAttribute("userId") String userId) {
        // TODO: 根据userId查询患者信息
        HealthPatient patient = healthPatientService.getById(userId);
        if (patient != null) {
            return Result.success(patient);
        } else {
            return Result.error("患者信息不存在");
        }
    }

    @Operation(summary = "更新患者信息")
    @PutMapping("/info")
    public Result<Void> updateInfo(@RequestBody HealthPatient patient,
                                    @RequestAttribute("userId") String userId) {
        // TODO: 根据userId设置patientId
        patient.setUserId(userId);
        boolean success = healthPatientService.updateById(patient);
        if (success) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    @Operation(summary = "获取健康画像摘要")
    @GetMapping("/health-summary")
    public Result<Map<String, Object>> getHealthSummary(@RequestAttribute("userId") String userId) {
        // TODO: 从数据库查询健康数据统计
        Map<String, Object> summary = new HashMap<>();
        summary.put("normalCount", 8);
        summary.put("abnormalCount", 4);
        summary.put("totalCount", 12);
        summary.put("status", "需要注意");
        return Result.success(summary);
    }
}
