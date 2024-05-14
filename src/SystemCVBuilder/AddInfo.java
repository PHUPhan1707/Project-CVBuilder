package SystemCVBuilder;

import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.sql.*;
import java.io.*;

public class AddInfo extends JFrame {
    private JLabel avatarLabel;
    private JButton uploadButton;
    private String imagePath;
    private byte[] imageData;
    private int userID;


    public AddInfo() {
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("My RESUME");
        heading.setFont(new Font("SAN_SERIF", Font.PLAIN, 25));
        add(heading);
        heading.setBounds(200, 20, 400, 30);
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel backgroundLabel = new JLabel();
        add(backgroundLabel);

        // Personal Information Title
        JLabel titleLabel = new JLabel("Personal Information");
        titleLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        add(titleLabel);
        titleLabel.setBounds(50, 80, 400, 30);

        // Qualifications Title
        JLabel titleLabel1 = new JLabel("Qualifications");
        titleLabel1.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        add(titleLabel1);
        titleLabel1.setBounds(370, 80, 400, 30);

        int yPosition = 120;
        int labelWidth = 100;
        int labelHeight = 30;
        int textFieldWidth = 200;
        int textFieldXPosition = 150;
        int gap = 40;

        // Upload Avatar
        avatarLabel = new JLabel();
        avatarLabel.setBounds(700, 120, 150, 150);
        add(avatarLabel);

        uploadButton = new JButton("Upload Avatar");
        uploadButton.setBounds(700, 280, 150, 30);
        add(uploadButton);

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
                        FileInputStream fis = new FileInputStream(selectedFile);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fis.read(buffer)) != -1) {
                            baos.write(buffer, 0, bytesRead);
                        }
                        imageData = baos.toByteArray();
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

        // Personal Information Fields
        JLabel labelFirstName = new JLabel("First Name:");
        labelFirstName.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(labelFirstName);
        labelFirstName.setBounds(50, yPosition, labelWidth, labelHeight);

        JTextField tfFirstName = new JTextField();
        add(tfFirstName);
        tfFirstName.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);
        tfFirstName.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));

        JLabel labelLastName = new JLabel("Last Name:");
        labelLastName.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(labelLastName);
        labelLastName.setBounds(50, yPosition + gap, labelWidth, labelHeight);

        JTextField tfLastName = new JTextField();
        add(tfLastName);
        tfLastName.setBounds(textFieldXPosition, yPosition + gap, textFieldWidth, labelHeight);
        tfLastName.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));

        yPosition += 2 * gap;

        JLabel labedob = new JLabel("Date of birth:");
        labedob.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(labedob);
        labedob.setBounds(50, yPosition, labelWidth, labelHeight);

        JDateChooser tfdob = new JDateChooser();
        add(tfdob);
        tfdob.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);

        yPosition += gap;

        JLabel address1Label = new JLabel("Address:");
        address1Label.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(address1Label);
        address1Label.setBounds(50, yPosition, labelWidth, labelHeight);

        JTextField address1Field = new JTextField();
        add(address1Field);
        address1Field.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);
        address1Field.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));

        yPosition += gap;

        JLabel postCodeLabel = new JLabel("Phone:");
        postCodeLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(postCodeLabel);
        postCodeLabel.setBounds(50, yPosition, labelWidth+20, labelHeight);

        JTextField postCodeField = new JTextField();
        add(postCodeField);
        postCodeField.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);
        postCodeField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));

        yPosition += gap;

        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(nationalityLabel);
        nationalityLabel.setBounds(50, yPosition, labelWidth, labelHeight);

        JTextField nationalityField = new JTextField();
        add(nationalityField);
        nationalityField.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);
        nationalityField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));

        yPosition += gap;

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(emailLabel);
        emailLabel.setBounds(50, yPosition, labelWidth, labelHeight);

        JTextField emailField = new JTextField();
        add(emailField);
        emailField.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);
        emailField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));

        yPosition = 120;

        // University Section
        JLabel universityLabel = new JLabel("University:");
        universityLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(universityLabel);
        universityLabel.setBounds(370, yPosition, labelWidth, labelHeight);

        JTextField universityField = new JTextField();
        add(universityField);
        universityField.setBounds(450, yPosition, textFieldWidth, labelHeight);
        universityField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));

        yPosition += gap;

        JLabel degreeLabel = new JLabel("Degree:");
        degreeLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(degreeLabel);
        degreeLabel.setBounds(370, yPosition, labelWidth, labelHeight);

        JTextField degreeField = new JTextField();
        add(degreeField);
        degreeField.setBounds(450, yPosition, textFieldWidth, labelHeight);
        degreeField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));

        yPosition += gap;

        // Skills Section
        JLabel skillsLabel1 = new JLabel("Skill 1:");
        skillsLabel1.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(skillsLabel1);
        skillsLabel1.setBounds(370, yPosition, labelWidth, labelHeight);

        JTextField skillsField1 = new JTextField();
        add(skillsField1);
        skillsField1.setBounds(450, yPosition, textFieldWidth, labelHeight);
        skillsField1.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));

        yPosition += gap;

        JLabel skillsLabel2 = new JLabel("Skill 2:");
        skillsLabel2.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(skillsLabel2);
        skillsLabel2.setBounds(370, yPosition, labelWidth, labelHeight);

        JTextField skillsField2 = new JTextField();
        add(skillsField2);
        skillsField2.setBounds(450, yPosition, textFieldWidth, labelHeight);
        skillsField2.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));

        yPosition += gap;

        JLabel skillsLabel3 = new JLabel("Skill 3:");
        skillsLabel3.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(skillsLabel3);
        skillsLabel3.setBounds(370, yPosition, labelWidth, labelHeight);

        JTextField skillsField3 = new JTextField();
        add(skillsField3);
        skillsField3.setBounds(450, yPosition, textFieldWidth, labelHeight);
        skillsField3.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));

        // Experience Section
        yPosition += 2 * gap;

        JLabel experienceLabel = new JLabel("Experience:");
        experienceLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        add(experienceLabel);
        experienceLabel.setBounds(370, yPosition - 40, labelWidth, labelHeight);

        JTextField experienceField = new JTextField();
        experienceField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));
        add(experienceField);
        experienceField.setBounds(450, yPosition - 40, textFieldWidth, 2 * labelHeight);


        yPosition += 5 * gap;

        JButton saveButton = new JButton("Save");
        add(saveButton);
        saveButton.setBounds(250, 450, 100, 30);

        setSize(900, 600);
        setLocation(300, 50);
        setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cvs", "root", "123456");
                    String sql = "INSERT INTO informationcv (FName, LName, dob, Address, post_code, nationality, email, university, degree, skill1, skill2, skill3, experience, avatar) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
                    PreparedStatement statement = conn.prepareStatement(sql);
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
                    statement.setString(13, experienceField.getText());
                    statement.setBytes(14, imageData);
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Thông tin đã được lưu vào cơ sở dữ liệu!");
                        try {
                            Connection connGetID = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cvs", "root", "123456");
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
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton exportPDFButton = new JButton("Export PDF");
        add(exportPDFButton);
        exportPDFButton.setBounds(450, 450, 120, 30);
        exportPDFButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame frame = new JFrame("CV Template");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setLayout(new BorderLayout());

                    // Tạo một ImageIcon từ tệp hình ảnh
                    ImageIcon icon = new ImageIcon(getClass().getResource("/icons/template1.jpg"));


                    // Tạo một JLabel để hiển thị hình ảnh
                    JLabel label = new JLabel(icon);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);

                    // Tính toán kích thước mới cho hình ảnh
                    int desiredWidth = 1200;
                    int desiredHeight = (int) (((double) icon.getIconHeight() / icon.getIconWidth()) * desiredWidth);

                    // Thay đổi kích thước của hình ảnh
                    Image img = icon.getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(img);
                    label.setIcon(icon);

                    // Tạo một JLabel để hiển thị tên
                    JLabel nameLabel = new JLabel("Your Name");
                    nameLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Đặt font chữ và kích thước
                    nameLabel.setForeground(Color.WHITE); // Đặt màu chữ
                    nameLabel.setBounds(100, 100, 200, 30); // Đặt vị trí và kích thước
                    label.add(nameLabel); // Thêm JLabel vào JLabel chứa hình ảnh

