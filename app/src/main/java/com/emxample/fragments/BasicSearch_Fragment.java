package com.emxample.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.megurinelucas.housemart.MainActivity;
import com.example.megurinelucas.housemart.R;

/**
 * Created by Megurine Lucas on 19-03-2016.
 */
public class BasicSearch_Fragment extends Fragment{
    private String searchValue;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /**
         * Inflate the layout for this fragment
         */
        return inflater.inflate(R.layout.fragment_basicsearch, container, false);
    }
}
