package com.notebook.notebookbackend.controller;

import com.notebook.notebookbackend.dto.LoginDTO;
import com.notebook.notebookbackend.dto.RegisterDTO;
import com.notebook.notebookbackend.error.BusinessException;
import com.notebook.notebookbackend.response.CommonResponse;
import com.notebook.notebookbackend.service.RedisService;
import com.notebook.notebookbackend.service.UserService;
import com.notebook.notebookbackend.utils.TokenUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 22454
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private final RedisService redisService;

    public UserController(UserService userService, RedisService redisService) {
        this.userService = userService;
        this.redisService = redisService;
    }

    @PostMapping("/login")
    public CommonResponse login(@RequestBody LoginDTO loginDTO) throws BusinessException {
        return CommonResponse.create(userService.login(loginDTO));
    }

    @GetMapping("/getVerificationCode")
    public CommonResponse getVerificationCode(@RequestParam("email") String email) throws BusinessException {
        return CommonResponse.create(redisService.getVerificationCode(email));
    }

    @PostMapping("/registered")
    public CommonResponse registered(@RequestBody RegisterDTO registerDTO) throws BusinessException {
        return CommonResponse.create(userService.registered(registerDTO));
    }

    @GetMapping("/test")
    public CommonResponse test(HttpServletRequest request) throws BusinessException {
        String userNameFromToken = TokenUtil.getUserNameFromToken(request.getHeader(TokenUtil.getTokenHeader()));
        return CommonResponse.create(userNameFromToken);
    }


}
