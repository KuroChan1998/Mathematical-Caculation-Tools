package com.jzy.xxaqsxjc.method1;

/**
 * @ClassName Method1
 * @Author JinZhiyun
 * @Description 多项式的计算方法工具类，大部分常用的计算都可直接从类中直接调用
 * @Date 2019/8/27 22:17
 * @Version 1.0
 **/
public class Method1 {
    /**
     * @Fields POLY_0: 多项式常量0
     */
    public static final Polynomial POLY_0 = new Polynomial();

    /**
     * @Fields POLY_1: 多项式常量1
     */
    public static final Polynomial POLY_1 = new Polynomial(0);

    /**
     * @Fields POLY_X: 多项式常量x
     */
    public static final Polynomial POLY_X = new Polynomial(1);

    /**
     * @Fields bezoutEquationSolution: 贝祖等式计算类的静态对象，设为静态，编译时生成，提升性能
     */
    private static PolynomialBezoutEquationSolution bezoutEquationSolution = new PolynomialBezoutEquationSolution();

    /**
     * @return com.jzy.xxaqsxjc.method1.Polynomial[]
     * @author JinZhiyun
     * @Description 多项式的贝祖等式求解 s*x+t*y=(x,y)，求出多项式s,t
     * @Date 10:31 2019/8/28
     * @Param [x, y]
     **/
    public static Polynomial[] polynomialBezoutSolveQrSt(Polynomial x, Polynomial y) {
        return bezoutEquationSolution.polynomialBezoutSolveQrSt(x, y);
    }

    /**
     * @return com.jzy.xxaqsxjc.method1.Polynomial
     * @author JinZhiyun
     * @Description 多项式的贝祖等式求解：s*x+t*y=(x,y)，计算出系数s
     * @Date 10:32 2019/8/28
     * @Param [x, y]
     **/
    public static Polynomial polynomialBezoutSolveQrS11(Polynomial x, Polynomial y) {
        return bezoutEquationSolution.polynomialBezoutSolveQrS11(x, y);
    }

    /**
     * @return com.jzy.xxaqsxjc.method1.Polynomial
     * @author JinZhiyun
     * @Description 多项式的最大公因式
     * @Date 9:26 2019/8/28
     * @Param [p1, p2]
     **/
    public static Polynomial maxCommonFactor(Polynomial p1, Polynomial p2) {
        return PolynomialCalculation.maxCommonFactor(p1, p2);
    }

    /**
     * @return com.jzy.xxaqsxjc.method1.Polynomial
     * @author JinZhiyun
     * @Description 多项式的最小公倍式
     * @Date 9:27 2019/8/28
     * @Param [p1, p2]
     **/
    public static Polynomial minCommonMultiple(Polynomial p1, Polynomial p2) {
        return PolynomialCalculation.minCommonMultiple(p1, p2);
    }

    /**
     * @return int
     * @author JinZhiyun
     * @Description 是否为不可约多项式，1是，0不是
     * @Date 9:28 2019/8/28
     * @Param [p1]
     **/
    public static int ifReduciblePolynomial(Polynomial p1) {
        return PolynomialCalculation.ifReduciblePolynomial(p1);
    }

    /**
     * @return int
     * @author JinZhiyun
     * @Description 本原多项式判断， 1是，0不是
     * @Date 9:32 2019/8/28
     * @Param [p1]
     **/
    public static int ifPrimPolynomial(Polynomial p1) {
        return PolynomialCalculation.ifPrimPolynomial(p1);
    }


}
