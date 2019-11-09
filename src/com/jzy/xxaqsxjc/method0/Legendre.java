package com.jzy.xxaqsxjc.method0;

import java.math.BigInteger;

import java.util.Scanner;

import com.jzy.exception.integer.InputNonPositiveException;
import com.jzy.util.BigIntegerTest;

/**
 * 勒让得符号计算
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class Legendre {

    /**
     * A, B, C, D: 大整数常量，便于后边代码调用
     */
    private static final BigInteger A = Method0.VALUE_0;
    private static final BigInteger B = Method0.VALUE_1;
    private static final BigInteger C = Method0.VALUE_MINUS_1;
    private static final BigInteger D = Method0.VALUE_2;

    /**
     * 勒让得符号计算 Legendre(q/p)，返回1或0或-1
     *
     * @param q legendre(q / p)的q
     * @param p legendre(q / p)的p
     * @return legendre(q / p)的值，1或0或-1
     * @throws InputNonPositiveException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int legendre(BigInteger q, BigInteger p) throws InputNonPositiveException {
        if (BigIntegerTest.isNonPositive(p)) {
            throw new InputNonPositiveException("输入的p非正");
        }

        if ((q.mod(p)).compareTo(A) == 0) {
            return 0;
        }

        if (q.compareTo(B) == 0) {
            return 1;
        }

        if (q.compareTo(C) == 0) {
            if (((p.subtract(B)).mod(D)).compareTo(A) == 0) {
                return 1;
            } else {
                return -1;
            }
        }

        if ((q.mod(D)).compareTo(A) != 0) {
            if (((((p.subtract(B)).divide(D)).multiply((q.subtract(B)).divide(D))).mod(D)).compareTo(A) == 0) {
                return legendre(p.mod(q), q);
            } else {
                return (-1) * (legendre(p.mod(q), q));
            }
        } else {
            BigInteger k = Method0.VALUE_0;

            while ((q.mod(D)).compareTo(A) == 0) {
                k = k.add(B);
                q = q.divide(D);
            }

            if ((k.mod(D)).compareTo(A) == 0) {
                return legendre(q, p);
            } else {
                if (((((p.multiply(p)).subtract(B)).divide(Method0.VALUE_8)).mod(D)).compareTo(A) == 0) {
                    return legendre(q, p);
                } else {
                    return (-1) * (legendre(q, p));
                }
            }
        }
    }
}
