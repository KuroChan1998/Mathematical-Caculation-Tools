package com.jzy.exception.integer;

import com.jzy.exception.InputException;

/**
 * @ClassName InputTooManyBigIntegerInputException
 * @Author JinZhiyun
 * @Description 输入的大整数数组长度过大
 * @Date 2019/8/25 21:04
 * @Version 1.0
 **/
public class InputTooManyBigIntegerInputException extends InputException {
    public InputTooManyBigIntegerInputException() {
        super();
    }

    public InputTooManyBigIntegerInputException(String message) {
        super(message);
    }
}
