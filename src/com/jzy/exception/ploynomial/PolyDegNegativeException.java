package com.jzy.exception.ploynomial;

import com.jzy.exception.InputException;

/**
 * @author JinZhiyun
 * @ClassName PolyDegNegativeException
 * @Description 多项式的输入次方小于0
 * @Date 2019/8/28 9:37
 * @Version 1.0
 **/
public class PolyDegNegativeException extends InputException {
    public PolyDegNegativeException() {
        super();
    }

    public PolyDegNegativeException(String message) {
        super(message);
    }
}
