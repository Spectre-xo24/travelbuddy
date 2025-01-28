package defunct;
/**
 *
 * @author Darkness
 */
public class City {
    private int cityID;
    private String cityName;
    private double lat;
    private double lng;
    
    public City(int cityID, String cityName, double lat, double lng) {
        this.cityID = cityID;
        this.cityName = cityName;
        this.lat = lat;
        this.lng = lng;
    }
    //cons without ID
    public City(String cityName, double lat, double lng){
        //should auto gen ID
        this.cityName = cityName;
        this.lat = lat;
        this.lng = lng;
    }

    //gets
    int getCityID(){
        return cityID;
    }
    String getCityName() {
        return cityName;
    }
    double getLat() {
        return lat;
    }
    double getLng() {
        return lng;
    }
    
    //sets
    public void setCityID(int cityID) {
        this.cityID = cityID;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
    
}
