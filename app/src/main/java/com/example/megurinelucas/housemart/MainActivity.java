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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import example.utils.HttpUtil;

import com.emxample.fragments.AdvanceSearch_Fragment;
import com.emxample.fragments.BasicSearch_Fragment;
import com.example.advertisements.Advertisement;
import com.example.provinces.Province;
import com.example.provinces.ProvinceList;
import com.excample.configs.ConfigConstants;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    //EditText basicSearchValue = (EditText) findViewById(R.id.txt_BasicSearchValue);
    final String getAllURL = "http://" + ConfigConstants.ipAddress
            + ":" + ConfigConstants.port + "/api/posts";

    List<Advertisement> advertisementList = new LinkedList<>();
    List<Advertisement> searchResult = new LinkedList<>();
    ProvinceList provinceList = new ProvinceList();

    FragmentManager fm = null;
    Fragment advSearchFragment = null;

    //Declare element for search
    ImageButton btn_Search = null;
    ImageButton btn_expandSearch = null;

    EditText txt_fromPrice = null;
    EditText txt_toPrice = null;
    EditText txt_fromArea = null;
    EditText txt_toArea = null;
    Spinner sp_district = null;
    Spinner sp_province = null;

    // Use to store boolean for expand or hide advance search
    // willBeExpanded = false => Advance Search is showing, It WILL BE HIDE
    // willBeExpanded = true => Advance Search is hiding, It WILL BE EXPAND
    // Default value here = false to hide advance search for fist time load app
    boolean willBeExpanded = false;

    //Use to suspend setOnItemSelectedListener for province spinner when first time loading app
    boolean firstTimeLoading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Get fragment manager
        fm = getFragmentManager();

        //Get basic element(s) and basic search element(s)
        btn_Search = (ImageButton) findViewById(R.id.btn_Search);
        btn_expandSearch = (ImageButton) findViewById(R.id.btn_expandBasicSearch);

        //Get advance search element(s)
        sp_province = (Spinner) findViewById(R.id.sp_province);
        sp_district = (Spinner) findViewById(R.id.sp_district);
        txt_fromPrice = (EditText) findViewById(R.id.txt_fromPrice);
        txt_toPrice = (EditText) findViewById(R.id.txt_fromPrice);
        txt_fromArea = (EditText) findViewById(R.id.txt_fromArea);
        txt_toArea = (EditText) findViewById(R.id.txt_toArea);

        //Load fragment
        loadFragment();

        //Hide advance search fragment
        expandSearch();


        if(isConnected()) {
            //Load all advertisement on server to list
            getAllAdvertisement();


            //Get all province from server
            getProvinceList();

            //Load provinces to spinner
            loadDataProvinceSpinner();
        }
        else {
            Toast.makeText(this, "No internet connection, " +
                    "please check again", Toast.LENGTH_LONG).show();
        }

        /**
         * This listener use to load data to district spinner when user select a valid value in
         * province spinner
         */
        sp_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(sp_province.getSelectedItemPosition() == 0) {
                    //Do nothing
                }
                else {
                    System.out.println("SELECTED CHANGE");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
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


    public void doSearch(View v){
        //Check if phone is connect to internet before search
        if(isConnected()) {
            String searchURL = "http://" + ConfigConstants.ipAddress + ":"
                    + ConfigConstants.port + "/api/posts?";

            System.out.println("DO ADVANCE SEARCH");

            String fromPrice = txt_fromPrice.getText().toString();
            String toPrice = txt_toPrice.getText().toString();
            String fromArea = txt_fromArea.getText().toString();
            String toArea = txt_toArea.getText().toString();


        }
        else {
            Toast.makeText(this, "No internet connection, " +
                    "please check again", Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(this, "Basic search started: ", Toast.LENGTH_LONG).show();
    }

    /**
     * Ham nay duoc goi khi bam nut expand
     * @param v
     */
    public void expandSearch(View v) {
        expandSearch();
    }

    public void expandSearch() {
        FragmentTransaction transaction = fm.beginTransaction().add(R.id.advanceSearch_Fragment
                , new AdvanceSearch_Fragment(), "advSearchFragment");

        if(willBeExpanded == true) {
            fm.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .show(advSearchFragment)
                    .commit();
            btn_expandSearch.setImageResource(R.drawable.ic_expand_less_black_24dp);
            willBeExpanded = false;
        }
        else {
            fm.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .hide(advSearchFragment)
                    .commit();
            btn_expandSearch.setImageResource(R.drawable.ic_expand_more_black_24dp);
            willBeExpanded = true;
        }
    }

    public void hideAdvSearchFragment() {
        if(advSearchFragment != null) {
            FragmentTransaction transaction = fm.beginTransaction().add(R.id.advanceSearch_Fragment
                    , new AdvanceSearch_Fragment(), "advSearchFragment");
            fm.beginTransaction().hide(advSearchFragment).commit();
            willBeExpanded = true; //Just to make sure advance search is hide when first load app
        }
    }

    public void loadFragment() {
        advSearchFragment = (AdvanceSearch_Fragment) fm.findFragmentById(R.id.advanceSearch_Fragment);
    }

    public boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public void getProvinceList() {

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

    /**
     * Ham dung de check coi nguoi dung co nhap gi khong
     * @return
     */
    public boolean checkEmptyField(String district, String province, String fromPrice
            , String toPrice, String fromArea, String toArea) {
        if(district == null && province == null && fromPrice == null && toPrice == null
                && fromArea == null && toArea == null) {
            return true;
        }
        else {
            if(district.length() <= 0 && province.length() <= 0 && fromPrice.length() <= 0 && toPrice.length() <= 0
                    && fromArea.length() <= 0 && toArea.length() <= 0 )
                return true;
        }
        return false;
    }

    public void loadDataProvinceSpinner() {
        if(provinceList != null) {
            List<String> spinnerArray =  new LinkedList<>();
            //Province p = new Province("1","A","1","A-1");
            //Province p2 = new Province("1","A","2","A-2");
            spinnerArray.add("Province1");
            spinnerArray.add("Province2");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_province.setAdapter(adapter);
        }
    }

}
