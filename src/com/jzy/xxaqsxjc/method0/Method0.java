package com.jzy.xxaqsxjc.method0;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @ClassName Method0
 * @Author JinZhiyun
 * @Description 大数的计算方法工具类，大部分常用的计算都可直接从类中直接调用
 * @Date 2019/8/25 16:53
 * @Version 1.0
 **/
public class Method0 {
    /**
     * @Fields VALUE_MINUS_1: 大整数常量-1
     */
    public static final BigInteger VALUE_MINUS_1 = BigInteger.valueOf(-1);

    /**
     * @Fields VALUE_0: 大整数常量0
     */
    public static final BigInteger VALUE_0 = BigInteger.valueOf(0);

    /**
     * @Fields VALUE_1: 大整数常量1
     */
    public static final BigInteger VALUE_1 = BigInteger.valueOf(1);

    /**
     * @Fields VALUE_2: 大整数常量2
     */
    public static final BigInteger VALUE_2 = BigInteger.valueOf(2);

    /**
     * @Fields VALUE_4: 大整数常量4
     */
    public static final BigInteger VALUE_4 = BigInteger.valueOf(4);

    /**
     * @Fields VALUE_8: 大整数常量8
     */
    public static final BigInteger VALUE_8 = BigInteger.valueOf(8);

    /**
     * @Fields bezoutEquationSolution: 贝祖等式计算类的静态对象，设为静态，编译时生成，提升性能
     */
    private static BezoutEquationSolution bezoutEquationSolution = new BezoutEquationSolution();

    /**
     * @return java.math.BigInteger
     * @Author JinZhiyun
     * @Description 贝祖等式求解：s*x+t*y=(x,y)，计算出系数s（x的逆元，只为正）
     * @Date 18:27 2019/8/25
     * @Param [x, y]
     **/
    public static BigInteger bezoutSolveQrS11(BigInteger x, BigInteger y) {
        return bezoutEquationSolution.bezoutSolveQrS11(x, y);
    }

    /**
     * @return java.math.BigInteger[]
     * @Author JinZhiyun
     * @Description 贝祖等式求解：s*x+t*y=(x,y)，计算出系数s,t
     * @Date 18:29 2019/8/25
     * @Param [x, y]
     **/
    public static BigInteger[] bezoutSolveQrSt(BigInteger x, BigInteger y) {
        return bezoutEquationSolution.bezoutSolveQrSt(x, y);
    }

    /**
     * @return java.math.BigInteger
     * @Author JinZhiyun
     * @Description b^n模m的最小非负余数计算
     * @Date 20:31 2019/8/25
     * @Param [b, n, m]
     **/
    public static BigInteger calculateMod(BigInteger b, BigInteger n, BigInteger m) {
        return CalculateMod.calculateMod(b, n, m);
    }

    /**
     * @return java.math.BigInteger
     * @Author JinZhiyun
     * @Description b^n模m的绝对值最小余数计算
     * @Date 20:33 2019/8/25
     * @Param [b, n, m]
     **/
    public static BigInteger calculateAbsMinMod(BigInteger b, BigInteger n, BigInteger m) {
        return CalculateMod.calculateAbsMinMod(b, n, m);
    }

    /**
     * @return java.math.BigInteger
     * @Author JinZhiyun
     * @Description 两个数的最大公因数
     * @Date 21:02 2019/8/25
     * @Param [x, y]
     **/
    public static BigInteger maxCommonFactorXY(BigInteger x, BigInteger y) {
        return CommonFactorMultiple.maxCommonFactorXY(x, y);
    }

    /**
     * @return java.math.BigInteger
     * @Author JinZhiyun
     * @Description 多个数的最大公因数
     * @Date 21:11 2019/8/25
     * @Param [a, size, index]
     **/
    public static BigInteger maxCommonFactorAll(BigInteger[] a, int size, int index) {
        return CommonFactorMultiple.maxCommonFactorAll(a, size, index);
    }

    /**
     * @return java.math.BigInteger
     * @Author JinZhiyun
     * @Description 两个数的最小公倍数
     * @Date 21:13 2019/8/25
     * @Param [x, y]
     **/
    public static BigInteger minCommonMultipleXY(BigInteger x, BigInteger y) {
        return CommonFactorMultiple.minCommonMultipleXY(x, y);
    }

    /**
     * @return java.math.BigInteger
     * @Author JinZhiyun
     * @Description 多个数的最小公倍数
     * @Date 21:14 2019/8/25
     * @Param [a, size, index]
     **/
    public static BigInteger minCommonMultipleAll(BigInteger[] a, int size, int index) {
        return CommonFactorMultiple.minCommonMultipleAll(a, size, index);
    }

    /**
     * @return java.math.BigInteger
     * @Author JinZhiyun
     * @Description 欧拉函数值的计算
     * @Date 21:23 2019/8/25
     * @Param [n]
     **/
    public static BigInteger Euler(BigInteger n) {
        return EulerFunction.Euler(n);
    }

