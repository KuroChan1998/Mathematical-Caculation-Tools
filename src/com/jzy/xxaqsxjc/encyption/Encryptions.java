package com.jzy.xxaqsxjc.encyption;

import com.jzy.xxaqsxjc.method0.PrimeTest;

/**
 * @ClassName Encryptions
 * @Author JinZhiyun
 * @Description 加密工具类
 * @Date 2019/8/26 19:28
 * @Version 1.0
 **/
public class Encryptions {
    /**
     * @Fields PRIME_TEST_SECURITY_PARAMETER: 加密类中生成秘钥时素性检验的默认安全参数
     */
    public static final int PRIME_TEST_SECURITY_PARAMETER = PrimeTest.SECURITY_PARAMETER_DEFAULT;

    /**
     * @Fields KEYS_BIT_DEFAULT_LENGTH: 秘钥的默认长度1024bit
     */
    public static final int KEYS_BIT_DEFAULT_LENGTH = 1 << 10;

    /**
     * @Fields KEYS_BIT_MIN_LENGTH: 规定秘钥的最小长度16bit
     */
    public static final int KEYS_BIT_MIN_LENGTH = 1 << 4;

    /**
     * @Fields KEYS_BIT_MIN_LENGTH: 规定秘钥的最大长2048bit
     */
    public static final int KEYS_BIT_MAX_LENGTH = 1 << 11;

    /**
     * @return boolean
     * @Author JinZhiyun
     * @Description 判断输入的秘钥位宽是否合法
     * @Date 20:01 2019/8/26
     * @Param [length]
     **/
    public static boolean isLegalKeysBitLength(int length) {
        return length >= KEYS_BIT_MIN_LENGTH && length <= KEYS_BIT_MAX_LENGTH;
    }

}
