package com.jzy.xxaqsxjc.method0;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.jzy.exception.integer.InputNonOddPrimeException;
import com.jzy.util.BigIntegerTest;

/**
 * 原根计算
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class PrimitiveRoot {

    /**
     * 求出p的最小原根
     *
     * @param p 入参大整数p
     * @return p的最小原根
     * @throws InputNonOddPrimeException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger minPrimitiveRoot(BigInteger p) throws InputNonOddPrimeException {    // p小于等于Integer.MAX_VALUE
        if (BigIntegerTest.ifEqualsTwo(p) || (Method0.solovayStassen(p) == 0)) {
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
     * 所有原根的计算，返回所有原根组成的ArrayList
     *
     * @param p 入参大整数p
     * @return 所有原根组成的ArrayList
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static ArrayList<BigInteger> primitiveRoots(BigInteger p) {
        ArrayList<BigInteger> primRootPs = new ArrayList<>();
        ArrayList<BigInteger> simplePs = new ArrayList<>();
        BigInteger g = minPrimitiveRoot(p);

        for (BigInteger i = Method0.VALUE_1; i.compareTo(p.subtract(Method0.VALUE_1)) < 0; i = i.add(Method0.VALUE_1)) {
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

//  public static void main(String[] args) {
//      BigInteger p;
//      Scanner scan = new Scanner(System.in);
//      do {
//          System.out.print("请输入奇素数p：");
//          if (scan.hasNextBigInteger()) {
//              p = scan.nextBigInteger();
//          } else {
//              System.out.println("发生错误！");
//              scan.close();
//              return;
//          }
//          while (Method0.violentIfPrime(p) == 0) {
//              System.out.println("p不是奇素数!");
//              System.out.print("请输入奇素数p：");
//              if (scan.hasNextBigInteger()) {
//                  p = scan.nextBigInteger();
//              } else {
//                  System.out.println("发生错误！");
//                  scan.close();
//                  return;
//              }
//          }
//      } while (Method0.violentIfPrime(p) == 2);
//      scan.close();
//
//      System.out.println("p的最小原根是：" + PrimitiveRoot.minPrimitiveRoot(p));
//      System.out.println("p的所有原根是：" + PrimitiveRoot.primitiveRoots(p));
//  }
}


//~ Formatted by Jindent --- http://www.jindent.com
