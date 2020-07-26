package com.jzy.xxaqsxjc.method1;

import com.jzy.exception.ploynomial.PolyDegNegativeException;
import com.jzy.exception.ploynomial.PolyDegOverflowException;
import com.jzy.exception.ploynomial.PolyDivideByZeroException;

/**
 * 描述多项式数据结构类，提供了多项式的基本计算（加减乘除模...）
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/02
 */
public class Polynomial implements Cloneable {

    /**
     * 限制最高多项式次数
     */
    public static final int MAX_DEG = 65535;

    /**
     * 域的特征
     */
    private static final int p = 2;

    /**
     * 多项式的有效的最高次数 ，如0*x^3+x^2+x，则 effectiveMaxDeg=2
     */
    public int effectiveMaxDeg;

    /**
     * 描述多项式的系数，低次在前，如0*x^4+0*x^3+x^2+x，则polyArray={0,1,1,0,0}（也等效于polyArray={0,1,1}）
     */
    private int[] polyArray;

    /**
     * 默认构造器，初始化多项式的一些特性
     *
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public Polynomial() {
        polyArray = new int[1];
        polyArray[0] = 0;
        effectiveMaxDeg = 0;
    }

    /**
     * 以最高有效次数为输入的构造器<br>
     * 如 Polynomial p=new Polynomial(3)，则获得多项式x^3
     *
     * @param deg 最高有效次数
     * @throws PolyDegNegativeException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public Polynomial(int deg) throws PolyDegNegativeException {
        if (deg < 0) {
            throw new PolyDegNegativeException("输入次数deg必须大于等于0！");
        }

        polyArray = new int[deg + 1];
        polyArray[deg] = 1;
        effectiveMaxDeg = deg;
    }

    /**
     * 以系数为输入的构造器<br>
     * 如 input={0,1,1}，则获得多项式x^2+x
     *
     * @param input 描述多项式的系数的数组，参见成员变量polyArray
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public Polynomial(int[] input) {
        if (input.length >= 1) {
            polyArray = input;

            for (int i = 0; i < polyArray.length; i++) {
                if (polyArray[i] > 0) {
                    polyArray[i] = 1;
                }

                if (polyArray[i] < 0) {
                    polyArray[i] = 0;
                }
            }
        } else {
            polyArray = new int[1];
            polyArray[0] = 1;
        }

        this.validate();
    }

    /**
     * 两个多项式之和
     * <p>p1.add(p2)即计算p1+p2的值<br>
     *
     * @param p2 入参多项式p2
     * @return p1+p2
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public Polynomial add(Polynomial p2) {
        Polynomial p1 = this;
        int maxDeg = Math.max(p1.polyArray.length, p2.polyArray.length);
        int minDeg = Math.min(p1.polyArray.length, p2.polyArray.length);
        int[] pAdd = new int[maxDeg];

        for (int i = 0; i < minDeg; i++) {
            pAdd[i] = (p1.polyArray[i] + p2.polyArray[i]) % p;
        }

        for (int i = minDeg; i < maxDeg; i++) {
            if (p1.polyArray.length > p2.polyArray.length) {
                pAdd[i] = p1.polyArray[i];
            } else {
                pAdd[i] = p2.polyArray[i];
            }
        }

        return new Polynomial(pAdd);
    }

    @Override
    public Object clone() {
        Polynomial copy = null;
        try {
            copy = (Polynomial) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return copy;
    }

    /**
     * 两个多项式之商
     * <p>p1.divide(p2)即计算p1/p2的值<br>
     *
     * @param p2 入参多项式p2
     * @return p1/p2
     * @throws PolyDivideByZeroException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public Polynomial divide(Polynomial p2) throws PolyDivideByZeroException {
        Polynomial p1 = this;
        Polynomial qResult = Method1.POLY_0;
        if (!p2.equals(Method1.POLY_0)) {
            if (p1.effectiveMaxDeg >= p2.effectiveMaxDeg) {
                Polynomial tmpp1 = p1;
                int[] result;
                do {
                    result = new int[tmpp1.effectiveMaxDeg - p2.effectiveMaxDeg + 1];
                    result[result.length - 1] = 1;

                    for (int i = 0; i < result.length - 1; i++) {
                        result[i] = 0;
                    }

                    Polynomial qTmpResult = new Polynomial(result);

                    qResult = qResult.add(qTmpResult);
                    tmpp1 = tmpp1.subtract(qTmpResult.multiply(p2));

                    if ((tmpp1.effectiveMaxDeg == 0) && (p2.effectiveMaxDeg == 0)) {
                        qResult = qResult.add(tmpp1);

                        break;
                    }
                } while (tmpp1.effectiveMaxDeg >= p2.effectiveMaxDeg);
            }
        } else {
            throw new PolyDivideByZeroException("除数不能为零");
        }

        return qResult;
    }

    /**
     * 判断两个多项式是否相等，当且仅当两者系数都相等是才相等
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        if (o instanceof Polynomial) {
            Polynomial that = (Polynomial) o;

            if (this.effectiveMaxDeg == that.effectiveMaxDeg) {
                for (int i = 0; i <= this.effectiveMaxDeg; i++) {
                    if (this.polyArray[i] != that.polyArray[i]) {
                        return false;
                    }
                }

                return true;
            }
        }

        return false;
    }

    /**
     * p1模p2的余式
     * <p>p1.mod(p2)即计算p1 mod p2的值<br>
     *
     * @param p2 入参多项式p2
     * @return p1 mod p2
     * @throws PolyDivideByZeroException
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public Polynomial mod(Polynomial p2) throws PolyDivideByZeroException {
        Polynomial p1 = this, mResult;

        mResult = p1.subtract(p1.divide(p2).multiply(p2));

        return mResult;
    }

    /**
     * 两个多项式之积
     * <p>p1.multiply(p2)即计算p1*p2的值<br>
     *
     * @param p2 入参多项式p2
     * @return p1*p2
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public Polynomial multiply(Polynomial p2) {
        Polynomial p1 = this;
        int[] result = {0};
        Polynomial pResult = new Polynomial(result);
        int[] a;

        for (int i = 0; i < p1.polyArray.length; i++) {    // 分配相乘
            if (p1.polyArray[i] != 0) {
                a = new int[i + p2.polyArray.length];

                for (int j = 0; j < i; j++) {
                    a[j] = 0;
                }

                for (int j = i; j < i + p2.polyArray.length; j++) {
                    a[j] = p2.polyArray[j - i];
                }

                pResult = pResult.add(new Polynomial(a));
            }
        }

        return pResult;
    }

    /**
     * p1的deg次方
     * <p>即计算p1^deg的值<br>
     *
     * @param p1  入参多项式p1
     * @param deg p1的幂次
     * @return p1^deg
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public static Polynomial pow(Polynomial p1, int deg) {
        if (deg < 0) {
            throw new PolyDegNegativeException("输入次数deg必须大于等于0！");
        }

        if (deg == 0) {
            return Method1.POLY_1;
        }

        Polynomial p2 = p1;

        for (int i = 0; i < deg - 1; i++) {
            p2 = p2.multiply(p1);

            if (p2.effectiveMaxDeg > MAX_DEG) {
                throw new PolyDegOverflowException("多项式次数溢出，超过了65535！");
            }
        }

        return p2;
    }

    /**
     * 两个多项式之差
     * <p>p1.subtract(p2)即计算p1-p2的值。由于这里特征p=2，所以减法结果上等于加法<br>
     *
     * @param p2 入参多项式p2
     * @return p1-p2
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public Polynomial subtract(Polynomial p2) {
        return this.add(p2);
    }

    /**
     * 重写toString
     * <p>可以像x^3+x^2+x这样直观地显示一个多项式<br>
     *
     * @return 输出的多项式形式的字串
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    @Override
    public String toString() {
        String output = "";

        for (int i = polyArray.length - 1; i >= 2; i--) {
            if (polyArray[i] != 0) {
                output += "x^" + i + "+";
            }
        }

        if (polyArray.length >= 2) {
            if ((polyArray[1] == 1) && (polyArray[0] == 1)) {
                output += "x+1";
            } else if ((polyArray[1] == 0) && (polyArray[0] == 1)) {
                output += "1";
            } else if ((polyArray[1] == 1) && (polyArray[0] == 0)) {
                output += "x";
            } else {
                if (!output.equals("")) {
                    output = output.substring(0, output.length() - 1);
                } else {
                    output = "0";
                }
            }
        } else {
            if (polyArray[0] == 1) {
                output += "1";
            } else {
                output += "0";
            }
        }

        return output;
    }

    /**
     * 对用户的一些不恰当的输入进行合法化
     * <p>如input={0,12,0,0},将其等效为input={0,1}(即多项式为x)<br>
     *
     * @version 1.0, 19/09/02
     * @author JinZhiyun
     */
    public void validate() {
        int[] newArray = {0};

        for (int i = polyArray.length - 1; i >= 0; i--) {
            if (polyArray[i] != 0) {
                newArray = new int[i + 1];

                for (int j = 0; j < i + 1; j++) {
                    newArray[j] = polyArray[j];
                }

                polyArray = newArray;
                effectiveMaxDeg = polyArray.length - 1;

                break;
            }
        }

        polyArray = newArray;
        effectiveMaxDeg = polyArray.length - 1;
    }

    public void setPolyArary(int[] input) {
        polyArray = input;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
