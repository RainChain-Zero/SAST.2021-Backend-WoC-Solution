package com.example.woc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 慕北_Innocent
 * @version 1.0
 * @date 2022/2/13 20:35
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationFailException extends RuntimeException {
    private Integer errCode;
    private String errMsg;
}
