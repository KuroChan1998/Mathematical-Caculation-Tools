package com.jzy.exception.integer;

/**
 * @ClassName SecurityParameterNonPostiveException
 * @Author JinZhiyun
 * @Description 输入安全参数数不为正
 * @Date 2019/8/26 11:18
 * @Version 1.0
 **/
public class SecurityParameterNonPostiveException extends InputNonPositiveException {
    public SecurityParameterNonPostiveException() {
        super();
    }

    public SecurityParameterNonPostiveException(String message) {
        super(message);
    }
}
