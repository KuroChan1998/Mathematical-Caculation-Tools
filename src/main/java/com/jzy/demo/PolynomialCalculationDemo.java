package com.jzy.demo;

import com.jzy.exception.ploynomial.PolyDivideByZeroException;
import com.jzy.xxaqsxjc.method1.Polynomial;
import com.jzy.xxaqsxjc.method1.PolynomialCalculation;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName PolynomialCalculationDemo
 * @description 使用示例：供多项式的一些延展计算：最大公因式、最小公倍式、不可约、本原多项式判断。 {@link PolynomialCalculation}
 * @date 2019/11/9 16:40
 **/
public class PolynomialCalculationDemo {
    private PolynomialCalculationDemo() {
    }

    public static void main(String[] args) throws PolyDivideByZeroException {
        //1+x+x^2+x^4
        int[] a = {1, 1, 1, 0, 1};
        //1+x^3
        int[] b = {1, 0, 0, 1};
        Polynomial pa = new Polynomial(a);
        Polynomial pb = new Polynomial(b);

        //a, b最大公因式
        System.out.println(PolynomialCalculation.maxCommonFactor(pa, pb));
        //a, b最小公倍式
        System.out.println(PolynomialCalculation.minCommonMultiple(pa, pb));
        //a是否为不可约多项式
        System.out.println(PolynomialCalculation.ifReduciblePolynomial(pa));
        //a是否为本原多项式
        System.out.println(PolynomialCalculation.ifPrimPolynomial(pa));
    }
}
