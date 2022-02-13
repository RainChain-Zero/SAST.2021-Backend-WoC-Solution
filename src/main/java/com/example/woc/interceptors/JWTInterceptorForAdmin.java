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
 * @date 2022/2/13 1:50
 */

//管理员鉴权拦截
@Component
public class JWTInterceptorForAdmin implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        String role=JWTUtils.verify(token).getClaim("role").asString();
        if(role.equals("1") || role.equals("2"))
            return true;
        throw new OperationFailException(403,"权限不足");
    }
}
