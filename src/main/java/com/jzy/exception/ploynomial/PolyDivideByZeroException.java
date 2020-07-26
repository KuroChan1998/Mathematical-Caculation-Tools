/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: PolyDivideByZeroException.java
 * @Package com.jzy.xxaqsxjc.method1
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午1:15:47
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */


package com.jzy.exception.ploynomial;

/**
 * 多项式除式不能为0的异常
 *
 *
 * @version 1.0, 19/09/02
 * @author JinZhiyun
 */
public class PolyDivideByZeroException extends RuntimeException {
    public PolyDivideByZeroException() {
        super();
    }

    public PolyDivideByZeroException(String message) {
        super(message);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
