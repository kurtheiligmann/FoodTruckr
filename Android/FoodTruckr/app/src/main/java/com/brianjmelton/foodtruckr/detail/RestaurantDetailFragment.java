package com.brianjmelton.foodtruckr.detail;

import com.brianjmelton.foodtruckr.R;
import com.brianjmelton.foodtruckr.log.LoggingEnabled;
import com.brianjmelton.foodtruckr.shared.Calendar;
import com.brianjmelton.foodtruckr.shared.Restaurant;
import com.brianjmelton.foodtruckr.shared.RestaurantDetailDelegate;
import com.brianjmelton.foodtruckr.shared.RestaurantDetailDispatch;
import com.brianjmelton.foodtruckr.view.AbstractViews;
import com.squareup.picasso.Picasso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by brianmelton on 1/16/15.
 *
 * The "detail" view of the Restaurant list - shows information like payment, their website, a long
 * description, and whatever else!
 */
public class RestaurantDetailFragment extends Fragment implements RestaurantDetailDispatch,
        LoggingEnabled {

    /**
     * Unique Strings to identify arguments within the {@link android.os.Bundle}
     */
    protected static final String sArgCal = "sArgCal";

    protected static final String sArgId = "sArgId";

    protected Binder mBinder;

    protected Calendar mCalendar;

    /**
     * Instance of this View container
     */
    protected Views mViews = new Views();

    protected long mId;

    /**
     * View container for this {@link android.app.Fragment}'s {@link android.view.View}s
     */
    protected class Views extends AbstractViews implements LoggingEnabled {

        protected ImageView mBackground;

        protected TextView mName, mTime, mLongDescription, mWebsite;

        @Override
        protected View initialize(View layout) {
            getLogger().info("^initialize(layout={})", layout);
            mBackground = (ImageView) layout.findViewById(R.id.detail_background);
            mName = (TextView) layout.findViewById(R.id.detail_name);
            mTime = (TextView) layout.findViewById(R.id.detail_time);
            mLongDescription = (TextView) layout.findViewById(R.id.detail_longdescription);
            mWebsite = (TextView) layout.findViewById(R.id.detail_website);
            getLogger().info("$initialize(layout={}) : {}", layout, layout);
            return layout;
        }

        @Override
        public Logger getLogger() {
            return LoggerFactory.getLogger(Views.class);
        }
    }

    public RestaurantDetailFragment() {
        // Mandatory empty constructor
    }

    public static RestaurantDetailFragment newInstance(Calendar calendar, long id) {
        RestaurantDetailFragment fragment = new RestaurantDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(sArgCal, calendar);
        args.putLong(sArgId, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getLogger().info("^onCreate(savedInstanceState={})", savedInstanceState);
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mCalendar = (Calendar) getArguments().getSerializable(sArgCal);
            mId = getArguments().getLong(sArgId);
        }
        getLogger().info("$onCreate(savedInstanceState={})", savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        getLogger().info("^onAttach(activity={})");
        super.onAttach(activity);
        mBinder = (Binder) activity;
        mBinder.setRestaurantDetailDispatch(this);
        getLogger().info("$onAttach(activity={})");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getLogger().info("^onCreateView(inflater={}, container={}, savedInstanceState={})",
                new Object[]{inflater, container, savedInstanceState});
        View rootView = inflater.inflate(R.layout.layout_detail_view, container, false);
        getLogger().info("$onCreateView(inflater={}, container={}, savedInstanceState={}) : {}",
                new Object[]{inflater, container, savedInstanceState, rootView});
        return mViews.initialize(rootView);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getLogger()
                .info("^onViewCreated(view={}, savedInstanceState={})", view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
        mBinder.onRestaurantDetailShown(mId);
        getLogger()
                .info("$onViewCreated(view={}, savedInstanceState={})", view, savedInstanceState);
    }

    @Override
    public void onResume() {
        getLogger().info("^onResume()");
        super.onResume();
        setHasOptionsMenu(true);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        getLogger().info("$onResume()");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getLogger().info("^onOptionsItemSelected(item={})", item);
        boolean handled = false;
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                handled = true;
        }

        if (!handled) {
            handled = super.onOptionsItemSelected(item);
        }
        getLogger().info("$onOptionsItemSelected(item={}) : {}", item, handled);
        return handled;
    }

    private Restaurant forId(long id) {
        getLogger().info("^forId(id={})", id);
        Restaurant restaurant = mCalendar.getRestaurantForId(id);
        getLogger().info("$forId(id={}) : {}", id, restaurant);
        return restaurant;
    }

    @Override
    public void onDisplayName(long id) {
        getLogger().info("^onDisplayName(id={})", id);
        mViews.mName.setText(forId(id).getName());
        getLogger().info("$onDisplayName(id={})", id);
    }

    @Override
    public void onDisplayLogo(long id) {
        getLogger().info("^onDisplayLogo(id={})", id);
        Picasso.with(getActivity()).load(forId(id).getBackground()).into(mViews.mBackground);
        getLogger().info("$onDisplayLogo(id={})", id);
    }

    @Override
    public void onDisplayLink(final long id) {
        getLogger().info("^onDisplayLink(id={})", id);
        mViews.mWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kick it to a browser
                String url = forId(id).getLink();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
        getLogger().info("$onDisplayLink(id={})", id);
    }

    @Override
    public void onDisplayCuisine(long id) {
        getLogger().info("^onDisplayCuisine(id={})", id);
        mViews.mTime.setText(forId(id).getCuisine());
        getLogger().info("$onDisplayCuisine(id={})", id);
    }

    @Override
    public void onDisplayPayment(long id) {
        getLogger().info("^onDisplayPayment(id={})", id);
        getLogger().info("$onDisplayPayment(id={})", id);
    }

    @Override
    public void onDisplayShortDescription(long id) {
        getLogger().info("^onDisplayShortDescription(id={})", id);
        getLogger().info("$onDisplayShortDescription(id={})", id);
    }

    @Override
    public void onDisplayLongDescription(long id) {
        getLogger().info("^onDisplayLongDescription(id={})", id);
        mViews.mLongDescription.setText(forId(id).getLongDescription());
        getLogger().info("$onDisplayLongDescription(id={})", id);
    }

    @Override
    public Logger getLogger() {
        return LoggerFactory.getLogger(RestaurantDetailFragment.class);
    }

    /**
     * Users of this {@link android.app.Fragment} <em>must</em> implement this interface for
     * #onAttach
     */
    public static interface Binder extends RestaurantDetailDelegate {

        /**
         * Sets the {@link com.brianjmelton.foodtruckr.shared.RestaurantDetailDispatch} on this
         * {@link android.app.Fragment}
         *
         * @param dispatch the dispatch to set
         */
        public void setRestaurantDetailDispatch(RestaurantDetailDispatch dispatch);
    }
}
