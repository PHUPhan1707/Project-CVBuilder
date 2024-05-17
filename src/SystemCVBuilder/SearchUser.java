package SystemCVBuilder;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
public class SearchUser extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;

    public SearchUser() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel searchLabel = new JLabel("Enter Email:");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        topPanel.add(searchLabel);
        topPanel.add(searchField);
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String emailSearch = searchField.getText();
                if (!emailSearch.isEmpty()) {
                    searchUserInfo(emailSearch);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter an email to search.");
                }
            }
        });

        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void searchUserInfo(String emailSearch) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cvdata", "root", "123456")) {
            String sql = "SELECT email, FName, LName, dob, address, phone, nationality,university, major, skill1, skill2, skill3, experience,hobby,achievement,avatar FROM informationcv WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, emailSearch);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    StringBuilder result = new StringBuilder();
                    result.append("Email: ").append(rs.getString("email")).append("\n");
                    result.append("First Name: ").append(rs.getString("FName")).append("\n");
                    result.append("Last Name: ").append(rs.getString("LName")).append("\n");
                    result.append("Date of Birth: ").append(rs.getDate("dob")).append("\n");
                    result.append("Address: ").append(rs.getString("address")).append("\n");
                    result.append("Phone: ").append(rs.getString("phone")).append("\n");
                    result.append("Nationality: ").append(rs.getString("nationality")).append("\n");
                    result.append("University: ").append(rs.getString("university")).append("\n");
                    result.append("Major: ").append(rs.getString("major")).append("\n");
                    result.append("Skill 1: ").append(rs.getString("skill1")).append("\n");
                    result.append("Skill 2: ").append(rs.getString("skill2")).append("\n");
                    result.append("Skill 3: ").append(rs.getString("skill3")).append("\n");
                    result.append("Hobby : ").append(rs.getString("hobby")).append("\n");
                    result.append("Achievement : ").append(rs.getString("achievement")).append("\n");

                    result.append("Experience: ").append(rs.getString("experience")).append("\n");

                    resultArea.setText(result.toString());
                } else {
                    resultArea.setText("No user found with email: " + emailSearch);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to retrieve user information.");
        }



    }


    public void main(String[] args) {
        new SearchUser();
    }
}
