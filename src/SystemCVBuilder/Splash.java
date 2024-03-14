package SystemCVBuilder;

import javax.swing.*;
import java.awt.*;
import java.lang.Thread;

public class Splash extends JFrame  {

  Splash() {
    getContentPane().setBackground(Color.WHITE);

    setLayout(null);


    JLabel heading = new JLabel("RESUME BUILDER ONLINE SYSTEM");

    heading.setBounds(50, 30, 1200, 60);
    heading.setFont(new Font("Tahoma", Font.BOLD, 60));
    heading.setForeground(Color.BLUE);
    add(heading);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
    Image i2 = i1.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(50,100,1050,500);
    add(image);

    JButton click= new JButton("Make Your Resume");
    click.setBackground(Color.WHITE);
    click.setBounds(485, 400, 200, 50);
    click.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    click.setFocusPainted(false);
    click.setFont(new Font("Tahoma", Font.ITALIC, 16));
    click.setForeground(Color.DARK_GRAY);
    click.setCursor(new Cursor(Cursor.HAND_CURSOR));
    click.addActionListener(e -> {
      new Login();
      dispose();
    });
    add(click);

    setSize(1170,650);
    setLocation(200,50);
    setVisible(true);

    while (true) {
      heading.setVisible(false);
      try {
        Thread.sleep(500);
      } catch (Exception e) {
      }
      heading.setVisible(true);
      try {
        Thread.sleep(500);
      } catch (Exception e) {
      }
    }

  }


  public static void main(String[] args) {
    new Splash();
  }
}
