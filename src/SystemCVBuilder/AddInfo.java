package SystemCVBuilder;
import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.sql.*;
import java.io.*;
public class AddInfo extends JFrame {
    private JLabel avatarLabel;
    private JButton uploadButton;
    private String imagePath;
    private byte[] imageData;
    private int userID;
    private ExportToPDF exporter;
    AddInfo() {
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("My RESUME");
        heading.setFont(new Font("SAN_SERIF", Font.PLAIN, 25)); // Giảm kích thước chữ của tiêu đề
        add(heading);
        heading.setBounds(200, 20, 400, 30);
        // Phần tiêu đề nhỏ
        JLabel titleLabel = new JLabel("Personal Information");
        titleLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        add(titleLabel);
        titleLabel.setBounds(50, 80, 400, 30);

        JLabel titleLabel1 = new JLabel("Qualifications");
        titleLabel1.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        add(titleLabel1);
        titleLabel1.setBounds(350, 80, 400, 30);

        // Biến để xác định vị trí và kích thước cho các thành phần
        int yPosition = 120; // Điều chỉnh khoảng cách giữa các thành phần
        int labelWidth = 100;
        int labelHeight = 30;
        int textFieldWidth = 200;
        int textFieldXPosition = 150;
        int gap = 40; // Điều chỉnh khoảng cách giữa các dòng

        // Add upload avatar
        avatarLabel = new JLabel();
        avatarLabel.setBounds(700, 120, 150, 150);
        add(avatarLabel);

        uploadButton = new JButton("Upload Avatar");
        uploadButton.setBounds(700, 280, 150, 30);
        add(uploadButton);

//        uploadButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser fileChooser = new JFileChooser();
//                fileChooser.setCurrentDirectory(new java.io.File("."));
//                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
//                fileChooser.setFileFilter(filter);
//                int result = fileChooser.showOpenDialog(null);
//                if (result == JFileChooser.APPROVE_OPTION) {
//                    imagePath = fileChooser.getSelectedFile().getPath();
//                    ImageIcon icon = new ImageIcon(imagePath);
//                    Image image = icon.getImage().getScaledInstance(avatarLabel.getWidth(), avatarLabel.getHeight(), Image.SCALE_SMOOTH);
//                    avatarLabel.setIcon(new ImageIcon(image));
//                }
//            }
//        });
        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("."));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
                fileChooser.setFileFilter(filter);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        File selectedFile = fileChooser.getSelectedFile();
                        // Đọc dữ liệu của hình ảnh vào một mảng byte
                        FileInputStream fis = new FileInputStream(selectedFile);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fis.read(buffer)) != -1) {
                            baos.write(buffer, 0, bytesRead);
                        }
                        imageData = baos.toByteArray();

                        // Hiển thị hình ảnh trên giao diện
                        ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
                        Image image = icon.getImage().getScaledInstance(avatarLabel.getWidth(), avatarLabel.getHeight(), Image.SCALE_SMOOTH);
                        avatarLabel.setIcon(new ImageIcon(image));

                        fis.close();
                        baos.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        // Tiêu đề
        JLabel labelFirstName = new JLabel("First Name:");
        labelFirstName.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(labelFirstName);
        labelFirstName.setBounds(50, yPosition, labelWidth, labelHeight);

        JTextField tfFirstName = new JTextField();
        add(tfFirstName);
        tfFirstName.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);
        tfFirstName.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));

        JLabel labelLastName = new JLabel("Last Name:");
        labelLastName.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Thêm label last name
        add(labelLastName);
        labelLastName.setBounds(50, yPosition + gap, labelWidth, labelHeight);

        JTextField tfLastName = new JTextField(); // Thêm text field last name
        add(tfLastName);
        tfLastName.setBounds(textFieldXPosition, yPosition + gap, textFieldWidth, labelHeight);
        tfLastName.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));

        yPosition += 2 * gap; // Tăng khoảng cách yPosition lên 2 lần gap

        JLabel labedob = new JLabel("Date of birth:");
        labedob.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(labedob);
        labedob.setBounds(50, yPosition, labelWidth, labelHeight);

        JDateChooser tfdob = new JDateChooser();
        add(tfdob);
        tfdob.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);

        yPosition += gap;

        JLabel address1Label = new JLabel("Address:");
        address1Label.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(address1Label);
        address1Label.setBounds(50, yPosition, labelWidth, labelHeight);

        JTextField address1Field = new JTextField();
        add(address1Field);
        address1Field.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);
        address1Field.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        yPosition += gap;

        JLabel postCodeLabel = new JLabel("Post Code:");
        postCodeLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(postCodeLabel);
        postCodeLabel.setBounds(50, yPosition, labelWidth, labelHeight);

        JTextField postCodeField = new JTextField();
        add(postCodeField);
        postCodeField.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);
        postCodeField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        yPosition += gap;

        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(nationalityLabel);
        nationalityLabel.setBounds(50, yPosition, labelWidth, labelHeight);

        JTextField nationalityField = new JTextField();
        add(nationalityField);
        nationalityField.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);
        nationalityField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        yPosition += gap;

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(emailLabel);
        emailLabel.setBounds(50, yPosition, labelWidth, labelHeight);

        JTextField emailField = new JTextField();
        add(emailField);
        emailField.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);
        emailField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản


        yPosition = 120; // Reset vị trí cho các phần tiếp theo

        // Thêm phần University
        JLabel universityLabel = new JLabel("University:");
        universityLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(universityLabel);
        universityLabel.setBounds(370, yPosition, labelWidth, labelHeight);


        JTextField universityField = new JTextField();
        add(universityField);
        universityField.setBounds(450, yPosition, textFieldWidth, labelHeight);
        universityField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        yPosition += gap;

        JLabel degreeLabel = new JLabel("Degree:");
        degreeLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(degreeLabel);
        degreeLabel.setBounds(370, yPosition, labelWidth, labelHeight);

        JTextField degreeField = new JTextField();
        add(degreeField);
        degreeField.setBounds(450, yPosition, textFieldWidth, labelHeight);
        degreeField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản


