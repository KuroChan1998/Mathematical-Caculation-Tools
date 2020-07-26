package com.jzy.xxaqsxjc.encyption.executor;

/**
 * 通用加密执行器，该执行器继承加密执行器{@link EncryptExecutor}，定义了把字符串形式明文加密成字符串形式密文的行为
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public interface CommonEncryptExecutor extends EncryptExecutor {

    /**
     * 加密方法，将明文加密成字符串形式的密文
     *
     * @return 字符串形式的密文
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    String encrypt();
}


//~ Formatted by Jindent --- http://www.jindent.com
