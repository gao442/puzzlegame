package com.gwb.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test1 {
    public static void main(String[] args) {
        JFrame jFrame=new JFrame();
        jFrame.setSize(603,680);
        jFrame.setTitle("演示");
        jFrame.setAlwaysOnTop(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setLayout(null);

        JButton jButton=new JButton("点我呀嘻嘻");
        jButton.setBounds(0,0,100,50);
        //addActionListener动作监听鼠标左键你和空格
        //addActionListener这是个接口，调用的时候必须调用它的实现类对象
        jButton.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("好疼");
                    }
                }
                /*
                当按钮功能只有一个，重开一个类太麻烦了所有直接用匿名内部类
                 */

        );

        jFrame.getContentPane().add(jButton);


        jFrame.setVisible(true);
    }
}
