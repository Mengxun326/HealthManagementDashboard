package org.jeecg.modules.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.health.entity.HealthCourse;
import org.jeecg.modules.health.mapper.HealthCourseMapper;
import org.jeecg.modules.health.service.HealthCourseService;
import org.springframework.stereotype.Service;

/**
 * 健康课程服务实现
 */
@Service
public class HealthCourseServiceImpl extends ServiceImpl<HealthCourseMapper, HealthCourse> implements HealthCourseService {
}
