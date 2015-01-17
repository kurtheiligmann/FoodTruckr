package com.brianjmelton.foodtruckr.list;

import com.brianjmelton.foodtruckr.conf.Constants;
import com.brianjmelton.foodtruckr.shared.vo.Calendar;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by brianmelton on 1/17/15.
 */
public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.ViewHolder> {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    protected Calendar mCalendar;

    public RestaurantListAdapter(Calendar calendar) {
        if (null == calendar) {
            throw new IllegalArgumentException("Calendar may not be null.");
        }

        mCalendar = calendar;
    }

    @Override
    public RestaurantListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(RestaurantListAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return Constants.NUM_DAYS;
    }
}
