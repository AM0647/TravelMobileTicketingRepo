package com.example.smagionas.redbustickets;

import io.realm.RealmObject;

/**
 * Created by s.magionas on 25/10/2017.
 */

public class GUID extends RealmObject {
    String unique_id_code;

    public  GUID(){


    }

    void setUnique_id_code(String value){
        this.unique_id_code = value;

    }

    String getUnique_id_code(){
        return unique_id_code;

    }


}