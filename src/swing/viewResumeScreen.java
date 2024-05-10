package swing;

import javax.swing.*;

public class viewResumeScreen extends JFrame {

    public viewResumeScreen() {
        setTitle("View Resume");
        setSize(1120, 630);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // PT đóng
        setLocationRelativeTo(null); // Căn giữa

        // Content ở đây
        JTextArea resumeTextArea = new JTextArea();

        // scroll
        JScrollPane scrollPane = new JScrollPane(resumeTextArea);
        add(scrollPane);
    }
}
