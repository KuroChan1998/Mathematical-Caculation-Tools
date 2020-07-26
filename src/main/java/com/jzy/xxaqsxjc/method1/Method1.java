package com.jzy.xxaqsxjc.method1;

/**
 * 多项式的计算方法工具类，大部分常用的计算都可直接从类中直接调用
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class Method1 {

    /**
     * 多项式常量0
     */
    public static final Polynomial POLY_0 = new Polynomial();

    /**
     * 多项式常量1
     */
    public static final Polynomial POLY_1 = new Polynomial(0);

    /**
     * 多项式常量x
     */
    public static final Polynomial POLY_X = new Polynomial(1);

    /**
     * 贝祖等式计算类的静态对象，设为静态，编译时生成，提升性能
     */
    private static PolynomialBezoutEquationSolution bezoutEquationSolution = new PolynomialBezoutEquationSolution();

    /**
     * 本原多项式判断， 1是，0不是
     *
     * @param p1 入参多项式p1
     * @return 0或1；1表示是本原多项式，0表示不是本原多项式
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int ifPrimPolynomial(Polynomial p1) {
        return PolynomialCalculation.ifPrimPolynomial(p1);
    }

    /**
     * 是否为不可约多项式，1是，0不是
     *
     * @param p1 入参多项式p1
     * @return 0或1；1表示是不可约多项式，0表示不是不可约多项式
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int ifReduciblePolynomial(Polynomial p1) {
        return PolynomialCalculation.ifReduciblePolynomial(p1);
    }

    /**
     * 最大公因式
     *
     * @param p1 入参多项式p1
     * @param p2 入参多项式p2
     * @return (p1, p2)，即p1和p2的最大公因式
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static Polynomial maxCommonFactor(Polynomial p1, Polynomial p2) {
        return PolynomialCalculation.maxCommonFactor(p1, p2);
    }

    /**
     * 最小公倍式
     *
     * @param p1 入参多项式p1
     * @param p2 入参多项式p2
     * @return [p1, p2]，即p1和p2的最小公倍式
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static Polynomial minCommonMultiple(Polynomial p1, Polynomial p2) {
        return PolynomialCalculation.minCommonMultiple(p1, p2);
    }

    /**
     * 计算出系数多项式s
     *
     * @param x 入参多项式x
     * @param y 入参多项式y
     * @return 贝祖等式系数多项式s
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static Polynomial polynomialBezoutSolveQrS11(Polynomial x, Polynomial y) {
        return bezoutEquationSolution.polynomialBezoutSolveQrS11(x, y);
    }

    /**
     * 计算出系数s, t
     * <p>返回s,t构成的长度为2数组，s在前，t在后<br>
     *
     * @param x 入参多项式x
     * @param y 入参多项式y
     * @return 贝祖等式系数多项式s, t构成的长度为2数组
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static Polynomial[] polynomialBezoutSolveQrSt(Polynomial x, Polynomial y) {
        return bezoutEquationSolution.polynomialBezoutSolveQrSt(x, y);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
