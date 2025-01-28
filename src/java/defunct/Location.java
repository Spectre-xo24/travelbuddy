package defunct;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private int locationID;
    private String locationName;
    private double lat;
    private double lng;
    
    public Location(int locationID, String locationName, double lat, double lng) {
        this.locationID = locationID;
        this.locationName = locationName;
        this.lat = lat;
        this.lng = lng;
    }

    //gets
    int getLocationID(){
        return locationID;
    }
    String getLocationName() {
        return locationName;
    }
    double getLat() {
        return lat;
    }
    double getLng() {
        return lng;
    }
    
    //sets
    public void setCityID(int locationID) {
        this.locationID = locationID;
    }
    public void setCityName(String locationName) {
        this.locationName = locationName;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
    
    public List<Location>getlocation(){
        List<Location> locations = new ArrayList<>();
        locations.add(new Location(1,"London",51.3,-0.1));
        locations.add(new Location(2,"Birmingham",52.4,-1.9));
        return locations;
    }
    
}
