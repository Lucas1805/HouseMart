package com.example.megurinelucas.housemart;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

import com.emxample.fragments.AdvanceSearch_Fragment;
import com.emxample.fragments.BasicSearch_Fragment;
import com.example.advertisements.Advertisement;
import com.excample.configs.ConfigConstants;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    //EditText basicSearchValue = (EditText) findViewById(R.id.txt_BasicSearchValue);
    final String getAllURL = "http://" + ConfigConstants.ipAddress
            + ":" + ConfigConstants.port + "/api/posts";
    List<Advertisement> advertisementList = new LinkedList<>();

    FragmentManager fm = null;
    Fragment advSearchFragment = null;
    Fragment basicSearchFragment = null;

    boolean isExpand = false; // Use to store boolean for expand or hide advance search

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Get fragment manager
        fm = getFragmentManager();

        ImageButton btn_BasicSearch = (ImageButton) findViewById(R.id.btn_BasicSearch);
        EditText txt_basicSearchValue = (EditText) findViewById(R.id.txt_BasicSearchValue);
        ImageButton btn_expandSearch = (ImageButton) findViewById(R.id.btn_expandBasicSearch);

        //Load fragment
        loadFragment();
        //Hide advance search fragment
        expandSearch();

        if(isConnected()) {
            //Load all advertisement on server to list
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

    public void expandSearch(View v) {
        expandSearch();
    }

    public void expandSearch() {
        FragmentTransaction transaction = fm.beginTransaction().add(R.id.advanceSearch_Fragment
                , new AdvanceSearch_Fragment(), "advSearchFragment");

        if(isExpand == true) {
            fm.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .show(advSearchFragment)
                    .commit();
            isExpand = false;
        }
        else {
            fm.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .hide(advSearchFragment)
                    .commit();
            isExpand = true;
        }
    }

    public void hideAdvSearchFragment() {
        if(advSearchFragment != null) {
            FragmentTransaction transaction = fm.beginTransaction().add(R.id.advanceSearch_Fragment
                    , new AdvanceSearch_Fragment(), "advSearchFragment");
            fm.beginTransaction().hide(advSearchFragment).commit();
        }
    }

    public void loadFragment() {
        advSearchFragment = (AdvanceSearch_Fragment) fm.findFragmentById(R.id.advanceSearch_Fragment);
        basicSearchFragment = (BasicSearch_Fragment) fm.findFragmentById(R.id.basicSearch_Fragment);
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


    /**
     * Pint create date and update date of sorted advertisement list to console to check
     */
    public void printToCheckSortedAdList() {
        for(int i = 0; i < advertisementList.size(); i++) {
            System.out.println(advertisementList.get(i).getDateCreate() + " || " + advertisementList.get(i).getDateUpdate());
        }
    }

    /**
     * Chua may code dung de test, lam them ma chua xong v...v...
     */
    public void needToProcess() {
        //System.out.println(advertisementList.size());
        //String a = "2014-10-02T10:00:00.000Z";
        //String b = "2014-10-02T21:00:00.000Z";
        //System.out.println("SO SANH: " + a.compareToIgnoreCase(b));
    }
}
