package com.example.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

/**
 * Created by Megurine Lucas on 07-04-2016.
 */
public class JSONParser {
    public static JSONArray parseJSON(String jsonString) {
        if(jsonString != null) {
            try {
                JSONArray jsonArray =  new JSONArray(jsonString);
                return jsonArray;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //Parse to ONE OBJECT only (because "parseJSON" cannot handle single obj parsing)
    public static JSONObject parseJSONto1Obj(String jsonString) {
        if(jsonString != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonString);
                return jsonObj;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
