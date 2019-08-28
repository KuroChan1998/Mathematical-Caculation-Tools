package com.jzy.exception.integer;

import com.jzy.exception.InputException;

/**
 * @ClassName InputTooLargeException
 * @Author JinZhiyun
 * @Description 输入的数值过大
 * @Date 2019/8/25 22:06
 * @Version 1.0
 **/
public class InputTooLargeException extends InputException {
    public InputTooLargeException() {
        super();
    }

    public InputTooLargeException(String message) {
        super(message);
    }
}
