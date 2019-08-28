/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: CommonFactorMultiple.java
 * @Package com.jzy.xxaqsxjc.method0
 * @Description: 最大公因数和最小公倍数的计算
 * @author: JinZhiyun
 * @date: 2019年3月14日 下午9:56:56
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.xxaqsxjc.method0;

import com.jzy.exception.integer.InputNonPositiveException;
import com.jzy.exception.integer.InputTooManyBigIntegerInputException;
import com.jzy.util.BigIntegerTest;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @ClassName: CommonFactorMultiple
 * @Description: 最大公因数和最小公倍数的计算
 * @author: JinZhiyun
 * @date: 2019年3月14日 下午9:57:13
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class CommonFactorMultiple {

    /**
     * @Fields result : 计算结果
     */
    private static BigInteger result = Method0.VALUE_0;

    /**
     * @throws
     * @Title: CommonFactorMultiple
     * @Description: 构造函数初始化
     * @param:
     */
    public CommonFactorMultiple() {
    }

    /**
     * @throws
     * @Title: maxCommonFactorXY
     * @Description: 两个数的最大公因数
     * @param: @param x
     * @param: @param y
     * @param: @return
     * @return: BigInteger
     */
    public static BigInteger maxCommonFactorXY(BigInteger x, BigInteger y) {
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
     * @throws
     * @Title: maxCommonFactorAll
     * @Description: 多个数的最大公因数
     * @param: @param a: 数组
     * @param: @param size: 数组长度
     * @param: @param index: 数组索引，传入0即可，表示从这个数组的a[index]个元素开始计算
     * @param: @return
     * @return: BigInteger
     */
    public static BigInteger maxCommonFactorAll(BigInteger[] a, int size, int index) {// size<=1000
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
     * @throws
     * @Title: minCommonMultipleXY
     * @Description: 两个数的最小公倍数
     * @param: @param x
     * @param: @param y
     * @param: @return
     * @return: BigInteger
     */
    public static BigInteger minCommonMultipleXY(BigInteger x, BigInteger y) {
        return (x.multiply(y)).divide(maxCommonFactorXY(x, y));
    }

    /**
     * @throws
     * @Title: minCommonMultipleAll
     * @Description: 多个数的最小公倍数
     * @param: @param a: 数组
     * @param: @param size: 数组长度
     * @param: @param index: 数组索引，传入0即可，表示从这个数组的a[index]个元素开始计算
     * @param: @return
     * @return: BigInteger
     */
    public static BigInteger minCommonMultipleAll(BigInteger[] a, int size, int index) {// size<=1000
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

    public static void main(String[] args) {
        BigInteger x;
        BigInteger[] s1 = new BigInteger[1000];
        BigInteger[] s2 = new BigInteger[1000];
        int size = 0;
        Scanner scan = new Scanner(System.in);

        System.out.print("请输入整数x（0为终止）：");
        if (scan.hasNextBigInteger()) {
            x = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();
            return;
        }
        if (x.toString() == "0") {
            scan.close();
            return;
        }
        while (x.toString() != "0") {
            s1[size] = x;
            s2[size] = x;
            size++;
            System.out.print("请输入整数x(x=0终止输入):");
            if (scan.hasNextBigInteger()) {
                x = scan.nextBigInteger();
            } else {
                System.out.println("发生错误！");
                scan.close();
                return;
            }
            if (x.toString() == "0" && size == 1) {
                System.out.println("至少输入两个数");
                System.out.print("请输入整数x(x=0终止输入):");
                x = scan.nextBigInteger();
            }
        }
        scan.close();

        for (int i = 0; i < size; i++) {
            System.out.print(s1[i]);
            if (i == size - 1)
                break;
            System.out.print(",");
        }
        System.out.println("的最大公因数是：" + CommonFactorMultiple.maxCommonFactorAll(s1, size, 0));

        for (int i = 0; i < size; i++) {
            System.out.print(s2[i]);
            if (i == size - 1)
                break;
            System.out.print(",");
        }
        System.out.println("的最小公倍数是：" + CommonFactorMultiple.minCommonMultipleAll(s2, size, 0));

    }

}
