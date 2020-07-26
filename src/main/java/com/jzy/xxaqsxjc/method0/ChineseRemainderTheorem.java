package com.jzy.xxaqsxjc.method0;

import java.math.BigInteger;

import java.util.Scanner;

import com.jzy.exception.InputException;
import com.jzy.exception.integer.InputNotCoprimeException;
import com.jzy.util.BigIntegerTest;

/**
 * 中国剩余定理求解，方程组：x同余b 模 m
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class ChineseRemainderTheorem {

    /**
     * 辅助计算数组
     */
    private static BigInteger[] M1;
    private static BigInteger[] M11;

    /**
     * 中国剩余定理求解，方程组：x同余b 模 m
     * <p>多个方程时系数存储在数组b[]，m[]中，返回值[]x<br>
     * 即解为x=x[0]+x[1]*q(q为整数)
     *
     * @param b 方程组：x同余b 模 m中的参数b数组
     * @param m 方程组：x同余b 模 m中的参数m数组
     * @return 返回值[]x，解为x=x[0]+x[1]*q(q为整数)
     * @throws InputException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public synchronized static BigInteger[] solve(BigInteger[] b, BigInteger[] m) throws InputException {
        if ((b.length < 1) || (m.length < 1) || (b.length != m.length)) {
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
}
