package com.notebook.notebookbackend.error;

/**
 * @author 22454
 */
public enum EmBusinessErr implements CommonError {

    //测试用无意义报错,一般用于测试回滚等机制使用
    TEST_ONLY_ERROR(0, "测试用报错"),


    //常规状态错误
    PARAMETER_INVALIDATION_ERROR(1001, "参数不合法"),
    UNKNOWN_ERROR(1002, "未知错误"),
    PERMISSION_DENIED(1003, "无权限"),
    NOT_LOGIN_USER(1004, "未登录"),
    FAILED_TO_CREATE_TOKEN(1005, "创建 Token 失败"),
    FAILED_TO_FRESH_TOKEN(1006, "刷新 Token 失败"),
    TOKEN_IS_EMPTY(1007, "请求中含有无效 Token"),
    FAILED_TO_PARSE_TOKEN(1008, "解析 Token 失败"),

    LOGIN_ERROR(2001, "登录异常"),
    REGISTERED_ERROR(2002, "注册异常"),
    USERNAME_OCCUPATION(2003, "用户名占用"),
    FAILED_TO_CREATE_VERIFICATION_CODE(2004, "生成验证码失败");
//


    private int errCode;
    private String msg;

    EmBusinessErr(int errCode, String msg) {
        this.errCode = errCode;
        this.msg = msg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.msg;
    }

    @Override
    public void setErrMsg(String msg) {
        this.msg = msg;
    }
}
