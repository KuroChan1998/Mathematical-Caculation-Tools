/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: EulerFunction.java
 * @Package com.jzy.xxaqsxjc.method0
 * @Description: 欧拉函数的计算
 * @author: JinZhiyun
 * @date: 2019年3月14日 下午10:41:49
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.xxaqsxjc.method0;

import com.jzy.exception.integer.InputNonPositiveException;
import com.jzy.exception.integer.InputTooLargeException;
import com.jzy.util.BigIntegerTest;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @ClassName: EulerFunction
 * @Description: 欧拉函数的计算
 * @author: JinZhiyun
 * @date: 2019年3月14日 下午10:40:30
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class EulerFunction {
    /**
     * @Fields MAX_INPUT_RECOMMENDED : 计算欧拉函数值的建议最大输入，超过此值调用此方法耗时将不可预计，不可取
     */
    public static final BigInteger MAX_INPUT_RECOMMENDED = BigInteger.valueOf(1 << 20);

    /**
     * @throws
     * @Title: Euler
     * @Description: 欧拉函数值的计算
     * @param: @param n
     * @param: @return
     * @return: BigInteger
     */
    public static BigInteger Euler(BigInteger n) {
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

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BigInteger n;
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入正整数n：");
        if (scan.hasNextBigInteger()) {
            n = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();
            return;
        }
        scan.close();
        System.out.println("n的欧拉函数值为：" + EulerFunction.Euler(n));
    }

}
