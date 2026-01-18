package org.jeecg.modules.health.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 文件工具类
 */
@Component
public class FileUtil {

    @Value("${file.upload.path}")
    private String uploadPath;

    /**
     * 上传文件
     */
    public String uploadFile(MultipartFile file, String subDir) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("文件不能为空");
        }

        // 创建目录
        String dateDir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String fullDir = uploadPath + File.separator + subDir + File.separator + dateDir;
        Path dirPath = Paths.get(fullDir);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        // 生成文件名
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String fileName = UUID.randomUUID().toString() + extension;

        // 保存文件
        Path filePath = dirPath.resolve(fileName);
        file.transferTo(filePath.toFile());

        // 返回访问路径（使用正斜杠，用于URL）
        return "/upload/" + subDir + "/" + dateDir + "/" + fileName;
    }

    /**
     * 删除文件
     */
    public boolean deleteFile(String fileUrl) {
        try {
            String filePath = uploadPath + fileUrl.replace("/upload", "");
            File file = new File(filePath);
            if (file.exists()) {
                return file.delete();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 检查文件类型
     */
    public boolean isValidFileType(String filename, String[] allowedTypes) {
        if (filename == null || filename.isEmpty()) {
            return false;
        }
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        for (String type : allowedTypes) {
            if (type.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }
}
