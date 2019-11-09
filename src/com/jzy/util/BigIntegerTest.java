package com.jzy.util;

import java.math.BigInteger;

import com.jzy.exception.InputException;
import com.jzy.xxaqsxjc.method0.Method0;

/**
 * 提供一些验证输入大数的方法
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public class BigIntegerTest {
    private BigIntegerTest(){}

    /**
     * 判断大数x是否等于1
     *
     * @param x 入参大整数x
     * @return 布尔值，是否等于1
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean ifEqualsOne(BigInteger x) {
        return x.compareTo(Method0.VALUE_1) == 0;
    }

    /**
     * 判断大数x是否等于2
     *
     * @param x 入参大整数x
     * @return 布尔值，是否等于2
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean ifEqualsTwo(BigInteger x) {
        return x.compareTo(Method0.VALUE_2) == 0;
    }

    /**
     * 判断大数x是否等于0
     *
     * @param x 入参大整数x
     * @return 布尔值，是否等于0
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean ifEqualsZero(BigInteger x) {
        return x.compareTo(Method0.VALUE_0) == 0;
    }

    /**
     * 判断输入大数数组m是否两两互素
     *
     * @param m 输入一些大整数组成的数组
     * @return 布尔值，这些输入的大数是否两两互素
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isCoprime(BigInteger[] m) {
        if (m.length < 2) {
            throw new InputException("输入数组长度小于了2！");
        }

        for (int i = 0; i < m.length - 1; i++) {
            for (int j = i + 1; j < m.length; j++) {
                if (Method0.maxCommonFactorXY(m[i], m[j]).compareTo(Method0.VALUE_1) != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 判断大数x是否<0
     *
     * @param x 入参大整数x
     * @return 布尔值，是否<0
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isNegative(BigInteger x) {
        return x.compareTo(Method0.VALUE_0) < 0;
    }

    /**
     * 判断大数x是否>=0
     *
     * @param x 入参大整数x
     * @return 布尔值，是否>=0
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isNonNegative(BigInteger x) {
        return x.compareTo(Method0.VALUE_0) >= 0;
    }

    /**
     * 判断大数x是否<=0
     *
     * @param x 入参大整数x
     * @return 布尔值，是否<=0
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isNonPositive(BigInteger x) {
        return x.compareTo(Method0.VALUE_0) <= 0;
    }

    /**
     * 判断大数x是否>0
     *
     * @param x 入参大整数x
     * @return 布尔值，是否>0
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isPositive(BigInteger x) {
        return x.compareTo(Method0.VALUE_0) > 0;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
