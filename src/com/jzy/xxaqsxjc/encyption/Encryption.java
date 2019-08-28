package com.jzy.xxaqsxjc.encyption;

import com.jzy.xxaqsxjc.encyption.executor.DecryptionExecutor;
import com.jzy.xxaqsxjc.encyption.executor.EncryptExecutor;

/**
 * @ClassName Encryption
 * @Author JinZhiyun
 * @Description 加密类抽象类
 * @Date 2019/8/27 18:44
 * @Version 1.0
 **/
public abstract class Encryption implements EncryptExecutor, DecryptionExecutor {
    protected String plainText;// 明文

    protected String decryptedPlainText;// 解密后的明文

    protected boolean isEncrypted=false; //当前对象是否执行过加密操作

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String getDecryptedPlainText() {
        return decryptedPlainText;
    }

    /**
     * @Author JinZhiyun
     * @Description 返回当前对象是否执行过加密操作
     * @Date 21:15 2019/8/26
     * @Param []
     * @return boolean
     **/
    public boolean isEncrypted(){
        return isEncrypted;
    }

    /**
     * @throws
     * @Title: decrypt
     * @Description: 返回解密密文后得到的明文，必须先调用encrypt()即加密后在调用次方法
     * @param: @return
     * @return: String
     */
    public abstract String decrypt();

    /**
     * @throws
     * @Title: show
     * @Description: 显示函数测试用
     * @param:
     * @return: void
     */
    public abstract void show();

    /**
     * @Author JinZhiyun
     * @Description 重置所有文本
     * @Date 21:38 2019/8/26
     * @Param []
     * @return void
     **/
    protected abstract void resetAllTexts();

    /**
     * @Author JinZhiyun
     * @Description 重置所有文本除了明文串
     * @Date 21:38 2019/8/26
     * @Param []
     * @return void
     **/
    protected abstract void resetAllTextsExceptPlainText();
}
