package com.jzy.demo;

import com.jzy.xxaqsxjc.method1.Method1;
import com.jzy.xxaqsxjc.method1.Polynomial;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName PolynomialDemo
 * @description 使用示例：多项式的基本计算（加减乘除模...） {@link Polynomial}
 * @date 2019/11/9 16:30
 **/
public class PolynomialDemo {
    private PolynomialDemo(){}

    public static void main(String[] args) {
        //1+x+x^2+x^4
        int[] a = {1, 1, 1, 0, 1};
        //1+x^2+x^3+x^4+x^8
        int[] b = {1, 0, 1, 1, 1, 0, 0, 0, 1};
        Polynomial pa = new Polynomial(a);
        Polynomial pb = new Polynomial(b);
        System.out.println(pa);
        System.out.println(pb);
        //多项式计算
        System.out.println(pa.add(pb));
        System.out.println(pa.multiply(pb));
        System.out.println(pb.divide(pa));
        System.out.println(Polynomial.pow(pb, 10));
        //多项式最大公因式
        System.out.println(Method1.maxCommonFactor(pa,pb));
    }
}
