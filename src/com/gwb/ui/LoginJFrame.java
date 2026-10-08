package com.gwb.ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Random;

public class LoginJFrame extends JFrame implements MouseListener {

    JLabel jLabel_name;
    JLabel jLabel_password;
    JLabel jLabel_test;

    // 输入框
    JTextField usernameField;
    JPasswordField passwordField;
    JTextField captchaField;

    // 按钮
    JButton loginButton;
    JButton registerButton;

    // 验证码
    String captchaCode;
    JLabel captchaLabel;

    // 用户数据存储（静态，供注册界面共享）
    static HashMap<String, String> userMap = new HashMap<>();

    static {
        // 默认测试账号
        userMap.put("admin", "123456");
    }

    //空参构造初始化界面
    public LoginJFrame() {
        initLoginFrame();
    }

    private void initLoginFrame() {
        this.setSize(488, 430);
        this.setTitle("拼图游戏登录");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setLayout(null);

        // 用户名图片标签
        jLabel_name = new JLabel(new ImageIcon("D:\\Java学习\\puzzlegame\\image\\用户名.jpg"));
        jLabel_name.setBounds(100, 80, 47, 17);
        this.getContentPane().add(jLabel_name);

        // 用户名输入框
        usernameField = new JTextField();
        usernameField.setBounds(170, 73, 200, 30);
        this.getContentPane().add(usernameField);

        // 密码图片标签
        jLabel_password = new JLabel(new ImageIcon("D:\\Java学习\\puzzlegame\\image\\密码.jpg"));
        jLabel_password.setBounds(100, 140, 47, 17);
        this.getContentPane().add(jLabel_password);

        // 密码输入框
        passwordField = new JPasswordField();
        passwordField.setBounds(170, 133, 200, 30);
        this.getContentPane().add(passwordField);

        // 验证码图片标签
        jLabel_test = new JLabel(new ImageIcon("D:\\Java学习\\puzzlegame\\image\\验证码.jpg"));
        jLabel_test.setBounds(100, 200, 47, 17);
        this.getContentPane().add(jLabel_test);

        // 验证码输入框
        captchaField = new JTextField();
        captchaField.setBounds(170, 193, 100, 30);
        this.getContentPane().add(captchaField);

        // 验证码显示（点击可刷新）
        generateCaptcha();
        captchaLabel = new JLabel(captchaCode);
        captchaLabel.setBounds(290, 193, 80, 30);
        captchaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        captchaLabel.setBorder(BorderFactory.createEtchedBorder());
        captchaLabel.addMouseListener(this);
        this.getContentPane().add(captchaLabel);

        // 登录按钮
        loginButton = new JButton("登录");
        loginButton.setBounds(120, 270, 100, 40);
        loginButton.addMouseListener(this);
        this.getContentPane().add(loginButton);

        // 注册按钮
        registerButton = new JButton("注册");
        registerButton.setBounds(260, 270, 100, 40);
        registerButton.addMouseListener(this);
        this.getContentPane().add(registerButton);

        this.setVisible(true);
    }

    // 生成4位随机验证码
    private void generateCaptcha() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(chars.charAt(rd.nextInt(chars.length())));
        }
        captchaCode = sb.toString();
    }

    // 注册成功后调用，添加用户
    public static void addUser(String username, String password) {
        userMap.put(username, password);
    }

    // 检查用户名是否已存在
    public static boolean containsUser(String username) {
        return userMap.containsKey(username);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

        if (source == loginButton) {
            // 登录逻辑
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String inputCaptcha = captchaField.getText().trim();

            // 校验非空
            if (username.equals("") || password.equals("") || inputCaptcha.equals("")) {
                JOptionPane.showMessageDialog(this, "用户名、密码、验证码不能为空！");
                return;
            }

            // 校验验证码（不区分大小写）
            if (!inputCaptcha.equalsIgnoreCase(captchaCode)) {
                JOptionPane.showMessageDialog(this, "验证码错误！");
                generateCaptcha();
                captchaLabel.setText(captchaCode);
                captchaField.setText("");
                return;
            }

            // 校验用户名密码
            if (userMap.containsKey(username) && userMap.get(username).equals(password)) {
                JOptionPane.showMessageDialog(this, "登录成功！");
                this.setVisible(false);
                new GameJFrame();
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误！");
                passwordField.setText("");
            }

        } else if (source == registerButton) {
            // 跳转到注册界面
            this.setVisible(false);
            new RegisterJFrame();
        } else if (source == captchaLabel) {
            // 点击验证码刷新
            generateCaptcha();
            captchaLabel.setText(captchaCode);
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
