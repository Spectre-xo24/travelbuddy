package JavaClasses;

/**
 *
 * @author Darkness
 */
public class EventJson {

    private int eventID;
    private String eventName;
    private String eventCity;
    private double lat;
    private double lng;
    private int subscribed;
    
    //constructor
    public EventJson(int eventID, 
            String eventName, 
            String eventCity, 
            double lat, 
            double lng, 
            int subscribed) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventCity = eventCity;
        this.lat = lat;
        this.lng = lng;
        this.subscribed = subscribed;
    }
    
    @Override 
    public String toString() {
        return "Event{" +
                "eventID=" + eventID +
                ", eventName" + eventName +
                ", eventCity" + eventCity +
                ", lat" + lat + 
                ", lng" + lng +
                ", subscribed" + subscribed +
                "}";
    }

}
