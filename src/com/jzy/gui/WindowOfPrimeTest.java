/**
 * All rights Reserved, Designed By CyborgKuroChan
 *
 * @Title: WindowOfPrimeTest.java
 * @Package com.jzy.gui
 * @Description: 素性检验图形类
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午12:31:31
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

/**
 * @ClassName: WindowOfPrimeTest
 * @Description: 素性检验图形类
 * @author: JinZhiyun
 * @date: 2019年3月16日 下午12:31:31
 *
 * @Copyright: 2019 CyborgKuroChan All rights reserved. 
 * 注意：本内容由超级酷乐酱开发(wechat:Jzy_bb_1998)
 */
public class WindowOfPrimeTest {
    /**
     * @Title: GUIPrimeTest
     * @Description: 设置素性检验模块
     * @param: @param frm
     * @return: void
     * @throws
     */
    public static void GUIPrimeTest(JFrame frm) {
        JLabel j11 = new JLabel("7、素性检验：输入大整数p和安全参数k，分别输出三种素性检验结果，返回1表示是素数，0反之");
        j11.setBounds(50, 240, GUIWindow.FrameWidth, 25);
        frm.add(j11);

        JButton btn = new JButton("进入");
        btn.setBounds(620, 240, 80, 25);
        frm.add(btn);


        btn.addActionListener(new ActionListener() { //按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frmHidden = new JFrame();
                frmHidden.setBounds(2 * GUIWindow.FrameStartX, 2 * GUIWindow.FrameStartY, 850, 150); // 设置窗口初始位置和大小
                frmHidden.setTitle("素性检验：输入大整数p和安全参数k，分别输出三种素性检验结果，返回1表示是素数，0反之"); // 设置标题
                frmHidden.setLayout(null); // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色


                GUIPrimeTestHidden(frmHidden);

                frmHidden.setVisible(true); // 显示窗口
            }
        });


    }

    /**
     * @Title: GUIPrimeTestHidden
     * @Description: 点击进入后的设置素性检验模块
     * @param: @param frm
     * @return: void
     * @throws
     */
    public static void GUIPrimeTestHidden(JFrame frm) {
        JLabel j12 = new JLabel("p=");
        j12.setBounds(50, 30, 20, 25);
        frm.add(j12);
        JTextField jtf1 = new JTextField();// 创建文本行组件
        jtf1.setBounds(70, 30, 200, 25); // 左边距，上边距，长，宽
        frm.add(jtf1);

        JLabel j13 = new JLabel("k=");
        j13.setBounds(300, 30, 20, 25);
        frm.add(j13);
        JTextField jtf2 = new JTextField(); // 创建文本行组件
        jtf2.setBounds(320, 30, 200, 25); // 左边距，上边距，长，宽
        frm.add(jtf2);

        JButton btn = new JButton("计算");
        btn.setBounds(550, 30, 80, 25);
        frm.add(btn);

        JLabel j14 = new JLabel("fermat:");
        j14.setBounds(50, 60, 50, 25);
        frm.add(j14);
        JTextField jtf3 = new JTextField(); // 创建文本行组件
        jtf3.setBounds(100, 60, 150, 25); // 左边距，上边距，长，宽
        jtf3.setEditable(false);
        frm.add(jtf3);

        JLabel j15 = new JLabel("millerRabin:");
        j15.setBounds(270, 60, 100, 25);
        frm.add(j15);
        JTextField jtf4 = new JTextField(); // 创建文本行组件
        jtf4.setBounds(340, 60, 150, 25); // 左边距，上边距，长，宽
        jtf4.setEditable(false);
        frm.add(jtf4);

        JLabel j16 = new JLabel("solovayStassen:");
        j16.setBounds(500, 60, 150, 25);
        frm.add(j16);
        JTextField jtf5 = new JTextField(); // 创建文本行组件
        jtf5.setBounds(600, 60, 150, 25); // 左边距，上边距，长，宽
        jtf5.setEditable(false);
        frm.add(jtf5);

        btn.addActionListener(new ActionListener() { // 按钮响应事件
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringTest.isLegalInteger(jtf1.getText()) && StringTest.isLegalPositiveInteger(jtf2.getText())) {
                    BigInteger n = new BigInteger(jtf1.getText());
                    if (new BigInteger(jtf2.getText()).compareTo(BigInteger.valueOf(10000)) == 1) {
                        JOptionPane.showMessageDialog(null, "安全参数k建议小于" + 10000);
                    } else {
                        int k = Integer.parseInt(jtf2.getText());
                        int f = Method0.fermat(n, k);
                        int m = Method0.millerRabin(n, k);
                        int s = Method0.solovayStassen(n, k);
                        jtf3.setText(Integer.toString(f));
                        jtf4.setText(Integer.toString(m));
                        jtf5.setText(Integer.toString(s));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请确认您的输入是否合法！");
                }
            }
        });

    }
}
