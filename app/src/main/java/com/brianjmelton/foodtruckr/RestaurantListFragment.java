package com.brianjmelton.foodtruckr;

import com.brianjmelton.foodtruckr.io.Calendar;
import com.brianjmelton.foodtruckr.io.RestAdapter;
import com.brianjmelton.foodtruckr.shared.list.RestaurantListDelegate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by brianmelton on 1/16/15.
 */
public class RestaurantListFragment extends Fragment {

    private static final Logger log = LoggerFactory.getLogger(RestaurantListFragment.class);

    protected RestaurantListDelegate mRestaurantListDelegate;

    public RestaurantListFragment() {
    }

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

    @Override
    public void onResume() {
        super.onResume();
        // Test slurping
        retrofit.RestAdapter restAdapter = RestAdapter.getInstance();
        Calendar calendar = restAdapter.create(Calendar.class);
        calendar.getCalendar(new Callback<com.brianjmelton.foodtruckr.shared.vo.Calendar>() {
            @Override
            public void success(com.brianjmelton.foodtruckr.shared.vo.Calendar calendar,
                    Response response) {
                log.debug(calendar.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                log.error(error.toString());
            }
        });
    }
}
