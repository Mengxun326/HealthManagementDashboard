package org.jeecg.modules.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.health.entity.HealthMetric;
import org.jeecg.modules.health.mapper.HealthMetricMapper;
import org.jeecg.modules.health.service.HealthMetricService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 健康指标服务实现
 */
@Service
public class HealthMetricServiceImpl extends ServiceImpl<HealthMetricMapper, HealthMetric> implements HealthMetricService {

    @Override
    public IPage<HealthMetric> getPageList(Page<HealthMetric> page, String patientId, String metricType) {
        LambdaQueryWrapper<HealthMetric> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HealthMetric::getPatientId, patientId)
                .eq(HealthMetric::getDelFlag, 0);
        if (metricType != null && !metricType.isEmpty()) {
            wrapper.eq(HealthMetric::getMetricType, metricType);
        }
        wrapper.orderByDesc(HealthMetric::getRecordDate);
        return this.page(page, wrapper);
    }

    @Override
    public boolean addMetric(HealthMetric healthMetric) {
        // 判断指标是否异常
        if (healthMetric.getNormalRangeMin() != null && healthMetric.getNormalRangeMax() != null) {
            double value = healthMetric.getMetricValue().doubleValue();
            double min = healthMetric.getNormalRangeMin().doubleValue();
            double max = healthMetric.getNormalRangeMax().doubleValue();
            if (value < min || value > max) {
                healthMetric.setStatus("ABNORMAL");
            } else {
                healthMetric.setStatus("NORMAL");
            }
        }
        return this.save(healthMetric);
    }

    @Override
    public List<HealthMetric> getTrendData(String patientId, String metricType, LocalDate startDate, LocalDate endDate) {
        return baseMapper.getTrendData(patientId, metricType, startDate, endDate);
    }

    @Override
    public List<HealthMetric> getAbnormalMetrics(String patientId) {
        return baseMapper.getAbnormalMetrics(patientId);
    }
}
