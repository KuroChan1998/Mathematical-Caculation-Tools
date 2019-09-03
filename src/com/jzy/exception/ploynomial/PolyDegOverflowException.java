package com.jzy.exception.ploynomial;

import com.jzy.exception.InputException;

/**
 * 多项式次数超过最大限制的异常
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class PolyDegOverflowException extends InputException {
    public PolyDegOverflowException() {
        super();
    }

    public PolyDegOverflowException(String message) {
        super(message);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
