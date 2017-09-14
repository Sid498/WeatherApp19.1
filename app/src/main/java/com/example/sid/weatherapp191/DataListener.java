package com.example.sid.weatherapp191;

import org.json.JSONException;

/**
 * Created by SID on 9/14/2017.
 */

public interface DataListener {

    void getData(String data) throws JSONException;
}
