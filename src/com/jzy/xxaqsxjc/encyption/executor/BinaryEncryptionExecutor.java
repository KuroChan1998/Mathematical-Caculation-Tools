package com.jzy.xxaqsxjc.encyption.executor;

import java.math.BigInteger;
import java.util.Vector;

public interface BinaryEncryptionExecutor extends EncryptExecutor{
    Vector<BigInteger> encrypt();
}
