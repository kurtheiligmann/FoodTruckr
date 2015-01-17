package com.brianjmelton.foodtruckr;

import com.brianjmelton.foodtruckr.shared.detail.RestaurantDetailDelegate;
import com.brianjmelton.foodtruckr.shared.list.RestaurantListDelegate;
import com.brianjmelton.foodtruckr.shared.list.RestaurantListDelegateImpl;
import com.brianjmelton.foodtruckr.shared.list.RestaurantListDispatch;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity implements RestaurantListDelegate,
        RestaurantDetailDelegate, RestaurantListDispatch {

    protected RestaurantListDelegate mRestaurantListDelegate = new RestaurantListDelegateImpl(this);

    protected RestaurantDetailDelegate mRestaurantDetailDelegate;

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
    public void onRestaurantDetailShown(long id) {
        mRestaurantDetailDelegate.onRestaurantDetailShown(id);
    }

    @Override
    public void onShowRestaurantDetail(long id) {
        // do a FragmentTransaction
    }
}
