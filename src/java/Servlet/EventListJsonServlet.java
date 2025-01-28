import JavaClasses.EventJson;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EventListJsonServlet", urlPatterns = {"/eventListJson"})
public class EventListJsonServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // PrintWriter does not need to be closed explicitly, so no need for try-with-resources
        PrintWriter out = response.getWriter();

        // Simulate a JSON string (replace this with your actual JSON data)
        String jsonString = "{\"eventID\":123, \"eventName\":\"test Event\",\"eventCity\":\"London\",\"lat\":\"51.5072\",\"lng\":\"-0.1275\",\"subscribed\":1}";

        // Use Gson to deserialize JSON to EventJson object
        Gson gson = new Gson();
        EventJson event = gson.fromJson(new StringReader(jsonString), EventJson.class);

        // Display the processed JSON in the browser
        out.println("<html>");
        out.println("<head>");
        out.println("<title>JSON Viewer</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>JSON Viewer</h2>");
        out.println("<div>");
        out.println("<p>" + event.toString() + "</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        // No need to close PrintWriter, as it will be closed when the response is finished
    }

    // Handle GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Handle POST requests
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}