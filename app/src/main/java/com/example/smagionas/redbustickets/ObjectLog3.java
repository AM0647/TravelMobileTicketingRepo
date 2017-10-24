package com.example.smagionas.redbustickets;



class ObjectLog3 {

//////////////////// START OF GLOBAL VARIABLES/////////////////////////

    long dateAndTime;
    String route;
    String from;
    String to;
    String type;
    double value;
    String tabletUniqueId;
    int tabletCounter;
    int driverCounter;
//////////////////// END OF GLOBAL VARIABLES///////////////////////////

    ObjectLog3(long dateAndTime, String route, String from, String to, String type, double value, String tabletUniqueId, int tabletCounter, int driverCounter) {
        this.dateAndTime = dateAndTime;
        this.route = route;
        this.from = from;
        this.to = to;
        this.type = type;
        this.value = value;
        this.tabletUniqueId = tabletUniqueId;
        this.tabletCounter = tabletCounter;
        this.driverCounter = driverCounter;
    }
}
