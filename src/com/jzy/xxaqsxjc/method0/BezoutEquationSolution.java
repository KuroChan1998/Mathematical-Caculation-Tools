/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: BezoutEquationSolution.java
 * @Package com.jzy.xxaqsxjc.method0
 * @Description: 贝祖等式求解：s*x+t*y=(x,y)，求出s,t
 * @author: JinZhiyun
 * @date: 2019年3月14日 下午4:35:05
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.xxaqsxjc.method0;

import com.jzy.exception.integer.InputNonPositiveException;
import com.jzy.util.BigIntegerTest;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @ClassName: BezoutEquationSolution
 * @Description: 贝祖等式求解：s*x+t*y=(x,y)，求出s,t
 * @author: JinZhiyun
 * @date: 2019年3月14日 下午3:26:01
 * @Copyright: 2019 CyborgKuroChan Inc. All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class BezoutEquationSolution {
    private static final int BEZOUT_SOLVE_QR_SIZE = 1000000;
    private boolean flag;
    private int j;

    /**
     * @Fields s, t, q, r : 辅助数组
     */
    private BigInteger[] s;
    private BigInteger[] t;
    private BigInteger[] q;
    private BigInteger[] r;

    /**
     * @Fields resultS : 求出的系数s
     */
    private BigInteger resultS = Method0.VALUE_0;

    /**
     * @Fields resultST : 求出的系数s和t
     */
    private BigInteger[] resultST = new BigInteger[2];

    /**
     * @throws
     * @Title: BezoutEquationSolution
     * @Description: 类构造函数，用以初始化
     * @param:
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
     * @throws
     * @Title: bezoutSolveQrS11
     * @Description: 计算出系数s（x的逆元，只为正）
     * @param: @param x
     * @param: @param y
     * @param: @return
     * @return: BigInteger
     */
    public synchronized BigInteger bezoutSolveQrS11(BigInteger x, BigInteger y) {
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
     * @throws
     * @Title: bezoutSolveQrSt
     * @Description: 计算出系数s, t
     * @param: @param x
     * @param: @param y
     * @param: @return
     * @return: BigInteger[]
     */
    public synchronized BigInteger[] bezoutSolveQrSt(BigInteger x, BigInteger y) {
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

    /**
     * @throws
     * @Title: main
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param args
     * @return: void
     */
    public static void main(String[] args) {
        BigInteger x, y;
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入正整数x：");
        if (scan.hasNextBigInteger()) {
            x = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();
            return;
        }
        System.out.print("请输入正整数y：");
        if (scan.hasNextBigInteger()) {
            y = scan.nextBigInteger();
        } else {
            System.out.println("发生错误！");
            scan.close();
            return;
        }
        scan.close();

        System.out.println("最大公因数为：" + Method0.maxCommonFactorXY(x, y));
        BigInteger[] tmp1 = Method0.bezoutSolveQrSt(x, y);
        System.out.println("s=" + tmp1[0] + ",t=" + tmp1[1]);
        System.out.println("正数s=" + Method0.bezoutSolveQrS11(x, y));
        System.out.println(x + " " + y);
    }

}
