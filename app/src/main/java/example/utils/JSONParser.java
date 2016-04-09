package example.utils;

import org.json.JSONArray;
import org.json.JSONException;

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
}
