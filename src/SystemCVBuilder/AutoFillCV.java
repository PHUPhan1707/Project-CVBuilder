package SystemCVBuilder;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.sql.*;

public class AutoFillCV {

    public void fillCV(int userId, JTextField tfFirstName, JTextField tfLastName, JDateChooser tfdob, JTextField address1Field, JTextField postCodeField, JTextField nationalityField, JTextField emailField, JTextField universityField, JTextField degreeField, JTextField skillsField1, JTextField skillsField2, JTextField skillsField3, JTextField experienceField, JLabel avatarLabel) {
        try {
            // Kết nối đến cơ sở dữ liệu
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cv_builder", "root", "password");

            // Truy vấn thông tin cá nhân từ cơ sở dữ liệu
            String sql = "SELECT * FROM personal_information WHERE UserID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, userId);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tfFirstName.setText(rs.getString("first_name"));
                tfLastName.setText(rs.getString("last_name"));
                tfdob.setDate(rs.getDate("date_of_birth"));
                address1Field.setText(rs.getString("address"));
                postCodeField.setText(rs.getString("phone"));
                nationalityField.setText(rs.getString("nationality"));
                emailField.setText(rs.getString("email"));
                universityField.setText(rs.getString("university"));
                degreeField.setText(rs.getString("degree"));
                skillsField1.setText(rs.getString("skill1"));
                skillsField2.setText(rs.getString("skill2"));
                skillsField3.setText(rs.getString("skill3"));
                experienceField.setText(rs.getString("experience"));

                // Lấy ảnh từ cơ sở dữ liệu
                byte[] imageBytes = rs.getBytes("avatar");
                if (imageBytes != null) {
                    ImageIcon icon = new ImageIcon(imageBytes);
                    Image image = icon.getImage().getScaledInstance(avatarLabel.getWidth(), avatarLabel.getHeight(), Image.SCALE_SMOOTH);
                    avatarLabel.setIcon(new ImageIcon(image));
                }
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
