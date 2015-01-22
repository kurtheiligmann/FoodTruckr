package com.brianjmelton.foodtruckr.list;

import com.brianjmelton.foodtruckr.log.LoggingEnabled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Simplified interface for detecting click events in CardViews
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener, LoggingEnabled {

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
        getLogger().info("^RecyclerItemClickListener(context={}, listener={})", context, listener);
        mListener = listener;
        mGestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        getLogger().info("^onSingleTapUp(e={})", e);
                        boolean handled = true;
                        getLogger().info("$onSingleTapUp(e={}) : {}", e, handled);
                        return handled;
                    }
                });
        getLogger().info("$RecyclerItemClickListener(context={}, listener={})", context, listener);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        getLogger().info("^onInterceptTouchEvent(view={}, e={})", view, e);
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        boolean handled = false;
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildPosition(childView));
            handled = true;
        }
        getLogger()
                .info("$onInterceptTouchEvent(view={}, e={}) : {}", new Object[]{view, e, handled});
        return handled;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        getLogger().info("^onTouchEvent(view={}, motionEvent={})", view, motionEvent);
        getLogger().info("$onTouchEvent(view={}, motionEvent={})", view, motionEvent);
    }

    @Override
    public Logger getLogger() {
        return LoggerFactory.getLogger(RecyclerItemClickListener.class);
    }
}
