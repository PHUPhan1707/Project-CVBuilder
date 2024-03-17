package SystemCVBuilder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.sql.*;

public class Sign extends JFrame implements ActionListener{
    JButton create, back;
    JTextField meter, username, name, password;
    Choice account;
    Sign() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

       setBounds(450,150,700,400);

        JPanel panel = new JPanel();
        panel.setBounds(30,30,650,300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 10),"Sign Up", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);


        JLabel heading = new JLabel("Create Account :");
        heading.setBounds(30, 20, 150, 30);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(heading);

        account = new Choice();
        account.add("Admin");
        account.add("User");
        account.setBounds(180, 25, 150, 30);
        panel.add(account);

        JLabel lblmeter = new JLabel("Meter Number:");
        lblmeter.setBounds(30, 50, 150, 30);
        lblmeter.setForeground(Color.GRAY);
        lblmeter.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblmeter);

        meter = new JTextField();
        meter.setBounds(180, 50, 150, 30);
        panel.add(meter);

        JLabel lblusername = new JLabel("Username:");
        lblusername.setBounds(30, 90, 150, 30);
        lblusername.setForeground(Color.GRAY);
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblusername);

        username = new JTextField();
        username.setBounds(180,90 , 150, 30);
        panel.add(username);

        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(30, 130, 150, 30);
        lblname.setForeground(Color.GRAY);
        lblname.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblname);

        name = new JTextField();
        name.setBounds(180,130 , 150, 30);
        panel.add(name);

       JLabel lblpassword = new JLabel("Password:");
       lblpassword.setBounds(30, 170, 150, 30);
       lblpassword.setForeground(Color.GRAY);
       lblpassword.setFont(new Font("Tahoma", Font.BOLD, 14));
       panel.add(lblpassword);

       password = new JTextField();
       password.setBounds(180,170 , 150, 30);
       panel.add(password);

       create = new JButton("Create");
       create.setBackground(Color.BLACK);
       create.setForeground(Color.WHITE);
       create.setBounds(50, 220, 100, 30);
       create.addActionListener(this);
       panel.add(create);

       back= new JButton("Back");
       back.setBackground(Color.BLACK);
       back.setForeground(Color.WHITE);
       back.setBounds(180, 220, 100, 30);
       back.addActionListener(this);
       panel.add(back);

      ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/signupImage.png"));
      Image i2= i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
      ImageIcon i3= new ImageIcon(i2);
      JLabel image= new JLabel(i3);
      image.setBounds(400, 50, 200, 200);
      panel.add(image);


      setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == create){
            String atype= account.getSelectedItem();
            String susername= username.getText();
            String sname= name.getText();
            String spassword= password.getText();
            String smeter= meter.getText();
        try {
            Conn c = new Conn();
            String query = "insert into login values('"+smeter+"','"+susername+"','"+sname+"','"+spassword+"','"+atype+"')";
            c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Account Created Successfully");
            setVisible(false);
            new Login();
        }
        catch (Exception e1) {
            e1.printStackTrace();

        }
        }else if(e.getSource() == back){
            setVisible(false);
            new Login();
        }
    }
    public static void main(String[] args) {
        new Sign();
    }


}
