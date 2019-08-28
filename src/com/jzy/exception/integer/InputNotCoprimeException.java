package com.jzy.exception.integer;

import com.jzy.exception.InputException;

/**
 * @ClassName InputNotCoprimeException
 * @Author JinZhiyun
 * @Description 输入数组不两两互质
 * @Date 2019/8/26 13:06
 * @Version 1.0
 **/
public class InputNotCoprimeException extends InputException {
    public InputNotCoprimeException() {
        super();
    }

    public InputNotCoprimeException(String message) {
        super(message);
    }
}
