package com.jzy.exception.integer;

import com.jzy.exception.InputException;

/**
 * 输入的数值过大的异常
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class InputTooLargeException extends InputException {
    public InputTooLargeException() {
        super();
    }

    public InputTooLargeException(String message) {
        super(message);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
