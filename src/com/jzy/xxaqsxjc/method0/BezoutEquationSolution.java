package com.jzy.xxaqsxjc.method0;

import com.jzy.exception.integer.InputNonPositiveException;
import com.jzy.util.BigIntegerTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 贝祖等式求解：s*x+t*y=(x,y)，求出s,t
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class BezoutEquationSolution {
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
    private boolean flag = false;

    /**
     * 辅助数组的指针
     */
    private int j = 0;

    /**
     * s, t, q, r : 辅助数组
     */
    private LinkedList<BigInteger> s = new LinkedList<>();
    private LinkedList<BigInteger> t = new LinkedList<>();
    private LinkedList<BigInteger> q = new LinkedList<>();
    private LinkedList<BigInteger> r = new LinkedList<>();

    /**
     * 类无参构造函数，用以初始化
     *
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public BezoutEquationSolution() {
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
     * @version 2.0, 20/07/26
     * @author JinZhiyun
     */
    public synchronized BigInteger[] bezoutSolveQrSt(BigInteger x, BigInteger y) throws InputNonPositiveException {
        if (BigIntegerTest.isNonPositive(x) || BigIntegerTest.isNonPositive(y)) {
            throw new InputNonPositiveException("输入的x,y非正");
        }

        if (!flag) {
            j = 0;
            flag = true;
            s = new LinkedList<>();
            t = new LinkedList<>();
            q = new LinkedList<>();
            q.add(null);
            q.add(null);
            r = new LinkedList<>();
            r.add(null);
            r.add(null);
            //s[1]=1, s[2]=0
            s.add(null);
            s.add(Method0.VALUE_1);
            s.add(Method0.VALUE_0);
            //t[1]=0, t[2]=1
            t.add(null);
            t.add(Method0.VALUE_0);
            t.add(Method0.VALUE_1);
        }

        r.set(j, x);
        r.set(j + 1, y);
        q.add(r.get(j).divide(r.get(j + 1)));
        r.add(r.get(j).mod(r.get(j + 1)));
        s.add(s.get(j + 1).subtract(q.get(j + 2).multiply(s.get(j + 2))));
        t.add(t.get(j + 1).subtract(q.get(j + 2).multiply(t.get(j + 2))));
        x = r.get(j + 1);
        y = r.get(j + 2);

        if (BigIntegerTest.ifEqualsZero(y)) {
            flag = false;
            resultST[0] = s.get(j + 2);
            resultST[1] = t.get(j + 2);
            return resultST;
        } else {
            j += 1;
            bezoutSolveQrSt(x, y);
        }

        return resultST;
    }
}
