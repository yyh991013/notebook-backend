package com.notebook.notebookbackend.interceptor;

import com.notebook.notebookbackend.error.BusinessException;
import com.notebook.notebookbackend.error.EmBusinessErr;
import com.notebook.notebookbackend.utils.MyLog;
import com.notebook.notebookbackend.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author 22454
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    /**
     * 免查 URL 集合
     * 部署的时候，将最大权限通配符注释掉，保留注册/登录接口即可
     */
    private static final String[] NOT_CHECK_URL = {
//            "http://101.200.153.254:8088/swagger-ui.html",
//            "http://101.200.153.254:8088/webjars/**",
//            "http://101.200.153.254:8088/swagger-resources/**",
//            "http://101.200.153.254:8088/null/swagger-resources/configuration/ui",
//            "http://101.200.153.254:8088/csrf",
//            "http://101.200.153.254:8088/",
//            "http://101.200.153.254:8088/error",
//            "http://101.200.153.254:8088/user/login",
//            "http://101.200.153.254:8088/user/registered",
//            "http://101.200.153.254:8088/user/getRegisteredVerificationCode",
//            "http://101.200.153.254:8088/user/getUpdatePasswordVerificationCode"
            "http://localhost:8088/**"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        MyLog.info(String.format("%s request to %s", request.getMethod(), url));

        String token = request.getHeader(TokenUtil.getTokenHeader());
        if (null == token) {
            if (isNotCheckUrl(url)) {
                return true;
            }
            MyLog.warn("不是NOT CHECK URL");
            MyLog.error("token is empty");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        Claims claims = TokenUtil.parseToken(token);
        if (claims != null && !claims.isEmpty() && !TokenUtil.isExpired(token)) {
            return true;
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return false;
        }
    }

    private boolean isNotCheckUrl(String url) {
        //如果请求以 / 结尾，去除 /
        url = url.endsWith("/") ?
                url.substring(0, url.lastIndexOf("/")) : url;
        for (String path : NOT_CHECK_URL) {
            //如果是通配符
            if (path.endsWith("/**")) {
                //取出通配符前缀
                String wildcardPrefix = path.substring(0, path.length() - 3);
                if (url.startsWith(wildcardPrefix)) {
                    return true;
                }
            }
            if (url.equals(path)) {
                return true;
            }
        }
        return false;
    }
}
