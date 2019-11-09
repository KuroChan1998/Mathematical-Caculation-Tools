package com.jzy.util;

import java.math.BigInteger;

import java.util.regex.Pattern;

import com.jzy.xxaqsxjc.method0.Method0;

/**
 * 提供一些验证输入串格式的方法
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public class StringTest {
    private StringTest(){}

    /**
     * 判断输入是否全为数字
     *
     * @param str 输入字符串
     * @return 布尔值，是否全为数字
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches() && !str.equals("-");
    }

    /**
     * 判断输入是否合法，即非空且是数字
     *
     * @param str 输入字符串
     * @return 布尔值，是否合法，即非空且是数字
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isLegalInteger(String str) {
        return (isInteger(str) && !str.equals(""));
    }

    /**
     * 判断输入是否合法，即非空且是数字，且满足min <= str <=max
     *
     * @param str 输入字符串
     * @param min 下限值
     * @param max 上限值
     * @return 布尔值，是否合法，即非空且是数字，且满足min <= str <=max
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isLegalIntegerBetweenMinAndMax(String str, BigInteger min, BigInteger max) {
        if (isLegalInteger(str)) {
            BigInteger b = new BigInteger(str);

            return (b.compareTo(min) >= 0) && (b.compareTo(max) <= 0);
        } else {
            return false;
        }
    }

    /**
     * 判断输入是否合法，即非空且是数字，且满足min <= str
     *
     * @param str 输入字符串
     * @param min 下限值
     * @return 布尔值，是否合法，即非空且是数字，且满足min <= str
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isLegalIntegerLargerThanMin(String str, BigInteger min) {
        if (isLegalInteger(str)) {
            BigInteger b = new BigInteger(str);

            return b.compareTo(min) >= 0;
        } else {
            return false;
        }
    }

    /**
     * 判断输入是否合法，即非空且是数字，且满足str <=max
     *
     * @param str 输入字符串
     * @param max 上限值
     * @return 布尔值，是否合法，即非空且是数字，且满足str <=max
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isLegalIntegerSmallerThanMax(String str, BigInteger max) {
        if (isLegalInteger(str)) {
            BigInteger b = new BigInteger(str);

            return b.compareTo(max) <= 0;
        } else {
            return false;
        }
    }

    /**
     * 判断输入是否为非负数
     *
     * @param str 输入字符串
     * @return 布尔值，是否为非负数
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isLegalNonNegativeInteger(String str) {
        return isLegalIntegerLargerThanMin(str, Method0.VALUE_0);    // >=0?
    }

    /**
     * 判断输入是否为奇素数
     *
     * @param str 输入字符串
     * @return 布尔值，是否为奇素数
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isLegalOddPrimeInteger(String str) {
        if (isLegalPositiveInteger(str)) {
            if (!str.equals("2") && (Method0.solovayStassen(new BigInteger(str)) == 1)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断输入是否为正数
     *
     * @param str 输入字符串
     * @return 布尔值，是否为正数
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isLegalPositiveInteger(String str) {
        return isLegalIntegerLargerThanMin(str, Method0.VALUE_1);    // >=1?
    }

    /**
     * 对（二进制加密）输入字串是否只含0、1的判断
     *
     * @param str 输入字符串
     * @return 布尔值，是否只含0、1
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isOnlyContain01(String str) {
        Pattern pattern = Pattern.compile("[0-1]*");
        return pattern.matcher(str).matches();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
