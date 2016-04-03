package com.example.megurinelucas.housemart;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.emxample.fragments.BasicSearch_Fragment;
import com.excample.configs.ConfigConstants;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    //EditText basicSearchValue = (EditText) findViewById(R.id.txt_BasicSearchValue);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btn_BasicSearch = (ImageButton) findViewById(R.id.btn_BasicSearch);
        EditText txt_basicSearchValue = (EditText) findViewById(R.id.txt_BasicSearchValue);


    }


    public void doBasicSearch(View v){
        final String searchAllUrl = "http://" + ConfigConstants.ipAddress
                + ":" + ConfigConstants.port + "/api/android";

        //Check if phone is connect to internet before search
        if(isConnected()) {
            new HttpAsyncTask().execute(searchAllUrl);
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

    public String sendGetRequest(String url) {
        OkHttpClient client = new OkHttpClient();
        String result = "Empty";
        Request request = new Request.Builder().url(url).build();

        try {
            Response respone = client.newCall(request).execute();
            result = respone.body().string();
            System.out.println("RESULT BODY" + result);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return sendGetRequest(urls[0]);
        }
    }
}
