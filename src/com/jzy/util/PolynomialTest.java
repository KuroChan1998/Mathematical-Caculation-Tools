package com.jzy.util;

import com.jzy.xxaqsxjc.method1.Method1;
import com.jzy.xxaqsxjc.method1.Polynomial;

/**
 * @ClassName PolynomialTest
 * @Author JinZhiyun
 * @Description 提供一些验证输入多项式的方法
 * @Date 2019/8/28 8:10
 * @Version 1.0
 **/
public class PolynomialTest {
    /**
     * @Author JinZhiyun
     * @Description 验证输入多项式是否=0
     * @Date 8:12 2019/8/28
     * @Param [p]
     * @return boolean
     **/
    public static boolean ifEqualZero(Polynomial p){
        return p.equals(Method1.POLY_0);
    }

    /**
     * @Author JinZhiyun
     * @Description 验证输入多项式是否=1
     * @Date 8:21 2019/8/28
     * @Param [p]
     * @return boolean
     **/
    public static boolean ifEqualOne(Polynomial p){
        return p.equals(Method1.POLY_1);
    }

    /**
     * @Author JinZhiyun
     * @Description 验证输入多项式是否非0
     * @Date 8:21 2019/8/28
     * @Param [p]
     * @return boolean
     **/
    public static boolean isNotZero(Polynomial p){
        return !ifEqualZero(p);
    }

}
