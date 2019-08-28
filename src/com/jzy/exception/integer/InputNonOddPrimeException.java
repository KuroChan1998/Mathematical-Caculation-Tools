package com.jzy.exception.integer;

/**
 * @ClassName InputNonOddPrimeException
 * @Author JinZhiyun
 * @Description 非奇素数异常
 * @Date 2019/8/26 12:35
 * @Version 1.0
 **/
public class InputNonOddPrimeException extends InputNonPositiveException{
    public InputNonOddPrimeException() {
        super();
    }

    public InputNonOddPrimeException(String message) {
        super(message);
    }
}
