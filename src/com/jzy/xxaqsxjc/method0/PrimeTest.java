package com.jzy.xxaqsxjc.method0;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Random;

import com.jzy.exception.integer.InputTooLargeException;
import com.jzy.exception.integer.SecurityParameterNonPositiveException;
import com.jzy.util.BigIntegerTest;

/**
 * 素性检验大类
 * 含暴力检测法、fermat素性检验、MillerRabin素性检验、SolovayStassen素性检验。
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class PrimeTest {

    /**
     * A_0, A_1, A_2 : 大整数常量，便于后边代码调用
     */
    private static final BigInteger A_0 = Method0.VALUE_0;
    private static final BigInteger A_1 = Method0.VALUE_1;
    private static final BigInteger A_2 = Method0.VALUE_2;

    /**
     * 默认安全参数为15
     */
    public static final int SECURITY_PARAMETER_DEFAULT = 15;

    /**
     * 暴力检测素数法的建议最大输入值为Long.MAX_VALUE
     */
    public static final BigInteger VIOLENT_PRIME_TEST_MAX_INPUT_RECOMMENDED = BigInteger.valueOf(Integer.MAX_VALUE);

    /**
     * 费马素性检验，返回1表示是素数，0反之 ,不传安全参数即默认k=15，即fermat(n, 15);
     *
     * @param n 入参大整数n
     * @return 素性检验结果，返回1表示是素数，0反之
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int fermat(BigInteger n) {
        return fermat(n, SECURITY_PARAMETER_DEFAULT);
    }

    /**
     * 费马素性检验，返回1表示是素数，0反之
     *
     * @param n 入参大整数n
     * @param t 安全参数，值越大检验准确性越高但耗时越长，默认为15
     * @return 素性检验结果，返回1表示是素数，0反之
     * @throws SecurityParameterNonPositiveException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int fermat(BigInteger n, int t) throws SecurityParameterNonPositiveException {
        if (t <= 0) {
            throw new SecurityParameterNonPositiveException("安全参数必须为正整数！");
        }

        if ((n == null) || (n.compareTo(Method0.VALUE_1) <= 0)) {
            return 0;
        }

        if (BigIntegerTest.ifEqualsTwo(n) || (n.compareTo(new BigInteger("3")) == 0)) {
            return 1;
        } else {
            if ((n.mod(A_2)).compareTo(A_0) == 0) {
                return 0;
            }
        }

        BigInteger b;

        for (int i = 0; i < t; i++) {
            do {
                b = new BigInteger(n.bitLength(), new Random());
                b = b.mod(n);
            } while ((b.compareTo(A_2) < 0) || (b.compareTo(n.subtract(A_2)) > 0));

            if (Method0.maxCommonFactorXY(b, n).compareTo(A_1) > 0) {
                return 0;
            } else {
                if (Method0.calculateMod(b, n.subtract(A_1), n).compareTo(A_1) == 0) {
                    continue;
                } else {
                    return 0;
                }
            }
        }

        return 1;
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
        return millerRabin(n, SECURITY_PARAMETER_DEFAULT);
    }

    /**
     * MillerRabin素性检验，返回1表示是素数，0反之
     *
     * @param n 入参大整数n
     * @param k 安全参数，值越大检验准确性越高但耗时越长，默认为15
     * @return 素性检验结果，返回1表示是素数，0反之
     * @throws SecurityParameterNonPositiveException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int millerRabin(BigInteger n, int k) throws SecurityParameterNonPositiveException {
        if (k <= 0) {
            throw new SecurityParameterNonPositiveException("安全参数必须为正整数！");
        }

        if ((n == null) || (n.compareTo(Method0.VALUE_1) <= 0)) {
            return 0;
        }

        if (BigIntegerTest.ifEqualsTwo(n) || (n.compareTo(new BigInteger("3")) == 0)) {
            return 1;
        } else {
            if ((n.mod(A_2)).compareTo(A_0) == 0) {
                return 0;
            }
        }

        BigInteger b, t,
                tempp = n.subtract(A_1);
        long s = 0;

        while (tempp.mod(A_2).compareTo(A_0) == 0) {
            s++;
            tempp = tempp.divide(A_2);
        }

        t = tempp;

        BigInteger r;

        for (int i = 0; i < k; i++) {
            do {
                b = new BigInteger(n.bitLength(), new Random());
                b = b.mod(n);
            } while ((b.compareTo(A_2) < 0) || (b.compareTo(n.subtract(A_2)) > 0));

            r = Method0.calculateMod(b, t, n);

            if ((s == 1) && (r.compareTo(A_1) != 0) && (r.compareTo(n.subtract(A_1)) != 0)) {
                return 0;
            } else {
                if ((r.compareTo(A_1) == 0) || (r.compareTo(n.subtract(A_1)) == 0)) {
                    continue;
                } else {
                    for (long j = 1; j < s; j++) {
                        r = (r.multiply(r)).mod(n);

                        if (r.compareTo(n.subtract(A_1)) == 0) {
                            break;
                        }

                        if ((j == s - 1) && (r.compareTo(n.subtract(A_1)) != 0)) {
                            return 0;
                        }
                    }
                }
            }
        }

        return 1;
    }

    /**
     * 求出x的所有的素因数，返回所有素因数组成的ArrayList
     *
     * @param x 入参大整数x
     * @return x的所有素因数组成的ArrayList
     * @throws InputTooLargeException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static ArrayList<BigInteger> primeFactor(BigInteger x) throws InputTooLargeException {
        ArrayList<BigInteger> primeFactorX = new ArrayList<>();

        if (x.compareTo(VIOLENT_PRIME_TEST_MAX_INPUT_RECOMMENDED) > 0) {
            throw new InputTooLargeException("输入的值大于" + VIOLENT_PRIME_TEST_MAX_INPUT_RECOMMENDED
                    + "使用暴力素数检测耗时将不可预计，不可取!");
        }

        if (x.compareTo(Method0.VALUE_1) > 0) {
            int r = solovayStassen(x);

            if (r == 1) {
                primeFactorX.add(x);
            } else {
                BigInteger tmpx = x;
                BigInteger i = Method0.VALUE_2;

                while (!BigIntegerTest.ifEqualsOne(tmpx)) {
                    if (BigIntegerTest.ifEqualsZero(tmpx.mod(i))) {
                        primeFactorX.add(i);

                        while (BigIntegerTest.ifEqualsZero(tmpx.mod(i))) {
                            tmpx = tmpx.divide(i);
                        }
                    }

                    do {
                        i = i.add(Method0.VALUE_1);
                    } while (solovayStassen(i) == 0);
                }
            }
        }

        return primeFactorX;
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
        return solovayStassen(n, SECURITY_PARAMETER_DEFAULT);
    }

    /**
     * SolovayStassenPrimeTest素性检验 ，返回1表示是素数，0反之
     *
     * @param n 入参大整数n
     * @param t 安全参数，值越大检验准确性越高但耗时越长，默认为15
     * @return 素性检验结果，返回1表示是素数，0反之
     * @throws SecurityParameterNonPositiveException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int solovayStassen(BigInteger n, int t) throws SecurityParameterNonPositiveException {
        if (t <= 0) {
            throw new SecurityParameterNonPositiveException("安全参数必须为正整数！");
        }

        if ((n == null) || (n.compareTo(Method0.VALUE_1) <= 0)) {
            return 0;
        }

        if (BigIntegerTest.ifEqualsTwo(n) || (n.compareTo(new BigInteger("3")) == 0)) {
            return 1;
        } else {
            if ((n.mod(A_2)).compareTo(A_0) == 0) {
                return 0;
            }
        }

        BigInteger b;

        for (int i = 0; i < t; i++) {
            do {
                b = new BigInteger(n.bitLength(), new Random());
                b = b.mod(n);
            } while ((b.compareTo(A_2) < 0) || (b.compareTo(n.subtract(A_2)) > 0));

            BigInteger r = Method0.calculateMod(b, n.subtract(A_1).divide(A_2), n);

            if ((r.compareTo(A_1) != 0) && (r.compareTo(n.subtract(A_1)) != 0)) {
                return 0;
            } else {
                int s = Method0.jacobi(b, n);

                if (r.compareTo(n.subtract(A_1)) == 0) {
                    r = r.subtract(n);
                }

                if (r.compareTo(BigInteger.valueOf((long) s)) != 0) {
                    return 0;
                }
            }
        }

        return 1;
    }

    /**
     * 暴力法素性检验，返回1表示是素数，0反之
     *
     * @param n 入参大整数，必须小于Integer.MAX_VALUE，否则会抛异常
     * @return 返回1表示是素数，0反之
     * @throws InputTooLargeException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static int violentIfPrime(BigInteger n) throws InputTooLargeException {
        if (n.compareTo(VIOLENT_PRIME_TEST_MAX_INPUT_RECOMMENDED) > 0) {
            throw new InputTooLargeException("输入的值大于" + VIOLENT_PRIME_TEST_MAX_INPUT_RECOMMENDED
                    + "使用暴力素数检测耗时将不可预计，不可取!");
        }

        if (BigIntegerTest.ifEqualsOne(n)) {
            return 0;
        } else if (BigIntegerTest.ifEqualsTwo(n) || (n.compareTo(new BigInteger("3")) == 0)) {
            return 1;
        } else {
            long n0 = n.longValue();
            long p = (long) Math.sqrt(n0);

            for (long i = 2; i <= p; i++) {
                if (n0 % i == 0) {
                    return 0;
                }
            }

            return 1;
        }
    }

//  public static void main(String[] args) {
//      System.out.println(primeFactor(new BigInteger("46116860184270")));
//      System.out.println(Long.MAX_VALUE);
//  }
}


//~ Formatted by Jindent --- http://www.jindent.com
