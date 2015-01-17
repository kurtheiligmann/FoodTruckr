package com.brianjmelton.foodtruckr;

import com.brianjmelton.foodtruckr.detail.RestaurantDetailFragment;
import com.brianjmelton.foodtruckr.list.RestaurantListFragment;
import com.brianjmelton.foodtruckr.shared.detail.RestaurantDetailDelegate;
import com.brianjmelton.foodtruckr.shared.detail.RestaurantDetailDelegateImpl;
import com.brianjmelton.foodtruckr.shared.detail.RestaurantDetailDispatch;
import com.brianjmelton.foodtruckr.shared.list.RestaurantListDelegate;
import com.brianjmelton.foodtruckr.shared.list.RestaurantListDelegateImpl;
import com.brianjmelton.foodtruckr.shared.list.RestaurantListDispatch;
import com.brianjmelton.foodtruckr.shared.vo.Calendar;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity implements RestaurantListFragment.Binder,
        RestaurantDetailDelegate, RestaurantListDispatch, RestaurantDetailFragment.Binder {

    protected RestaurantListDelegate mRestaurantListDelegate = new RestaurantListDelegateImpl(this);

    protected RestaurantDetailDelegate mRestaurantDetailDelegate;

    protected Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new RestaurantListFragment())
                    .commit();
        }
    }

    @Override
    public void onRestaurantClicked(long id) {
        mRestaurantListDelegate.onRestaurantClicked(id);
    }

    @Override
    public void setRestaurantDetailDispatch(RestaurantDetailDispatch dispatch) {
        mRestaurantDetailDelegate = new RestaurantDetailDelegateImpl(dispatch);
    }

    @Override
    public void onRestaurantDetailShown(long id) {
        mRestaurantDetailDelegate.onRestaurantDetailShown(id);
    }

    @Override
    public void onShowRestaurantDetail(long id) {
        // do a FragmentTransaction
        getFragmentManager().beginTransaction()
                .add(R.id.container, RestaurantDetailFragment.newInstance(id)).commit();
    }


    @Override
    public void setCalendar(Calendar calendar) {
        mCalendar = calendar;
    }

    @Override
    public Calendar getCalendar() {
        return mCalendar;
    }
}
