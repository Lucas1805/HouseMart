package com.emxample.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.megurinelucas.housemart.R;

/**
 * Created by Megurine Lucas on 09-04-2016.
 */
public class AdvanceSearch_Fragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /**
         * Inflate the layout for this fragment
         */
        return inflater.inflate(R.layout.fragment_advancesearch, container, false);
    }
}
