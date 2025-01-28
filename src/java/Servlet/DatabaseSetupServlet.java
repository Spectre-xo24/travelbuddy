package Servlet;

import defunct.CityManager;
import JavaClasses.ConnectionManager;
import PATH.RootConfig;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Darkness
 */
public class DatabaseSetupServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        super.init();
        //Load SQLite JDBC
        try{
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            initialiseDatabase();
            out.println("Database setup complete");
        } catch (IOException e){
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseSetupServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initialiseDatabase() throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
                Statement statement = connection.createStatement()) {
            //create user table
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
                    +"id INTEGER PRIMARY KEY AUTOINCREMENT," 
                    +"userID INTEGER NOT NULL," 
                    +"username TEXT NOT NULL UNIQUE," 
                    +"password TEXT NOT NULL)");
            
            //events table
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS events ("
                    +"eventID INTEGER PRIMARY KEY AUTOINCREMENT," 
                    +"eventName TEXT NOT NULL," 
                    +"eventCity TEXT," 
                    +"eventDate TEXT," 
                    +"eventTime TEXT)");
            
            //userevents for many-to-many
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user_events (" 
                    +"id INTEGER PRIMARY KEY AUTOINCREMENT," 
                    +"userID INTEGER NOT NULL," 
                    +"eventID INTEGER NOT NULL," 
                    +"FOREIGN KEY (userID) REFERENCES users(id)," 
                    +"FOREIGN KEY (eventID) REFERENCES events(eventID))");
            
            //cities 
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS cities (" 
                    +"cityid INTEGER PRIMARY KEY AUTOINCREMENT," 
                    +"cityname TEXT NOT NULL," 
                    +"lat DOUBLE NOT NULL," 
                    +"lng DOUBLE NOT NULL)"); 
            
            //add default cities
            CityManager cityManager = new CityManager();
            cityManager.addDefaultCities();
        }
    }

}
