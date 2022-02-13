package com.example.woc.exception;

import com.auth0.jwt.exceptions.*;
import com.example.woc.Utils.ResultData;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author 慕北_Innocent
 * @version 1.0
 * @date 2022/2/13 20:09
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //处理token异常
    @ExceptionHandler(value = {SignatureVerificationException.class, AlgorithmMismatchException.class,
            TokenExpiredException.class, InvalidClaimException.class})
    public ResultData<String> tokenException(Exception e) {
        ResultData<String> resultData = new ResultData<>();
        resultData.setSuccess(false);
        resultData.setErrCode(403);
        if (e instanceof SignatureVerificationException) {
            resultData.setErrMsg("签名不一致");
        } else if (e instanceof AlgorithmMismatchException) {
            resultData.setErrMsg("错误的加密算法");
        } else if (e instanceof TokenExpiredException) {
            resultData.setErrMsg("token已过期");
        } else {
            resultData.setErrMsg("内容被篡改！");
        }
        return resultData;
    }

    //处理参数校验异常
    @ExceptionHandler(value = {MethodArgumentNotValidException.class,
            ConstraintViolationException.class, BindException.class})
    public ResultData<String> validateException(Exception e) {
        return ResultData.fail(404, e.getMessage());
    }

    //处理自定义异常
    @ExceptionHandler(value = OperationFailException.class)
    public ResultData<String> operationFailException(OperationFailException e) {
        return ResultData.fail(e.getErrCode(), e.getErrMsg());
    }

}
