package com.jzy.xxaqsxjc.encyption.executor;

/**
 * 解密执行器接口，继承执行器{@link Executor}
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public interface DecryptionExecutor extends Executor {

    /**
     * 定义解密方法
     *
     * @return 返回解密密文后得到的子串，该字串应该与明文相同
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    String decrypt();
}


//~ Formatted by Jindent --- http://www.jindent.com
