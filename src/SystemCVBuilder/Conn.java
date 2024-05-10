package SystemCVBuilder;

import java.sql.*;
public class Conn {

        public static final String Conn = null;
        public static final String onn = null;
        Connection c;
        Statement s;

        public Conn(){//relationship with all class to database


            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cvdatasystem", "root", "123456");
                s = c.createStatement();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return c.prepareStatement(sql);
    }
    }





