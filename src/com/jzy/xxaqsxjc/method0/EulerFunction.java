package com.jzy.xxaqsxjc.method0;

import java.math.BigInteger;

import java.util.Scanner;

import com.jzy.exception.integer.InputNonPositiveException;
import com.jzy.exception.integer.InputTooLargeException;
import com.jzy.util.BigIntegerTest;

/**
 * 欧拉函数的计算
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class EulerFunction {

    /**
     * 计算欧拉函数值的建议最大输入，超过此值调用此方法耗时将不可预计，不可取
     */
    public static final BigInteger MAX_INPUT_RECOMMENDED = BigInteger.valueOf(1 << 20);

    /**
     * 欧拉函数值的计算
     *
     * @param n 入参大整数n
     * @return n的欧拉函数值
     * @throws InputNonPositiveException
     * @throws InputTooLargeException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger euler(BigInteger n) throws InputNonPositiveException, InputTooLargeException {
        if (BigIntegerTest.isNonPositive(n)) {
            throw new InputNonPositiveException("输入的n非正");
        }

        if (n.compareTo(MAX_INPUT_RECOMMENDED) > 0) {
            throw new InputTooLargeException("输入的值大于" + MAX_INPUT_RECOMMENDED + "此法计算欧拉函数耗时将不可预计，不可取!");
        }

        BigInteger size = Method0.VALUE_0;
        BigInteger i = Method0.VALUE_1;

        while (i.compareTo(n) <= 0) {
            if (Method0.maxCommonFactorXY(i, n).compareTo(Method0.VALUE_1) == 0) {
                size = size.add(Method0.VALUE_1);
            }

            i = i.add(Method0.VALUE_1);
        }

        return size;
    }

//  public static void main(String[] args) {
//      BigInteger n;
//      Scanner scan = new Scanner(System.in);
//      System.out.print("请输入正整数n：");
//      if (scan.hasNextBigInteger()) {
//          n = scan.nextBigInteger();
//      } else {
//          System.out.println("发生错误！");
//          scan.close();
//          return;
//      }
//      scan.close();
//      System.out.println("n的欧拉函数值为：" + EulerFunction.euler(n));
//  }
}


//~ Formatted by Jindent --- http://www.jindent.com
