package org.jeecg.modules.health.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jeecg.modules.health.common.Result;
import org.jeecg.modules.health.entity.ChronicDisease;
import org.jeecg.modules.health.service.ChronicDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 慢病管理控制器
 */
@Tag(name = "慢病管理")
@RestController
@RequestMapping("/api/chronic-disease")
public class ChronicDiseaseController {

    @Autowired
    private ChronicDiseaseService chronicDiseaseService;

    @Operation(summary = "获取慢病列表")
    @GetMapping("/list")
    public Result<IPage<ChronicDisease>> getList(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestAttribute("userId") String userId) {
        Page<ChronicDisease> page = new Page<>(current, size);
        // TODO: 根据userId获取patientId
        String patientId = userId;
        IPage<ChronicDisease> result = chronicDiseaseService.getPageList(page, patientId);
        return Result.success(result);
    }

    @Operation(summary = "获取慢病详情")
    @GetMapping("/{id}")
    public Result<ChronicDisease> getDetail(@PathVariable String id) {
        ChronicDisease disease = chronicDiseaseService.getById(id);
        if (disease != null) {
            return Result.success(disease);
        } else {
            return Result.error("慢病记录不存在");
        }
    }

    @Operation(summary = "获取干预方案")
    @GetMapping("/intervention-plan")
    public Result<Map<String, Object>> getInterventionPlan(@RequestParam String diseaseId) {
        Map<String, Object> plan = chronicDiseaseService.getInterventionPlan(diseaseId);
        if (plan != null) {
            return Result.success(plan);
        } else {
            return Result.error("获取失败");
        }
    }

    @Operation(summary = "AI健康趋势预测")
    @GetMapping("/trend-prediction")
    public Result<Map<String, Object>> getTrendPrediction(@RequestParam String diseaseId) {
        Map<String, Object> prediction = chronicDiseaseService.getTrendPrediction(diseaseId);
        if (prediction != null) {
            return Result.success(prediction);
        } else {
            return Result.error("预测失败");
        }
    }
}
