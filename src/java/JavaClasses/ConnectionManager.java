package JavaClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Darkness
 */
public class ConnectionManager {
    
    private static final String JDBC_URL = "jdbc:sqlite:E:/javaprojects/netbeans/TravelBuddyFinderApp/DB/imperatoris.db";
    
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }
    
    public static void closeConnection(Connection connection){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e){
            }
        }
    }
}
