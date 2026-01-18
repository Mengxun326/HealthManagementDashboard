package org.jeecg.modules.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.health.entity.DoctorAdvice;
import org.jeecg.modules.health.mapper.DoctorAdviceMapper;
import org.jeecg.modules.health.service.DoctorAdviceService;
import org.springframework.stereotype.Service;

/**
 * 医嘱服务实现
 */
@Service
public class DoctorAdviceServiceImpl extends ServiceImpl<DoctorAdviceMapper, DoctorAdvice> implements DoctorAdviceService {
}
