package com.brianjmelton.foodtruckr;

import com.brianjmelton.foodtruckr.detail.RestaurantDetailFragment;
import com.brianjmelton.foodtruckr.list.RestaurantListFragment;
import com.brianjmelton.foodtruckr.log.LoggingEnabled;
import com.brianjmelton.foodtruckr.shared.detail.RestaurantDetailDelegate;
import com.brianjmelton.foodtruckr.shared.detail.RestaurantDetailDelegateImpl;
import com.brianjmelton.foodtruckr.shared.detail.RestaurantDetailDispatch;
import com.brianjmelton.foodtruckr.shared.list.RestaurantListDelegate;
import com.brianjmelton.foodtruckr.shared.list.RestaurantListDelegateImpl;
import com.brianjmelton.foodtruckr.shared.list.RestaurantListDispatch;
import com.brianjmelton.foodtruckr.shared.vo.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import static com.brianjmelton.foodtruckr.conf.Constants.FRIDAY;
import static com.brianjmelton.foodtruckr.conf.Constants.MONDAY;
import static com.brianjmelton.foodtruckr.conf.Constants.THURSDAY;
import static com.brianjmelton.foodtruckr.conf.Constants.TUESDAY;
import static com.brianjmelton.foodtruckr.conf.Constants.WEDNESDAY;


public class MainActivity extends Activity implements RestaurantListFragment.Binder,
        RestaurantDetailDelegate, RestaurantListDispatch, RestaurantDetailFragment.Binder,
        LoggingEnabled {

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
                .replace(R.id.container, RestaurantDetailFragment.newInstance(id))
                .addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void setCalendar(Calendar calendar) {
        mCalendar = calendar;
    }

    @Override
    public Calendar getCalendar() {
        return mCalendar;
    }

    @Override
    public void onItemClick(View view, int position) {
        // FIXME this isn't great, but I had made the JSON cleaner it wouldn't be like this
        long whichId;
        switch (position) {
            case MONDAY:
                whichId = mCalendar.getMonday().getId();
                break;
            case TUESDAY:
                whichId = mCalendar.getTuesday().getId();
                break;
            case WEDNESDAY:
                whichId = mCalendar.getWednesday().getId();
                break;
            case THURSDAY:
                whichId = mCalendar.getThursday().getId();
                break;
            case FRIDAY:
                whichId = mCalendar.getFriday().getId();
                break;
            default:
                throw new IllegalStateException("No day for position: " + position);
        }

        getLogger().info("User clicked {}", whichId);
        onRestaurantClicked(whichId);
    }

    @Override
    public Logger getLogger() {
        return LoggerFactory.getLogger(MainActivity.class);
    }
}
