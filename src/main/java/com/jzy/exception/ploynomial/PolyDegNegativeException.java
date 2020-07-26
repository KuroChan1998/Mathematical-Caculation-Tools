package com.jzy.exception.ploynomial;

import com.jzy.exception.InputException;

/**
 * 多项式的输入次方小于0
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class PolyDegNegativeException extends InputException {
    public PolyDegNegativeException() {
        super();
    }

    public PolyDegNegativeException(String message) {
        super(message);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
