package SystemCVBuilder;
import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class AddInfo extends JFrame{

    AddInfo(){
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Add Personal Details");
        heading.setFont(new Font("SAN_SERIF", Font.PLAIN, 25)); // Giảm kích thước chữ của tiêu đề
        add(heading);
        heading.setBounds(200, 20, 400, 30);

        // Biến để xác định vị trí và kích thước cho các thành phần
        int yPosition = 80; // Điều chỉnh khoảng cách giữa các thành phần
        int labelWidth = 100;
        int labelHeight = 30;
        int textFieldWidth = 200;
        int textFieldXPosition = 150;
        int gap = 40; // Điều chỉnh khoảng cách giữa các dòng

        JLabel labename = new JLabel("Name:");
        labename.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(labename);
        labename.setBounds(50, yPosition, labelWidth, labelHeight);

        JTextField tfname = new JTextField();
        add(tfname);
        tfname.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);
        tfname.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        yPosition += gap;

        JLabel labedob = new JLabel("Date of birth:");
        labedob.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(labedob);
        labedob.setBounds(50, yPosition, labelWidth, labelHeight);

        JDateChooser tfdob = new JDateChooser();
        add(tfdob);
        tfdob.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);

        yPosition += gap;

        JLabel address1Label = new JLabel("Address 1:");
        address1Label.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(address1Label);
        address1Label.setBounds(50, yPosition, labelWidth, labelHeight);

        JTextField address1Field = new JTextField();
        add(address1Field);
        address1Field.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);
        address1Field.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        yPosition += gap;

        JLabel address2Label = new JLabel("Address 2:");
        address2Label.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(address2Label);
        address2Label.setBounds(50, yPosition, labelWidth, labelHeight);

        JTextField address2Field = new JTextField();
        add(address2Field);
        address2Field.setBounds(textFieldXPosition, yPosition, textFieldWidth, labelHeight);
        address2Field.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

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

        yPosition += gap;

        // Thêm phần kỹ năng

        JTextField skillsField = new JTextField(); // Skill3 1
        add(skillsField);
        skillsField.setBounds(450, 80, textFieldWidth, labelHeight);
        skillsField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        yPosition = 120; // Reset vị trí cho các phần tiếp theo

        // Thêm phần Degree
        JLabel degreeLabel = new JLabel("Degree:");
        degreeLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(degreeLabel);
        degreeLabel.setBounds(350, yPosition, labelWidth, labelHeight);

        JTextField degreeField = new JTextField();
        add(degreeField);
        degreeField.setBounds(450, yPosition, textFieldWidth, labelHeight);
        degreeField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        yPosition += gap;

        // Thêm phần University
        JLabel universityLabel = new JLabel("University:");
        universityLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(universityLabel);
        universityLabel.setBounds(350, yPosition, labelWidth, labelHeight);
        universityLabel.setBounds(350, 80, labelWidth, labelHeight);

        JTextField universityField = new JTextField();
        add(universityField);
        universityField.setBounds(450, yPosition, textFieldWidth, labelHeight);
        universityField.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        yPosition += gap;

        // Thêm phần kỹ năng tiếp theo
        JLabel skillsLabel2 = new JLabel("Skill 2:");
        skillsLabel2.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(skillsLabel2);
        skillsLabel2.setBounds(350, yPosition, labelWidth, labelHeight);

        JTextField skillsField2 = new JTextField(); // Skill 2
        add(skillsField2);
        skillsField2.setBounds(450, yPosition, textFieldWidth, labelHeight);
        skillsField2.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        yPosition += gap;

        // Thêm phần kỹ năng tiếp theo
        JLabel skillsLabel3 = new JLabel("Skill 3:");
        skillsLabel3.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(skillsLabel3);
        skillsLabel3.setBounds(350, yPosition, labelWidth, labelHeight);

        JTextField skillsField3 = new JTextField(); // Skill 3
        add(skillsField3);
        skillsField3.setBounds(450, yPosition, textFieldWidth, labelHeight);
        skillsField3.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        yPosition += gap;

        // Thêm phần kỹ năng cuối cùng
        JLabel skillsLabel4 = new JLabel("Skill 4:");
        skillsLabel4.setFont(new Font("SAN_SERIF", Font.PLAIN, 15)); // Giảm kích thước chữ của nhãn
        add(skillsLabel4);
        skillsLabel4.setBounds(350, yPosition, labelWidth, labelHeight);

        JTextField skillsField4 = new JTextField(); // Skill 4
        add(skillsField4);
        skillsField4.setBounds(450, yPosition, textFieldWidth, labelHeight);
        skillsField4.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); // Giảm kích thước chữ của trường văn bản

        JButton saveButton = new JButton("Save");
        add(saveButton);
        saveButton.setBounds(300, 450, 100, 30);

        setSize(900, 600);
        setLocation(300, 50);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddInfo();
    }
}
