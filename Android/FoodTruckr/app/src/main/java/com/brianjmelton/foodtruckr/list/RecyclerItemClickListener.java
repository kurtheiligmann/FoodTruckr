package com.brianjmelton.foodtruckr.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Simplified interface for detecting click events in CardViews
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    /**
     * Delegate for click events
     */
    private OnItemClickListener mListener;

    /**
     * Simplified interface for these click events to emulate
     * {@link com.brianjmelton.foodtruckr.list.RecyclerItemClickListener.OnItemClickListener}
     */
    public interface OnItemClickListener {

        public void onItemClick(View view, int position);
    }

    GestureDetector mGestureDetector;

    public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return true;
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }
}
