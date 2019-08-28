/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: WindowOfBezoutEquationSolution.java
 * @Package com.jzy.gui
 * @Description: 贝祖等式求解图形类
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午12:13:38
 * @version V1.0
 * @Copyright: 2019 CyborgKuroChan All rights reserved.
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
package com.jzy.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.*;

import com.jzy.util.StringTest;
import com.jzy.xxaqsxjc.method0.Method0;

/**
 * @ClassName: WindowOfBezoutEquationSolution
 * @Description: 贝祖等式求解图形类
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午12:13:38
 *
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class WindowOfBezoutEquationSolution {
    /**
     * @Title: GUIBezoutEquationSolution
     * @Description: 贝祖等式求解图形模块
     * @param: @param frm
     * @return: void
     * @throws
     */
    public static void GUIBezoutEquationSolution(JFrame frm) {
        JLabel j11 = new JLabel("1、贝祖等式求解：s*x+t*y=(x,y)，请输入x,y");
        j11.setBounds(50, 60, GUIWindow.FrameWidth, 25);
        frm.add(j11);

        JButton btn = new JButton("进入");
        btn.setBounds(320, 60, 80, 25);
        frm.add(btn);


        btn.addActionListener(new ActionListener() { //按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frmHidden = new JFrame();
                frmHidden.setBounds(2 * GUIWindow.FrameStartX, 2 * GUIWindow.FrameStartY, 900, 150); // 设置窗口初始位置和大小
                frmHidden.setTitle("贝祖等式求解：s*x+t*y=(x,y)，请输入x,y"); // 设置标题
                frmHidden.setLayout(null); // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色

                GUIBezoutEquationSolutionHidden(frmHidden);


                frmHidden.setVisible(true); // 显示窗口
            }
        });

    }

    /**
     * @Title: GUIBezoutEquationSolutionHidden
     * @Description: 点击进入后的贝祖等式求解图形模块
     * @param: @param frm
     * @return: void
     * @throws
     */
    public static void GUIBezoutEquationSolutionHidden(JFrame frm) {
        JLabel j12 = new JLabel("x=");
        j12.setBounds(50, 30, 20, 25);
        frm.add(j12);
        JTextField jtf1 = new JTextField();// 创建文本行组件
        jtf1.setBounds(70, 30, 200, 25); // 左边距，上边距，长，宽
        frm.add(jtf1);

        JLabel j13 = new JLabel("y=");
        j13.setBounds(300, 30, 20, 25);
        frm.add(j13);
        JTextField jtf2 = new JTextField(); // 创建文本行组件
        jtf2.setBounds(320, 30, 200, 25); // 左边距，上边距，长，宽
        frm.add(jtf2);

        JButton btn = new JButton("计算");
        btn.setBounds(550, 30, 80, 25);
        frm.add(btn);

        JLabel j14 = new JLabel("(x,y)=");
        j14.setBounds(50, 60, 50, 25);
        frm.add(j14);
        JTextField jtf3 = new JTextField(); // 创建文本行组件
        jtf3.setBounds(90, 60, 150, 25); // 左边距，上边距，长，宽
        jtf3.setEditable(false);
        frm.add(jtf3);

        JLabel j15 = new JLabel("s=");
        j15.setBounds(250, 60, 20, 25);
        frm.add(j15);
        JTextField jtf4 = new JTextField(); // 创建文本行组件
        jtf4.setBounds(270, 60, 150, 25); // 左边距，上边距，长，宽
        jtf4.setEditable(false);
        frm.add(jtf4);

        JLabel j16 = new JLabel("t=");
        j16.setBounds(430, 60, 20, 25);
        frm.add(j16);
        JTextField jtf5 = new JTextField(); // 创建文本行组件
        jtf5.setBounds(450, 60, 150, 25); // 左边距，上边距，长，宽
        jtf5.setEditable(false);
        frm.add(jtf5);

        JLabel j17 = new JLabel("恒为正的s=");
        j17.setBounds(610, 60, 70, 25);
        frm.add(j17);
        JTextField jtf6 = new JTextField(); // 创建文本行组件
        jtf6.setBounds(680, 60, 150, 25); // 左边距，上边距，长，宽
        jtf6.setEditable(false);
        frm.add(jtf6);

        btn.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringTest.isLegalPositiveInteger(jtf1.getText()) && StringTest.isLegalPositiveInteger(jtf2.getText())) {
                    BigInteger x = new BigInteger(jtf1.getText());
                    BigInteger y = new BigInteger(jtf2.getText());
                    BigInteger[] st = Method0.bezoutSolveQrSt(x, y);
                    BigInteger s = st[0];
                    BigInteger t = st[1];
                    BigInteger s1 = Method0.bezoutSolveQrS11(x, y);
                    BigInteger maxCommonFactor = Method0.maxCommonFactorXY(x, y);
                    jtf3.setText(maxCommonFactor.toString());
                    jtf4.setText(s.toString());
                    jtf5.setText(t.toString());
                    jtf6.setText(s1.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "请确认您的输入是否合法！");
                }
            }
        });
    }
}
