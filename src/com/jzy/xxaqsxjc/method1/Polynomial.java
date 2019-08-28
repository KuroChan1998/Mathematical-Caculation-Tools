/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: Polynomial.java
 * @Package com.jzy.xxaqsxjc.method1
 * @Description: 描述了多项式的数据结构和性质
 * @author: JinZhiyun
 * @date: 2019年3月14日 下午11:12:44
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.xxaqsxjc.method1;

import com.jzy.exception.ploynomial.PolyDegNegativeException;
import com.jzy.exception.ploynomial.PolyDegOverflowException;
import com.jzy.exception.ploynomial.PolyDivideByZeroException;

/**
 * @ClassName: Polynomial
 * @Description: 描述多项式数据结构类
 * @author: JinZhiyun
 * @date: 2019年3月14日 下午11:12:01
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class Polynomial implements Cloneable {
    /**
     * @Fields MAX_DEG : 限制最高多项式次数
     */
    public static final int MAX_DEG = 65535;

    /**
     * @Fields p : 域的特征
     */
    private static final int p = 2;

    /**
     * @Fields effectiveMaxDeg : 多项式的有效的最高次数 ，如0*x^3+x^2+x，则 effectiveMaxDeg=2
     */
    public int effectiveMaxDeg;

    /**
     * @Fields polyArray : 描述多项式的系数，低次在前，如0*x^4+0*x^3+x^2+x，则polyArray={0,1,1,0,0}（也等效于polyArray={0,1,1}）
     */
    private int[] polyArray;

    /**
     * <p>Title: clone</p>
     * <p>Description: 克隆得到一个对象的副本 </p>
     *
     * @return
     * @see java.lang.Object#clone()
     */
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
     * @throws
     * @Title: Polynomial
     * @Description: 默认构造器，初始化多项式的一些特性
     * @param:
     */
    public Polynomial() {
        polyArray = new int[1];
        polyArray[0] = 0;
        effectiveMaxDeg = 0;
    }

    /**
     * @throws
     * @Title: Polynomial
     * @Description: 以最高有效次数为输入的构造器，如  Polynomial p=new Polynomial(3)，则获得多项式x^3
     * @param: @param deg
     */
    public Polynomial(int deg) {
        if (deg < 0) {
            throw new PolyDegNegativeException("输入次数deg必须大于等于0！");
        }
        polyArray = new int[deg + 1];
        polyArray[deg] = 1;
        effectiveMaxDeg = deg;
    }

    /**
     * @throws
     * @Title: Polynomial
     * @Description: 以系数为输入的构造器，如input={0,1,1}，则获得多项式x^2+x
     * @param: @param input
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
     * @throws
     * @Title: validate
     * @Description: 对用户的一些不恰当的输入进行合法化，如input={0,12,0,0},将其等效为input={0,1}(即多项式为x)
     * @param:
     * @return: void
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

    /**
     * @throws
     * @Title: setPolyArary
     * @Description: setter函数，用来修改多项式系数
     * @param: @param input
     * @return: void
     */
    public void setPolyArary(int[] input) {
        polyArray = input;
    }

    /**
     * <p>Title: toString</p>
     * <p>Description: 重写toString，使调用sout时，可以显示x^3+x^2+x这样直观地形式</p>
     *
     * @return
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String output = "";
        for (int i = polyArray.length - 1; i >= 2; i--) {
            if (polyArray[i] != 0) {
                output += "x^" + i + "+";
            }
        }
        if (polyArray.length >= 2) {
            if (polyArray[1] == 1 && polyArray[0] == 1) {
                output += "x+1";
            } else if (polyArray[1] == 0 && polyArray[0] == 1) {
                output += "1";
            } else if (polyArray[1] == 1 && polyArray[0] == 0) {
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
     * @throws
     * @Title: equals
     * @Description: 判断两个多项式是否相等
     * @param: @param o
     * @param: @return
     * @return: boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
     * @throws
     * @Title: add
     * @Description: 两个多项式之和
     * @param: @param p2
     * @param: @return
     * @return: Polynomial
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

    /**
     * @throws
     * @Title: sub
     * @Description: 两个多项式之差
     * @param: @param p2
     * @param: @return
     * @return: Polynomial
     */
    public Polynomial sub(Polynomial p2) {
        return this.add(p2);
    }

    /**
     * @throws
     * @Title: multiply
     * @Description: 两个多项式之积
     * @param: @param p2
     * @param: @return
     * @return: Polynomial
     */
    public Polynomial multiply(Polynomial p2) {
        Polynomial p1 = this;
        int[] result = {0};
        Polynomial pResult = new Polynomial(result);
        int[] a;
        for (int i = 0; i < p1.polyArray.length; i++) { // 分配相乘
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
     * @throws
     * @Title: divide
     * @Description: 两个多项式之商
     * @param: @param p2
     * @param: @return
     * @param: @throws PolyDivideByZeroException
     * @return: Polynomial
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
                    tmpp1 = tmpp1.sub(qTmpResult.multiply(p2));
                    if (tmpp1.effectiveMaxDeg == 0 && p2.effectiveMaxDeg == 0) {
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
     * @throws
     * @Title: mod
     * @Description: 模p2的余数
     * @param: @param p2
     * @param: @return
     * @param: @throws PolyDivideByZeroException
     * @return: Polynomial
     */
    public Polynomial mod(Polynomial p2) throws PolyDivideByZeroException {
        Polynomial p1 = this, mResult;
        mResult = p1.sub(p1.divide(p2).multiply(p2));
        return mResult;
    }

    /**
     * @throws
     * @Title: pow
     * @Description: p1的deg次方
     * @param: @param p1
     * @param: @param deg
     * @param: @return
     * @return: Polynomial
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

    public static void main(String[] args) throws PolyDivideByZeroException {
        // TODO Auto-generated method stub
        int[] a = {1, 1, 1, 0, 1};
        int[] b = {1, 0, 1, 1, 1, 0, 0, 0, 1};
        Polynomial pa = new Polynomial(a);
        Polynomial pb = new Polynomial(b);
        System.out.println(pa);
        System.out.println(pb);
        System.out.println(pa.add(pb));
        System.out.println(pa.multiply(pb));
        System.out.println(pb.divide(pa));
        System.out.println(pow(pb, 65535));

    }

}
