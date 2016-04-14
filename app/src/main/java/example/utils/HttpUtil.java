package example.utils;

import android.os.AsyncTask;

import com.example.advertisements.Advertisement;
import com.example.provinces.Province;
import com.example.provinces.ProvinceList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

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
                    try {
                        JSONObject tmp = jsonArray.getJSONObject(i);

                        //Create Advertisement object and add to list
                        String id = tmp.getString("postID");
                        String title = tmp.getString("title");
                        String address = tmp.getString("address");
                        String districtID = tmp.getString("districtID");
                        String districtName = tmp.getString("districtName");
                        String provinceID = tmp.getString("provinceID");
                        String provinceName = tmp.getString("provinceName");
                        //String phone = tmp.getString("phone");
                        //String description = tmp.getString("description");
                        String area = tmp.getString("area");
                        String price = tmp.getString("price");
                        String type = tmp.getString("type");
                        //String latitude = tmp.getString("latitude");
                        //String longtitude = tmp.getString("longtitude");
                        String image1 = tmp.getString("image1");
                        String image2 = tmp.getString("image2");
                        String image3 = tmp.getString("image3");

                        //Xu ly ngay Create

                        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        Date dateObj = formater.parse(tmp.getString("dateCreate"));
                        formater.setTimeZone(TimeZone.getTimeZone("UTC"));   // This line converts the given date into UTC time zone
                        String aRevisedDate = new SimpleDateFormat("dd/MM/yyyy KK:mm:ss a").format(dateObj);

                        Date dateCreate = new SimpleDateFormat("dd/MM/yyyy KK:mm:ss a").parse(aRevisedDate);
                        //System.out.println(dateCreate.getDate()+dateCreate.getMonth()+dateCreate.getYear());
                        //System.out.println(dateCreate.toString());

                        //Xu ly Date Update
                        dateObj = formater.parse(tmp.getString("dateUpdate"));
                        formater.setTimeZone(TimeZone.getTimeZone("UTC"));   // This line converts the given date into UTC time zone
                        aRevisedDate = new SimpleDateFormat("dd/MM/yyyy KK:mm:ss a").format(dateObj);
                        Date dateUpdate = new SimpleDateFormat("dd/MM/yyyy KK:mm:ss a").parse(aRevisedDate);

                        //Date dateUpdate = new SimpleDateFormat("dd/MM/yyyy KK:mm:ss a").parse(aRevisedDate);

                        Advertisement tmpAd = new Advertisement(address, area, dateCreate
                                , dateUpdate, districtID, districtName, id, title
                                , price, provinceID, provinceName, type);

                        list.add(tmpAd);
                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }
                }// End of for
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        //Sort newest advertisement first before return
        if(list.size() > 0) {
            Collections.sort(list,new AdvertisementDateComparator());
        }
        return list;
    }

    public static List<Advertisement> searchAdvertisement(String jsonResult) {
        List<Advertisement> list;
        list = makeAdvertisementList(jsonResult);
        return list;
    }

    public static Advertisement getDetailedAdvertisement(String jsonResult, boolean isDetail) {
        Advertisement result = null;
        JSONArray jsonArray = JSONParser.parseJSON(jsonResult);
        try {
            JSONObject tmp = jsonArray.getJSONObject(0);

            //Create Advertisement object and add to list
            String id = tmp.getString("postID");
            String ownerName = tmp.getString("ownerName");
            String title = tmp.getString("title");
            String address = tmp.getString("address");
            String districtID = tmp.getString("districtID");
            String districtName = tmp.getString("districtName");
            String provinceID = tmp.getString("provinceID");
            String provinceName = tmp.getString("provinceName");
            String phone = tmp.getString("phone");
            String description = tmp.getString("description");
            String area = tmp.getString("area");
            String price = tmp.getString("price");
            String type = tmp.getString("type");
            //String latitude = tmp.getString("latitude");
            //String longtitude = tmp.getString("longtitude");
            String image1 = tmp.getString("image1");
            String image2 = tmp.getString("image2");
            String image3 = tmp.getString("image3");

            //Xu ly ngay Create

            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date dateObj = formater.parse(tmp.getString("dateCreate"));
            formater.setTimeZone(TimeZone.getTimeZone("UTC"));   // This line converts the given date into UTC time zone
            String aRevisedDate = new SimpleDateFormat("dd/MM/yyyy KK:mm:ss a").format(dateObj);

            Date dateCreate = new SimpleDateFormat("dd/MM/yyyy KK:mm:ss a").parse(aRevisedDate);

            //Xu ly Date Update
            dateObj = formater.parse(tmp.getString("dateUpdate"));
            formater.setTimeZone(TimeZone.getTimeZone("UTC"));   // This line converts the given date into UTC time zone
            aRevisedDate = new SimpleDateFormat("dd/MM/yyyy KK:mm:ss a").format(dateObj);
            Date dateUpdate = new SimpleDateFormat("dd/MM/yyyy KK:mm:ss a").parse(aRevisedDate);

            //With detail flag is ON
            if(isDetail) {
                String creatorID = tmp.getString("creatorID");
                String creatorName = tmp.getString("creatorName");
                String updatorID = tmp.getString("updatorID");
                String updatorName = tmp.getString("updatorName");

                result = new Advertisement(id, ownerName, title, address, districtID
                        , districtName, provinceID, provinceName, phone, description
                        , area, price, type, image1, image2, image3, dateCreate, dateUpdate
                        , creatorID, creatorName, updatorID, updatorName);


            }
            //With detail flag is OFF
            else {
                result = new Advertisement(id, ownerName, title, address, districtID
                        , districtName, provinceID, provinceName, phone, description
                        , area, price, type, image1, image2, image3, dateCreate, dateUpdate);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<String> getProvinceNameList (String jsonResult) {
        List<String> tmpList = new LinkedList<>();
        try {
            JSONArray jsonArray = JSONParser.parseJSON(jsonResult);
            if(jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject tmp = jsonArray.getJSONObject(i);
                    tmpList.add(tmp.getString("provinceName"));
                }// End of for
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return tmpList;
    }

    public static ProvinceList getProvinceListDetail (String jsonResult) {
        List<Province> tmpList = new LinkedList<>();
        ProvinceList result = null;
        try {
            JSONArray jsonArray = JSONParser.parseJSON(jsonResult);
            if(jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject tmp = jsonArray.getJSONObject(i);

                    //Create province with detail object and add to list
                    String provinceID = tmp.getString("provinceID");
                    String provinceName = tmp.getString("provinceName");
                    String districtID = tmp.getString("districtID");
                    String districtName = tmp.getString("districtName");

                    Province p = new Province(districtID,districtName,provinceID,provinceName);

                    tmpList.add(p);
                }// End of for
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        if(tmpList.size() > 0) {
            result = new ProvinceList(tmpList);
        }
        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return HttpUtil.sendGetRequest(urls[0]);
        }
    }
}
