package ServiceClass;

import JavaClasses.EventJson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/events")
public class EventService {

    private static List<EventJson> events = new ArrayList<>();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EventJson> getEvents() {
        return events;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addEvent(EventJson event) {
        events.add(event);
    }
    
}
