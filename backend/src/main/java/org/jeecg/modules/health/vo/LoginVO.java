package org.jeecg.modules.health.vo;

import lombok.Data;

/**
 * 登录响应VO
 */
@Data
public class LoginVO {
    private String token;
    private String userId;
    private String username;
    private Long expiration;
}
