package SystemCVBuilder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JTextField tfusername, tfpassword;
    JButton login, cancel,sign;

    Login() {
        super("Login Application");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 20);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 20);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 20);
        add(lblpassword);
        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70, 150, 20);
        add(tfpassword);

        Choice logginias= new Choice();
        logginias.add("Admin");
        logginias.add("User");
        logginias.setBounds(150,120,150,20);
        add(logginias);

        ImageIcon ilogin1= new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
        Image ilogin2= ilogin1.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        login = new JButton("Login", new ImageIcon(ilogin2));
        login.setBounds(40, 145, 120, 30);
        login.addActionListener(this);
        add(login);

        ImageIcon ilogin3= new ImageIcon(ClassLoader.getSystemResource("icons/cancel.jpg"));
        Image ilogin4= ilogin3.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel",new ImageIcon(ilogin4));
        cancel.setBounds(170, 145, 120, 30);
        cancel.addActionListener(this);
        add(cancel);

        sign = new JButton("Sign Up");
        sign.setBounds(100, 180, 120, 30);
        sign.setBackground(Color.BLACK);
        sign.setForeground(Color.WHITE);
        sign.setFont(new Font("Tahoma", Font.BOLD, 15));
        sign.addActionListener(this);
        add(sign);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 20, 200, 200);
        add(image);

        setSize(600, 300);
        setLocation(500, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==login){
            String username = tfusername.getText();
            String password = tfpassword.getText();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both username and password");
            } else {
                String query = "select * from Login where username = '" + username + "' and password = '" + password + "'";

                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery(query);
                    if (rs.next()) {
                        setVisible(false);
                        new Home();
                    } else {
                        JOptionPane.showMessageDialog(null, "Mật khẩu hoặc tên đăng nhập chưa đúng");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource()==cancel){
            System.exit(0);
        } else if (ae.getSource()==sign){
            setVisible(false);
            new Sign().setVisible(true);

        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
       