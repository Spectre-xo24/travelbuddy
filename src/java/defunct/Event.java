/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defunct;

/**
 *
 * @author Darkness
 */
public class Event {
    private int id;
    private String name;
    private String city;
    private String date;
    //private String time;
    
    //constructor
    public Event (String name, String city, String date) {
        //this.id = id;
        this.name = name;
        this.city = city;
        this.date = date;
    }

   
    //get
    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public String getDate() {
        return date;
    }
    
    //sets
    public void setID(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
