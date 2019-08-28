/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: WindowOfEllipticCurveCalculationOfFp.java
 * @Package com.jzy.gui
 * @Description: Fp上的椭圆曲线y^2=x^3+a4*x+a6的点的计算图形类
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午4:01:43
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.jzy.util.StringTest;
import com.jzy.xxaqsxjc.method0.Method0;
import com.jzy.xxaqsxjc.method1.EllipticCurveCalculationOfFp;

/**
 * @ClassName: WindowOfEllipticCurveCalculationOfFp
 * @Description: Fp上的椭圆曲线y^2=x^3+a4*x+a6的点的计算图形类
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午4:01:43
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class WindowOfEllipticCurveCalculationOfFp {
    /**
     * @throws
     * @Title: GUIEllipticCurveCalculationOfFp
     * @Description: 设置Fp上的椭圆曲线计算图形模块
     * @param: @param frm
     * @return: void
     */
    public static void GUIEllipticCurveCalculationOfFp(JFrame frm) {
        JLabel j11 = new JLabel("13、Fp上的椭圆曲线y^2=x^3+a4*x+a6上点的计算");
        j11.setBounds(50, 420, GUIWindow.FrameWidth, 25);
        frm.add(j11);

        JButton btn = new JButton("进入");
        btn.setBounds(350, 420, 80, 25);
        frm.add(btn);

        btn.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frmHidden = new JFrame();
                frmHidden.setBounds(2 * GUIWindow.FrameStartX, 2 * GUIWindow.FrameStartY, 800, 350); // 设置窗口初始位置和大小
                frmHidden.setTitle("Fp上的椭圆曲线y^2=x^3+a4*x+a6上点的计算"); // 设置标题
                frmHidden.setLayout(null); // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色

                GUIEllipticCurveCalculationOfFpHidden(frmHidden);

                frmHidden.setVisible(true); // 显示窗口
            }
        });
    }

    /**
     * @throws
     * @Title: GUIEllipticCurveCalculationOfFpHidden
     * @Description: 点击进入后的Fp上的椭圆曲线计算图形模块
     * @param: @param frm
     * @return: void
     */
    public static void GUIEllipticCurveCalculationOfFpHidden(JFrame frm) {
        JLabel j11 = new JLabel("输入素数p，系数a4和a6:");
        j11.setBounds(50, 30, 200, 25);
        frm.add(j11);

        JLabel j12 = new JLabel("p=");
        j12.setBounds(50, 60, 50, 25);
        frm.add(j12);
        JTextField jtf1 = new JTextField();// 创建文本行组件
        jtf1.setBounds(70, 60, 200, 25); // 左边距，上边距，长，宽
        frm.add(jtf1);

        JLabel j13 = new JLabel("a4=");
        j13.setBounds(295, 60, 50, 25);
        frm.add(j13);
        JTextField jtf2 = new JTextField(); // 创建文本行组件
        jtf2.setBounds(320, 60, 150, 25); // 左边距，上边距，长，宽
        frm.add(jtf2);

        JLabel j14 = new JLabel("a6=");
        j14.setBounds(490, 60, 50, 25);
        frm.add(j14);
        JTextField jtf3 = new JTextField();// 创建文本行组件
        jtf3.setBounds(515, 60, 150, 25); // 左边距，上边距，长，宽
        frm.add(jtf3);

        JLabel j15 = new JLabel("点P1(P1x,P1y):    P1x=");
        j15.setBounds(50, 90, 150, 25);
        frm.add(j15);
        JTextField jtf4 = new JTextField(); // 创建文本行组件
        jtf4.setBounds(180, 90, 200, 25); // 左边距，上边距，长，宽
        frm.add(jtf4);

        JLabel j16 = new JLabel("P1y=");
        j16.setBounds(400, 90, 50, 25);
        frm.add(j16);
        JTextField jtf5 = new JTextField(); // 创建文本行组件
        jtf5.setBounds(435, 90, 200, 25); // 左边距，上边距，长，宽
        frm.add(jtf5);

        JLabel j17 = new JLabel("点P2(P2x,P2y):    P2x=");
        j17.setBounds(50, 120, 150, 25);
        frm.add(j17);
        JTextField jtf6 = new JTextField(); // 创建文本行组件
        jtf6.setBounds(180, 120, 200, 25); // 左边距，上边距，长，宽
        frm.add(jtf6);

        JLabel j18 = new JLabel("P2y=");
        j18.setBounds(400, 120, 50, 25);
        frm.add(j18);
        JTextField jtf7 = new JTextField(); // 创建文本行组件
        jtf7.setBounds(435, 120, 200, 25); // 左边距，上边距，长，宽
        frm.add(jtf7);

        JLabel j19 = new JLabel("计算：");
        j19.setBounds(50, 150, 50, 25);
        frm.add(j19);

        JLabel j110 = new JLabel("该椭圆曲线的阶=");
        j110.setBounds(50, 180, 150, 25);
        frm.add(j110);
        JTextField jtf8 = new JTextField(); // 创建文本行组件
        jtf8.setBounds(155, 180, 200, 25); // 左边距，上边距，长，宽
        jtf8.setEditable(false);
        frm.add(jtf8);
        JButton btnp1 = new JButton("计算");
        btnp1.setBounds(380, 180, 80, 25);
        frm.add(btnp1);

        JLabel j111 = new JLabel("P1+P2=");
        j111.setBounds(50, 210, 150, 25);
        frm.add(j111);
        JTextField jtf9 = new JTextField(); // 创建文本行组件
        jtf9.setBounds(100, 210, 200, 25); // 左边距，上边距，长，宽
        jtf9.setEditable(false);
        frm.add(jtf9);
        JButton btnp2 = new JButton("计算");
        btnp2.setBounds(320, 210, 80, 25);
        frm.add(btnp2);

        JLabel j112 = new JLabel("k倍P1的计算：        k=");
        j112.setBounds(50, 240, 200, 25);
        frm.add(j112);
        JTextField jtf10 = new JTextField(); // 创建文本行组件
        jtf10.setBounds(180, 240, 100, 25); // 左边距，上边距，长，宽
        frm.add(jtf10);
        JLabel j113 = new JLabel("k倍P1=");
        j113.setBounds(300, 240, 100, 25);
        frm.add(j113);
        JTextField jtf11 = new JTextField(); // 创建文本行组件
        jtf11.setBounds(345, 240, 150, 25); // 左边距，上边距，长，宽
        jtf11.setEditable(false);
        frm.add(jtf11);
        JButton btnp3 = new JButton("计算");
        btnp3.setBounds(520, 240, 80, 25);
        frm.add(btnp3);

        btnp1.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringTest.isLegalPositiveInteger(jtf1.getText()) && Method0.solovayStassen(new BigInteger(jtf1.getText())) == 1 && StringTest.isLegalPositiveInteger(jtf2.getText())
                        && StringTest.isLegalPositiveInteger(jtf3.getText())) {
                    BigInteger p = new BigInteger(jtf1.getText());
                    BigInteger a4 = new BigInteger(jtf2.getText());
                    BigInteger a6 = new BigInteger(jtf3.getText());
                    EllipticCurveCalculationOfFp ellipticCurveCalculationOfFp = new EllipticCurveCalculationOfFp(p, a4, a6);
                    jtf8.setText(ellipticCurveCalculationOfFp.ordFp().toString());
                } else {
                    JOptionPane.showMessageDialog(null, "请确认您的曲线系数p，a4，a6输入是否合法！");
                }
            }
        });

        btnp2.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringTest.isLegalPositiveInteger(jtf1.getText()) && Method0.solovayStassen(new BigInteger(jtf1.getText())) == 1 && StringTest.isLegalPositiveInteger(jtf2.getText())
                        && StringTest.isLegalPositiveInteger(jtf3.getText()) && StringTest.isLegalInteger(jtf4.getText())
                        && StringTest.isLegalInteger(jtf5.getText()) && StringTest.isLegalInteger(jtf6.getText())
                        && StringTest.isLegalInteger(jtf7.getText())) {
                    BigInteger p = new BigInteger(jtf1.getText());
                    BigInteger a4 = new BigInteger(jtf2.getText());
                    BigInteger a6 = new BigInteger(jtf3.getText());
                    EllipticCurveCalculationOfFp ellipticCurveCalculationOfFp = new EllipticCurveCalculationOfFp(p, a4, a6);
                    BigInteger[] p1 = {new BigInteger(jtf4.getText()), new BigInteger(jtf5.getText())};
                    BigInteger[] p2 = {new BigInteger(jtf6.getText()), new BigInteger(jtf7.getText())};
                    BigInteger[] result = ellipticCurveCalculationOfFp.pointAdd(p1, p2);
                    jtf9.setText("(" + result[0].toString() + "," + result[1].toString() + ")");
                } else {
                    JOptionPane.showMessageDialog(null, "请确认您的曲线系数p，a4，a6和点P1、P2输入是否合法！");
                }
            }
        });

        btnp3.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringTest.isLegalPositiveInteger(jtf1.getText()) && Method0.solovayStassen(new BigInteger(jtf1.getText())) == 1 && StringTest.isLegalPositiveInteger(jtf2.getText())
                        && StringTest.isLegalPositiveInteger(jtf3.getText()) && StringTest.isLegalInteger(jtf4.getText())
                        && StringTest.isLegalInteger(jtf5.getText()) && StringTest.isLegalPositiveInteger(jtf10.getText())) {
                    if (StringTest.isLegalIntegerLargerThanMin(jtf10.getText(), BigInteger.valueOf(EllipticCurveCalculationOfFp.MAX_K))) {
                        JOptionPane.showMessageDialog(null, "k不应该大于" + EllipticCurveCalculationOfFp.MAX_K + "！");
                    } else {
                        BigInteger p = new BigInteger(jtf1.getText());
                        BigInteger a4 = new BigInteger(jtf2.getText());
                        BigInteger a6 = new BigInteger(jtf3.getText());
                        EllipticCurveCalculationOfFp ellipticCurveCalculationOfFp = new EllipticCurveCalculationOfFp(p, a4, a6);
                        BigInteger[] p1 = {new BigInteger(jtf4.getText()), new BigInteger(jtf5.getText())};
                        int k = Integer.parseInt(jtf10.getText());
                        BigInteger[] result = ellipticCurveCalculationOfFp.kPoint(p1, k);
                        jtf11.setText("(" + result[0].toString() + "," + result[1].toString() + ")");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "请确认您的曲线系数p，a4，a6，点P1，k输入是否合法！");
                }
            }
        });
    }
}
