package SystemCVBuilder;

import java.awt.*;

import javax.swing.*;
public class Project extends JFrame{

    Project() {
        setSize(1540,850);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
       
        add(image);

        JMenuBar mb= new JMenuBar();
        // New Information
        JMenu newInfo= new JMenu("New Information");
        newInfo.setForeground(Color.BLUE);
        mb.add(newInfo);

        JMenuItem facultyInfo= new JMenuItem("New Faculty Information");
        facultyInfo.setForeground(Color.WHITE);
        newInfo.add(facultyInfo);

        JMenuItem studentInfo= new JMenuItem("New Student Information");
        studentInfo.setForeground(Color.WHITE);
        newInfo.add(studentInfo);

        // Details
        JMenu details= new JMenu("View Details");
        details.setForeground(Color.RED);
        mb.add(details);

        JMenuItem facultDetails= new JMenuItem("View Faculty Details");
        facultDetails.setForeground(Color.WHITE);
        details.add(facultDetails);

        JMenuItem studentDetails= new JMenuItem("View Student Details");
        studentDetails.setForeground(Color.WHITE);
        details.add(studentDetails);
        
        // Leave
        JMenu leave= new JMenu("Apply Leave");
        leave.setForeground(Color.RED);
        mb.add(leave);

        JMenuItem facultLeave= new JMenuItem("View Faculty Leave");
        facultLeave.setForeground(Color.WHITE);
        leave.add(facultDetails);

        JMenuItem studentLeave= new JMenuItem("View Student Details");
        studentLeave.setForeground(Color.WHITE);
        leave.add(studentLeave);
        // Leave Details
        JMenu leaveDetails= new JMenu("Leave Details");
        leaveDetails.setForeground(Color.RED);
        mb.add(leaveDetails);

        JMenuItem facultyLeaveDetails= new JMenuItem("View Faculty Leave");
        facultyLeaveDetails.setForeground(Color.WHITE);
        leaveDetails.add(facultyLeaveDetails);

        JMenuItem studentLeaveDetails= new JMenuItem("View Student Details");
        studentLeaveDetails.setForeground(Color.WHITE);
        leaveDetails.add(studentLeaveDetails);
        
        //Exams
        setJMenuBar(mb);

        setVisible(true);
    }
    public static void main(String[] args) {
        new Project();
    }
}
