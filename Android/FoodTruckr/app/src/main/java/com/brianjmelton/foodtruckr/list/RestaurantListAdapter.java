package com.brianjmelton.foodtruckr.list;

import com.brianjmelton.foodtruckr.R;
import com.brianjmelton.foodtruckr.conf.Constants;
import com.brianjmelton.foodtruckr.log.LoggingEnabled;
import com.brianjmelton.foodtruckr.shared.Calendar;
import com.brianjmelton.foodtruckr.shared.Restaurant;
import com.squareup.picasso.Picasso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static com.brianjmelton.foodtruckr.conf.Constants.FRIDAY;
import static com.brianjmelton.foodtruckr.conf.Constants.MONDAY;
import static com.brianjmelton.foodtruckr.conf.Constants.THURSDAY;
import static com.brianjmelton.foodtruckr.conf.Constants.TUESDAY;
import static com.brianjmelton.foodtruckr.conf.Constants.WEDNESDAY;

/**
 * Created by brianmelton on 1/17/15.
 *
 * The ViewAdapter for the RestaurantList - uses the spiffy new RecyclerView for efficient
 * allocation of new views
 */
public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>
        implements
        LoggingEnabled {

    /**
     * The ViewHolder acts as a programmatic representation of the xml view
     */
    class ViewHolder extends RecyclerView.ViewHolder implements LoggingEnabled {

        /**
         * The background img of the {@link com.brianjmelton.foodtruckr.shared.Restaurant}
         */
        public ImageView mBackground;

        public TextView mLargeLabel, mSmallLabel, mCardText;

        public ViewHolder(View layout) {
            super(layout);
            mBackground = (ImageView) layout.findViewById(R.id.background);
            mLargeLabel = (TextView) layout.findViewById(R.id.large_label);
            mSmallLabel = (TextView) layout.findViewById(R.id.small_label);
            mCardText = (TextView) layout.findViewById(R.id.card_text);
        }

        @Override
        public Logger getLogger() {
            return LoggerFactory.getLogger(ViewHolder.class);
        }
    }

    protected Context mContext;

    protected Calendar mCalendar;

    public RestaurantListAdapter(Calendar calendar) {
        if (null == calendar) {
            throw new IllegalArgumentException("Calendar may not be null.");
        }

        mCalendar = calendar;
    }

    @Override
    public RestaurantListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        getLogger().info("^onCreateViewHolder(viewGroup={}, i={})", viewGroup, i);
        mContext = viewGroup.getContext();
        View layout = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_card1, viewGroup, false);
        RestaurantListAdapter.ViewHolder viewHolder = new ViewHolder(layout);
        getLogger().info("$onCreateViewHolder(viewGroup={}, i={}) : {}",
                new Object[]{viewGroup, i, viewHolder});
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantListAdapter.ViewHolder viewHolder, int i) {
        getLogger().info("^onBindViewHolder(viewHolder={}, i={})", viewHolder, i);
        // cancel any already in progress load actions...
        Picasso.with(mContext).cancelRequest(viewHolder.mBackground);
        Restaurant which;
        String dayName;

        // FIXME yuck!
        switch (i) {
            case MONDAY:
                which = mCalendar.getMonday();
                dayName = "Monday";
                break;
            case TUESDAY:
                which = mCalendar.getTuesday();
                dayName = "Tuesday";
                break;
            case WEDNESDAY:
                which = mCalendar.getWednesday();
                dayName = "Wednesday";
                break;
            case THURSDAY:
                which = mCalendar.getThursday();
                dayName = "Thursday";
                break;
            case FRIDAY:
                which = mCalendar.getFriday();
                dayName = "Friday";
                break;
            default:
                throw new IllegalStateException("No day for position: " + i);
        }

        if (null == which) {
            throw new IllegalStateException("Day contents cannot be null... for now.");
        }

        // load the background thumbnail
        Picasso.with(mContext).load(which.getBackground()).into(viewHolder.mBackground);
        // set the day of the week
        viewHolder.mSmallLabel.setText(dayName);
        // set the name of joint
        viewHolder.mLargeLabel.setText(which.getName());
        // set the card text
        viewHolder.mCardText.setText(which.getShortDescription());
        getLogger().info("$onBindViewHolder(viewHolder={}, i={})", new Object[]{viewHolder, i});
    }

    @Override
    public int getItemCount() {
        getLogger().info("^getItemCount()");
        final int count = Constants.NUM_DAYS;
        getLogger().info("$getItemCount() : {}", count);
        return count;
    }

    @Override
    public Logger getLogger() {
        return LoggerFactory.getLogger(RestaurantListAdapter.class);
    }
}
