package com.jzy.exception.ploynomial;

import com.jzy.exception.InputException;

/**
 * 输入的k倍点k值过大的异常
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class EllipticCurveTooLargeKException extends InputException {
    public EllipticCurveTooLargeKException() {
        super();
    }

    public EllipticCurveTooLargeKException(String message) {
        super(message);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
