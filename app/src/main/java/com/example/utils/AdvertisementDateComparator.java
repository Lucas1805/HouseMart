package com.example.utils;

import com.example.models.Advertisement;

import java.util.Comparator;

/**
 * Created by Megurine Lucas on 10-04-2016.
 *
 * NEU SO SANH 2 NGAY: GOI 2 NGAY LA  A VA B
 * A IS AFTER B COMPARETO TRA VE > 0
 * A IS BEFORE B TRA VE < 0
 * A = B TRA VE 0
 */
public class AdvertisementDateComparator implements Comparator<Advertisement> {

    /**
     * This comparator return descending (newest ad first) list of advertisement according to create date and update date
     * @param ad1
     * @param ad2
     * @return
     */
    @Override
    public int compare(Advertisement ad1, Advertisement ad2) {
        int compareResult = 0;
        compareResult = ad1.getDateCreate().compareTo(ad2.getDateCreate());
        if( compareResult == 0) {
            compareResult = ad1.getDateUpdate().compareTo(ad2.getDateUpdate());
            if(compareResult > 0)
                return -1; //any result < 0
            else
                return 2; //any result > 0
        }
        else {
            if(compareResult > 0)
                return -1; //any result < 0
            else
                return 2; //any result > 0
        }
    }
}
