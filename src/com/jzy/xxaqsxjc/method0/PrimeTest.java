/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: PrimeTest.java
 * @Package com.jzy.xxaqsxjc.method0
 * @Description: 素性检验大类
 * @author: JinZhiyun
 * @date: 2019年3月31日 上午10:41:04
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.xxaqsxjc.method0;

import com.jzy.exception.integer.SecurityParameterNonPostiveException;
import com.jzy.exception.integer.InputTooLargeException;
import com.jzy.util.BigIntegerTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 * @ClassName: PrimeTest
 * @Description: 素性检验大类
 * @author: JinZhiyun
 * @date: 2019年3月31日 上午10:41:04
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class PrimeTest {
    /**
     * @Fields A_0, A_1, A_2 : 大整数常量，便于后边代码调用
     */
    private static final BigInteger A_0 = Method0.VALUE_0;
    private static final BigInteger A_1 = Method0.VALUE_1;
    private static final BigInteger A_2 = Method0.VALUE_2;

    /**
     * @Fields SECURITY_PARAMETER_DEFAULT : 默认安全参数为15
     */
    public static final int SECURITY_PARAMETER_DEFAULT = 15;

    /**
     * @Fields VIOLENT_PRIME_TEST_MAX_INPUT_RECOMMENDED : 暴力检测素数法的建议最大输入值为Long.MAX_VALUE
     */
    public static final BigInteger VIOLENT_PRIME_TEST_MAX_INPUT_RECOMMENDED = BigInteger.valueOf(Integer.MAX_VALUE);

    /**
     * @throws
     * @Title: fermat
     * @Description: 费马素性检验 ，返回1表示是素数，0反之 ,不传安全参数即默认15
     * @param: @param n
     * @param: @return
     * @return: int
     */
    public static int fermat(BigInteger n) {
        return fermat(n, SECURITY_PARAMETER_DEFAULT);
    }

    /**
     * @throws
     * @Title: fermat
     * @Description: 费马素性检验 ，返回1表示是素数，0反之
     * @param: @param n
     * @param: @param t 安全参数，值越大检验准确性越高但耗时越长，建议为15
     * @param: @return
     * @return: int
     */
    public static int fermat(BigInteger n, int t) {
        if (t <= 0) {
            throw new SecurityParameterNonPostiveException("安全参数必须为正整数！");
        }
        if (n == null || n.compareTo(Method0.VALUE_1) <= 0) {
            return 0;
        }
        if (BigIntegerTest.ifEqualsTwo(n) || n.compareTo(new BigInteger("3")) == 0) {
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
            } while (b.compareTo(A_2) < 0 || b.compareTo(n.subtract(A_2)) > 0);

            if (Method0.maxCommonFactorXY(b, n).compareTo(A_1) > 0) {
                return 0;
            } else {
                if (Method0.calculateMod(b, n.subtract(A_1), n)
                        .compareTo(A_1) == 0) {
                    continue;
                } else {
                    return 0;
                }
            }
        }
        return 1;
    }

    /**
     * @throws
     * @Title: millerRabin
     * @Description: MillerRabin素性检验，返回1表示是素数，0反之 ,不传安全参数即默认15
     * @param: @param n
     * @param: @return
     * @return: int
     */
    public static int millerRabin(BigInteger n) {
        return millerRabin(n, SECURITY_PARAMETER_DEFAULT);
    }

    /**
     * @throws
     * @Title: millerRabin
     * @Description: MillerRabin素性检验，返回1表示是素数，0反之
     * @param: @param n
     * @param: @param k 安全参数，值越大检验准确性越高但耗时越长，建议为15
     * @param: @return
     * @return: int
     */
    public static int millerRabin(BigInteger n, int k) {
        if (k <= 0) {
            throw new SecurityParameterNonPostiveException("安全参数必须为正整数！");
        }
        if (n == null || n.compareTo(Method0.VALUE_1) <= 0) {
            return 0;
        }
        if (BigIntegerTest.ifEqualsTwo(n) || n.compareTo(new BigInteger("3")) == 0) {
            return 1;
        } else {
            if ((n.mod(A_2)).compareTo(A_0) == 0) {
                return 0;
            }
        }
        BigInteger b, t, tempp = n.subtract(A_1);
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
            } while (b.compareTo(A_2) < 0 || b.compareTo(n.subtract(A_2)) > 0);
            r = Method0.calculateMod(b, t, n);
            if (s == 1 && r.compareTo(A_1) != 0 && r.compareTo(n.subtract(A_1)) != 0) {
                return 0;
            } else {
                if (r.compareTo(A_1) == 0 || r.compareTo(n.subtract(A_1)) == 0) {
                    continue;
                } else {
                    for (long j = 1; j < s; j++) {
                        r = (r.multiply(r)).mod(n);
                        if (r.compareTo(n.subtract(A_1)) == 0) {
                            break;
                        }
                        if (j == s - 1 && r.compareTo(n.subtract(A_1)) != 0) {
                            return 0;
                        }
                    }
                }

            }
        }
        return 1;
    }

    /**
     * @throws
     * @Title: solovayStassen
     * @Description: SolovayStassenPrimeTest素性检验 ，返回1表示是素数，0反之 ,不传安全参数即默认15
     * @param: @param n
     * @param: @return
     * @return: int
     */
    public static int solovayStassen(BigInteger n) {
        return solovayStassen(n, SECURITY_PARAMETER_DEFAULT);
    }

    /**
     * @throws
     * @Title: solovayStassen
     * @Description: SolovayStassenPrimeTest素性检验 ，返回1表示是素数，0反之
     * @param: @param n
     * @param: @param t 安全参数，值越大检验准确性越高但耗时越长，建议为15
     * @param: @return
     * @return: int
     */
    public static int solovayStassen(BigInteger n, int t) {
        if (t <= 0) {
            throw new SecurityParameterNonPostiveException("安全参数必须为正整数！");
        }
        if (n == null || n.compareTo(Method0.VALUE_1) <= 0) {
            return 0;
        }
        if (BigIntegerTest.ifEqualsTwo(n) || n.compareTo(new BigInteger("3")) == 0) {
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
            } while (b.compareTo(A_2) < 0 || b.compareTo(n.subtract(A_2)) > 0);

            BigInteger r = Method0.calculateMod(b, n.subtract(A_1).divide(A_2), n);
            if (r.compareTo(A_1) != 0 && r.compareTo(n.subtract(A_1)) != 0) {
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
     * @throws
     * @Title: violentIfPrime
     * @Description: 暴力法素性检验，返回1表示是素数，0反之
     * @param: @param n 必须小于Integer.MAX_VALUE
     * @param: @return
     * @return: int
     */
    public static int violentIfPrime(BigInteger n) {
        if (n.compareTo(VIOLENT_PRIME_TEST_MAX_INPUT_RECOMMENDED) > 0) {
            throw new InputTooLargeException("输入的值大于" + VIOLENT_PRIME_TEST_MAX_INPUT_RECOMMENDED + "使用暴力素数检测耗时将不可预计，不可取!");
        }
        if (BigIntegerTest.ifEqualsOne(n)) {
            return 0;
        } else if (BigIntegerTest.ifEqualsTwo(n) || n.compareTo(new BigInteger("3")) == 0) {
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

    /**
     * @throws
     * @Title: primeFactor
     * @Description: 求出所有的素因数
     * @param: @param x
     * @param: @return
     * @return: ArrayList<BigInteger>
     */
    public static ArrayList<BigInteger> primeFactor(BigInteger x) {
        ArrayList<BigInteger> primeFactorX = new ArrayList<>();
        if (x.compareTo(VIOLENT_PRIME_TEST_MAX_INPUT_RECOMMENDED) > 0) {
            throw new InputTooLargeException("输入的值大于" + VIOLENT_PRIME_TEST_MAX_INPUT_RECOMMENDED + "使用暴力素数检测耗时将不可预计，不可取!");
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

    public static void main(String[] args) {
        System.out.println(primeFactor(new BigInteger("46116860184270")));
        System.out.println(Long.MAX_VALUE);
    }
}