//        JTextField universityField = new JTextField();
//        add(universityField);
//        universityField.setBounds(450, yPosition, textFieldWidth, labelHeight);
//        universityField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        yPosition += gap;

        // Thêm phần kỹ năng tiếp theo
        JLabel skillsLabel1 = new JLabel("Skill 1:");
        skillsLabel1.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(skillsLabel1);
        skillsLabel1.setBounds(370, yPosition, labelWidth, labelHeight);

        JTextField skillsField1 = new JTextField();
        add(skillsField1);
        skillsField1.setBounds(450, yPosition, textFieldWidth, labelHeight);
        skillsField1.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        yPosition += gap;

        // Thêm phần kỹ năng tiếp theo
        JLabel skillsLabel2 = new JLabel("Skill 2:");
        skillsLabel2.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(skillsLabel2);
        skillsLabel2.setBounds(370, yPosition, labelWidth, labelHeight);

        JTextField skillsField2 = new JTextField(); // Skill 3
        add(skillsField2);
        skillsField2.setBounds(450, yPosition, textFieldWidth, labelHeight);
        skillsField2.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        yPosition += gap;

        // Thêm phần kỹ năng cuối cùng
        JLabel skillsLabel3 = new JLabel("Skill 3:");
        skillsLabel3.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(skillsLabel3);
        skillsLabel3.setBounds(370, yPosition, labelWidth, labelHeight);

        JTextField skillsField3 = new JTextField(); // Skill 4
        add(skillsField3);
        skillsField3.setBounds(450, yPosition, textFieldWidth, labelHeight);
        skillsField3.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        JButton saveButton = new JButton("Save");
        add(saveButton);
        saveButton.setBounds(300, 450, 100, 30);

        setSize(900, 600);
        setLocation(300, 50);
        setVisible(true);


        // Pần
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Kết nối đến cơ sở dữ liệu
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cvdatasystem", "root", "123456");

                    // Câu lệnh SQL INSERT
                    String sql = "INSERT INTO cv_info (first_name, last_name, date_of_birth, address, post_code, nationality, email, university, degree, skill1, skill2, skill3, avatar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
                    PreparedStatement statement = conn.prepareStatement(sql);

                    // Lấy thông tin từ các trường nhập liệu
                    statement.setString(1, tfFirstName.getText());
                    statement.setString(2, tfLastName.getText());
                    java.sql.Date dob = new java.sql.Date(tfdob.getDate().getTime());
                    statement.setDate(3, dob);
                    statement.setString(4, address1Field.getText());
                    statement.setString(5, postCodeField.getText());
                    statement.setString(6, nationalityField.getText());
                    statement.setString(7, emailField.getText());
                    statement.setString(8, universityField.getText());
                    statement.setString(9, degreeField.getText());
                    statement.setString(10, skillsField1.getText());
                    statement.setString(11, skillsField2.getText());
                    statement.setString(12, skillsField3.getText());
                    statement.setBytes(13, imageData);

                    // Thực thi câu lệnh INSERT
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Thông tin đã được lưu vào cơ sở dữ liệu!");
                        try {
                            Connection connGetID = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cvdatasystem", "root", "123456");
                            String sqlGetID = "SELECT LAST_INSERT_ID()";
                            PreparedStatement statementGetID = conn.prepareStatement(sqlGetID);
                            ResultSet resultSet = statementGetID.executeQuery();
                            if (resultSet.next()) {
                                userID = resultSet.getInt(1);
                            }
                            conn.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }

                    // Đóng kết nối
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        });

        exporter = new ExportToPDF();
        // Thêm nút để xuất file PDF
        JButton exportPDFButton = new JButton("Export PDF");
        add(exportPDFButton);
        exportPDFButton.setBounds(400, 450, 120, 30);

        // Thêm ActionListener cho nút xuất file PDF
        exportPDFButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    exporter.exportDataToPDF(userID); // Gọi phương thức xuất PDF đã được tạo trước đó
                    System.out.println("PDF file exported successfully.");
                } catch (Exception ex) {
                    System.err.println("Error exporting PDF: " + ex.getMessage());
                }
            }
        });



    }
    public static void main(String[] args) {
        new AddInfo();
    }
}