package org.jeecg.modules.health.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jeecg.modules.health.common.Result;
import org.jeecg.modules.health.entity.DoctorAdvice;
import org.jeecg.modules.health.service.DoctorAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 医嘱控制器
 */
@Tag(name = "医嘱管理")
@RestController
@RequestMapping("/api/doctor")
public class DoctorAdviceController {

    @Autowired
    private DoctorAdviceService doctorAdviceService;

    @Operation(summary = "获取医嘱列表")
    @GetMapping("/advice")
    public Result<IPage<DoctorAdvice>> getAdviceList(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestAttribute("userId") String userId) {
        Page<DoctorAdvice> page = new Page<>(current, size);
        // TODO: 根据userId获取patientId并查询医嘱
        IPage<DoctorAdvice> result = doctorAdviceService.page(page);
        return Result.success(result);
    }
}
