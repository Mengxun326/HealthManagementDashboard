package org.jeecg.modules.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.health.entity.MedicalHistory;

import java.util.List;

/**
 * 过往病史服务接口
 */
public interface MedicalHistoryService extends IService<MedicalHistory> {

    /**
     * 获取过往病史列表
     */
    List<MedicalHistory> getList(String patientId);
}
