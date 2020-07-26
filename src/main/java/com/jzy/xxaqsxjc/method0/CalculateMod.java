package com.jzy.xxaqsxjc.method0;

import java.math.BigInteger;

import java.util.Scanner;

import com.jzy.exception.integer.InputNonPositiveException;
import com.jzy.util.BigIntegerTest;

/**
 * 模重复平方计算余数；绝对值最小余数、最小非负余数
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class CalculateMod {

    /**
     * 绝对值最小余数，b^n mod m
     *
     * @param b b^n mod m中的b
     * @param n b^n mod m中的n
     * @param m b^n mod m中的m
     * @return b^n mod m的值
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger calculateAbsMinMod(BigInteger b, BigInteger n, BigInteger m) {
        return transferAbsMinRemainder(calculateMod(b, n, m), m);
    }

    /**
     * 最小非负余数，b^n mod m
     *
     * @param b b^n mod m中的b
     * @param n b^n mod m中的n
     * @param m b^n mod m中的m
     * @return b^n mod m的值
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger calculateMod(BigInteger b, BigInteger n, BigInteger m) {
        return (b.modPow(n, m));
    }


    /**
     * 把a转换成模n的绝对值最小余数
     * <p>即-n/2~n/2之间<br>
     *
     * @param a 输入余数a
     * @param n a mod n的n
     * @return a mod n（绝对值最小余数）
     * @throws InputNonPositiveException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger transferAbsMinRemainder(BigInteger a, BigInteger n) throws InputNonPositiveException {
        if (BigIntegerTest.isNonPositive(n)) {
            throw new InputNonPositiveException("输入的n非正");
        }

        BigInteger tmp = a.mod(n);

        if (tmp.compareTo(n.divide(Method0.VALUE_2)) > 0) {
            tmp = tmp.subtract(n);
        } else if ((tmp.negate()).compareTo(n.divide(Method0.VALUE_2)) > 0) {
            tmp = tmp.add(n);
        }

        return tmp;
    }
}
