package org.jeecg.modules.health.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jeecg.modules.health.common.Result;
import org.jeecg.modules.health.entity.AllergyHistory;
import org.jeecg.modules.health.entity.FamilyMember;
import org.jeecg.modules.health.entity.MedicalHistory;
import org.jeecg.modules.health.service.AllergyHistoryService;
import org.jeecg.modules.health.service.FamilyMemberService;
import org.jeecg.modules.health.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 档案管理控制器
 */
@Tag(name = "档案管理")
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private AllergyHistoryService allergyHistoryService;

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @Autowired
    private FamilyMemberService familyMemberService;

    @Operation(summary = "获取过敏史")
    @GetMapping("/allergy-history")
    public Result<List<AllergyHistory>> getAllergyHistory(@RequestAttribute("userId") String userId) {
        // TODO: 根据userId获取patientId
        String patientId = userId;
        List<AllergyHistory> result = allergyHistoryService.getList(patientId);
        return Result.success(result);
    }

    @Operation(summary = "添加过敏史")
    @PostMapping("/allergy-history")
    public Result<Void> addAllergyHistory(@RequestBody AllergyHistory allergyHistory,
                                          @RequestAttribute("userId") String userId) {
        // TODO: 根据userId获取patientId
        allergyHistory.setPatientId(userId);
        boolean success = allergyHistoryService.save(allergyHistory);
        if (success) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @Operation(summary = "获取过往病史")
    @GetMapping("/medical-history")
    public Result<List<MedicalHistory>> getMedicalHistory(@RequestAttribute("userId") String userId) {
        // TODO: 根据userId获取patientId
        String patientId = userId;
        List<MedicalHistory> result = medicalHistoryService.getList(patientId);
        return Result.success(result);
    }

    @Operation(summary = "获取亲友列表")
    @GetMapping("/family-members")
    public Result<List<FamilyMember>> getFamilyMembers(@RequestAttribute("userId") String userId) {
        // TODO: 根据userId获取patientId
        String patientId = userId;
        List<FamilyMember> result = familyMemberService.getList(patientId);
        return Result.success(result);
    }

    @Operation(summary = "添加亲友")
    @PostMapping("/family-members")
    public Result<Void> addFamilyMember(@RequestBody FamilyMember familyMember,
                                         @RequestAttribute("userId") String userId) {
        // TODO: 根据userId获取patientId
        familyMember.setPatientId(userId);
        boolean success = familyMemberService.save(familyMember);
        if (success) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }
}
