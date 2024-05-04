package SystemCVBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class cvInfo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Nhập thông tin cá nhân");
        frame.setSize(1200, 700);
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Tạo các nhãn và ô nhập thông tin
        JLabel firstNameLabel = new JLabel("Họ:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(firstNameLabel, gbc);

        JTextField firstNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(firstNameField, gbc);

        JLabel lastNameLabel = new JLabel("Tên:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(lastNameLabel, gbc);

        JTextField lastNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(lastNameField, gbc);

        JLabel address1Label = new JLabel("Địa chỉ 1:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(address1Label, gbc);

        JTextField address1Field = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(address1Field, gbc);

        JLabel address2Label = new JLabel("Địa chỉ 2:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(address2Label, gbc);

        JTextField address2Field = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        frame.add(address2Field, gbc);

        JLabel postCodeLabel = new JLabel("Post Code:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(postCodeLabel, gbc);

        JTextField postCodeField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        frame.add(postCodeField, gbc);

        JLabel nationalityLabel = new JLabel("Quốc tịch:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(nationalityLabel, gbc);

        JTextField nationalityField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        frame.add(nationalityField, gbc);

        JLabel dobLabel = new JLabel("Ngày sinh:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        frame.add(dobLabel, gbc);

        JTextField dobField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        frame.add(dobField, gbc);

        JLabel emailLabel = new JLabel("Email liên hệ:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        frame.add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 7;
        frame.add(emailField, gbc);

        // Thêm nút Lưu
        JButton saveButton = new JButton("Lưu");
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(saveButton, gbc);

        // Xử lý sự kiện khi nhấn nút Lưu
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy thông tin từ các ô nhập
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String address1 = address1Field.getText();
                String address2 = address2Field.getText();
                String postCode = postCodeField.getText();
                String nationality = nationalityField.getText();
                String dob = dobField.getText();
                String email = emailField.getText();

                // In thông tin ra console
                System.out.println("Họ: " + firstName);
                System.out.println("Tên: " + lastName);
                System.out.println("Địa chỉ 1: " + address1);
                System.out.println("Địa chỉ 2: " + address2);
                System.out.println("Post Code: " + postCode);
                System.out.println("Quốc tịch: " + nationality);
                System.out.println("Ngày sinh: " + dob);
                System.out.println("Email liên hệ: " + email);
            }
        });

        frame.setVisible(true);
    }
}
