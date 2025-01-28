package Servlet;

import REST.RandomNoResource;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Darkness
 */
public class RandomNoServlet extends HttpServlet {
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RandomNoResource.performNumberRequestAndSave();
            response.getWriter().println("Servlet executed successfull!");
        } catch (IOException e) {
            response.getWriter().println("Error executing servlet: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
