<%-- 
    Document   : eventlist
    Created on : 22 Jan 2024, 00:05:24
    Author     : Darkness
--%>

<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- jQuery for date picker-->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- Your HTML content here -->
        <div style="float:left;width:50%;">
            <h2>Available events</h2>
            <c:choose>
                <c:when test="${empty events}">
                    <p>No events found</p>
                </c:when>
                <c:otherwise>
                    <form action="joinEvent" method="post">
                        <c:forEach var="event" items="${events}">
                            <input type="hidden" name="eventID" value="${event.eventID}">
                            <label>${event.eventName},${event.eventCity},${event.eventDate}</label>
                            <input type="submit" value="Join">
                        </c:forEach>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
        <div style="float:left; width:50%;">
            <p>Create event</p>
            <form action="createEvent" method="post">
                <label for="eventName">Event Name:</label>
                <input type="text" id="eventName" name="eventName" required>
                <br>
                <label for="eventDate">Event Date:</label>
                <input type="text" id="eventDate" name="eventDate" class="datepicker" required>
                <br>
                <label for="eventCity">Event City:</label>
                <!-- populate with city locations -->
                <c:forEach var="cityName" items="${cityNames}">
                    <input type="radio" id="city_${cityName}" name="eventCity" value="${cityName}">
                    <label for="city_${cityName}">${cityName}</label>
                </c:forEach>
                <br>
                <input type="submit" value="Create Event">
            </form>
            <!-- script run for date picker -->
            <script>
                $(function(){
                    $("#eventDate").datepicker();
                });
            </script>
        </div>

        <!-- The rest of your HTML content -->
        
    </body>
</html>
