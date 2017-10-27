package com.example.smagionas.redbustickets;

import io.realm.RealmObject;

/**
 * Created by s.magionas on 25/10/2017.
 */

public class ConnectionInfo extends RealmObject {
    String ktel_server_url;
    String ktel_server_token;

    public ConnectionInfo(){


    }

    void set_ktel_server_url(String value){
        this.ktel_server_url = value;

    }

    void set_ktel_server_token(String value){
        this.ktel_server_token = value;

    }

    String get_ktel_server_url(){
        return ktel_server_url;

    }

    String get_ktel_server_token(){
        return ktel_server_token;

    }


}