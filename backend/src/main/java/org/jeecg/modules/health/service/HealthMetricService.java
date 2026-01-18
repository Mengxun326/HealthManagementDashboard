package org.jeecg.modules.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.health.entity.HealthMetric;

import java.time.LocalDate;
import java.util.List;

/**
 * 健康指标服务接口
 */
public interface HealthMetricService extends IService<HealthMetric> {

    /**
     * 分页查询健康指标列表
     */
    IPage<HealthMetric> getPageList(Page<HealthMetric> page, String patientId, String metricType);

    /**
     * 添加健康指标记录
     */
    boolean addMetric(HealthMetric healthMetric);

    /**
     * 获取健康指标趋势数据
     */
    List<HealthMetric> getTrendData(String patientId, String metricType, LocalDate startDate, LocalDate endDate);

    /**
     * 获取异常指标列表
     */
    List<HealthMetric> getAbnormalMetrics(String patientId);
}
