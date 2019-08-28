/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: PolynomialBezoutEquationSolution.java
 * @Package com.jzy.xxaqsxjc.method1
 * @Description: 多项式的贝祖等式求解 s*x+t*y=(x,y)，求出多形式s,t
 * @author: JinZhiyun
 * @date: 2019年3月15日 上午10:10:57
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */

package com.jzy.xxaqsxjc.method1;

import com.jzy.exception.ploynomial.InputPolyIsZeroException;
import com.jzy.exception.ploynomial.PolyDivideByZeroException;
import com.jzy.util.PolynomialTest;

/**
 * @ClassName: PolynomialBezoutEquationSolution
 * @Description: 多项式的贝祖等式求解 s*x+t*y=(x,y)，求出多形式s,t
 * @author: JinZhiyun
 * @date: 2019年3月15日 上午10:10:27
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class PolynomialBezoutEquationSolution {
    private static final int BEZOUT_SOLVE_QR_SIZE = 1000000;
    private boolean flag;
    private int j;

    /**
     * @Fields s, t, q, r : 辅助数组
     */
    private Polynomial[] s;
    private Polynomial[] t;
    private Polynomial[] q;
    private Polynomial[] r;

    /**
     * @Fields resultS : 求出的系数多项式s
     */
    private Polynomial resultS = Method1.POLY_0;

    /**
     * @Fields resultST : 求出的系数多项式s和t
     */
    private Polynomial[] resultST = new Polynomial[2];

    /**
     * @throws
     * @Title: PolynomialBezoutEquationSolution
     * @Description: 类构造函数，用以初始化
     * @param:
     */
    public PolynomialBezoutEquationSolution() {
        s = new Polynomial[BEZOUT_SOLVE_QR_SIZE];
        t = new Polynomial[BEZOUT_SOLVE_QR_SIZE];
        q = new Polynomial[BEZOUT_SOLVE_QR_SIZE];
        r = new Polynomial[BEZOUT_SOLVE_QR_SIZE];
        j = 0;
        flag = true;
    }

    /**
     * @throws
     * @Title: polynomialBezoutSolveQrSt
     * @Description: 计算出系数s, t
     * @param: @param x
     * @param: @param y
     * @param: @return
     * @param: @throws PolyDivideByZeroException
     * @return: Polynomial[]
     */
    public synchronized Polynomial[] polynomialBezoutSolveQrSt(Polynomial x, Polynomial y) {
        if (PolynomialTest.ifEqualZero(x) || PolynomialTest.ifEqualZero(y)) {
            throw new InputPolyIsZeroException("输入的多项式x,y不能为0");
        }
        if (!flag) {
            j = 0;
            flag = true;
        }
        s[1] = new Polynomial(0);//1
        s[2] = new Polynomial();//0
        t[1] = new Polynomial();
        t[2] = new Polynomial(0);
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

    /**
     * @throws
     * @Title: polynomialBezoutSolveQrS11
     * @Description: 计算出系数多项式s
     * @param: @param x
     * @param: @param y
     * @param: @return
     * @param: @throws PolyDivideByZeroException
     * @return: Polynomial
     */
    public synchronized Polynomial polynomialBezoutSolveQrS11(Polynomial x, Polynomial y) {
        resultS = polynomialBezoutSolveQrSt(x, y)[0];
        return resultS;
    }

    public static void main(String[] args) throws PolyDivideByZeroException {
        // TODO Auto-generated method stub
        int[] a = {1, 1};
        int[] b = {1, 1, 0, 1};
        Polynomial pa = new Polynomial();
        Polynomial pb = new Polynomial(b);
        System.out.println(new PolynomialBezoutEquationSolution().polynomialBezoutSolveQrS11(pa, pb));
    }

}
