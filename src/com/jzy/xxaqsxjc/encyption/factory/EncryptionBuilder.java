package com.jzy.xxaqsxjc.encyption.factory;

import com.jzy.xxaqsxjc.encyption.Encryption;

public interface EncryptionBuilder {
    /**
     * @Author JinZhiyun
     * @Description 获得加密类实例
     * @Date 8:50 2019/8/27
     * @Param []
     * @return com.jzy.xxaqsxjc.encyption.BinaryEncryptionExecutor
     **/
    Encryption getInstance();
}
