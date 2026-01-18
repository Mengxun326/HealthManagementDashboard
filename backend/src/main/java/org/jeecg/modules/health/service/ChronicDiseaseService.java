package org.jeecg.modules.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.health.entity.ChronicDisease;

import java.util.Map;

/**
 * 慢病管理服务接口
 */
public interface ChronicDiseaseService extends IService<ChronicDisease> {

    /**
     * 分页查询慢病列表
     */
    IPage<ChronicDisease> getPageList(Page<ChronicDisease> page, String patientId);

    /**
     * 获取干预方案
     */
    Map<String, Object> getInterventionPlan(String diseaseId);

    /**
     * AI健康趋势预测
     */
    Map<String, Object> getTrendPrediction(String diseaseId);
}
