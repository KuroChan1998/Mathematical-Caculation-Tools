package com.jzy.xxaqsxjc.encyption;

import com.jzy.xxaqsxjc.method0.PrimeTest;

/**
 * 加密工具类
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public class Encryptions {

    /**
     * 加密类中生成秘钥时素性检验的默认安全参数=15
     */
    public static final int PRIME_TEST_SECURITY_PARAMETER = PrimeTest.SECURITY_PARAMETER_DEFAULT;

    /**
     * 秘钥的默认长度1024bit
     */
    public static final int KEYS_BIT_DEFAULT_LENGTH = 1 << 10;

    /**
     * 规定秘钥的最小长度16bit
     */
    public static final int KEYS_BIT_MIN_LENGTH = 1 << 4;

    /**
     * 规定秘钥的最大长度2048bit
     */
    public static final int KEYS_BIT_MAX_LENGTH = 1 << 11;

    /**
     * 判断输入的秘钥位宽是否合法，即是否在16~2048之间
     *
     * @param length 入参位宽
     * @return 布尔值，表示是否合法
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static boolean isLegalKeysBitLength(int length) {
        return (length >= KEYS_BIT_MIN_LENGTH) && (length <= KEYS_BIT_MAX_LENGTH);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
