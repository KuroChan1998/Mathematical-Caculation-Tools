package com.jzy.xxaqsxjc.encyption;

import com.jzy.xxaqsxjc.encyption.executor.DecryptionExecutor;
import com.jzy.xxaqsxjc.encyption.executor.EncryptExecutor;

/**
 * 加密类抽象类，向上实现加解密执行器接口{@link EncryptExecutor,DecryptionExecutor}，向下提供加密类的通用特性
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public abstract class Encryption implements EncryptExecutor, DecryptionExecutor {

    /**
     * 当前对象是否执行过加密操作的标志
     */
    protected boolean isEncrypted = false;

    /**
     * 明文
     */
    protected String plainText;    // 明文

    /**
     * 解密后的明文
     */
    protected String decryptedPlainText;    // 解密后的明文

    /**
     * 解密方法
     * <p>返回解密密文后得到的明文，必须先调用encrypt()即加密后在调用次方法<br>
     *
     * @return 返回解密密文后得到的明文字符串
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public abstract String decrypt();

    /**
     * 重置所有文本
     * <p>让子类实现，负责清空明文，密文，解密得到的明文串等变量<br>
     *
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    protected abstract void resetAllTexts();

    /**
     * 重置所有文本除了明文串
     * <p>让子类实现，负责清空密文，解密得到的明文串等变量<br>
     *
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    protected abstract void resetAllTextsExceptPlainText();

    /**
     * 显示函数，用以快速的测试当前加密算法
     * <p>初始化加密对象obj并传入明文后，调用obj.show()即可快速测试，相关输出于控制台<br>
     *
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public abstract void show();

    public String getDecryptedPlainText() {
        return decryptedPlainText;
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
