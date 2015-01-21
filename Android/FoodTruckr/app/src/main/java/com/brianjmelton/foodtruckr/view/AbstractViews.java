package com.brianjmelton.foodtruckr.view;

import android.view.View;

/**
 * Created by brianmelton on 1/17/15.
 *
 * Abstract super class for {@link android.app.Fragment} ViewContainer pattern
 */
public abstract class AbstractViews {

    /**
     * Initialize {@link android.app.Fragment} {@link android.view.View}s here
     *
     * @param layout the root layout
     * @return the root layout
     */
    protected abstract View initialize(View layout);

}
