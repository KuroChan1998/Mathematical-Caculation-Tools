package com.jzy.xxaqsxjc.encyption.executor;

import java.math.BigInteger;

import java.util.Vector;

/**
 * 二进制加密执行器，该执行器继承加密执行器{@link EncryptExecutor}，
 * 定义了把字符串形式明文（此明文为由0、1组成的字符串，表示二进制串）每一个字符加密成一个大整数的行为
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public interface BinaryEncryptionExecutor extends EncryptExecutor {

    /**
     * 加密方法，将明文每一个字符对应加密成一个大整数
     *
     * @return 大整数构成的Vector，对应每一个明文字符
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    Vector<BigInteger> encrypt();
}


//~ Formatted by Jindent --- http://www.jindent.com
