package com.gwb.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //在成员位置创建二维数组
    //用来管理数据，加载图片是用到二维数组
    int[][] data = new int[4][4];

    int x, y;

    String image_path = "image\\plane\\";

    int win[][] = {
            {0, 1, 2, 3},
            {4, 5, 6, 7},
            {8, 9, 10, 11},
            {12, 13, 14, 15}
    };

    int step = 0;

    //选项条目
    JMenuItem replayItem = new JMenuItem("重新开始");
    JMenuItem reloginItem = new JMenuItem("重新登录");
    JMenuItem closItem = new JMenuItem("关闭游戏");
    JMenuItem payItem = new JMenuItem("充值入口");
    JMenuItem montain=new JMenuItem("张家界");
    JMenuItem gg=new JMenuItem("刚神");
    JMenuItem zxy=new JMenuItem("赵欣怡");
    JMenu changeItem=new JMenu("切换图片");




    //空参构造初始化界面整体
    public GameJFrame() {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据
        initdata();

        //初始化图片
        initimage();

        //可视化界面
        this.setVisible(true);
    }


    //成员方法
    //初始化数据
    private void initdata() {

        int[] temparr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};


        int idx = 0;
        Random rd = new Random();
        for (int i = 0; i < temparr.length; i++) {
            int temp;
            int temp_idx = rd.nextInt(temparr.length);
            temp = temparr[temp_idx];
            temparr[temp_idx] = temparr[i];
            temparr[i] = temp;
        }
        for (int i = 0; i < temparr.length; i++) {
            if (temparr[i] == 15) {
                //记录空白在二维数组中的位置
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = temparr[i];
        }

    }


    //初始化图片
    private void initimage() {

        this.getContentPane().removeAll();

        if (this.victory()) {
            JLabel win_jLabel = new JLabel(new ImageIcon("image\\win_image.jpg"));
            //指定图片位置
            win_jLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(win_jLabel);
        }

        JLabel step_iamge = new JLabel("步数" + step);
        step_iamge.setBounds(50, 30, 100, 20);
        this.getContentPane().add(step_iamge);


        for (int i = 0; i < 4; i++) {
            for (int i1 = 0; i1 < 4; i1++) {
                int number = data[i][i1];

                //创建imageicon的对象(创建图片的对象）
                //创建jlable对象（管理图片的容器）
                JLabel jLabel = new JLabel(new ImageIcon(image_path + number + ".jpg"));
                //指定图片位置
                jLabel.setBounds(105 * i1 + 83, 105 * i + 134, 105, 105);
                //给图片加边框
                jLabel.setBorder(new BevelBorder(0));
                //添加jlable对象加入界面
                this.getContentPane().add(jLabel);


            }

        }

        JLabel backgrand = new JLabel(new ImageIcon("image\\拼图游戏界面类.jpg"));
        backgrand.setBounds(40, 40, 508, 560);
        this.getContentPane().add(backgrand);


        this.getContentPane().repaint();

    }


    private void initJMenuBar() {
        //初始化菜单
        JMenuBar jMenuBar = new JMenuBar();
        //菜单选项
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJmenu = new JMenu("一键通关");

        //添加条目进入选项
        functionJMenu.add(replayItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(closItem);
        functionJMenu.add(changeItem);
        aboutJmenu.add(payItem);

        changeItem.add(montain);
        changeItem.add(gg);
        changeItem.add(zxy);

        //添加选项进入菜单
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJmenu);
        //添加菜单进入界面
        this.setJMenuBar(jMenuBar);

        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closItem.addActionListener(this);
        payItem.addActionListener(this);
        montain.addActionListener(this);
        gg.addActionListener(this);
        zxy.addActionListener(this);
    }


    private void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("拼图游戏");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);

        //取消图片居中的布局方式
        this.setLayout(null);
        //添加事件，加入键盘监听
        this.addKeyListener(this);
    }


    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code_ = e.getKeyCode();
        if (code_ == 79) {
            this.getContentPane().removeAll();

            JLabel full_image = new JLabel(new ImageIcon(image_path + "full.jpg"));
            full_image.setBounds(83, 134, 420, 420);
            this.getContentPane().add(full_image);


            JLabel backgrand = new JLabel(new ImageIcon("image\\拼图游戏界面类.jpg"));
            backgrand.setBounds(40, 40, 508, 560);
            this.getContentPane().add(backgrand);

            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (victory()) {
            return;
        }


        int code = e.getKeyCode();

        if (code == 37) {            //左

            if (y == 3) {
                return;
            }

            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 15;
            y++;
            step++;
            initimage();


        } else if (code == 38) {//上

            if (x == 3) {
                return;
            }

            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 15;
            x++;
            step++;
            initimage();


        } else if (code == 39) {//右
            if (y == 0) {
                return;
            }

            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 15;
            y--;
            step++;
            initimage();


        } else if (code == 40) {   //下

            if (x == 0) {
                return;
            }

            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 15;
            x--;
            step++;
            initimage();


        } else if (code == 79) {
            initimage();
        } else if (code == 75) {

            this.getContentPane().removeAll();


            for (int i = 0; i < 16; i++) {
                JLabel jl = new JLabel(new ImageIcon(image_path + i + ".jpg"));
                jl.setBounds(105 * (i % 4) + 83, 105 * (i / 4) + 134, 105, 105);
                this.getContentPane().add(jl);
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    data[i][j] = win[i][j];
                }
            }
            // 空白块15 在第3行第3列
            x = 3;
            y = 3;
            initimage();


            JLabel backgrand = new JLabel(new ImageIcon("image\\拼图游戏界面类.jpg"));
            backgrand.setBounds(40, 40, 508, 560);
            this.getContentPane().add(backgrand);

            this.getContentPane().repaint();


        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();


        if(source==replayItem){
            step=0;
            initdata();
            initimage();


        } else if (source==reloginItem) {

            this.setVisible(false);
            new LoginJFrame();


        } else if (source==closItem) {

            System.exit(0);

        } else if (source==payItem) {
            JDialog jDialog=new JDialog();

            JLabel jLabel=new JLabel(new ImageIcon("D:\\Java学习\\puzzlegame\\image\\pay_image.jpg"));
            jLabel.setBounds(0,0,258,258);

            jDialog.setSize(344,344);
            jDialog.getContentPane().add(jLabel);
            jDialog.setAlwaysOnTop(true);

            jDialog.setLocationRelativeTo(null);

            jDialog.setModal(true);

            jDialog.setVisible(true);

        } else if (source==montain) {
            image_path="image\\montain\\";
            step=0;
            this.initimage();

        }else if(source==gg){
            image_path="image\\gangshen\\";
            step=0;
            this.initimage();
        }else if(source==zxy){
            image_path="image\\zxy\\";
            step=0;
            this.initimage();
        }


    }
}


