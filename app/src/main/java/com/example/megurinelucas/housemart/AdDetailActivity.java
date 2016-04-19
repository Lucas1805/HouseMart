package com.example.megurinelucas.housemart;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.configs.ConfigConstants;
import com.example.models.Advertisement;
import com.example.utils.HttpUtil;

import java.util.concurrent.ExecutionException;

/**
 * Activity to show Advertisement details
 *
 * @author LongVH
 */
public class AdDetailActivity extends AppCompatActivity {

    final String getAdURL = "http://" + ConfigConstants.ipAddress
            + ":" + ConfigConstants.port + "/api/posts/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_detail);

        if (isConnected()) {
            TextView id = (TextView) findViewById(R.id.adID);
            TextView title = (TextView) findViewById(R.id.adTitle);

            Intent intent=getIntent();
            Advertisement ad = getAdvertisement(intent.getStringExtra("adID"));

            id.setText(ad.getId());
            title.setText(ad.getTitle());
        }
        else {
            Toast.makeText(this, "No internet connection, " +
                    "please check again", Toast.LENGTH_LONG).show();
        }
    }

    public Advertisement getAdvertisement(String id) {
        HttpAsyncTask asyncTask = new HttpAsyncTask();
        try {
            String jsonResult = asyncTask.execute(getAdURL + id).get();
            return HttpUtil.getDetailedAdvertisement(jsonResult, false);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
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

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
}
