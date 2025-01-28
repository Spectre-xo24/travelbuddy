package Servlet;

import defunct.City;
import defunct.CityManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Darkness
 */
@WebServlet("/cityList")
public class CityListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // fetch city list
            CityManager cityManager = new CityManager();
            List<City> cityList = cityManager.getAllCities();

            // set request attribute
            request.setAttribute("cityList", cityList);

            // forward to jsp
            request.getRequestDispatcher("/eventList.jsp").forward(request, response);
        } catch (IOException | ServletException | SQLException e) {
            e.printStackTrace();
        }
    }
}
