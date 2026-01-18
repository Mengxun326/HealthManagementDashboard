package org.jeecg.modules.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.health.entity.AllergyHistory;

import java.util.List;

/**
 * 过敏史服务接口
 */
public interface AllergyHistoryService extends IService<AllergyHistory> {

    /**
     * 获取过敏史列表
     */
    List<AllergyHistory> getList(String patientId);
}
