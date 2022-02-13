package com.example.woc.interceptors;
import com.example.woc.Utils.JWTUtils;
import com.example.woc.exception.OperationFailException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 慕北_Innocent
 * @version 1.0
 * @date 2022/2/12 23:10
 */

//超级管理员的鉴权拦截器
@Component
public class JWTInterceptorForSuperAdmin implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        if(JWTUtils.verify(token).getClaim("role").asString().equals("2"))
            return true;
        throw new OperationFailException(403,"权限不足");
    }
}
