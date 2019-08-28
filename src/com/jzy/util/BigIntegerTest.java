package com.jzy.util;

import com.jzy.exception.InputException;
import com.jzy.xxaqsxjc.method0.Method0;

import java.math.BigInteger;

/**
 * @ClassName BigIntegerTest
 * @Author JinZhiyun
 * @Description 提供一些验证输入大数的方法
 * @Date 2019/8/26 13:07
 * @Version 1.0
 **/
public class BigIntegerTest {
    /**
     * @Author JinZhiyun
     * @Description 判断输入大数数组m是否两两互素
     * @Date 17:33 2019/8/26
     * @Param [m]
     * @return boolean
     **/
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
     * @return boolean
     * @Author JinZhiyun
     * @Description 判断大数x是否等于0
     * @Date 17:16 2019/8/26
     * @Param [x]
     **/
    public static boolean ifEqualsZero(BigInteger x) {
        return x.compareTo(Method0.VALUE_0) == 0;
    }

    /**
     * @return boolean
     * @Author JinZhiyun
     * @Description 判断大数x是否等于1
     * @Date 17:17 2019/8/26
     * @Param [x]
     **/
    public static boolean ifEqualsOne(BigInteger x) {
        return x.compareTo(Method0.VALUE_1) == 0;
    }

    /**
     * @Author JinZhiyun
     * @Description 判断大数x是否等于2
     * @Date 17:45 2019/8/26
     * @Param [x]
     * @return boolean
     **/
    public static boolean ifEqualsTwo(BigInteger x) {
        return x.compareTo(Method0.VALUE_2) == 0;
    }

    /**
     * @return boolean
     * @Author JinZhiyun
     * @Description 判断大数x是否>0
     * @Date 17:17 2019/8/26
     * @Param [x]
     **/
    public static boolean isPositive(BigInteger x) {
        return x.compareTo(Method0.VALUE_0) > 0;
    }

    /**
     * @return boolean
     * @Author JinZhiyun
     * @Description 判断大数x是否>=0
     * @Date 17:17 2019/8/26
     * @Param [x]
     **/
    public static boolean isNonNegative(BigInteger x) {
        return x.compareTo(Method0.VALUE_0) >= 0;
    }

    /**
     * @return boolean
     * @Author JinZhiyun
     * @Description 判断大数x是否 <0
     * @Date 17:18 2019/8/26
     * @Param [x]
     **/
    public static boolean isNegative(BigInteger x) {
        return x.compareTo(Method0.VALUE_0) < 0;
    }

    /**
     * @Author JinZhiyun
     * @Description 判断大数x是否 <=0
     * @Date 17:18 2019/8/26
     * @Param [x]
     * @return boolean
     **/
    public static boolean isNonPositive(BigInteger x) {
        return x.compareTo(Method0.VALUE_0) <= 0;
    }
}
