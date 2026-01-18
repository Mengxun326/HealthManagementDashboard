package org.jeecg.modules.health.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jeecg.modules.health.common.Result;
import org.jeecg.modules.health.entity.HealthCourse;
import org.jeecg.modules.health.service.HealthCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 健康课程控制器
 */
@Tag(name = "健康课程管理")
@RestController
@RequestMapping("/api/health/course")
public class HealthCourseController {

    @Autowired
    private HealthCourseService healthCourseService;

    @Operation(summary = "获取健康课程列表")
    @GetMapping("/list")
    public Result<IPage<HealthCourse>> getCourseList(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String courseType) {
        Page<HealthCourse> page = new Page<>(current, size);
        IPage<HealthCourse> result = healthCourseService.page(page);
        return Result.success(result);
    }
}
