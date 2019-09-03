package com.jzy.exception.ploynomial;

import com.jzy.exception.InputException;

/**
 * 输入的多项式为0的异常
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class InputPolyIsZeroException extends InputException {
    public InputPolyIsZeroException() {
    }

    public InputPolyIsZeroException(String message) {
        super(message);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
