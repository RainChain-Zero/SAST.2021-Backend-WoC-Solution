package com.example.woc.Config;

import com.example.woc.interceptors.JWTInterceptorForAdmin;
import com.example.woc.interceptors.JWTInterceptorForSuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 慕北_Innocent
 * @version 1.0
 * @date 2022/2/13 1:43
 */

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    private JWTInterceptorForSuperAdmin jwtInterceptorForSuperAdmin;

    @Autowired
    private JWTInterceptorForAdmin jwtInterceptorForAdmin;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptorForSuperAdmin).addPathPatterns("/admin/superAdmin/deleteAccount");

        registry.addInterceptor(jwtInterceptorForAdmin).addPathPatterns("/admin/deleteAccount")
                .addPathPatterns("/admin/getAmount");
    }
}
