package org.jeecg.modules.health.config;

import org.jeecg.modules.health.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT拦截器
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 排除登录接口
        String uri = request.getRequestURI();
        if (uri.startsWith("/api/auth/login") || uri.startsWith("/doc.html") || uri.startsWith("/swagger")) {
            return true;
        }

        // 获取Token
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 验证Token
        String actualToken = jwtUtil.removeTokenPrefix(token);
        if (!jwtUtil.validateToken(actualToken)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 将用户信息存入request
        String userId = jwtUtil.getUserIdFromToken(actualToken);
        String username = jwtUtil.getUsernameFromToken(actualToken);
        request.setAttribute("userId", userId);
        request.setAttribute("username", username);

        return true;
    }
}
