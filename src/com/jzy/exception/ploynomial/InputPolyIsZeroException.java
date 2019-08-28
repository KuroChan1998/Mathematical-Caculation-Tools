package com.jzy.exception.ploynomial;

import com.jzy.exception.InputException;

/**
 * @ClassName InputPolyIsZeroException
 * @Author JinZhiyun
 * @Description 输入的多项式为0
 * @Date 2019/8/28 8:10
 * @Version 1.0
 **/
public class InputPolyIsZeroException extends InputException {
    public InputPolyIsZeroException() {
    }

    public InputPolyIsZeroException(String message) {
        super(message);
    }
}
