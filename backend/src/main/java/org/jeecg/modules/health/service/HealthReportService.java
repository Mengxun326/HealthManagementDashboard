package org.jeecg.modules.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.health.entity.HealthReport;

/**
 * 体检报告服务接口
 */
public interface HealthReportService extends IService<HealthReport> {

    /**
     * 分页查询报告列表
     */
    IPage<HealthReport> getPageList(Page<HealthReport> page, String patientId);

    /**
     * 上传体检报告
     */
    boolean uploadReport(HealthReport healthReport, String fileUrl);

    /**
     * AI报告解读
     */
    boolean analyzeReport(String reportId);
}
