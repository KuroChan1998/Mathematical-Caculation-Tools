package com.jzy.xxaqsxjc.method1;

import com.jzy.exception.ploynomial.InputPolyIsZeroException;
import com.jzy.exception.ploynomial.PolyDivideByZeroException;
import com.jzy.util.PolynomialTest;

/**
 * 多项式的贝祖等式求解 s*x+t*y=(x,y)，求出多形式s,t
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class PolynomialBezoutEquationSolution {
    /**
     * 规定贝祖等式计算辅助数组的最大长度
     */
    private static final int BEZOUT_SOLVE_QR_SIZE = 1000000;

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
    private boolean flag;

    /**
     * 辅助数组的指针
     */
    private int j;

    /**
     * s, t, q, r : 辅助数组
     */
    private Polynomial[] s;
    private Polynomial[] t;
    private Polynomial[] q;
    private Polynomial[] r;

    /**
     * 类无参构造函数，用以初始化
     *
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public PolynomialBezoutEquationSolution() {
        s = new Polynomial[BEZOUT_SOLVE_QR_SIZE];
        t = new Polynomial[BEZOUT_SOLVE_QR_SIZE];
        q = new Polynomial[BEZOUT_SOLVE_QR_SIZE];
        r = new Polynomial[BEZOUT_SOLVE_QR_SIZE];
        j = 0;
        flag = true;
    }

//    public static void main(String[] args) throws PolyDivideByZeroException {
//        int[] a = {1, 1};
//        int[] b = {1, 1, 0, 1};
//        Polynomial pa = new Polynomial();
//        Polynomial pb = new Polynomial(b);
//
//        System.out.println(new PolynomialBezoutEquationSolution().polynomialBezoutSolveQrS11(pa, pb));
//    }

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
        }

        s[1] = Method1.POLY_1;    // 1
        s[2] = Method1.POLY_0;     // 0
        t[1] = Method1.POLY_0;
        t[2] = Method1.POLY_1;
        r[j] = x;
        r[j + 1] = y;
        q[j + 2] = r[j].divide(r[j + 1]);
        r[j + 2] = r[j].mod(r[j + 1]);
        s[j + 3] = s[j + 1].sub(q[j + 2].multiply(s[j + 2]));
        t[j + 3] = t[j + 1].sub(q[j + 2].multiply(t[j + 2]));
        x = r[j + 1];
        y = r[j + 2];

        if (PolynomialTest.ifEqualZero(y)) {
            flag = false;
            resultST[0] = s[j + 2];
            resultST[1] = t[j + 2];

            return resultST;
        } else {
            j += 1;
            polynomialBezoutSolveQrSt(x, y);
        }

        return resultST;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
