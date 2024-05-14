package CVHandle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cvs_incre";
    private static final String USER = "root";
    private static final String PASSWORD = "Minhtri17052004@";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
