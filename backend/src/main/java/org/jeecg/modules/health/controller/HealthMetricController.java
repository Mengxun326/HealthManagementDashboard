package org.jeecg.modules.health.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jeecg.modules.health.common.Result;
import org.jeecg.modules.health.entity.HealthMetric;
import org.jeecg.modules.health.service.HealthMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 健康指标控制器
 */
@Tag(name = "健康指标管理")
@RestController
@RequestMapping("/api/health/metric")
public class HealthMetricController {

    @Autowired
    private HealthMetricService healthMetricService;

    @Operation(summary = "获取健康指标列表", description = "支持分页和筛选")
    @GetMapping("/list")
    public Result<IPage<HealthMetric>> getList(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestAttribute("userId") String userId,
            @RequestParam(required = false) String metricType) {
        Page<HealthMetric> page = new Page<>(current, size);
        // TODO: 根据userId获取patientId
        String patientId = userId; // 简化处理，实际应该从数据库查询
        IPage<HealthMetric> result = healthMetricService.getPageList(page, patientId, metricType);
        return Result.success(result);
    }

    @Operation(summary = "添加健康指标记录")
    @PostMapping("/add")
    public Result<Void> addMetric(@RequestBody HealthMetric healthMetric,
                                   @RequestAttribute("userId") String userId) {
        // TODO: 根据userId获取patientId
        healthMetric.setPatientId(userId);
        boolean success = healthMetricService.addMetric(healthMetric);
        if (success) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @Operation(summary = "获取健康指标趋势数据")
    @GetMapping("/trend")
    public Result<List<HealthMetric>> getTrend(
            @RequestParam String metricType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestAttribute("userId") String userId) {
        // TODO: 根据userId获取patientId
        String patientId = userId;
        if (startDate == null) {
            startDate = LocalDate.now().minusMonths(1);
        }
        if (endDate == null) {
            endDate = LocalDate.now();
        }
        List<HealthMetric> result = healthMetricService.getTrendData(patientId, metricType, startDate, endDate);
        return Result.success(result);
    }

    @Operation(summary = "获取异常指标列表")
    @GetMapping("/abnormal")
    public Result<List<HealthMetric>> getAbnormal(@RequestAttribute("userId") String userId) {
        // TODO: 根据userId获取patientId
        String patientId = userId;
        List<HealthMetric> result = healthMetricService.getAbnormalMetrics(patientId);
        return Result.success(result);
    }
}
