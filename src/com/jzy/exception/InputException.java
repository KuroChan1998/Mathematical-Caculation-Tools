package com.jzy.exception;

/**
 * @ClassName InputException
 * @Author JinZhiyun
 * @Description 输入异常
 * @Date 2019/8/26 12:20
 * @Version 1.0
 **/
public class InputException extends RuntimeException {
    public InputException() {
        super();
    }

    public InputException(String message) {
        super(message);
    }
}
