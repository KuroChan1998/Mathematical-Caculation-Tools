package com.jzy.xxaqsxjc.method0;

import java.math.BigInteger;

import java.util.Scanner;

import com.jzy.exception.integer.InputNonPositiveException;
import com.jzy.exception.integer.InputTooManyBigIntegerInputException;
import com.jzy.util.BigIntegerTest;

/**
 * 最大公因数和最小公倍数的计算。
 * 可以计算两个数的最大公因数和最小公倍数，也可以计算多个数的
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class CommonFactorMultiple {

    /**
     * 存放计算结果
     */
    private static BigInteger result = Method0.VALUE_0;

    /**
     * 计算多个数的最大公因数
     *
     * @param a     多个输入大数构成的数组
     * @param size  输入数组的长度，即输入大数的个数
     * @param index 数组索引，一开始必须传入0，表示从这个数组的a[index]个元素开始递归计算
     * @return a[]中所有数的最大公因数
     * @throws InputNonPositiveException
     * @throws InputTooManyBigIntegerInputException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger maxCommonFactorAll(BigInteger[] a, int size, int index)
            throws InputTooManyBigIntegerInputException, InputNonPositiveException {    // size<=1000
        if (size > 1000) {
            throw new InputTooManyBigIntegerInputException("输入的大数数组长度过长！");
        }

        if (BigIntegerTest.isNonPositive(a[index])) {
            throw new InputNonPositiveException("输入的大数数组中有非正的值");
        }

        BigInteger tmp;

        tmp = maxCommonFactorXY(a[index], a[index + 1]);
        a[index + 1] = tmp;

        if (index + 1 == size - 1) {
            result = tmp;

            return result;
        } else {
            maxCommonFactorAll(a, size, index + 1);
        }

        return result;
    }

    /**
     * 两个数的最大公因数
     * <p>用辗转相除法，递归实现<br>
     *
     * @param x 入参大整数x
     * @param y 入参大整数y
     * @return (x, y)，即x和y的最大公因数
     * @throws InputNonPositiveException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger maxCommonFactorXY(BigInteger x, BigInteger y) throws InputNonPositiveException {
        if (BigIntegerTest.isNonPositive(x) || BigIntegerTest.isNonPositive(y)) {
            throw new InputNonPositiveException("输入的x,y非正");
        }

        if (x.compareTo(y) == -1) {
            BigInteger tmp = y;

            y = x;
            x = tmp;
        }

        BigInteger r;

        r = x.mod(y);

        if (!r.toString().equals("0")) {
            x = y;
            y = r;
            maxCommonFactorXY(x, y);
        } else {
            result = y;
        }

        return result;
    }

    /**
     * 多个数的最小公倍数
     *
     * @param a     多个输入大数构成的数组
     * @param size  输入数组的长度，即输入大数的个数
     * @param index 数组索引，一开始必须传入0，表示从这个数组的a[index]个元素开始递归计算
     * @return a[]中所有数的最小公倍数
     * @throws InputNonPositiveException
     * @throws InputTooManyBigIntegerInputException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger minCommonMultipleAll(BigInteger[] a, int size, int index)
            throws InputTooManyBigIntegerInputException, InputNonPositiveException {    // size<=1000
        if (size > 1000) {
            throw new InputTooManyBigIntegerInputException("输入的大数数组长度过长！");
        }

        if (BigIntegerTest.isNonPositive(a[index])) {
            throw new InputNonPositiveException("输入的大数数组中有非正的值");
        }

        BigInteger tmp;

        tmp = minCommonMultipleXY(a[index], a[index + 1]);
        a[index + 1] = tmp;

        if (index + 1 == size - 1) {
            result = tmp;

            return result;
        } else {
            minCommonMultipleAll(a, size, index + 1);
        }

        return result;
    }

    /**
     * 两个数的最小公倍数
     * <p>=x*y/(x,y)<br>
     *
     * @param x 入参大整数x
     * @param y 入参大整数y
     * @return [x, y]，即x,y的最小公倍数
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger minCommonMultipleXY(BigInteger x, BigInteger y) {
        return (x.multiply(y)).divide(maxCommonFactorXY(x, y));
    }
}
