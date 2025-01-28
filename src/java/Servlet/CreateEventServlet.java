/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import defunct.Event;
import defunct.EventManager;
import JavaClasses.UserAccount;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Darkness
 */
@WebServlet("/createEvent")
public class CreateEventServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        UserAccount user = (UserAccount) session.getAttribute("user");
        
        if (user != null) {
            String eventName = request.getParameter("eventName");
            String eventCity = request.getParameter("eventCity");
            String eventDate = request.getParameter("eventDate");
            //String eventCity = request.getParameter("eventTime");
            
            if (eventName != null && eventCity != null && eventDate != null) {
                Event event = new Event(eventName, eventCity, eventDate);
                EventManager eventManager = new EventManager();
                eventManager.createEvent(event);
                //redirect to event page
                response.sendRedirect("eventList.jsp?success=true");
            } else {
                response.sendRedirect("eventList.jsp?success=true");
            }
        } else {
            response.sendRedirect("login.html");
        }
        
    }

}
