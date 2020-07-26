package com.jzy.exception.integer;

import com.jzy.exception.InputException;

/**
 * 输入数组不两两互质的异常
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class InputNotCoprimeException extends InputException {
    public InputNotCoprimeException() {
        super();
    }

    public InputNotCoprimeException(String message) {
        super(message);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
