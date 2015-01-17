package com.brianjmelton.foodtruckr;

import com.brianjmelton.foodtruckr.shared.list.RestaurantListDelegate;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by brianmelton on 1/16/15.
 */
public class RestaurantListFragment extends Fragment{

    protected RestaurantListDelegate mRestaurantListDelegate;

    public RestaurantListFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mRestaurantListDelegate = (RestaurantListDelegate) activity;
    }
}
