package component;

import MenuApp.main.Main;
import SystemCVBuilder.Login;
import SystemCVBuilder.Sign;
import model.ModelUser;
import swing.Button;
import swing.MyPasswordField;
import swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    public ModelUser getUser() {
        return user;
    }

    private ModelUser user;

    public PanelLoginAndRegister(ActionListener eventRegister) {
        initComponents();
        initRegister(eventRegister);
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }


    private void initRegister(ActionListener eventRegister) {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        register.add(label);
        swing.MyTextField txtUser = new swing.MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/icons/user.png")));
        txtUser.setHint("Name");
        register.add(txtUser, "w 60%");
        swing.MyTextField txtEmail = new swing.MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icons/mail.png")));
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%");
        swing.MyPasswordField txtPass = new swing.MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icons/pass.png")));
        txtPass.setHint("Password");
        register.add(txtPass, "w 60%");
        swing.Button cmd = new swing.Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(eventRegister);
        cmd.setText("SIGN UP");
        register.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String userName = txtUser.getText().trim();
                String email = txtEmail.getText().trim();
                String password = String.valueOf(txtPass.getPassword());
                user = new model.ModelUser(0, userName, email, password);
            }
        });
    }
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
                loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
                loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
                registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
                registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents
    public void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);
        swing.MyTextField txtEmail = new swing.MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icons/mail.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");
        swing.MyPasswordField txtPass = new swing.MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icons/pass.png")));
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");
        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        swing.Button cmd = new swing.Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        login.add(cmd, "w 40%, h 40%");
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN UP");
        login.add(cmd, "w 40%, h 40%");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String email = txtEmail.getText().trim();
                String password = String.valueOf(txtPass.getPassword());

                // Kiểm tra thông tin trong cơ sở dữ liệu
                if (checkUserInDatabase(email, password)) {
                    // Mở Main() nếu thông tin đúng
                    new Main().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username, email, or password!");
                }
            }
        });
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    // Phương thức kiểm tra thông tin trong cơ sở dữ liệu
    private boolean checkUserInDatabase(String email, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Kết nối đến cơ sở dữ liệu của bạn
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cvdata", "root", "123456");

            // Truy vấn kiểm tra thông tin người dùng
            String query = "SELECT * FROM user WHERE Email=? AND Password=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            // Nếu có kết quả trả về, thông tin người dùng là đúng
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Đóng kết nối và các tài nguyên
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

// Phương thức mở Main()

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
// End of variables declaration//GEN-END:variables
}