// Tạo một JLabel để hiển thị tuổi
                    JLabel ageLabel = new JLabel("Your Brith Day");
                    ageLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Đặt font chữ và kích thước
                    ageLabel.setForeground(Color.WHITE); // Đặt màu chữ
                    ageLabel.setBounds(100, 150, 200, 30); // Đặt vị trí và kích thước
                    label.add(ageLabel); // Thêm JLabel vào JLabel chứa hình ảnh

                    JLabel avatarLabel = new JLabel();
                    avatarLabel.setHorizontalAlignment(JLabel.CENTER);
                    avatarLabel.setVerticalAlignment(JLabel.CENTER);
                    avatarLabel.setBounds(100, 200, 200, 200); // Đặt vị trí và kích thước cho avatar label
                    label.add(avatarLabel);

                    JLabel addressLabel = new JLabel("Address: ");
                    addressLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                    addressLabel.setForeground(Color.BLACK);
                    addressLabel.setBounds(300, 650, 400, 30);
                    label.add(addressLabel);

                    JLabel phoneLabel = new JLabel("Phone: ");
                    phoneLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                    phoneLabel.setForeground(Color.BLACK);
                    phoneLabel.setBounds(300, 605, 400, 30);
                    label.add(phoneLabel);

                    JLabel nationalityLabel = new JLabel("Nationality: ");
                    nationalityLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    nationalityLabel.setForeground(Color.BLACK);
                    nationalityLabel.setBounds(717, 450, 400, 30);
                    label.add(nationalityLabel);

                    JLabel emailLabel = new JLabel("Email: ");
                    emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                    emailLabel.setForeground(Color.BLACK);
                    emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    emailLabel.setBounds(0, 690, 400, 30);
                    label.add(emailLabel);

                    JLabel universityLabel = new JLabel("University: ");
                    universityLabel.setFont(new Font("Arial", Font.PLAIN, 25));
                    universityLabel.setForeground(Color.BLACK);
                    universityLabel.setBounds(680, 610, 400, 30);
                    label.add(universityLabel);

                    JLabel degreeLabel = new JLabel("Degree: ");
                    degreeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    degreeLabel.setForeground(Color.BLACK);
                    degreeLabel.setBounds(680, 650, 400, 30);
                    label.add(degreeLabel);

                    JLabel skill1Label = new JLabel("Skill 1: ");
                    skill1Label.setFont(new Font("Arial", Font.PLAIN, 15));
                    skill1Label.setForeground(Color.BLACK);
                    skill1Label.setHorizontalAlignment(SwingConstants.RIGHT);
                    skill1Label.setBounds(550, 500, 400, 30);
                    label.add(skill1Label);

                    JLabel skill2Label = new JLabel("Skill 2: ");
                    skill2Label.setFont(new Font("Arial", Font.PLAIN, 15));
                    skill2Label.setForeground(Color.BLACK);
                    skill2Label.setHorizontalAlignment(SwingConstants.RIGHT);
                    skill2Label.setBounds(550, 550, 400, 30);
                    label.add(skill2Label);

                    JLabel skill3Label = new JLabel("Skill 3: ");
                    skill3Label.setFont(new Font("Arial", Font.PLAIN, 15));
                    skill3Label.setForeground(Color.BLACK);
                    skill3Label.setHorizontalAlignment(SwingConstants.RIGHT);
                    skill3Label.setBounds(550, 600, 400, 30);
                    label.add(skill3Label);

                    JLabel experienceLabel = new JLabel("Experience: ");
                    experienceLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                    experienceLabel.setForeground(Color.BLACK);
                    experienceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    experienceLabel.setBounds(550, 650, 400, 30);
                    label.add(experienceLabel);

                    // Tạo JScrollPane để chứa JLabel và tạo thanh cuộn khi cần
                    JScrollPane scrollPane = new JScrollPane(label);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    // Thêm JScrollPane vào cửa sổ JFrame
                    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
                    JScrollPane scrollPane2= new JScrollPane(label);
                    scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // Đặt chế độ cuộn ngang
                    scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

