package com.jzy.xxaqsxjc.method1;

import java.math.BigInteger;

import java.util.ArrayList;

import com.jzy.exception.ploynomial.InputPolyIsZeroException;
import com.jzy.exception.ploynomial.PolyDivideByZeroException;
import com.jzy.util.PolynomialTest;
import com.jzy.xxaqsxjc.method0.Method0;

/**
 * 供多项式的一些延展计算：最大公因式、最小公倍式、不可约、本原多项式判断。
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class PolynomialCalculation {

    /**
     * 存储最大公因式和最小公倍式的计算结果
     */
    private static Polynomial result;

    /**
     * 本原多项式判断， 1是，0不是
     *
     * @param p1 入参多项式p1
     * @return 0或1；1表示是本原多项式，0表示不是本原多项式
     * @throws PolyDivideByZeroException
     * @throws InputPolyIsZeroException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int ifPrimPolynomial(Polynomial p1) throws PolyDivideByZeroException, InputPolyIsZeroException {
        if (PolynomialTest.ifEqualZero(p1)) {
            throw new InputPolyIsZeroException("输入的多项式不能为0");
        }

        int deg = (1 << p1.effectiveMaxDeg) - 1;
        int[] factorSet = new int[deg + 1];

        factorSet[deg] = 1;

        if (!(new Polynomial(factorSet)).mod(p1).equals(Method1.POLY_1)) {
            return 0;
        }

        ArrayList<BigInteger> primeFactors = Method0.primeFactor(BigInteger.valueOf(deg));

        for (int i = 0; i < primeFactors.size(); i++) {
            factorSet = new int[deg / primeFactors.get(i).intValue() + 1];
            factorSet[deg / primeFactors.get(i).intValue()] = 1;

            Polynomial tmp = new Polynomial(factorSet);

            if (tmp.mod(p1).equals(Method1.POLY_1)) {
                return 0;
            }
        }

        return 1;
    }

    /**
     * 是否为不可约多项式，1是，0不是
     *
     * @param p1 入参多项式p1
     * @return 0或1；1表示是不可约多项式，0表示不是不可约多项式
     * @throws PolyDivideByZeroException
     * @throws InputPolyIsZeroException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int ifReduciblePolynomial(Polynomial p1) throws PolyDivideByZeroException, InputPolyIsZeroException {
        if (PolynomialTest.ifEqualZero(p1)) {
            throw new InputPolyIsZeroException("输入的多项式不能为0");
        }

        int[] a = {1};

        if (p1.equals(new Polynomial(a))) {
            return 1;
        }

        for (int i = 2; i <= 1 + p1.effectiveMaxDeg / 2; i++) {
            a = new int[i];

            // a[i-1]=1;
            for (int j = 1 << (i - 1); j < 1 << i; j++) {
                String s = Integer.toBinaryString(j);

                s = new StringBuffer(s).reverse().toString();

                for (int k = 0; k < i; k++) {
                    a[k] = Integer.parseInt(String.valueOf(s.charAt(k)));
                }

                if ((p1.mod(new Polynomial(a))).equals(Method1.POLY_0)) {
                    return 0;
                }
            }
        }

        return 1;
    }

//    public static void main(String[] args) throws PolyDivideByZeroException {
//        int[] a = {3, 3, 1, 0, 1};
//        int[] b = {1, 0, 0, 1};
//        Polynomial pa = new Polynomial(a);
//        Polynomial pb = new Polynomial(b);
//
//        System.out.println(PolynomialCalculation.maxCommonFactor(pa, pb));
//        System.out.println(PolynomialCalculation.minCommonMultiple(pa, pb));
//        System.out.println(PolynomialCalculation.ifReduciblePolynomial(pa));
//        System.out.println(PolynomialCalculation.ifPrimPolynomial(pa));
//    }

    /**
     * 最大公因式
     *
     * @param p1 入参多项式p1
     * @param p2 入参多项式p2
     * @return (p1, p2)，即p1和p2的最大公因式
     * @throws PolyDivideByZeroException
     * @throws InputPolyIsZeroException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static Polynomial maxCommonFactor(Polynomial p1, Polynomial p2) throws PolyDivideByZeroException, InputPolyIsZeroException {
        if (PolynomialTest.ifEqualZero(p1) || PolynomialTest.ifEqualZero(p2)) {
            throw new InputPolyIsZeroException("输入的多项式不能为0");
        }

        if (p1.effectiveMaxDeg < p2.effectiveMaxDeg) {
            Polynomial tmp = p2;

            p2 = p1;
            p1 = tmp;
        }

        Polynomial rp = p1.mod(p2);

        if (!rp.equals(Method1.POLY_0) && (rp.effectiveMaxDeg >= 1)) {
            p1 = p2;
            p2 = rp;
            maxCommonFactor(p1, p2);
        } else {
            if (rp.equals(Method1.POLY_0)) {
                result = p2;
            } else {
                result = rp;
            }
        }

        return result;
    }

    /**
     * 最小公倍式
     *
     * @param p1 入参多项式p1
     * @param p2 入参多项式p2
     * @return [p1, p2]，即p1和p2的最小公倍式
     * @throws PolyDivideByZeroException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static Polynomial minCommonMultiple(Polynomial p1, Polynomial p2) throws PolyDivideByZeroException {
        return (p1.multiply(p2)).divide(maxCommonFactor(p1, p2));
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
