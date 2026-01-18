package org.jeecg.modules.health.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jeecg.modules.health.common.Result;
import org.jeecg.modules.health.entity.HealthReport;
import org.jeecg.modules.health.service.HealthReportService;
import org.jeecg.modules.health.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 体检报告控制器
 */
@Tag(name = "体检报告管理")
@RestController
@RequestMapping("/api/health/report")
public class HealthReportController {

    @Autowired
    private HealthReportService healthReportService;

    @Autowired
    private FileUtil fileUtil;

    @Operation(summary = "获取报告列表")
    @GetMapping("/list")
    public Result<IPage<HealthReport>> getList(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestAttribute("userId") String userId) {
        Page<HealthReport> page = new Page<>(current, size);
        // TODO: 根据userId获取patientId
        String patientId = userId;
        IPage<HealthReport> result = healthReportService.getPageList(page, patientId);
        return Result.success(result);
    }

    @Operation(summary = "获取报告详情")
    @GetMapping("/{id}")
    public Result<HealthReport> getDetail(@PathVariable String id) {
        HealthReport report = healthReportService.getById(id);
        if (report != null) {
            return Result.success(report);
        } else {
            return Result.error("报告不存在");
        }
    }

    @Operation(summary = "上传体检报告")
    @PostMapping("/upload")
    public Result<Void> uploadReport(
            @RequestParam("file") MultipartFile file,
            @RequestParam String reportType,
            @RequestParam String reportDate,
            @RequestParam(required = false) String hospitalName,
            @RequestAttribute("userId") String userId) throws java.io.IOException {
        // 使用FileUtil上传文件
        String fileUrl = fileUtil.uploadFile(file, "reports");
        
        HealthReport report = new HealthReport();
        report.setPatientId(userId);
        report.setReportType(reportType);
        report.setReportDate(java.time.LocalDate.parse(reportDate));
        report.setHospitalName(hospitalName);
        
        boolean success = healthReportService.uploadReport(report, fileUrl);
        if (success) {
            return Result.success("上传成功");
        } else {
            return Result.error("上传失败");
        }
    }

    @Operation(summary = "AI报告解读")
    @PostMapping("/ai-analysis")
    public Result<Void> analyzeReport(@RequestParam String reportId) {
        boolean success = healthReportService.analyzeReport(reportId);
        if (success) {
            return Result.success("解读成功");
        } else {
            return Result.error("解读失败");
        }
    }
}
