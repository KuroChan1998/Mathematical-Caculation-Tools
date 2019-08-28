package com.jzy.util;

import com.jzy.xxaqsxjc.method0.Method0;

import java.math.BigInteger;
import java.util.regex.Pattern;

/**
 * @ClassName: StringTest
 * @Description: 提供一些验证输入串格式的方法
 * @author: JinZhiyun
 * @date: 2019年3月16日 上午8:30:54
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class StringTest {
    /**
     * @throws
     * @Title: isOnlyContain01
     * @Description: 对GoldwasserMicali加密算法（对二进制加密）的输入子串是否只含0、1的判断
     * @param: @param str
     * @param: @return
     * @return: boolean
     */
    public static boolean isOnlyContain01(String str) {
        Pattern pattern = Pattern.compile("[0-1]*");
        return pattern.matcher(str).matches();
    }

    /**
     * @throws
     * @Title: isLegalInteger
     * @Description: 判断输入是否合法，即非空且是数字
     * @param: @param str
     * @param: @return
     * @return: boolean
     */
    public static boolean isLegalInteger(String str) {
        return (isInteger(str) && !str.equals(""));
    }

    /**
     * @return boolean
     * @Author JinZhiyun
     * @Description 判断输入是否合法，即非空且是数字，且满足min <= str <=max
     * @Date 22:22 2019/8/25
     * @Param [str, min, max]
     **/
    public static boolean isLegalIntegerBetweenMinAndMax(String str, BigInteger min, BigInteger max) {
        if (isLegalInteger(str)) {
            BigInteger b = new BigInteger(str);
            return b.compareTo(min) >= 0 && b.compareTo(max) <= 0;
        } else {
            return false;
        }
    }

    /**
     * @return boolean
     * @Author JinZhiyun
     * @Description 判断输入是否合法，即非空且是数字，且满足min <= str
     * @Date 22:28 2019/8/25
     * @Param [str, min]
     **/
    public static boolean isLegalIntegerLargerThanMin(String str, BigInteger min) {
        if (isLegalInteger(str)) {
            BigInteger b = new BigInteger(str);
            return b.compareTo(min) >= 0;
        } else {
            return false;
        }
    }

    /**
     * @return boolean
     * @Author JinZhiyun
     * @Description 判断输入是否合法，即非空且是数字，且满足str <=max
     * @Date 22:28 2019/8/25
     * @Param [str, max]
     **/
    public static boolean isLegalIntegerSmallerThanMax(String str, BigInteger max) {
        if (isLegalInteger(str)) {
            BigInteger b = new BigInteger(str);
            return b.compareTo(max) <= 0;
        } else {
            return false;
        }
    }

    /**
     * @throws
     * @Title: isLegalPositiveInteger
     * @Description: 判断输入是否为正数
     * @param: @param str
     * @param: @return
     * @return: boolean
     */
    public static boolean isLegalPositiveInteger(String str) {
        return isLegalIntegerLargerThanMin(str, Method0.VALUE_1); // >=1?
    }

    /**
     * @return boolean
     * @Author JinZhiyun
     * @Description 判断输入是否为非负数
     * @Date 20:37 2019/8/25
     * @Param [str]
     **/
    public static boolean isLegalNonNegativeInteger(String str) {
        return isLegalIntegerLargerThanMin(str, Method0.VALUE_0); // >=0?
    }

    /**
     * @return boolean
     * @Author JinZhiyun
     * @Description 判断输入是否为奇素数
     * @Date 12:31 2019/8/26
     * @Param [str]
     **/
    public static boolean isLegalOddPrimeInteger(String str) {
        if (isLegalPositiveInteger(str)) {
            if (!str.equals("2") && Method0.solovayStassen(new BigInteger(str)) == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * @throws
     * @Title: isInteger
     * @Description: 判断输入是否全为数字
     * @param: @param str
     * @param: @return
     * @return: boolean
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches() && !str.equals("-");
    }
}
