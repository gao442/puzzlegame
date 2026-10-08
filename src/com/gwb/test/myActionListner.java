package com.gwb.test;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class myActionListner extends JFrame implements ActionListener, MouseListener, KeyListener {


    JButton jButton1=new JButton("赵欣怡");
    JButton jButton2=new JButton("高文博");



    public myActionListner(){
        //JFrame jFrame=new JFrame();
        this.setSize(603,680);
        this.setTitle("演示");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setLayout(null);



        jButton1.setBounds(100,0,100,50);


        jButton2.setBounds(0,0,100,50);

        jButton1.addActionListener(this);

        jButton2.addActionListener(this);

        this.getContentPane().add(jButton1);
        this.getContentPane().add(jButton2);

        jButton1.addMouseListener(this);

        this.addKeyListener(this);

        this.setFocusable(true);
        this.requestFocus();

        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object souce=e.getSource();
        if(souce==jButton1){
            System.out.println("111");
        } else if (souce==jButton2) {
            System.out.println("333");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按键盘");
        int a= e.getKeyCode();
        System.out.println(a);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("松键盘");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("按下鼠标");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("松开鼠标");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
