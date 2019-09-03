package com.jzy.exception.integer;

import com.jzy.exception.InputException;

/**
 * 输入的大整数数组长度过大的异常
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class InputTooManyBigIntegerInputException extends InputException {
    public InputTooManyBigIntegerInputException() {
        super();
    }

    public InputTooManyBigIntegerInputException(String message) {
        super(message);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
