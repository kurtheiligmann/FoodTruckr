package com.brianjmelton.foodtruckr;

import com.brianjmelton.foodtruckr.detail.RestaurantDetailFragment;
import com.brianjmelton.foodtruckr.list.RestaurantListFragment;
import com.brianjmelton.foodtruckr.log.LoggingEnabled;
import com.brianjmelton.foodtruckr.shared.Calendar;
import com.brianjmelton.foodtruckr.shared.RestaurantDetailDelegate;
import com.brianjmelton.foodtruckr.shared.RestaurantDetailDelegateImpl;
import com.brianjmelton.foodtruckr.shared.RestaurantDetailDispatch;
import com.brianjmelton.foodtruckr.shared.RestaurantListDelegate;
import com.brianjmelton.foodtruckr.shared.RestaurantListDelegateImpl;
import com.brianjmelton.foodtruckr.shared.RestaurantListDispatch;

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


/**
 * Central {@link android.app.Activity} - uses the single Activity, multi-{@link
 * android.app.Fragment} pattern
 *
 * @author brianmelton
 */
public class MainActivity extends Activity implements RestaurantListFragment.Binder,
        RestaurantDetailDelegate, RestaurantListDispatch, RestaurantDetailFragment.Binder,
        LoggingEnabled {

    /**
     * Shared delegate for RestaurantList events
     */
    protected RestaurantListDelegate mRestaurantListDelegate = new RestaurantListDelegateImpl(this);

    /**
     * Shared delegate for RestaurantDetail events
     */
    protected RestaurantDetailDelegate mRestaurantDetailDelegate;

    /**
     * Shared Calendar vo
     */
    protected Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLogger().info("^onCreate(savedInstanceState={})", savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new RestaurantListFragment())
                    .commit();
        }
        getLogger().info("$onCreate(savedInstanceState={})", savedInstanceState);
    }

    @Override
    public void onRestaurantClicked(long id) {
        getLogger().info("^onRestaurantClicked(id={})", id);
        mRestaurantListDelegate.onRestaurantClicked(id);
        getLogger().info("$onRestaurantClicked(id={})", id);
    }

    @Override
    public void setRestaurantDetailDispatch(RestaurantDetailDispatch dispatch) {
        getLogger().info("^setRestaurantDetailDispatch(dispatch={})", dispatch);
        mRestaurantDetailDelegate = new RestaurantDetailDelegateImpl(dispatch);
        getLogger().info("$setRestaurantDetailDispatch(dispatch={})", dispatch);
    }

    @Override
    public void onRestaurantDetailShown(long id) {
        getLogger().info("^onRestaurantDetailShown(id={})", id);
        mRestaurantDetailDelegate.onRestaurantDetailShown(id);
        getLogger().info("$onRestaurantDetailShown(id={})", id);
    }

    @Override
    public void onShowRestaurantDetail(long id) {
        // do a FragmentTransaction
        getLogger().info("^onShowRestaurantDetail(id)", id);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, RestaurantDetailFragment.newInstance(mCalendar, id))
                .addToBackStack(null).commit();
        getLogger().info("$onShowRestaurantDetail(id)", id);
    }

    @Override
    public void onBackPressed() {
        getLogger().info("^onBackPressed()");
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
        getLogger().info("$onBackPressed()");
    }


    @Override
    public void setCalendar(Calendar calendar) {
        getLogger().info("^setCalendar(calendar={})", calendar);
        mCalendar = calendar;
        getLogger().info("$setCalendar(calendar={})", calendar);
    }

    @Override
    public Calendar getCalendar() {
        getLogger().info("^getCalendar()");
        getLogger().info("$getCalendar() : {}", mCalendar);
        return mCalendar;
    }

    @Override
    public void onItemClick(View view, int position) {
        // FIXME this isn't great, but I had made the JSON cleaner it wouldn't be like this
        getLogger().info("^onItemClick(view={}, position={})", view, position);
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
        getLogger().info("$onItemClick(view={}, position={})", view, position);
    }

    @Override
    public Logger getLogger() {
        return LoggerFactory.getLogger(MainActivity.class);
    }
}
