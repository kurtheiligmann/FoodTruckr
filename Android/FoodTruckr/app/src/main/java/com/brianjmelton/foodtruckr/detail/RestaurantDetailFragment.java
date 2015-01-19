package com.brianjmelton.foodtruckr.detail;

import com.brianjmelton.foodtruckr.R;
import com.brianjmelton.foodtruckr.log.LoggingEnabled;
import com.brianjmelton.foodtruckr.shared.detail.RestaurantDetailDelegate;
import com.brianjmelton.foodtruckr.shared.detail.RestaurantDetailDispatch;
import com.brianjmelton.foodtruckr.shared.vo.Calendar;
import com.brianjmelton.foodtruckr.shared.vo.Restaurant;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brianmelton on 1/16/15.
 */
public class RestaurantDetailFragment extends Fragment implements RestaurantDetailDispatch,
        LoggingEnabled {

    private static final String sArgCal = "sArgCal";

    private static final String sArgId = "argId";

    protected Binder mBinder;

    protected Views mViews = new Views();

    protected Map<Long, Restaurant> mRestaurantMap;

    protected long mId;

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
            final Calendar calendar = (Calendar) getArguments().getSerializable(sArgCal);
            mRestaurantMap = new HashMap<Long, Restaurant>() {{
                put(calendar.getMonday().getId(), calendar.getMonday());
                put(calendar.getTuesday().getId(), calendar.getTuesday());
                put(calendar.getWednesday().getId(), calendar.getWednesday());
                put(calendar.getThursday().getId(), calendar.getThursday());
                put(calendar.getFriday().getId(), calendar.getFriday());
            }};
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
    }

    private Restaurant forId(long id) {
        return mRestaurantMap.get(id);
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

    public static interface Binder extends RestaurantDetailDelegate {

        public void setRestaurantDetailDispatch(RestaurantDetailDispatch dispatch);
    }
}
