package org.jeecg.modules.health.controller;

import org.jeecg.modules.health.common.Result;
import org.jeecg.modules.health.dto.LoginDTO;
import org.jeecg.modules.health.util.JwtUtil;
import org.jeecg.modules.health.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 认证控制器
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginDTO loginDTO) {
        // TODO: 实际项目中应该从数据库验证用户名和密码
        // 这里暂时使用模拟数据
        if ("admin".equals(loginDTO.getUsername()) && "123456".equals(loginDTO.getPassword())) {
            String userId = "1";
            String username = loginDTO.getUsername();
            String token = jwtUtil.generateToken(userId, username);

            LoginVO loginVO = new LoginVO();
            loginVO.setToken(token);
            loginVO.setUserId(userId);
            loginVO.setUsername(username);
            loginVO.setExpiration(expiration);

            return Result.success("登录成功", loginVO);
        } else {
            return Result.error(401, "用户名或密码错误");
        }
    }

    /**
     * 用户登出
     */
    @PostMapping("/logout")
    public Result<Void> logout() {
        // TODO: 实际项目中可以将token加入黑名单
        return Result.<Void>success("登出成功");
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/userinfo")
    public Result<Object> getUserInfo(@RequestAttribute("userId") String userId,
                                      @RequestAttribute("username") String username) {
        // TODO: 从数据库查询用户详细信息
        return Result.success("获取成功", null);
    }
}
