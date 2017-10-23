package com.example.smagionas.redbustickets;




/**
 * Created by s.magionas on 9/10/2017.
 */

public class ObjectLog3 {

    public long dateAndTime;
    public String route;
    public String from;
    public String to;
    public String type;
    public double value;
    public String tabletUniqueId;
    public int tabletCounter;
    public int driverCounter;

    // constructor
    public ObjectLog3(long dateAndTime, String route, String from, String to, String type, double value, String tabletUniqueId, int tabletCounter, int driverCounter) {
        this.dateAndTime = dateAndTime;
        this.route = route;
        this.from = from;
        this.to = to;
        this.type = type;
        this.value = value;
        this.tabletUniqueId = tabletUniqueId;
        this.tabletCounter = tabletCounter;
        this.driverCounter = driverCounter;;
    }
}
