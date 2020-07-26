package com.jzy.xxaqsxjc.method0;

import java.math.BigInteger;

import java.util.ArrayList;

/**
 * 大数的计算方法工具类，大部分常用的计算都可直接从类中直接调用
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class Method0 {

    /**
     * 大整数常量-1
     */
    public static final BigInteger VALUE_MINUS_1 = BigInteger.valueOf(-1);

    /**
     * 大整数常量0
     */
    public static final BigInteger VALUE_0 = BigInteger.valueOf(0);

    /**
     * 大整数常量1
     */
    public static final BigInteger VALUE_1 = BigInteger.valueOf(1);

    /**
     * 大整数常量2
     */
    public static final BigInteger VALUE_2 = BigInteger.valueOf(2);

    /**
     * 大整数常量4
     */
    public static final BigInteger VALUE_4 = BigInteger.valueOf(4);

    /**
     * 大整数常量8
     */
    public static final BigInteger VALUE_8 = BigInteger.valueOf(8);

    /**
     * 贝祖等式计算类的静态对象，设为静态，编译时生成，提升性能
     */
    private static BezoutEquationSolution bezoutEquationSolution = new BezoutEquationSolution();

    /**
     * 欧拉函数值的计算
     *
     * @param n 入参大整数n
     * @return n的欧拉函数值
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger euler(BigInteger n) {
        return EulerFunction.euler(n);
    }

    /**
     * 计算出系数多项式s
     *
     * @param x 入参大整数x
     * @param y 入参大整数y
     * @return 贝祖等式系数多项式s
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger bezoutSolveQrS11(BigInteger x, BigInteger y) {
        return bezoutEquationSolution.bezoutSolveQrS11(x, y);
    }

    /**
     * 计算出系数s, t
     * <p>返回s,t构成的长度为2数组，s在前，t在后<br>
     *
     * @param x 入参大整数x
     * @param y 入参大整数y
     * @return 贝祖等式系数多项式s
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger[] bezoutSolveQrSt(BigInteger x, BigInteger y) {
        return bezoutEquationSolution.bezoutSolveQrSt(x, y);
    }

    /**
     * 绝对值最小余数，b^n mod m
     *
     * @param b b^n mod m中的b
     * @param n b^n mod m中的n
     * @param m b^n mod m中的m
     * @return b^n mod m的值
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger calculateAbsMinMod(BigInteger b, BigInteger n, BigInteger m) {
        return CalculateMod.calculateAbsMinMod(b, n, m);
    }

    /**
     * 最小非负余数，b^n mod m
     *
     * @param b b^n mod m中的b
     * @param n b^n mod m中的n
     * @param m b^n mod m中的m
     * @return b^n mod m的值
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger calculateMod(BigInteger b, BigInteger n, BigInteger m) {
        return CalculateMod.calculateMod(b, n, m);
    }

    /**
     * 费马素性检验，返回1表示是素数，0反之 ,不传安全参数即默认k=15，即fermat(n, 15);
     *
     * @param n 入参大整数n
     * @return 素性检验结果，返回1表示是素数，0反之
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int fermat(BigInteger n) {
        return PrimeTest.fermat(n);
    }

    /**
     * 费马素性检验，返回1表示是素数，0反之
     *
     * @param n 入参大整数n
     * @param t 安全参数，值越大检验准确性越高但耗时越长，默认为15
     * @return 素性检验结果，返回1表示是素数，0反之
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int fermat(BigInteger n, int t) {
        return PrimeTest.fermat(n, t);
    }

    /**
     * 雅可比符号的计算Jacobi(q / p)，返回1或0或-1
     *
     * @param q Jacobi(q / p)的q
     * @param p Jacobi(q / p)的p
     * @return Jacobi(q / p)的值，1或0或-1
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int jacobi(BigInteger q, BigInteger p) {
        return Jacobi.jacobi(q, p);
    }

    /**
     * 勒让得符号计算 Legendre(q/p)，返回1或0或-1
     *
     * @param q legendre(q / p)的q
     * @param p legendre(q / p)的p
     * @return legendre(q / p)的值，1或0或-1
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int legendre(BigInteger q, BigInteger p) {
        return Legendre.legendre(q, p);
    }

    /**
     * 计算多个数的最大公因数
     *
     * @param a     多个输入大数构成的数组
     * @param size  输入数组的长度，即输入大数的个数
     * @param index 数组索引，一开始必须传入0，表示从这个数组的a[index]个元素开始递归计算
     * @return a[]中所有数的最大公因数
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger maxCommonFactorAll(BigInteger[] a, int size, int index) {
        return CommonFactorMultiple.maxCommonFactorAll(a, size, index);
    }

    /**
     * 两个数的最大公因数
     * <p>用辗转相除法，递归实现<br>
     *
     * @param x 入参大整数x
     * @param y 入参大整数y
     * @return (x, y)，即x和y的最大公因数
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger maxCommonFactorXY(BigInteger x, BigInteger y) {
        return CommonFactorMultiple.maxCommonFactorXY(x, y);
    }

    /**
     * MillerRabin素性检验，返回1表示是素数，0反之 ,不传安全参数即默认15，即millerRabin(n, 15)
     *
     * @param n 入参大整数n
     * @return 素性检验结果，返回1表示是素数，0反之
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int millerRabin(BigInteger n) {
        return PrimeTest.millerRabin(n);
    }

    /**
     * MillerRabin素性检验，返回1表示是素数，0反之
     *
     * @param n 入参大整数n
     * @param k 安全参数，值越大检验准确性越高但耗时越长，默认为15
     * @return 素性检验结果，返回1表示是素数，0反之
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int millerRabin(BigInteger n, int k) {
        return PrimeTest.millerRabin(n, k);
    }

    /**
     * 多个数的最小公倍数
     *
     * @param a     多个输入大数构成的数组
     * @param size  输入数组的长度，即输入大数的个数
     * @param index 数组索引，一开始必须传入0，表示从这个数组的a[index]个元素开始递归计算
     * @return a[]中所有数的最小公倍数
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger minCommonMultipleAll(BigInteger[] a, int size, int index) {
        return CommonFactorMultiple.minCommonMultipleAll(a, size, index);
    }

    /**
     * 两个数的最小公倍数
     * <p>=x*y/(x,y)<br>
     *
     * @param x 入参大整数x
     * @param y 入参大整数y
     * @return [x, y]，即x,y的最小公倍数
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger minCommonMultipleXY(BigInteger x, BigInteger y) {
        return CommonFactorMultiple.minCommonMultipleXY(x, y);
    }

    /**
     * 求出p的最小原根
     *
     * @param p 入参大整数p
     * @return p的最小原根
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger minPrimitiveRoot(BigInteger p) {
        return PrimitiveRoot.minPrimitiveRoot(p);
    }

    /**
     * 求出x的所有的素因数，返回所有素因数组成的ArrayList
     *
     * @param x 入参大整数x
     * @return x的所有素因数组成的ArrayList
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static ArrayList<BigInteger> primeFactor(BigInteger x) {
        return PrimeTest.primeFactor(x);
    }

    /**
     * 所有原根的计算，返回所有原根组成的ArrayList
     *
     * @param p 入参大整数p
     * @return 所有原根组成的ArrayList
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static ArrayList<BigInteger> primitiveRoots(BigInteger p) {
        return PrimitiveRoot.primitiveRoots(p);
    }

    /**
     * SolovayStassenPrimeTest素性检验 ，返回1表示是素数，0反之 ,不传安全参数即默认15，即solovayStassen(n, 15)
     *
     * @param n 入参大整数n
     * @return 素性检验结果，返回1表示是素数，0反之
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int solovayStassen(BigInteger n) {
        return PrimeTest.solovayStassen(n);
    }

    /**
     * SolovayStassenPrimeTest素性检验 ，返回1表示是素数，0反之
     *
     * @param n 入参大整数n
     * @param t 安全参数，值越大检验准确性越高但耗时越长，默认为15
     * @return 素性检验结果，返回1表示是素数，0反之
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int solovayStassen(BigInteger n, int t) {
        return PrimeTest.solovayStassen(n, t);
    }

    /**
     * 中国剩余定理求解，方程组：x同余b 模 m
     * <p>多个方程时系数存储在数组b[]，m[]中，返回值[]x<br>
     * 即解为x=x[0]+x[1]*q(q为整数)
     *
     * @param b 方程组：x同余b 模 m中的参数b数组
     * @param m 方程组：x同余b 模 m中的参数m数组
     * @return 返回值[]x，解为x=x[0]+x[1]*q(q为整数)
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static BigInteger[] solveChineseRemainderTheorem(BigInteger[] b, BigInteger[] m) {
        return ChineseRemainderTheorem.solve(b, m);
    }

    /**
     * 暴力法素性检验，返回1表示是素数，0反之
     *
     * @param n 入参大整数，必须小于Integer.MAX_VALUE，否则会抛异常
     * @return 返回1表示是素数，0反之
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int violentIfPrime(BigInteger n) {
        return PrimeTest.violentIfPrime(n);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
