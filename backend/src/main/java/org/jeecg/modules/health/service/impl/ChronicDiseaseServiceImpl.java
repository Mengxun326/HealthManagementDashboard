package org.jeecg.modules.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.health.entity.ChronicDisease;
import org.jeecg.modules.health.mapper.ChronicDiseaseMapper;
import org.jeecg.modules.health.service.ChronicDiseaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 慢病管理服务实现
 */
@Service
public class ChronicDiseaseServiceImpl extends ServiceImpl<ChronicDiseaseMapper, ChronicDisease> implements ChronicDiseaseService {

    @Override
    public IPage<ChronicDisease> getPageList(Page<ChronicDisease> page, String patientId) {
        LambdaQueryWrapper<ChronicDisease> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChronicDisease::getPatientId, patientId)
                .eq(ChronicDisease::getDelFlag, 0)
                .orderByDesc(ChronicDisease::getDiagnosedDate);
        return this.page(page, wrapper);
    }

    @Override
    public Map<String, Object> getInterventionPlan(String diseaseId) {
        ChronicDisease disease = this.getById(diseaseId);
        if (disease == null) {
            return null;
        }

        Map<String, Object> plan = new HashMap<>();
        
        // 饮食建议
        List<String> dietAdvice = new ArrayList<>();
        dietAdvice.add("减少钠盐摄入，每日不超过6克");
        dietAdvice.add("多食用富含钾的食物（香蕉、土豆）");
        dietAdvice.add("控制饱和脂肪摄入，增加不饱和脂肪酸（深海鱼、坚果）");
        dietAdvice.add("多吃富含膳食纤维的食物（燕麦、蔬菜）");
        plan.put("dietAdvice", dietAdvice);

        // 运动计划
        List<String> exercisePlan = new ArrayList<>();
        exercisePlan.add("每周5次有氧运动，每次30分钟");
        exercisePlan.add("推荐快走、游泳、骑车等中等强度运动");
        exercisePlan.add("避免剧烈运动和突然发力动作");
        exercisePlan.add("运动前后注意血压监测");
        plan.put("exercisePlan", exercisePlan);

        // 用药提醒
        List<String> medicationReminder = new ArrayList<>();
        medicationReminder.add("按时服药，不可随意停药");
        medicationReminder.add("定期监测血压/血糖/血脂");
        medicationReminder.add("如有不适及时就医");
        plan.put("medicationReminder", medicationReminder);

        return plan;
    }

    @Override
    public Map<String, Object> getTrendPrediction(String diseaseId) {
        // TODO: 调用AI接口进行趋势预测
        // 这里暂时返回模拟数据
        Map<String, Object> prediction = new HashMap<>();
        prediction.put("prediction", "根据您的健康数据趋势分析，预计未来一个月内指标将逐步改善");
        prediction.put("confidence", 0.85);
        prediction.put("suggestions", "继续保持当前治疗方案，注意饮食和运动");
        return prediction;
    }
}
