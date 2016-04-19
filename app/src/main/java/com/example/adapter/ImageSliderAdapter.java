package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.megurinelucas.housemart.AppController;
import com.example.megurinelucas.housemart.R;
import com.viewpagerindicator.IconPagerAdapter;

import java.util.List;

/**
 * Created by LENOVO on 04/17/2016.
 */
public class ImageSliderAdapter extends PagerAdapter {

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    private Activity _activity;
    private List<String> _imagePaths;
    private LayoutInflater inflater;

    public ImageSliderAdapter(Activity activity, List<String> imagePaths) {
        _activity = activity;
        _imagePaths = imagePaths;
        if(_imagePaths.size()==0){
            _imagePaths.add(null);
        }
    }

    @Override
    public int getCount() {
        return _imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.image_in_slider, container, false);

        NetworkImageView imgDisplay = (NetworkImageView) viewLayout.findViewById(R.id.imgDisplay);
        imgDisplay.setDefaultImageResId(R.drawable.home_icon);
        imgDisplay.setImageUrl(_imagePaths.get(position), imageLoader);
        ((ViewPager) container).addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    /*@Override
    public int getIconResId(int index) {
        return R.drawable.holo_circle;
    }*/
}
