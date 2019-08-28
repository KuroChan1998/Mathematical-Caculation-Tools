package com.jzy.exception.encryption;

import com.jzy.exception.InputException;

/**
 * @ClassName NotBinaryStringException
 * @Author JinZhiyun
 * @Description 输入串没有仅仅含有0,1
 * @Date 2019/8/27 19:29
 * @Version 1.0
 **/
public class NotBinaryStringException extends InputException {
    public NotBinaryStringException() {
        super();
    }

    public NotBinaryStringException(String message) {
        super(message);
    }
}
