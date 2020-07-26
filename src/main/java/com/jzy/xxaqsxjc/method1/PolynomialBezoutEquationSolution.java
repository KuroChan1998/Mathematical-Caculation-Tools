package com.jzy.xxaqsxjc.method1;

import com.jzy.exception.ploynomial.InputPolyIsZeroException;
import com.jzy.util.PolynomialTest;

import java.util.LinkedList;

/**
 * 多项式的贝祖等式求解 s*x+t*y=(x,y)，求出多形式s,t
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class PolynomialBezoutEquationSolution {
    /**
     * 计算结果s
     */
    private Polynomial resultS = Method1.POLY_0;

    /**
     * 计算结果s,t
     */
    private Polynomial[] resultST = new Polynomial[2];

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
    private LinkedList<Polynomial> s = new LinkedList<>();
    private LinkedList<Polynomial> t = new LinkedList<>();
    private LinkedList<Polynomial> q = new LinkedList<>();
    private LinkedList<Polynomial> r = new LinkedList<>();

    /**
     * 类无参构造函数，用以初始化
     *
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public PolynomialBezoutEquationSolution() {
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
    public synchronized Polynomial polynomialBezoutSolveQrS11(Polynomial x, Polynomial y) {
        resultS = polynomialBezoutSolveQrSt(x, y)[0];
        return resultS;
    }

    /**
     * 计算出系数s, t
     * <p>返回s,t构成的长度为2数组，s在前，t在后<br>
     *
     * @param x 入参多项式x
     * @param y 入参多项式y
     * @return 贝祖等式系数多项式s, t构成的长度为2数组
     * @throws InputPolyIsZeroException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public synchronized Polynomial[] polynomialBezoutSolveQrSt(Polynomial x, Polynomial y) throws InputPolyIsZeroException {
        if (PolynomialTest.ifEqualZero(x) || PolynomialTest.ifEqualZero(y)) {
            throw new InputPolyIsZeroException("输入的多项式x,y不能为0");
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
            s.add(Method1.POLY_1);
            s.add(Method1.POLY_0);
            //t[1]=0, t[2]=1
            t.add(null);
            t.add(Method1.POLY_0);
            t.add(Method1.POLY_1);
        }

        r.set(j, x);
        r.set(j + 1, y);
        q.add(r.get(j).divide(r.get(j + 1)));
        r.add(r.get(j).mod(r.get(j + 1)));
        s.add(s.get(j + 1).subtract(q.get(j + 2).multiply(s.get(j + 2))));
        t.add(t.get(j + 1).subtract(q.get(j + 2).multiply(t.get(j + 2))));
        x = r.get(j + 1);
        y = r.get(j + 2);

        if (PolynomialTest.ifEqualZero(y)) {
            flag = false;
            resultST[0] = s.get(j + 2);
            resultST[1] = t.get(j + 2);
            return resultST;
        } else {
            j += 1;
            polynomialBezoutSolveQrSt(x, y);
        }

        return resultST;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
