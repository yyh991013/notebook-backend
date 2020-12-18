package com.notebook.notebookbackend.controller;

import com.notebook.notebookbackend.dto.*;
import com.notebook.notebookbackend.error.BusinessException;
import com.notebook.notebookbackend.error.EmBusinessErr;
import com.notebook.notebookbackend.response.CommonResponse;
import com.notebook.notebookbackend.service.RedisService;
import com.notebook.notebookbackend.service.UserService;
import com.notebook.notebookbackend.utils.*;
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

    private final HttpServletRequest request;

    public UserController(UserService userService, RedisService redisService, HttpServletRequest request) {
        this.userService = userService;
        this.redisService = redisService;
        this.request = request;
    }

    @PostMapping("/login")
    public CommonResponse login(@RequestBody LoginDTO loginDTO) throws BusinessException {
        return CommonResponse.create(userService.login(loginDTO));
    }

    @GetMapping("/getRegisteredVerificationCode")
    public CommonResponse getRegisteredVerificationCode(@RequestParam("email") String key) throws BusinessException {
        generateVerificationCode(key, VerificationCodeType.Registered);
        return CommonResponse.success();
    }

    @GetMapping("/getUpdatePasswordVerificationCode")
    public CommonResponse getUpdatePasswordVerificationCode(HttpServletRequest request) throws BusinessException {
        String token = request.getHeader(TokenUtil.getTokenHeader());
        String userNameFromToken = TokenUtil.getUserNameFromToken(token);
        generateVerificationCode(userNameFromToken, VerificationCodeType.UPDATE_PASSWORD);
        return CommonResponse.success();
    }

    private synchronized void generateVerificationCode(String key, int verificationType) throws BusinessException {
        String userName = getUserNameFromToken();
        String email = userService.getEmailByUserName(userName);
        redisService.getVerificationCode(key, email, verificationType);
    }

    @PostMapping("/registered")
    public CommonResponse registered(@RequestBody RegisterDTO registerDTO) throws BusinessException {
        String verificationCodeKey = VerificationUtil.getVerificationCodeKey(VerificationCodeType.Registered, registerDTO.getEmail());
        if (redisService.compareVerificationCode(verificationCodeKey, registerDTO.getVerificationCode())) {
            return CommonResponse.create(userService.registered(registerDTO));
        }
        throw new BusinessException(EmBusinessErr.ERROR_VERIFICATION_CODE);
    }

    @PostMapping("/updatePassword")
    public CommonResponse updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) throws BusinessException {
        String userName = getUserNameFromToken();
        String verificationCodeKey = VerificationUtil.getVerificationCodeKey(VerificationCodeType.UPDATE_PASSWORD, userName);
        if (redisService.compareVerificationCode(verificationCodeKey, updatePasswordDTO.getVerificationCode())) {
            userService.updatePassword(userName, updatePasswordDTO.getOldPassword(), updatePasswordDTO.getNewPassword());
            return CommonResponse.success();
        }
        throw new BusinessException(EmBusinessErr.FAILED_TO_UPDATE_PASSWORD);
    }

    @PostMapping("/updatePhone")
    public CommonResponse updatePhone(@RequestBody UpdatePhoneDTO updatePhoneDTO) throws BusinessException {
        String userName = getUserNameFromToken();
        userService.updatePhone(userName, updatePhoneDTO.getPassword(), updatePhoneDTO.getPhone());
        return CommonResponse.success();
    }

    private synchronized String getUserNameFromToken() throws BusinessException {
        String token = request.getHeader(TokenUtil.getTokenHeader());
        return TokenUtil.getUserNameFromToken(token);
    }

    @PostMapping("/updateEmail")
    public CommonResponse updateEmail(@RequestBody UpdateEmailDTO updateEmailDTO) throws BusinessException {
        String userName = getUserNameFromToken();
        userService.updateEmail(userName, updateEmailDTO.getPassword(), updateEmailDTO.getEmail());
        return CommonResponse.success();
    }

}
