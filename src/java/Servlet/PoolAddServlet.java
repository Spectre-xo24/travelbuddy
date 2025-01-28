package Servlet;

import static REST.PoolAddResource.addCacheUsedPools;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Darkness
 */
public class PoolAddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            //code checks pools, then adds new into cache.
            addCacheUsedPools();
            response.getWriter().println("Servlet executed successfully!");
        } catch (IOException e) {
            //response.getWriter().println("Error executing servlet: " + e.getMessage());
            e.printStackTrace();
        }
    }
}