// Thêm JScrollPane vào cửa sổ JFrame
                    frame.getContentPane().add(scrollPane2, BorderLayout.CENTER);
                    // Lấy kích thước màn hình
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int screenWidth = (int) screenSize.getWidth();
                    int screenHeight = (int) screenSize.getHeight();

                    // Cài đặt kích thước cho cửa sổ JFrame
                    frame.setSize(screenWidth, screenHeight);
                    frame.setResizable(false); // Không cho phép cửa sổ thu nhỏ hoặc phóng to
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    // Hiển thị cửa sổ JFrame
                    frame.setVisible(true);
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cvs", "root", "123456");
                        String sql = "SELECT * FROM informationcv WHERE id=?";
                        PreparedStatement statement = conn.prepareStatement(sql);
                        statement.setInt(1, userID); // Assuming 1 is the ID of the record you want to retrieve
                        ResultSet resultSet = statement.executeQuery();

                        if (resultSet.next()) {
                            String name = resultSet.getString("FName") + " " + resultSet.getString("LName");
                            String dob = resultSet.getDate("dob").toString();
                            String address = resultSet.getString("Address");
                            String postCode = resultSet.getString("post_code");
                            String nationality = resultSet.getString("nationality");
                            String email = resultSet.getString("email");
                            String university = resultSet.getString("university");
                            String degree = resultSet.getString("degree");
                            String skill1 = resultSet.getString("skill1");
                            String skill2 = resultSet.getString("skill2");
                            String skill3 = resultSet.getString("skill3");
                            String experience = resultSet.getString("experience");

                            // Update JLabels with retrieved data
                            // Update the JLabels with retrieved data
                            nameLabel.setText(name);
                            nameLabel.setFont(new Font("Arial", Font.BOLD, 60));
                            nameLabel.setForeground(Color.BLACK);
                            nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                            nameLabel.setBounds(650, 100, 400, 60);

                            ageLabel.setText("Date of Birth: " + dob);
                            ageLabel.setFont(new Font("Arial",Font.PLAIN,20));
                            ageLabel.setForeground(Color.BLACK);
                            ageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                            ageLabel.setBounds(550, 400, 400, 30);

                            addressLabel.setText(address);
                            phoneLabel.setText(postCode);
                            nationalityLabel.setText("Nation:"+nationality);
                            emailLabel.setText(email);
                            universityLabel.setText(university);
                            skill1Label.setText(skill1);
                            skill2Label.setText(skill2);
                            skill3Label.setText(skill3);
                            degreeLabel.setText(degree);
                            experienceLabel.setText(experience);

                            // Set avatar icon if available
                            byte[] avatarData = resultSet.getBytes("avatar");
                            if (avatarData != null && avatarData.length > 0) {
                                ImageIcon avatarIcon = new ImageIcon(avatarData);
                                if (avatarIcon != null) {
                                    Image scaledImage = avatarIcon.getImage().getScaledInstance(avatarLabel.getWidth() * 2+35, avatarLabel.getHeight() *2-15, Image.SCALE_SMOOTH);
                                    ImageIcon scaledAvatarIcon = new ImageIcon(scaledImage);
                                    avatarLabel.setIcon(scaledAvatarIcon);
                                    avatarLabel.setBounds(avatarLabel.getX() +111, avatarLabel.getY()-175, avatarLabel.getWidth() * 2, avatarLabel.getHeight() * 2);
                                } else {
                                    System.out.println("Failed to create ImageIcon from avatarData");
                                }
                            } else {
                                System.out.println("No avatar data found in ResultSet");
                            }
                            // Similarly, update other JLabels with their respective data
                        } else {
                            System.out.println("No data found for the specified ID");
                        }

                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } catch (HeadlessException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });

        JButton printCVButton = new JButton("Print CV");
        add(printCVButton);
        printCVButton.setBounds(600, 450, 120, 30);
        printCVButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    Printable printable = new Printable() {
                        @Override
                        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                            if (pageIndex > 0) {
                                return Printable.NO_SUCH_PAGE;
                            }
                            Graphics2D g2d = (Graphics2D) graphics;
                            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                            try {
                                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cvs", "root", "123456");
                                String sql = "SELECT * FROM informationcv WHERE id=?";
                                PreparedStatement statement = connection.prepareStatement(sql);
                                statement.setInt(1, 1);
                                ResultSet resultSet = statement.executeQuery();
                                if (resultSet.next()) {
                                    String fullName = resultSet.getString("FName") + " " + resultSet.getString("LName");
                                    String dateOfBirth = resultSet.getString(   "dob");
                                    String address = resultSet.getString("Address");
                                    String postCode = resultSet.getString("post_code");
                                    String nationality = resultSet.getString("nationality");
                                    String email = resultSet.getString("email");
                                    String university = resultSet.getString("university");
                                    String degree = resultSet.getString("degree");
                                    String skill1 = resultSet.getString("skill1");
                                    String skill2 = resultSet.getString("skill2");
                                    String skill3 = resultSet.getString("skill3");
                                    String experience = resultSet.getString("experience");
                                    double pageWidth = pageFormat.getImageableWidth();
                                    double pageHeight = pageFormat.getImageableHeight();
                                    Image background = Toolkit.getDefaultToolkit().getImage("icons/background.jpg"); // Đường dẫn đến hình ảnh mẫu
                                    g2d.drawImage(background, 0, 0, (int) pageWidth, (int) pageHeight, null); // Vẽ hình ảnh mẫu
                                    g2d.setColor(new Color(0, 0, 0));
                                    g2d.fillRect(0, 0, (int) pageWidth, (int) pageHeight);
                                    Font titleFont = new Font("Arial", Font.BOLD, 14);
                                    Font normalFont = new Font("Arial", Font.PLAIN, 12);
                                    g2d.setColor(Color.BLACK);
                                    g2d.setFont(new Font("Arial", Font.BOLD, 16));
                                    g2d.drawString("RESUME", 50, 30);
                                    g2d.setFont(titleFont);
                                    g2d.drawString("Personal Information", 50, 70);
                                    g2d.setFont(normalFont);
                                    g2d.drawString("Full Name: " + fullName, 50, 90);
                                    g2d.drawString("Date of Birth: " + dateOfBirth, 50, 110);
                                    g2d.drawString("Address: " + address, 50, 130);
                                    g2d.drawString("Post Code: " + postCode, 50, 150);
                                    g2d.drawString("Nationality: " + nationality, 50, 170);
                                    g2d.drawString("Email: " + email, 50, 190);
                                    g2d.setFont(titleFont);
                                    g2d.drawString("Qualifications", 50, 230);
                                    g2d.setFont(normalFont);
                                    g2d.drawString("University: " + university, 50, 250);
                                    g2d.drawString("Degree: " + degree, 50, 270);
                                    g2d.drawString("Skills:", 50, 290);
                                    g2d.drawString("- " + skill1, 50, 310);
                                    g2d.drawString("- " + skill2, 50, 330);
                                    g2d.drawString("- " + skill3, 50, 350);
                                    g2d.drawString("Experience:", 50, 290);
                                    if (imageData != null) {
                                        Image image = new ImageIcon(imageData).getImage();
                                        g2d.drawImage(image, 300, 70, 100, 100, null);
                                    }
                                }
                                resultSet.close();
                                statement.close();
                                connection.close();
                            } catch (SQLException ex) {
                                System.err.println("Error fetching data from database: " + ex.getMessage());
                            }
                            return Printable.PAGE_EXISTS;
                        }
                    };
                    PageFormat pageFormat = printerJob.defaultPage();
                    if (printerJob.printDialog()) {
                        printerJob.setPrintable(printable, pageFormat);
                        printerJob.print();
                    }
                } catch (PrinterException ex) {
                    System.err.println("Error printing CV: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        new AddInfo();
    }
}
