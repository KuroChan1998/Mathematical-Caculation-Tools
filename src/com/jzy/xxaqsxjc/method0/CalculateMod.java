/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: CalculateMod.java
 * @Package com.jzy.xxaqsxjc.method0
 * @Description: 余数的计算
 * @author: JinZhiyun
 * @date: 2019年3月14日 下午9:32:11
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.xxaqsxjc.method0;

import com.jzy.exception.integer.InputNonPositiveException;
import com.jzy.util.BigIntegerTest;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @ClassName: CalculateMod
 * @Description: 余数的计算
 * @author: JinZhiyun
 * @date: 2019年3月14日 下午12:24:16
 *
 * @Copyright: 2019 CyborgKuroChan Inc. All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class CalculateMod {

    /**
     * @Title: calculateMod
     * @Description: 最小非负余数
     * @param: @param b
     * @param: @param n
     * @param: @param m
     * @param: @return
     * @return: BigInteger
     * @throws
     */
    public static BigInteger calculateMod(BigInteger b, BigInteger n, BigInteger m) {
        return (b.modPow(n, m));
    }

    /**
     * @Title: transferAbsMinRemainder
     * @Description: 把a转换成模n的绝对值最小余数
     * @param: @param a
     * @param: @param n
     * @param: @return
     * @return: BigInteger
     * @throws
     */
    public static BigInteger transferAbsMinRemainder(BigInteger a, BigInteger n) {
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

    /**
     * @Title: calculateAbsMinMod
     * @Description: 绝对值最小余数
     * @param: @param b
     * @param: @param n
     * @param: @param m
     * @param: @return
     * @return: BigInteger
     * @throws
     */
    public static BigInteger calculateAbsMinMod(BigInteger b, BigInteger n, BigInteger m) {
        return transferAbsMinRemainder(calculateMod(b, n, m), m);
    }

    /**
     * @Title: main
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param args
     * @return: void
     * @throws
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BigInteger b, n, m, a, n1;
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入正整数b：");
        if (scan.hasNextBigInteger()) {
            b = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();
            return;
        }
        System.out.print("请输入正整数n：");
        if (scan.hasNextBigInteger()) {
            n = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();
            return;
        }
        System.out.print("请输入正整数m：");
        if (scan.hasNextBigInteger()) {
            m = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();
            return;
        }

        System.out.println(b + "^" + n + "模" + m + "=" + CalculateMod.calculateMod(b, n, m));

        System.out.print("请输入正整数a：");
        if (scan.hasNextBigInteger()) {
            a = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();
            return;
        }
        System.out.print("请输入正整数n：");
        if (scan.hasNextBigInteger()) {
            n1 = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();
            return;
        }
        scan.close();
        System.out.println(a + "模" + n1 + "=" + CalculateMod.transferAbsMinRemainder(a, n1));
    }

}
