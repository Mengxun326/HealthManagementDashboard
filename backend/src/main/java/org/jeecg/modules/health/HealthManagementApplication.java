package org.jeecg.modules.health;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 医疗健康管理系统启动类
 */
@SpringBootApplication
@MapperScan("org.jeecg.modules.health.mapper")
public class HealthManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthManagementApplication.class, args);
    }
}
