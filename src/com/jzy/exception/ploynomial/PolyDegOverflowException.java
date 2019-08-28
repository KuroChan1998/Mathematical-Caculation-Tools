package com.jzy.exception.ploynomial;

import com.jzy.exception.InputException;

/**
 * @author JinZhiyun
 * @ClassName PolyDegOverflowException
 * @Description 多项式次数超过最大限制
 * @Date 2019/8/28 9:54
 * @Version 1.0
 **/
public class PolyDegOverflowException extends InputException {
    public PolyDegOverflowException() {
        super();
    }

    public PolyDegOverflowException(String message) {
        super(message);
    }
}
