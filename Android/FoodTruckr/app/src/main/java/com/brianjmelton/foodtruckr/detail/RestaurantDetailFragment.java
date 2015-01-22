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
    protected class Views extends AbstractViews {

        protected ImageView mBackground;

        protected TextView mName, mTime, mLongDescription, mWebsite;


        @Override
        protected View initialize(View layout) {
            mBackground = (ImageView) layout.findViewById(R.id.detail_background);
            mName = (TextView) layout.findViewById(R.id.detail_name);
            mTime = (TextView) layout.findViewById(R.id.detail_time);
            mLongDescription = (TextView) layout.findViewById(R.id.detail_longdescription);
            mWebsite = (TextView) layout.findViewById(R.id.detail_website);
            return layout;
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
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mCalendar = (Calendar) getArguments().getSerializable(sArgCal);
            mId = getArguments().getLong(sArgId);
        }
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
        return mViews.initialize(rootView);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinder.onRestaurantDetailShown(mId);
    }

    @Override
    public void onResume() {
        super.onResume();
        setHasOptionsMenu(true);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Restaurant forId(long id) {
        return mCalendar.getRestaurantForId(id);
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
