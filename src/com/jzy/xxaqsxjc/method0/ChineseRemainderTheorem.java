/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: ChineseRemainderTheorem.java
 * @Package com.jzy.xxaqsxjc.method0
 * @Description: 中国剩余定理求解
 * @author: JinZhiyun
 * @date: 2019年3月15日 下午5:41:56
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.xxaqsxjc.method0;

import com.jzy.exception.InputException;
import com.jzy.exception.integer.InputNotCoprimeException;
import com.jzy.util.BigIntegerTest;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @ClassName: ChineseRemainderTheorem
 * @Description: 中国剩余定理求解
 * @author: JinZhiyun
 * @date: 2019年3月15日 下午5:39:27
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class ChineseRemainderTheorem {
    private static BigInteger[] M1;
    private static BigInteger[] M11;

    /**
     * @throws
     * @Title: solve
     * @Description: 中国剩余定理求解，方程组：x同余b 模 m（多个方程时系数存储在数组b[]，m[]中）m，返回值[]x，即解为x=x[0]+x[1]*q(q为整数)
     * @param: @param b
     * @param: @param m
     * @param: @return
     * @return: BigInteger[]
     */
    public synchronized static BigInteger[] solve(BigInteger[] b, BigInteger[] m) {
        if (b.length < 1 || m.length < 1 || b.length != m.length) {
            throw new InputException("不合法的中国剩余定理系数输入！");
        }
        if (m.length > 1) {
            if (!BigIntegerTest.isCoprime(m)) {
                throw new InputNotCoprimeException("输入的中国剩余定理系数m(x同余b 模 m)没有两两互素！");
            }
        }
        M1 = new BigInteger[m.length];
        M11 = new BigInteger[m.length];
        BigInteger[] x = new BigInteger[2];
        x[0] = Method0.VALUE_0;
        BigInteger multiM = Method0.VALUE_1;
        for (BigInteger i : m) {
            multiM = multiM.multiply(i);
        }
        x[1] = multiM;

        for (int i = 0; i < m.length; i++) {
            M1[i] = multiM.divide(m[i]);
            M11[i] = Method0.bezoutSolveQrS11(M1[i], m[i]);
            x[0] = x[0].add(b[i].multiply(M1[i]).multiply(M11[i]));
        }
        return x;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int size;
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入方程个数：");
        if (scan.hasNextInt()) {
            size = scan.nextInt();
        } else {
            System.out.println("发生错误！");
            scan.close();
            return;
        }
        BigInteger[] b = new BigInteger[size];
        BigInteger[] m = new BigInteger[size];
        for (int i = 0; i < size; i++) {
            BigInteger tmp;
            System.out.println("请输入方程x同余b" + (i + 1) + "(mod m" + (i + 1) + ")的系数b" + (i + 1) + ",m" + (i + 1) + ":");
            if (scan.hasNextBigInteger()) {
                tmp = scan.nextBigInteger();
            } else {
                System.out.println("发生错误！");
                scan.close();
                return;
            }
            b[i] = tmp;
            if (scan.hasNextBigInteger()) {
                tmp = scan.nextBigInteger();
            } else {
                System.out.println("发生错误！");
                scan.close();
                return;
            }
            m[i] = tmp;
        }
        scan.close();

        ChineseRemainderTheorem chineseRemainderTheorem = new ChineseRemainderTheorem();
        System.out.println(
                "该同余方程组的解为：x=" + chineseRemainderTheorem.solve(b, m)[0] + "+" + chineseRemainderTheorem.solve(b, m)[1] + "q(q为整数)");
    }

}
