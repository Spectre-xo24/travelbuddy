/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defunct;

import JavaClasses.CachePool;
import JavaClasses.UserAccount;
import JavaClasses.UserManager;
import PATH.RootConfig;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Darkness
 */
public class testServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    /*
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
            CachePool cachePool = new CachePool();
            cachePool.checkAndCreateUserFile();
            
            int firstEntry = cachePool.readFirstEntry();
            out.println("First entry: " + firstEntry);
            
            int userID = cachePool.readFirstEntry();
            String username = "testZone";
            String password = "testZone";
            String firstname = "testZone";
            String surname = "testZone";
            String email = "testZone";
        }
    }
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String filePath = RootConfig.getUserPath();
        
        String username = "usernametest";
        String password = "passwordtest";
        String firstname = "firstnametest";
        String surname = "surnametest";
        String email = "emailtest";
        
        try {
            
            
            CachePool cachePool = new CachePool();
            //read first entry from cache
            int userID = cachePool.readFirstEntry();
            //read user list
            List<UserAccount> userList = UserManager.readUsersFromJson();
            //create new user
            UserAccount newUser = new UserAccount(userID,username,password,firstname,surname,email);
            userList.add(newUser);
            //update file
            UserManager.writeUserToJson(userList);
            //delete cachePool first entry
            cachePool.deleteFirstEntry();
            //send response to client
            response.getWriter().write("registration successful");

        } catch (IOException e ) {
            e.printStackTrace();
            response.getWriter().write("Error during registration");
        }
        
    }

}
