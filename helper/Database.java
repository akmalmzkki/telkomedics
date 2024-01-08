package telkomedics.helper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3456/medicare", "root", "");
        return connection;
    }
}
