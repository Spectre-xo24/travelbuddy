package Servlet;

import JavaClasses.CachePool;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import JavaClasses.UserAccount;
import JavaClasses.UserManager;
import static REST.PoolAddResource.addCacheUsedPools;
import java.util.List;

@WebServlet("/register")

public class RegisterServlet extends HttpServlet {
    
    //@Override
    //protected void doPost(HttpServletRequest request, HttpServletResponse response )
    //        throws ServletException, IOException {
        //processRequest(request,response);
        //return 0;
    //}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        
        try {
            addCacheUsedPools();
            CachePool cachePool = new CachePool();
            //read first entry from cache
            int userID = cachePool.readFirstEntry();
            //read user list
            //UserList userList = UserManager.readUsersFromJson(filePath);
            List<UserAccount> userList = UserManager.readUsersFromJson();
            //create new user
            UserAccount newUser = new UserAccount(userID,username,password,firstname,surname,email);
            //update file
            userList.add(newUser);

            //delete cachePool first entry
            cachePool.deleteFirstEntry();
            //write new user into Users
            UserManager.writeUserToJson(userList);
            //send response to client
            
            response.getWriter().write("registration successful");
        } catch (IOException e ) {
            response.getWriter().write("Error during registration");
        }
        
    }
    
}