    /**
     * @return int
     * @Author JinZhiyun
     * @Description 雅可比符号的计算Jacobi(q / p)，返回1或0或-1
     * @Date 21:34 2019/8/25
     * @Param [q, p]
     **/
    public static int jacobi(BigInteger q, BigInteger p) {
        return Jacobi.jacobi(q, p);
    }

    /**
     * @return int
     * @Author JinZhiyun
     * @Description 勒让得符号计算 Legendre(q/p)，返回1或0或-1
     * @Date 21:38 2019/8/25
     * @Param [q, p]
     **/
    public static int legendre(BigInteger q, BigInteger p) {
        return Legendre.legendre(q, p);
    }

    /**
     * @return int
     * @Author JinZhiyun
     * @Description 费马素性检验 ，返回1表示是素数，0反之 ,不传安全参数即默认15
     * @Date 8:54 2019/8/26
     * @Param [n]
     **/
    public static int fermat(BigInteger n) {
        return PrimeTest.fermat(n);
    }

    /**
     * @return int
     * @Author JinZhiyun
     * @Description 费马素性检验 ，返回1表示是素数，0反之;t安全参数，值越大检验准确性越高但耗时越长，建议为15
     * @Date 8:55 2019/8/26
     * @Param [n, t]
     **/
    public static int fermat(BigInteger n, int t) {
        return PrimeTest.fermat(n, t);
    }

    /**
     * @return int
     * @Author JinZhiyun
     * @Description MillerRabin素性检验，返回1表示是素数，0反之 ,不传安全参数即默认15
     * @Date 8:55 2019/8/26
     * @Param [n]
     **/
    public static int millerRabin(BigInteger n) {
        return PrimeTest.millerRabin(n);
    }

    /**
     * @return int
     * @Author JinZhiyun
     * @Description MillerRabin素性检验，返回1表示是素数，0反之;k安全参数，值越大检验准确性越高但耗时越长，建议为15
     * @Date 8:56 2019/8/26
     * @Param [n, k]
     **/
    public static int millerRabin(BigInteger n, int k) {
        return PrimeTest.millerRabin(n, k);
    }

    /**
     * @return int
     * @Author JinZhiyun
     * @Description SolovayStassenPrimeTest素性检验 ，返回1表示是素数，0反之 ,不传安全参数即默认15
     * @Date 8:57 2019/8/26
     * @Param [n]
     **/
    public static int solovayStassen(BigInteger n) {
        return PrimeTest.solovayStassen(n);
    }

    /**
     * @return int
     * @Author JinZhiyun
     * @Description SolovayStassenPrimeTest素性检验 ，返回1表示是素数，0反之;t安全参数，值越大检验准确性越高但耗时越长，建议为15
     * @Date 8:57 2019/8/26
     * @Param [n, t]
     **/
    public static int solovayStassen(BigInteger n, int t) {
        return PrimeTest.solovayStassen(n, t);
    }

    /**
     * @return int
     * @Author JinZhiyun
     * @Description 暴力法素性检验，返回1表示是素数，0反之;n必须小于Integer.MAX_VALUE
     * @Date 8:58 2019/8/26
     * @Param [n]
     **/
    public static int violentIfPrime(BigInteger n) {
        return PrimeTest.violentIfPrime(n);
    }

    /**
     * @return java.util.ArrayList<java.math.BigInteger>
     * @Author JinZhiyun
     * @Description 求出所有的素因数;x必须小于Integer.MAX_VALUE
     * @Date 11:12 2019/8/26
     * @Param [x]
     **/
    public static ArrayList<BigInteger> primeFactor(BigInteger x) {
        return PrimeTest.primeFactor(x);
    }

    /**
     * @return java.math.BigInteger
     * @Author JinZhiyun
     * @Description 求出最小原根
     * @Date 12:41 2019/8/26
     * @Param [p]
     **/
    public static BigInteger minPrimitiveRoot(BigInteger p) {
        return PrimitiveRoot.minPrimitiveRoot(p);
    }

    /**
     * @return java.util.ArrayList<java.math.BigInteger>
     * @Author JinZhiyun
     * @Description 所有原根的计算，返回ArrayList<BigInteger>
     * @Date 12:41 2019/8/26
     * @Param [p]
     **/
    public static ArrayList<BigInteger> primitiveRoots(BigInteger p) {
        return PrimitiveRoot.primitiveRoots(p);
    }

    /**
     * @return java.math.BigInteger[]
     * @Author JinZhiyun
     * @Description 中国剩余定理求解，方程组：x同余b 模 m（多个方程时系数存储在数组b[]，m[]中）m，返回值[]x，即解为x=x[0]+x[1]*q(q为整数)
     * @Date 17:00 2019/8/26
     * @Param [b, m]
     **/
    public static BigInteger[] solve(BigInteger[] b, BigInteger[] m) {
        return ChineseRemainderTheorem.solve(b, m);
    }
}
