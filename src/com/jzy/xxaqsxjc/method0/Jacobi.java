package com.jzy.xxaqsxjc.method0;

import java.math.BigInteger;

import java.util.Scanner;

import com.jzy.exception.integer.InputNonPositiveException;
import com.jzy.util.BigIntegerTest;

/**
 * 雅可比符号的计算
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class Jacobi {

    /**
     * A, B, C, D: 大整数常量，便于后边代码调用
     */
    private static final BigInteger A = Method0.VALUE_0;
    private static final BigInteger B = Method0.VALUE_1;
    private static final BigInteger C = Method0.VALUE_MINUS_1;
    private static final BigInteger D = Method0.VALUE_2;

    /**
     * 雅可比符号的计算Jacobi(q / p)，返回1或0或-1
     *
     * @param q Jacobi(q / p)的q
     * @param p Jacobi(q / p)的p
     * @return Jacobi(q / p)的值，1或0或-1
     * @throws InputNonPositiveException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int jacobi(BigInteger q, BigInteger p) throws InputNonPositiveException {
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
                return jacobi(p.mod(q), q);
            } else {
                return (-1) * (jacobi(p.mod(q), q));
            }
        } else {
            BigInteger k = Method0.VALUE_0;

            while ((q.mod(D)).compareTo(A) == 0) {
                k = k.add(B);
                q = q.divide(D);
            }

            if ((k.mod(D)).compareTo(A) == 0) {
                return jacobi(q, p);
            } else {
                if (((((p.multiply(p)).subtract(B)).divide(Method0.VALUE_8)).mod(D)).compareTo(A) == 0) {
                    return jacobi(q, p);
                } else {
                    return (-1) * (jacobi(q, p));
                }
            }
        }
    }

//  public static void main(String[] args) {
//      BigInteger q, p;
//      Scanner scan = new Scanner(System.in);
//      while (true) {
//          System.out.print("请输入正整数q：");
//          if (scan.hasNextBigInteger()) {
//              q = scan.nextBigInteger();
//          } else {
//              System.out.println("发生错误！");
//              scan.close();
//              return;
//          }
//          System.out.print("请输入正整数p：");
//          if (scan.hasNextBigInteger()) {
//              p = scan.nextBigInteger();
//          } else {
//              System.out.println("发生错误！");
//              scan.close();
//              return;
//          }
//
//          System.out.println("Jacobi(q/p)=" + Jacobi.jacobi(q, p));
//      }
//
//  }
}


//~ Formatted by Jindent --- http://www.jindent.com
