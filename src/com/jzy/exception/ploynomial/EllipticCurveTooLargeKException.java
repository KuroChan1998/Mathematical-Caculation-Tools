package com.jzy.exception.ploynomial;

import com.jzy.exception.InputException;

/**
 * @author JinZhiyun
 * @ClassName EllipticCurveTooLargeKException
 * @Description 输入的k倍点k值过大
 * @Date 2019/8/28 11:08
 * @Version 1.0
 **/
public class EllipticCurveTooLargeKException extends InputException {
    public EllipticCurveTooLargeKException() {
        super();
    }

    public EllipticCurveTooLargeKException(String message) {
        super(message);
    }
}
