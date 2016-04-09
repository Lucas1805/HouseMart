package com.example.megurinelucas.housemart;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import example.utils.HttpUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.example.advertisements.Advertisement;
import com.excample.configs.ConfigConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    //EditText basicSearchValue = (EditText) findViewById(R.id.txt_BasicSearchValue);
    final String getAllURL = "http://" + ConfigConstants.ipAddress
            + ":" + ConfigConstants.port + "/api/android";
    List<Advertisement> advertisementList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btn_BasicSearch = (ImageButton) findViewById(R.id.btn_BasicSearch);
        EditText txt_basicSearchValue = (EditText) findViewById(R.id.txt_BasicSearchValue);

        //Load all advertisement on server to list
        if(isConnected()) {
            getAllAdvertisement();
        }
        else {
            Toast.makeText(this, "No internet connection, " +
                    "please check again", Toast.LENGTH_LONG).show();
        }


    }

    public void getAllAdvertisement() {
        HttpAsyncTask asyncTask = new HttpAsyncTask();
        try {
            String tmp = asyncTask.execute(getAllURL).get();
            this. advertisementList = HttpUtil.makeAdvertisementList(tmp);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public void doBasicSearch(View v){
        //Check if phone is connect to internet before search
        if(isConnected()) {

        }
        else {
            Toast.makeText(this, "No internet connection, " +
                    "please check again", Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(this, "Basic search started: ", Toast.LENGTH_LONG).show();
    }


    public boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    /**
     * This method use to run sendGetRequest method, because cannot sen get request in same thread
     * with main class
     */
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return HttpUtil.sendGetRequest(urls[0]);
        }
    }


}
