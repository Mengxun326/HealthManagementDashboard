package org.jeecg.modules.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.health.entity.HealthMetric;

import java.time.LocalDate;
import java.util.List;

/**
 * 健康指标Mapper
 */
public interface HealthMetricMapper extends BaseMapper<HealthMetric> {

    /**
     * 获取健康指标趋势数据
     */
    List<HealthMetric> getTrendData(@Param("patientId") String patientId,
                                     @Param("metricType") String metricType,
                                     @Param("startDate") LocalDate startDate,
                                     @Param("endDate") LocalDate endDate);

    /**
     * 获取异常指标列表
     */
    List<HealthMetric> getAbnormalMetrics(@Param("patientId") String patientId);
}
