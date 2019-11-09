package com.jzy.demo;

import com.jzy.exception.ploynomial.PolyDivideByZeroException;
import com.jzy.xxaqsxjc.method1.Method1;
import com.jzy.xxaqsxjc.method1.Polynomial;
import com.jzy.xxaqsxjc.method1.PolynomialBezoutEquationSolution;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName PolynomialBezoutEquationSolutionDemo
 * @description 使用示例：多项式的贝祖等式求解 s*x+t*y=(x,y)，求出多形式s,t {@link PolynomialBezoutEquationSolution}
 * @date 2019/11/9 16:33
 **/
public class PolynomialBezoutEquationSolutionDemo {
    private PolynomialBezoutEquationSolutionDemo(){}

    public static void main(String[] args) throws PolyDivideByZeroException {
        //1+x
        int[] a = {1, 1};
        //1+x+x^3
        int[] b = {1, 1, 0, 1};
        Polynomial pa = new Polynomial(a);
        Polynomial pb = new Polynomial(b);

        Polynomial []result=Method1.polynomialBezoutSolveQrSt(pa, pb);
        System.out.println("s(x)="+result[0]+", t(x)="+result[1]);
    }
}
