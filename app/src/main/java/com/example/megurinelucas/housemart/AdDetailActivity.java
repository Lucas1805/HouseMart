package com.example.megurinelucas.housemart;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.ImageSliderAdapter;
import com.example.adapter.ListAdvertisementAdapter;
import com.example.configs.ConfigConstants;
import com.example.models.Advertisement;
import com.example.utils.HttpUtil;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Activity to show Advertisement details
 *
 * @author LongVH
 */
public class AdDetailActivity extends AppCompatActivity {

    final String getAdURL = "http://" + ConfigConstants.ipAddress
            + ":" + ConfigConstants.port + "/api/posts/";
    private List<String> imgUrls = new ArrayList<>();

    TextView title;
    TextView address;
    TextView price;
    TextView last_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_detail);

        if (isConnected()) {
            //TextView id = (TextView) findViewById(R.id.adID);

            Intent intent = getIntent();
            Advertisement ad = getAdvertisement(intent.getStringExtra("adID"));

            getSupportActionBar().setTitle(ad.getTitle());

            //id.setText(ad.getId());

            if (!ad.getImage1().equalsIgnoreCase("null")) {
                imgUrls.add("http://"+ConfigConstants.ipAddress + ":" + ConfigConstants.port + ad.getImage1());
            }
            if(!ad.getImage2().equalsIgnoreCase("null")){
                imgUrls.add("http://"+ConfigConstants.ipAddress + ":" + ConfigConstants.port + ad.getImage2());
            }
            if(!ad.getImage3().equalsIgnoreCase("null")){
                imgUrls.add("http://"+ConfigConstants.ipAddress + ":" + ConfigConstants.port + ad.getImage3());
            }

            if (imgUrls.size() == 0) {
                imgUrls.add(ConfigConstants.DEFAULT_IMAGE_URL);
            }

            ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            viewPager.setAdapter(new ImageSliderAdapter(this, imgUrls));
            PageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
            indicator.setViewPager(viewPager);
            ((CirclePageIndicator) indicator).setSnap(true);

            title=(TextView) findViewById(R.id.adTitle);
            title.setText(ad.getTitle());
            address=(TextView) findViewById(R.id.adAddress);
            address.setText(ListAdvertisementAdapter.formatAddress(ad));
            price=(TextView) findViewById(R.id.adPrice);
            price.setText(ListAdvertisementAdapter.formatPrice(ad.getPrice()));
            last_update=(TextView) findViewById(R.id.adLastUpdate);
            last_update.setText("Last Updated: "+ListAdvertisementAdapter.formatDate(ad.getDateUpdate()));

        } else {
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
