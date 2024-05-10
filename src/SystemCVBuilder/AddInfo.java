package SystemCVBuilder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddInfo extends JFrame{

    AddInfo(){
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(600, 200, 800, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddInfo().setVisible(true);
    }
}
