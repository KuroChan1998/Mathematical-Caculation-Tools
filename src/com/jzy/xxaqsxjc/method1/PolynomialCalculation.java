/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: PolynomialCalculation.java
 * @Package com.jzy.xxaqsxjc.method1
 * @Description: 提供多项式的一些延展计算， 最大公因式、最小公倍式、不可约、本原多项式判断等
 * @author: JinZhiyun
 * @date: 2019年3月15日 上午10:06:52
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.xxaqsxjc.method1;

import java.math.BigInteger;
import java.util.ArrayList;

import com.jzy.exception.ploynomial.InputPolyIsZeroException;
import com.jzy.exception.ploynomial.PolyDivideByZeroException;
import com.jzy.util.PolynomialTest;
import com.jzy.xxaqsxjc.method0.Method0;


/**
 * @ClassName: PolynomialCalculation
 * @Description: 提供多项式的一些延展计算， 最大公因式、最小公倍式、不可约、本原多项式判断等
 * @author: JinZhiyun
 * @date: 2019年3月15日 上午10:04:54
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class PolynomialCalculation {
    /**
     * @Fields result : 存储最大公因式和最小公倍式
     */
    private static Polynomial result;

    /**
     * @throws
     * @Title: maxCommonFactor
     * @Description: 最大公因式
     * @param: @param p1
     * @param: @param p2
     * @param: @return
     * @param: @throws PolyDivideByZeroException
     * @return: Polynomial
     */
    public static Polynomial maxCommonFactor(Polynomial p1, Polynomial p2) throws PolyDivideByZeroException {
        if (PolynomialTest.ifEqualZero(p1) || PolynomialTest.ifEqualZero(p2)) {
            throw new InputPolyIsZeroException("输入的多项式不能为0");
        }
        if (p1.effectiveMaxDeg < p2.effectiveMaxDeg) {
            Polynomial tmp = p2;
            p2 = p1;
            p1 = tmp;
        }
        Polynomial rp = p1.mod(p2);
        if (!rp.equals(Method1.POLY_0) && rp.effectiveMaxDeg >= 1) {
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
     * @throws
     * @Title: minCommonMultiple
     * @Description: 最小公倍式
     * @param: @param p1
     * @param: @param p2
     * @param: @return
     * @param: @throws PolyDivideByZeroException
     * @return: Polynomial
     */
    public static Polynomial minCommonMultiple(Polynomial p1, Polynomial p2) throws PolyDivideByZeroException {
        return (p1.multiply(p2)).divide(maxCommonFactor(p1, p2));
    }

    /**
     * @throws
     * @Title: ifReduciblePolynomial
     * @Description: 是否为不可约多项式，1是，0不是
     * @param: @param p1
     * @param: @return
     * @param: @throws PolyDivideByZeroException
     * @return: int
     */
    public static int ifReduciblePolynomial(Polynomial p1) throws PolyDivideByZeroException {
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

    /**
     * @throws
     * @Title: ifPrimPolynomial
     * @Description: 本原多项式判断， 1是，0不是
     * @param: @param p1
     * @param: @return
     * @param: @throws PolyDivideByZeroException
     * @return: int
     */
    public static int ifPrimPolynomial(Polynomial p1) throws PolyDivideByZeroException {
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

    public static void main(String[] args) throws PolyDivideByZeroException {
        int[] a = {3, 3, 1, 0, 1};
        int[] b = {1, 0, 0, 1};
        Polynomial pa = new Polynomial(a);
        Polynomial pb = new Polynomial(b);
        System.out.println(PolynomialCalculation.maxCommonFactor(pa, pb));
        System.out.println(PolynomialCalculation.minCommonMultiple(pa, pb));
        System.out.println(PolynomialCalculation.ifReduciblePolynomial(pa));
        System.out.println(PolynomialCalculation.ifPrimPolynomial(pa));
    }

}
