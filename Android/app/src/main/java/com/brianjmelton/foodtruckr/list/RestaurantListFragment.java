package com.brianjmelton.foodtruckr.list;

import com.brianjmelton.foodtruckr.view.AbstractViews;
import com.brianjmelton.foodtruckr.R;
import com.brianjmelton.foodtruckr.io.Calendar;
import com.brianjmelton.foodtruckr.io.RestAdapter;
import com.brianjmelton.foodtruckr.log.LoggingEnabled;
import com.brianjmelton.foodtruckr.shared.list.RestaurantListDelegate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by brianmelton on 1/16/15.
 */
public class RestaurantListFragment extends Fragment
        implements Callback<com.brianjmelton.foodtruckr.shared.vo.Calendar>, LoggingEnabled {

    protected Binder mBinder;

    /**
     * The {@link View}s managed by this {@link android.app.Fragment}
     */
    protected class Views extends AbstractViews {

        protected RecyclerView mRecyclerView;

        protected RecyclerView.Adapter mAdapter;

        protected RecyclerView.LayoutManager mLayoutManager;

        @Override
        protected View initialize(View layout) {
            mRecyclerView = (RecyclerView) layout.findViewById(R.id.list_recycler_view);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mRecyclerView.hasFixedSize();

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);

            // TODO show a spinner while you wait!

            return layout;
        }
    }

    public RestaurantListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return mViews.initialize(rootView);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Now that we a View to shove data into, load that data
        RestAdapter.getInstance().create(Calendar.class).getCalendar(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mBinder = (Binder) activity;
    }

    @Override
    public Logger getLogger() {
        return LoggerFactory.getLogger(RestaurantListFragment.class);
    }

    //
    // Network I/O
    //
    @Override
    public void success(com.brianjmelton.foodtruckr.shared.vo.Calendar calendar,
            Response response) {
        mBinder.setCalendar(calendar);
        mViews.mAdapter = new RestaurantListAdapter(calendar);
        mViews.mRecyclerView.setAdapter(mViews.mAdapter);
    }

    @Override
    public void failure(RetrofitError error) {
        // TODO show an error
    }

    //
    // Eager View container
    //
    protected Views mViews = new Views();

    /**
     * This {@link android.app.Fragment}'s Binder interface
     */
    public static interface Binder extends RestaurantListDelegate {

        public void setCalendar(com.brianjmelton.foodtruckr.shared.vo.Calendar calendar);

        public com.brianjmelton.foodtruckr.shared.vo.Calendar getCalendar();
    }
}
