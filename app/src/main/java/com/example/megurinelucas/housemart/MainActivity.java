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

import com.example.utils.HttpUtil;

import com.example.fragments.AdvanceSearch_Fragment;
import com.example.models.Advertisement;
import com.example.models.ProvinceList;
import com.example.configs.ConfigConstants;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    //EditText basicSearchValue = (EditText) findViewById(R.id.txt_BasicSearchValue);
    final String getAllURL = "http://" + ConfigConstants.ipAddress
            + ":" + ConfigConstants.port + "/api/posts";

    List<Advertisement> advertisementList = new LinkedList<>();
    List<Advertisement> searchResult = new LinkedList<>();

    //2 list nay dung de load du lieu vao spinner
    List<String> provinceList = new LinkedList<>();
    List<String> districtList = new LinkedList<>();

    ProvinceList provinceListDetail = new ProvinceList();

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
        txt_toPrice = (EditText) findViewById(R.id.txt_toPrice);
        txt_fromArea = (EditText) findViewById(R.id.txt_fromArea);
        txt_toArea = (EditText) findViewById(R.id.txt_toArea);

        //Load fragment
        loadFragment();

        //Hide advance search fragment
        expandSearch();

        if(isConnected()) {
            //Load all advertisement on server to list
            getAllAdvertisement();

            //Get province list from server and load to spinner
            getProvinceNameList();
            loadDataProvinceSpinner();

            //Load detail province list
            getProvinceListDetail();

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
                    districtList.clear();
                    String province = sp_province.getSelectedItem().toString();
                    districtList = provinceListDetail.getListOfDistrictName(province);
                    Collections.sort(districtList);

                    loadDataDistrictSpinner();
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
            String jsonResult = asyncTask.execute(getAllURL).get();
            this.advertisementList = HttpUtil.makeAdvertisementList(jsonResult);

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

            String district = "";
            if(sp_district.getSelectedItemPosition() != 0) {
                district = sp_district.getSelectedItem().toString();
            }
            String province = "";
            if(sp_province.getSelectedItemPosition() != 0) {
                province = sp_province.getSelectedItem().toString();
            }

            String fromPrice = txt_fromPrice.getText().toString();
            String toPrice = txt_toPrice.getText().toString();
            String fromArea = txt_fromArea.getText().toString();
            String toArea = txt_toArea.getText().toString();

            if(checkEmptyField(district,province,fromPrice,toPrice,fromArea,toArea) == false) {
                if(fromPrice.length() > 0) {
                    searchURL = searchURL + "minPrice=" + fromPrice + "&";
                }
                if(toPrice.length() > 0) {
                    searchURL = searchURL + "maxPrice=" + toPrice + "&";
                }
                if(fromArea.length() > 0) {
                    searchURL = searchURL + "minArea=" + fromArea + "&";
                }
                if(toArea.length() > 0) {
                    searchURL = searchURL + "maxArea=" + toArea + "&";
                }
                if(district.length() > 0) {

                    searchURL = searchURL + "districtID=" + provinceListDetail.getDistrictID(district) + "&";
                }
                if(province.length() > 0) {
                    searchURL = searchURL + "provinceID=" + provinceListDetail.getProvinceID(province) + "&";
                }

                searchURL = searchURL + "isDetailed=false";

                HttpAsyncTask asyncTask = new HttpAsyncTask();
                try {
                    searchResult.clear();
                    String jsonResult = asyncTask.execute(searchURL).get();
                    this.searchResult = HttpUtil.searchAdvertisement(jsonResult);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

        }
        else {
            Toast.makeText(this, "No internet connection, " +
                    "please check again", Toast.LENGTH_LONG).show();
        }
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

    public void getProvinceNameList() {
        String url = "http://" + ConfigConstants.ipAddress + ":"
                + ConfigConstants.port + "/api/provinces";

        HttpAsyncTask asyncTask = new HttpAsyncTask();
        try {
            String jsonResult = asyncTask.execute(url).get();
            this.provinceList = HttpUtil.getProvinceNameList(jsonResult);
            Collections.sort(provinceList);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void getProvinceListDetail() {
        String url = "http://" + ConfigConstants.ipAddress + ":"
                + ConfigConstants.port + "/api/districts";

        HttpAsyncTask asyncTask = new HttpAsyncTask();
        try {
            String jsonResult = asyncTask.execute(url).get();
            this.provinceListDetail = HttpUtil.getProvinceListDetail(jsonResult);
            Collections.sort(provinceList);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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

            //Add default value
            spinnerArray.add(getResources().getString(R.string.spinnerDefaultValue));

            if(provinceList.size() > 0) {
                for (int i = 0; i < provinceList.size(); i++) {
                    spinnerArray.add(provinceList.get(i));
                }
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_province.setAdapter(adapter);
        }
    }

    public void loadDataDistrictSpinner() {
        if(provinceList != null) {
            List<String> spinnerArray =  new LinkedList<>();

            //Add default value
            spinnerArray.add(getResources().getString(R.string.spinnerDefaultValue));

            if(districtList.size() > 0) {
                for (int i = 0; i < districtList.size(); i++) {
                    spinnerArray.add(districtList.get(i));
                }
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_district.setAdapter(adapter);
        }
    }

}
