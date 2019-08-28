package com.jzy.exception.integer;

import com.jzy.exception.InputException;

/**
 * @ClassName InputNonPositiveException
 * @Author JinZhiyun
 * @Description 输入大数不为正
 * @Date 2019/8/26 12:03
 * @Version 1.0
 **/
public class InputNonPositiveException extends InputException {
    public InputNonPositiveException() {
        super();
    }

    public InputNonPositiveException(String message) {
        super(message);
    }
}
