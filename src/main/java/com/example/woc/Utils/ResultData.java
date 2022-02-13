package com.example.woc.Utils;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.transform.Result;

/**
 * @author 慕北_Innocent
 * @version 1.0
 * @date 2022/2/13 18:05
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
//统一返回工具类
public class ResultData<T> {
    @JSONField
    private boolean success;
    @JSONField(ordinal = 1)
    private Integer errCode;
    @JSONField(ordinal = 2)
    private String errMsg;
    @JSONField(ordinal = 3)
    private T data;

    //成功时的返回
    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setSuccess(true);
        resultData.setErrMsg(null);
        resultData.setErrCode(null);
        resultData.setData(data);
        return resultData;
    }

    //不成功的返回
    public static <T> ResultData<T> fail(Integer code, String errMsg) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setSuccess(false);
        resultData.setErrCode(code);
        resultData.setErrMsg(errMsg);
        return resultData;
    }

}
