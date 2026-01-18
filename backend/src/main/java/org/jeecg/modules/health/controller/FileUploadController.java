package org.jeecg.modules.health.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jeecg.modules.health.common.Result;
import org.jeecg.modules.health.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传控制器
 */
@Tag(name = "文件上传管理")
@RestController
@RequestMapping("/api/file")
public class FileUploadController {

    @Autowired
    private FileUtil fileUtil;

    @Value("${file.upload.max-size}")
    private Long maxSize;

    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public Result<Map<String, String>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(required = false, defaultValue = "common") String subDir) {
        try {
            // 检查文件大小
            if (file.getSize() > maxSize) {
                return Result.error("文件大小超过限制");
            }

            // 检查文件类型（允许图片和PDF）
            String[] allowedTypes = {"jpg", "jpeg", "png", "gif", "pdf", "doc", "docx"};
            if (!fileUtil.isValidFileType(file.getOriginalFilename(), allowedTypes)) {
                return Result.error("不支持的文件类型");
            }

            // 上传文件
            String fileUrl = fileUtil.uploadFile(file, subDir);

            Map<String, String> result = new HashMap<>();
            result.put("url", fileUrl);
            result.put("filename", file.getOriginalFilename());
            result.put("size", String.valueOf(file.getSize()));

            return Result.success("上传成功", result);
        } catch (IOException e) {
            return Result.error("文件上传失败：" + e.getMessage());
        }
    }

    @Operation(summary = "删除文件")
    @DeleteMapping("/delete")
    public Result<Void> deleteFile(@RequestParam String fileUrl) {
        boolean success = fileUtil.deleteFile(fileUrl);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}
