/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defunct;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darkness
 */
public class EventManager {
    private List<Event> events = new ArrayList<>();
    
    public void createEvent(Event event) {
        //save event
        events.add(event);
    }
}
