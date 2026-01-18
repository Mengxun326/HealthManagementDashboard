package org.jeecg.modules.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.health.entity.HealthPatient;
import org.jeecg.modules.health.mapper.HealthPatientMapper;
import org.jeecg.modules.health.service.HealthPatientService;
import org.springframework.stereotype.Service;

/**
 * 患者信息服务实现
 */
@Service
public class HealthPatientServiceImpl extends ServiceImpl<HealthPatientMapper, HealthPatient> implements HealthPatientService {
}
