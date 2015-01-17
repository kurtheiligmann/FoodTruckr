package com.brianjmelton.foodtruckr;

import com.brianjmelton.foodtruckr.shared.detail.RestaurantDetailDelegate;
import com.brianjmelton.foodtruckr.shared.detail.RestaurantDetailDispatch;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by brianmelton on 1/16/15.
 */
public class RestaurantDetailFragment extends Fragment implements RestaurantDetailDispatch {

    private static final String sArgId = "argId";

    protected Binder mBinder;

    protected long mId;

    public RestaurantDetailFragment() {
    }

    public static RestaurantDetailFragment newInstance(long id) {
        RestaurantDetailFragment fragment = new RestaurantDetailFragment();
        Bundle args = new Bundle();
        args.putLong(sArgId, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mBinder = (Binder) activity;
        mBinder.setRestaurantDetailDispatch(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mBinder.onRestaurantDetailShown(mId);
    }

    @Override
    public void onDisplayName(long id) {

    }

    @Override
    public void onDisplayLogo(long id) {

    }

    @Override
    public void onDisplayLink(long id) {

    }

    @Override
    public void onDisplayCuisine(long id) {

    }

    @Override
    public void onDisplayPayment(long id) {

    }

    @Override
    public void onDisplayShortDescription(long id) {

    }

    @Override
    public void onDisplayLongDescription(long id) {

    }

    public static interface Binder extends RestaurantDetailDelegate {

        public void setRestaurantDetailDispatch(RestaurantDetailDispatch dispatch);
    }
}
