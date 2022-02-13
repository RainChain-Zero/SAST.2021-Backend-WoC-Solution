package com.example.woc.controller.ResponseAdvice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.woc.Utils.ResultData;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author 慕北_Innocent
 * @version 1.0
 * @date 2022/2/13 19:55
 */

@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;   //true代表需要进行处理
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //! 一定要处理String类型，如果是String，SpringBoot会直接返回，而不会处理成json
        if (body instanceof String)
            return JSON.toJSONString(ResultData.success(body), SerializerFeature.WriteMapNullValue,
                    SerializerFeature.PrettyFormat);   //使用fastjson转json
        if (body instanceof ResultData)
            return body;
        return ResultData.success(body);
    }
}
