package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {

    }

    public void connectToDatabase() throws SQLException {
        String server = "ADMIN\\MSSQLSERVER01";
        String port = "1434";
        String database = "UserEmail";
        String userName = "kazeru280603";
        String password = "thanh8461";
connection = DriverManager.getConnection("jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database, userName, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
