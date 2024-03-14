package SystemCVBuilder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class Home extends JFrame implements ActionListener{

    JButton view,add, update, remove;
    Home(){
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/main.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1120,630);
        add(image);

        JLabel heading = new JLabel("RESUME BUILDER ONLINE SYSTEM");
        heading.setBounds(650,20,400,40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        image.add(heading);

        add = new JButton("Create Resume");
        add.setBounds(820,80,150,40);
        add.addActionListener(this);
        image.add(add);

        view = new JButton("View Resume");
        view.setBounds(650,140,150,40);
        add.addActionListener(this);

        image.add(view);
        update = new JButton("Updata Resume");
        update.setBounds(820,140,150,40);
        add.addActionListener(this);

        image.add(update);
        remove = new JButton("Remove Resume");
        remove.setBounds(650,80,150,40);
        add.addActionListener(this);

        image.add(remove);



        setSize(1120,630);
        setLocation(250,100);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add){

        }else if (e.getSource() == view){

        } else if (e.getSource() == update){

        }else if (e.getSource() == remove){

        }


        }
        public static void main(String[] args){
        new Home();
    }


}
