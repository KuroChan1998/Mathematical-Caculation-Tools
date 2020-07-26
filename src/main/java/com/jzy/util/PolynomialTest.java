package com.jzy.util;

import com.jzy.xxaqsxjc.method1.Method1;
import com.jzy.xxaqsxjc.method1.Polynomial;

/**
 * 提供一些验证输入多项式的方法
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public class PolynomialTest {
    private PolynomialTest(){}

    /**
     * 验证输入多项式是否=1
     *
     * @param p 入参多项式
     * @return 布尔值，是否等于1
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean ifEqualOne(Polynomial p) {
        return p.equals(Method1.POLY_1);
    }

    /**
     * 验证输入多项式是否=0
     *
     * @param p 入参多项式
     * @return 布尔值，是否等于1
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean ifEqualZero(Polynomial p) {
        return p.equals(Method1.POLY_0);
    }

    /**
     * 验证输入多项式是否非0
     *
     * @param p 入参多项式
     * @return 布尔值，是否非0
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isNotZero(Polynomial p) {
        return !ifEqualZero(p);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
