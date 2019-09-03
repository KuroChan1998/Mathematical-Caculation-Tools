package com.jzy.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 实现图形界面
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public class GUIWindow {

    /**
     * Frame的位置大小参数
     */
    public static int FrameStartX = 100;
    public static int FrameStartY = 100;
    public static int FrameWidth = 1000;
    public static int FrameHeight = 800;

    public GUIWindow() {
    }

    /**
     * 设置整体图形界面布局
     *
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public void setFrame() {
        JFrame frm = new JFrame();

        frm.setBounds(FrameStartX, FrameStartY, FrameWidth, FrameHeight);    // 设置窗口初始位置和大小
        frm.setTitle("信息安全数学基础课解题辅助v1.1");                                   // 设置标题
        frm.setLayout(null);                                                 // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色

        JLabel j0 = new JLabel("请选择对应的模块进行计算！");

        j0.setBounds(50, 30, FrameWidth, 25);
        frm.add(j0);

        WindowOfBezoutEquationSolution.GUIBezoutEquationSolution(frm);    // 设置贝祖等式求解图形模块
        WindowOfCalculateMod.GUICalculateMod(frm);                        // 设置最小非负余数和绝对值最小余数计算模块
        WindowOfCommonFactorMultiple.GUICommonFactorMultiple(frm);        // 设置最大公因数和最小公倍数的计算模块
        WindowOfEulerFunction.GUIEulerFuction(frm);                        // 设置欧拉函数值的计算模块
        WindowOfLegendreAndJacobi.GUILegendreAndJacobi(frm);              // 设置勒让得符号和雅可比符号计算模块
        WindowOfPrimitiveRoot.GUIPrimitiveRoot(frm);                      // 设置计算最小原根模块
        WindowOfPrimeTest.GUIPrimeTest(frm);                              // 设置素性检验模块
        WindowOfRSAEncryption.GUIRSAEncryption(frm);                      // 设置RSA加密算法模块
        WindowOfPaillierEncryption.GUIPaillierEncryption(frm);            // 设置Paillier加密算法模块
        WindowOfGoldwasserMicaliBinaryEncryption.GUIGoldwasserMicaliBinaryEncryption(frm);    // 设置GoldwasserMicali加密算法（对二进制加密）模块
        WindowOfChineseRemainderTheorem.GUIChineseRemainderTheorem(frm);              // 设置中国剩余定理计算模块
        WindowOfPolynomial.GUIPolynomial(frm);                                        // 设置多项式计算模块
        WindowOfEllipticCurveCalculationOfFp.GUIEllipticCurveCalculationOfFp(frm);    // 设置Fp上的椭圆曲线上的点的计算模块
        WindowOfEllipticCurveCalculationOfF2n.GUIEllipticCurveCalculationOfF2n(frm);    // F2n上的椭圆曲线y^2+xy=x^3+a2*x^2+a6的点的计算

        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // 关闭
        frm.setVisible(true);                                  // 显示窗口
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
