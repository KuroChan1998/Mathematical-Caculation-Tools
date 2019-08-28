/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: WindowOfLegendreAndJacobi.java
 * @Package com.jzy.gui
 * @Description: 勒让得符号和雅可比符号计算图形类
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午12:27:22
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
 * @ClassName: WindowOfLegendreAndJacobi
 * @Description: 勒让得符号和雅可比符号计算图形类
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午12:27:22
 *
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class WindowOfLegendreAndJacobi {


    /**
     * @Title: GUILegendreAndJacobi
     * @Description: 勒让得符号和雅可比符号计算模块
     * @param: @param frm
     * @return: void
     * @throws
     */
    public static void GUILegendreAndJacobi(JFrame frm) {
        JLabel j11 = new JLabel("5、勒让得符号和雅可比符号计算：输入q,p，返回Legendre(q/p)和Jacobi(q/p)");
        j11.setBounds(50, 180, GUIWindow.FrameWidth, 25);
        frm.add(j11);

        JButton btn = new JButton("进入");
        btn.setBounds(500, 180, 80, 25);
        frm.add(btn);

        btn.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frmHidden = new JFrame();
                frmHidden.setBounds(2 * GUIWindow.FrameStartX, 2 * GUIWindow.FrameStartY, 700, 150); // 设置窗口初始位置和大小
                frmHidden.setTitle("勒让得符号和雅可比符号计算：输入q,p，返回Legendre(q/p)和Jacobi(q/p)"); // 设置标题
                frmHidden.setLayout(null); // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色

                GUILegendreAndJacobiHidden(frmHidden);

                frmHidden.setVisible(true); // 显示窗口
            }
        });
    }

    /**
     * @Title: GUILegendreAndJacobiHidden
     * @Description: 点击进入后的勒让得符号和雅可比符号计算模块
     * @param: @param frm
     * @return: void
     * @throws
     */
    public static void GUILegendreAndJacobiHidden(JFrame frm) {
        JLabel j12 = new JLabel("q=");
        j12.setBounds(50, 30, 20, 25);
        frm.add(j12);
        JTextField jtf1 = new JTextField();// 创建文本行组件
        jtf1.setBounds(70, 30, 200, 25); // 左边距，上边距，长，宽
        frm.add(jtf1);

        JLabel j13 = new JLabel("p=");
        j13.setBounds(300, 30, 20, 25);
        frm.add(j13);
        JTextField jtf2 = new JTextField(); // 创建文本行组件
        jtf2.setBounds(320, 30, 200, 25); // 左边距，上边距，长，宽
        frm.add(jtf2);

        JButton btn = new JButton("计算");
        btn.setBounds(550, 30, 80, 25);
        frm.add(btn);

        JLabel j14 = new JLabel("Legendre(q/p)=");
        j14.setBounds(50, 60, 100, 25);
        frm.add(j14);
        JTextField jtf3 = new JTextField(); // 创建文本行组件
        jtf3.setBounds(140, 60, 200, 25); // 左边距，上边距，长，宽
        jtf3.setEditable(false);
        frm.add(jtf3);

        JLabel j15 = new JLabel("Jacobi(q/p)=");
        j15.setBounds(360, 60, 100, 25);
        frm.add(j15);
        JTextField jtf4 = new JTextField(); // 创建文本行组件
        jtf4.setBounds(440, 60, 200, 25); // 左边距，上边距，长，宽
        jtf4.setEditable(false);
        frm.add(jtf4);

        btn.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringTest.isLegalNonNegativeInteger(jtf1.getText()) && StringTest.isLegalPositiveInteger(jtf2.getText())) {
                    BigInteger q = new BigInteger(jtf1.getText());
                    BigInteger p = new BigInteger(jtf2.getText());
                    int legendreqp = Method0.legendre(q, p);
                    int jacobiqp = Method0.jacobi(q, p);
                    jtf3.setText(Integer.toString(legendreqp));
                    jtf4.setText(Integer.toString(jacobiqp));
                } else {
                    JOptionPane.showMessageDialog(null, "请确认您的输入是否合法！");
                }
            }
        });
    }
}
