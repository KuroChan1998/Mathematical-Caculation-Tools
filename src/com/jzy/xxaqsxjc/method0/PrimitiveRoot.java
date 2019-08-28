/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: PrimitiveRoot.java
 * @Package com.jzy.xxaqsxjc.method0
 * @Description: 原根计算
 * @author: JinZhiyun
 * @date: 2019年3月14日 下午10:48:02
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.xxaqsxjc.method0;

import com.jzy.exception.integer.InputNonOddPrimeException;
import com.jzy.util.BigIntegerTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @ClassName: PrimitiveRoot
 * @Description: 原根计算
 * @author: JinZhiyun
 * @date: 2019年3月14日 下午10:47:43
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class PrimitiveRoot {
    /**
     * @throws
     * @Title: minPrimitiveRoot
     * @Description: 求出最小原根
     * @param: @param p
     * @param: @return
     * @return: BigInteger
     */
    public static BigInteger minPrimitiveRoot(BigInteger p) {// p小于等于Integer.MAX_VALUE
        if (BigIntegerTest.ifEqualsTwo(p) || Method0.solovayStassen(p) == 0) {
            throw new InputNonOddPrimeException("输入p不是奇素数！");
        }
        BigInteger g = Method0.VALUE_1;
        ArrayList<BigInteger> q = Method0.primeFactor(p.subtract(Method0.VALUE_1));
        BigInteger[] p_q = new BigInteger[q.size()];
        for (int i = 0; i < q.size(); i++) {
            p_q[i] = (p.subtract(Method0.VALUE_1)).divide(q.get(i));
        }
        int flag = 0;
        while (flag != q.size()) {
            g = g.add(Method0.VALUE_1);
            flag = 0;
            for (int i = 0; i < q.size(); i++) {
                if (Method0.calculateMod(g, p_q[i], p).compareTo(Method0.VALUE_1) != 0) {
                    flag++;
                }
            }
        }
        return g;
    }

    /**
     * @throws
     * @Title: primitiveRoots
     * @Description: 所有原根的计算，返回ArrayList<BigInteger>
     * @param: @param p
     * @param: @return
     * @return: ArrayList<BigInteger>
     */
    public static ArrayList<BigInteger> primitiveRoots(BigInteger p) {
        ArrayList<BigInteger> primRootPs = new ArrayList<>();
        ArrayList<BigInteger> simplePs = new ArrayList<>();
        BigInteger g = minPrimitiveRoot(p);
        for (BigInteger i = Method0.VALUE_1; i.compareTo(p.subtract(Method0.VALUE_1)) < 0; i = i
                .add(Method0.VALUE_1)) {
            if (BigIntegerTest.ifEqualsOne(Method0.maxCommonFactorXY(i, p.subtract(Method0.VALUE_1)))) {
                simplePs.add(i);
            }
        }
        for (BigInteger simpleP : simplePs) {
            primRootPs.add(Method0.calculateMod(g, simpleP, p));
        }
        Collections.sort(primRootPs);
        return primRootPs;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BigInteger p;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("请输入奇素数p：");
            if (scan.hasNextBigInteger()) {
                p = scan.nextBigInteger();
            } else {
                System.out.println("发生错误！");
                scan.close();
                return;
            }
            while (Method0.violentIfPrime(p) == 0) {
                System.out.println("p不是奇素数!");
                System.out.print("请输入奇素数p：");
                if (scan.hasNextBigInteger()) {
                    p = scan.nextBigInteger();
                } else {
                    System.out.println("发生错误！");
                    scan.close();
                    return;
                }
            }
        } while (Method0.violentIfPrime(p) == 2);
        scan.close();

        System.out.println("p的最小原根是：" + PrimitiveRoot.minPrimitiveRoot(p));
        System.out.println("p的所有原根是：" + PrimitiveRoot.primitiveRoots(p));
    }

}
