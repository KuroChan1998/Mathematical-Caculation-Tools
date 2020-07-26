package com.jzy.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.math.BigInteger;

import javax.swing.*;

import com.jzy.util.StringTest;
import com.jzy.xxaqsxjc.method0.Method0;

/**
 * 最大公因数和最小公倍数的计算图形类
 *
 * @author JinZhiyun
 * @version 1.0, 19/09/03
 */
public class WindowOfCommonFactorMultiple {

    /**
     * 最大公因数和最小公倍数的计算模块
     *
     * @param frm
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static void GUICommonFactorMultiple(JFrame frm) {
        JLabel j11 = new JLabel("3、最大公因数和最小公倍数计算：输入x,y，返回(x,y)，[x,y]");

        j11.setBounds(50, 120, GUIWindow.FrameWidth, 25);
        frm.add(j11);

        JButton btn = new JButton("进入");

        btn.setBounds(410, 120, 80, 25);
        frm.add(btn);
        btn.addActionListener(new ActionListener() {                                              // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frmHidden = new JFrame();

                frmHidden.setBounds(2 * GUIWindow.FrameStartX,
                        2 * GUIWindow.FrameStartY,
                        700,
                        150);                                   // 设置窗口初始位置和大小
                frmHidden.setTitle("最大公因数和最小公倍数计算：输入x,y，返回(x,y)，[x,y]");    // 设置标题
                frmHidden.setLayout(null);    // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色
                GUICommonFactorMultipleHidden(frmHidden);
                frmHidden.setVisible(true);                                 // 显示窗口
            }
        });
    }

    /**
     * 点击进入后的最大公因数和最小公倍数的计算模块
     *
     * @param frm
     * @version 1.0, 19/09/03
     * @author JinZhiyun
     */
    public static void GUICommonFactorMultipleHidden(JFrame frm) {
        JLabel j12 = new JLabel("x=");

        j12.setBounds(50, 30, 20, 25);
        frm.add(j12);

        JTextField jtf1 = new JTextField();    // 创建文本行组件

        jtf1.setBounds(70, 30, 200, 25);    // 左边距，上边距，长，宽
        frm.add(jtf1);

        JLabel j13 = new JLabel("y=");

        j13.setBounds(300, 30, 20, 25);
        frm.add(j13);

        JTextField jtf2 = new JTextField();    // 创建文本行组件

        jtf2.setBounds(320, 30, 200, 25);    // 左边距，上边距，长，宽
        frm.add(jtf2);

        JButton btn = new JButton("计算");

        btn.setBounds(550, 30, 80, 25);
        frm.add(btn);

        JLabel j14 = new JLabel("(x,y)=");

        j14.setBounds(50, 60, 50, 25);
        frm.add(j14);

        JTextField jtf3 = new JTextField();    // 创建文本行组件

        jtf3.setBounds(90, 60, 200, 25);    // 左边距，上边距，长，宽
        jtf3.setEditable(false);
        frm.add(jtf3);

        JLabel j15 = new JLabel("[x,y]=");

        j15.setBounds(300, 60, 50, 25);
        frm.add(j15);

        JTextField jtf4 = new JTextField();    // 创建文本行组件

        jtf4.setBounds(340, 60, 200, 25);               // 左边距，上边距，长，宽
        jtf4.setEditable(false);
        frm.add(jtf4);
        btn.addActionListener(new ActionListener() {    // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringTest.isLegalPositiveInteger(jtf1.getText())
                        && StringTest.isLegalPositiveInteger(jtf2.getText())) {
                    BigInteger x = new BigInteger(jtf1.getText());
                    BigInteger y = new BigInteger(jtf2.getText());
                    BigInteger maxCommonFactor = Method0.maxCommonFactorXY(x, y);
                    BigInteger minCommonMultiple = Method0.minCommonMultipleXY(x, y);

                    jtf3.setText(maxCommonFactor.toString());
                    jtf4.setText(minCommonMultiple.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "请确认您的输入是否合法！");
                }
            }
        });
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
