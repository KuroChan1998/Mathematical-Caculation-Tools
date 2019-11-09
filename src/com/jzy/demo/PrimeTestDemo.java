package com.jzy.demo;

import com.jzy.xxaqsxjc.method0.Method0;
import com.jzy.xxaqsxjc.method0.PrimeTest;

import java.math.BigInteger;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName PrimeTestDemo
 * @description 使用示例：素性检验 {@link PrimeTest}
 * @date 2019/11/9 16:01
 **/
public class PrimeTestDemo {
    private PrimeTestDemo(){}

    public static void main(String[] args) {
        BigInteger x=new BigInteger("10101");
        //10101的暴力素性检验法
        System.out.println(Method0.violentIfPrime(x));

        BigInteger y=new BigInteger("162993160745844274481046832338751490197879688671042919691646338640554224639447624870951517022596815361787926957324146091787273893635028629428696548146985788051755472681202044391106927283112354581638816277266932786892102988490153640121216917508264157100854698888416600520661589289778979341074022871487840589593");
        //安全参数为15的费马素性检验
        System.out.println(Method0.fermat(y, 15));
        //安全参数为15的millerRabin素性检验
        System.out.println(Method0.millerRabin(y, 15));
        //安全参数为15的solovayStassen素性检验
        System.out.println(Method0.solovayStassen(y, 15));
    }
}
