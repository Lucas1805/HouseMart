package example.utils;

import android.os.AsyncTask;

import com.example.advertisements.Advertisement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Megurine Lucas on 07-04-2016.
 */
public class HttpUtil {
    public static String sendGetRequest(String url) {
        OkHttpClient client = new OkHttpClient();
        String result = "Empty";
        Request request = new Request.Builder().url(url).build();

        try {
            Response respone = client.newCall(request).execute();
            result = respone.body().string();
            JSONArray jsonArray =  new JSONArray(result);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e1) {
            System.out.println(e1.getStackTrace());

        }
        return result;
    }

    public static List<Advertisement> makeAdvertisementList(String jsonResult) {
        List<Advertisement> list = new LinkedList<>();
        try {
            JSONArray jsonArray = JSONParser.parseJSON(jsonResult);
            if(jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject tmp = jsonArray.getJSONObject(i);

                    //Create Advertisement object and add to list
                    String id = tmp.getString("id");
                    String ownerName = tmp.getString("ownerName");
                    String address = tmp.getString("address");
                    String district = tmp.getString("district");
                    String province = tmp.getString("city");
                    String phone = tmp.getString("phone");
                    String description = tmp.getString("description");
                    String area = tmp.getString("area");
                    String price = tmp.getString("price");
                    String type = tmp.getString("type");
                    String dateCreate= tmp.getString("dateCreate");
                    String dateUpdate= tmp.getString("dateUpdate");
                    String creator= tmp.getString("creator");
                    String updator= tmp.getString("updator");

                    Advertisement tmpAd = new Advertisement(address, area, creator, dateCreate
                            , dateUpdate, description, district, id, ownerName
                            , phone, price, province, type, updator);

                    list.add(tmpAd);
                }// End of for
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return HttpUtil.sendGetRequest(urls[0]);
        }
    }
}
