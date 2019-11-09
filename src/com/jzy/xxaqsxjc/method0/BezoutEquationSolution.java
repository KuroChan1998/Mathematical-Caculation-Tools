package com.jzy.xxaqsxjc.method0;

import com.jzy.exception.integer.InputNonPositiveException;
import com.jzy.util.BigIntegerTest;

import java.math.BigInteger;

/**
 * 贝祖等式求解：s*x+t*y=(x,y)，求出s,t
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class BezoutEquationSolution {

    /**
     * 规定贝祖等式计算辅助数组的最大长度
     */
    private static final int BEZOUT_SOLVE_QR_SIZE = 1000000;

    /**
     * 计算结果s
     */
    private BigInteger resultS = Method0.VALUE_0;

    /**
     * 计算结果s,t
     */
    private BigInteger[] resultST = new BigInteger[2];

    /**
     * 表示是否计算过的标志位，若为false需要初始化指针j=0，并至自身为true
     */
    private boolean flag;

    /**
     * 辅助数组的指针
     */
    private int j;

    /**
     * s, t, q, r : 辅助数组
     */
    private BigInteger[] s;
    private BigInteger[] t;
    private BigInteger[] q;
    private BigInteger[] r;

    /**
     * 类无参构造函数，用以初始化
     *
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public BezoutEquationSolution() {
        s = new BigInteger[BEZOUT_SOLVE_QR_SIZE];
        t = new BigInteger[BEZOUT_SOLVE_QR_SIZE];
        q = new BigInteger[BEZOUT_SOLVE_QR_SIZE];
        r = new BigInteger[BEZOUT_SOLVE_QR_SIZE];
        j = 0;
        flag = true;
    }

    /**
     * 计算出系数多项式s
     *
     * @param x 入参大整数x
     * @param y 入参大整数y
     * @return 贝祖等式系数多项式s
     * @throws InputNonPositiveException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public synchronized BigInteger bezoutSolveQrS11(BigInteger x, BigInteger y) throws InputNonPositiveException {
        BigInteger tmpS = bezoutSolveQrSt(x, y)[0];
        BigInteger tmp = Method0.VALUE_0;

        if (tmpS.compareTo(tmp) >= 0) {
            resultS = tmpS;

            return resultS;
        } else {
            resultS = tmpS.add(y);

            return resultS;
        }
    }

    /**
     * 计算出系数s, t
     * <p>返回s,t构成的长度为2数组，s在前，t在后<br>
     *
     * @param x 入参大整数x
     * @param y 入参大整数y
     * @return 贝祖等式系数多项式s
     * @throws InputNonPositiveException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public synchronized BigInteger[] bezoutSolveQrSt(BigInteger x, BigInteger y) throws InputNonPositiveException {
        if (BigIntegerTest.isNonPositive(x) || BigIntegerTest.isNonPositive(y)) {
            throw new InputNonPositiveException("输入的x,y非正");
        }

        if (!flag) {
            j = 0;
            flag = true;
        }

        s[1] = Method0.VALUE_1;
        s[2] = Method0.VALUE_0;
        t[1] = Method0.VALUE_0;
        t[2] = Method0.VALUE_1;
        r[j] = x;
        r[j + 1] = y;
        q[j + 2] = r[j].divide(r[j + 1]);
        r[j + 2] = r[j].mod(r[j + 1]);
        s[j + 3] = s[j + 1].subtract(q[j + 2].multiply(s[j + 2]));
        t[j + 3] = t[j + 1].subtract(q[j + 2].multiply(t[j + 2]));
        x = r[j + 1];
        y = r[j + 2];

        if (BigIntegerTest.ifEqualsZero(y)) {
            flag = false;
            resultST[0] = s[j + 2];
            resultST[1] = t[j + 2];

            return resultST;
        } else {
            j += 1;
            bezoutSolveQrSt(x, y);
        }

        return resultST;
    }
}
