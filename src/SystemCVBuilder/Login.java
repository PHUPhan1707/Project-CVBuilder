package SystemCVBuilder;

import java.awt.Color;

import javax.swing.*;

public class Login extends JFrame{

    Login() {


        getContentPane().setBackground(Color.WHITE);
        JLabel lblusername=new JLabel("Username");
        setSize(600, 300);
        setLocation(500,250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
    
}
