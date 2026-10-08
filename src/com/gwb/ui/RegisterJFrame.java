package com.gwb.ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterJFrame extends JFrame implements MouseListener {

    // 输入框
    JTextField usernameField;
    JPasswordField passwordField;
    JPasswordField confirmPasswordField;

    // 按钮
    JButton registerButton;
    JButton backButton;

    //空参构造初始化界面
    public RegisterJFrame() {
        initRegisterFrame();
    }

    private void initRegisterFrame() {
        this.setSize(488, 500);
        this.setTitle("拼图游戏注册");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setLayout(null);

        // 用户名标签
        JLabel usernameLabel = new JLabel("用户名：");
        usernameLabel.setBounds(100, 80, 80, 30);
        this.getContentPane().add(usernameLabel);

        // 用户名输入框
        usernameField = new JTextField();
        usernameField.setBounds(190, 80, 180, 30);
        this.getContentPane().add(usernameField);

        // 密码标签
        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setBounds(100, 140, 80, 30);
        this.getContentPane().add(passwordLabel);

        // 密码输入框
        passwordField = new JPasswordField();
        passwordField.setBounds(190, 140, 180, 30);
        this.getContentPane().add(passwordField);

        // 确认密码标签
        JLabel confirmLabel = new JLabel("确认密码：");
        confirmLabel.setBounds(100, 200, 80, 30);
        this.getContentPane().add(confirmLabel);

        // 确认密码输入框
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(190, 200, 180, 30);
        this.getContentPane().add(confirmPasswordField);

        // 注册按钮
        registerButton = new JButton("注册");
        registerButton.setBounds(120, 280, 100, 40);
        registerButton.addMouseListener(this);
        this.getContentPane().add(registerButton);

        // 返回登录按钮
        backButton = new JButton("返回登录");
        backButton.setBounds(260, 280, 100, 40);
        backButton.addMouseListener(this);
        this.getContentPane().add(backButton);

        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

        if (source == registerButton) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            // 校验非空
            if (username.equals("") || password.equals("") || confirmPassword.equals("")) {
                JOptionPane.showMessageDialog(this, "用户名、密码、确认密码不能为空！");
                return;
            }

            // 校验用户名长度
            if (username.length() < 3) {
                JOptionPane.showMessageDialog(this, "用户名长度不能少于3位！");
                return;
            }

            // 校验密码长度
            if (password.length() < 6) {
                JOptionPane.showMessageDialog(this, "密码长度不能少于6位！");
                return;
            }

            // 校验两次密码一致
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "两次输入的密码不一致！");
                passwordField.setText("");
                confirmPasswordField.setText("");
                return;
            }

            // 校验用户名是否已存在
            if (LoginJFrame.containsUser(username)) {
                JOptionPane.showMessageDialog(this, "该用户名已被注册！");
                usernameField.setText("");
                return;
            }

            // 注册成功，写入共享用户数据
            LoginJFrame.addUser(username, password);
            JOptionPane.showMessageDialog(this, "注册成功！请登录。");
            this.setVisible(false);
            new LoginJFrame();

        } else if (source == backButton) {
            // 返回登录界面
            this.setVisible(false);
            new LoginJFrame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
