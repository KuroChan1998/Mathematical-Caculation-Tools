# Mathematical-Caculation-Tools

This a tool for mathematical caculation such as greatest common factor, primitive roots, primality test .etc



## A quick start

* I provided the executable file 'SSSAssistant.exe' which you can run on Windows without any JDK environment.

* If you have JDK and JRE, you can just copy these folders to the 'scr' directory of your java project. Then run 'Main.java' and you will find the same gui window as 'SSSAssistant.exe'.



## My environment

### java

java version "9.0.4"
Java(TM) SE Runtime Environment (build 9.0.4+11)
Java HotSpot(TM) 64-Bit Server VM (build 9.0.4+11, mixed mode)

### IDE

Eclipse Java EE IDE for Web Developers.

Version: Photon Release (4.8.0)



## Project Struture

I list the functions that may be useful for you as follows:

### xxaqsxjc

#### method0 

In this package, you can find some fundamental programmes.

* CaculMod.java：计算大整数模
* CommonFactorMultiple.java：最大公因数和最小公倍数计算
* BezoutEquationSolution.java：贝祖等式系数求解
* EulerFuction.java：欧拉函数值计算
* Legendre.java：勒让得符号计算
* Jacobi.java：雅可比符号计算
* PrimeTest.java：素性检验，集成了三种素性检验和暴力检验
* PrimitiveRoot.java：原根计算
* Zgsydl.java：中国剩余定理求解

#### method1

* Polynomial.java：多项式计算基础类
* PolynomialBezoutEquationSolution.java：多项式贝祖等式系数求解
* PolynomialCaculation.java：多项式其他一些相关计算
* EllipticCurveCaculationOfFp.java：Fp上的椭圆曲线点的计算
* EllipticCurveCaculationOfF2n.java：F2n上的椭圆曲线点的计算

### EncyptionAlgorithm

In this package, there are three encyption algorithms.

* RSAEncryption.java：RSA加密算法

  see more about RSA: https://en.wikipedia.org/wiki/RSA_(cryptosystem)

* GoldwasserMicaliBinaryEncryption.java：GoldwasserMicali二进制串加密算法

  see more about GoldwasserMicali :https://en.wikipedia.org/wiki/Goldwasser–Micali_cryptosystem

* PaillierEncryption.java：Paillier加密算法

  see more about Paillier：https://en.wikipedia.org/wiki/Paillier_cryptosystem

### gui

In this package, there are some codes used for generating GUI windows .

* GUIWindow.java：生成图形界面的主要类
* Main.java：整个工程主函数入口，即调用了GUIWindow

### someTest

Some work for testing legality of input is done here, which means you don't need to care much about these codes.

### picture

Some pictures to beautify GUI.
