package org.jeecg.modules.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.health.entity.HealthReport;
import org.jeecg.modules.health.mapper.HealthReportMapper;
import org.jeecg.modules.health.service.HealthReportService;
import org.springframework.stereotype.Service;

/**
 * 体检报告服务实现
 */
@Service
public class HealthReportServiceImpl extends ServiceImpl<HealthReportMapper, HealthReport> implements HealthReportService {

    @Override
    public IPage<HealthReport> getPageList(Page<HealthReport> page, String patientId) {
        LambdaQueryWrapper<HealthReport> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HealthReport::getPatientId, patientId)
                .eq(HealthReport::getDelFlag, 0)
                .orderByDesc(HealthReport::getReportDate);
        return this.page(page, wrapper);
    }

    @Override
    public boolean uploadReport(HealthReport healthReport, String fileUrl) {
        healthReport.setReportFileUrl(fileUrl);
        healthReport.setStatus("PENDING");
        return this.save(healthReport);
    }

    @Override
    public boolean analyzeReport(String reportId) {
        HealthReport report = this.getById(reportId);
        if (report == null) {
            return false;
        }
        // TODO: 调用AI接口进行报告解读
        // 这里暂时使用模拟数据
        report.setAiSummary("AI智能分析：检测到血脂异常，建议调整饮食结构，增加运动量。");
        report.setAiScore(75);
        report.setStatus("ANALYZED");
        return this.updateById(report);
    }
}
