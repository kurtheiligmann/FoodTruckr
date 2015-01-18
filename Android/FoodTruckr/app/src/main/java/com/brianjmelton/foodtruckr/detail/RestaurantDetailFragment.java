package com.brianjmelton.foodtruckr.detail;

import com.brianjmelton.foodtruckr.R;
import com.brianjmelton.foodtruckr.log.LoggingEnabled;
import com.brianjmelton.foodtruckr.shared.detail.RestaurantDetailDelegate;
import com.brianjmelton.foodtruckr.shared.detail.RestaurantDetailDispatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by brianmelton on 1/16/15.
 */
public class RestaurantDetailFragment extends Fragment implements RestaurantDetailDispatch,
        LoggingEnabled {

    private static final String sArgId = "argId";

    protected Binder mBinder;

    protected long mId;

    protected class Views {

        protected ImageView mBackground;

        protected TextView mName, mTime, mLongDescription, mWebsite;
    }

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
        View rootView = inflater.inflate(R.layout.layout_detail_view, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinder.onRestaurantDetailShown(mId);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDisplayName(long id) {
        getLogger().info("^onDisplayName(id={})");
        getLogger().info("$onDisplayName(id={})");
    }

    @Override
    public void onDisplayLogo(long id) {
        getLogger().info("^onDisplayLogo(id={})");
        getLogger().info("$onDisplayLogo(id={})");
    }

    @Override
    public void onDisplayLink(long id) {
        getLogger().info("^onDisplayLink(id={})");
        getLogger().info("$onDisplayLink(id={})");
    }

    @Override
    public void onDisplayCuisine(long id) {
        getLogger().info("^onDisplayCuisine(id={})");
        getLogger().info("$onDisplayCuisine(id={})");
    }

    @Override
    public void onDisplayPayment(long id) {
        getLogger().info("^onDisplayPayment(id={})");
        getLogger().info("$onDisplayPayment(id={})");
    }

    @Override
    public void onDisplayShortDescription(long id) {
        getLogger().info("^onDisplayShortDescription(id={})");
        getLogger().info("$onDisplayShortDescription(id={})");
    }

    @Override
    public void onDisplayLongDescription(long id) {
        getLogger().info("^onDisplayLongDescription(id={})");
        getLogger().info("$onDisplayLongDescription(id={})");
    }

    @Override
    public Logger getLogger() {
        return LoggerFactory.getLogger(RestaurantDetailFragment.class);
    }

    public static interface Binder extends RestaurantDetailDelegate {

        public void setRestaurantDetailDispatch(RestaurantDetailDispatch dispatch);
    }
}
