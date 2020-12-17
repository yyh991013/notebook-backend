package com.notebook.notebookbackend.controller;

import com.notebook.notebookbackend.dto.LoginDTO;
import com.notebook.notebookbackend.dto.RegisterDTO;
import com.notebook.notebookbackend.error.BusinessException;
import com.notebook.notebookbackend.response.CommonResponse;
import com.notebook.notebookbackend.service.UserService;
import com.notebook.notebookbackend.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 22454
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public CommonResponse login(@RequestBody LoginDTO loginDTO) throws BusinessException {
        return CommonResponse.create(userService.login(loginDTO));
    }

    @PostMapping("registered")
    public CommonResponse registered(@RequestBody RegisterDTO registerDTO) throws BusinessException {
        return CommonResponse.create(userService.registered(registerDTO));
    }

    @GetMapping("test")
    public CommonResponse test(HttpServletRequest request) throws BusinessException {
        String userNameFromToken = TokenUtil.getUserNameFromToken(request.getHeader(TokenUtil.getTokenHeader()));
        return CommonResponse.create(userNameFromToken);
    }


}
