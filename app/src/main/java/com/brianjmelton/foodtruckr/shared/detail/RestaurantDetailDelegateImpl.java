package com.brianjmelton.foodtruckr.shared.detail;

/**
 * Created by brianmelton on 1/16/15.
 */
public class RestaurantDetailDelegateImpl implements RestaurantDetailDelegate {

    final RestaurantDetailDispatch mRestaurantDetailDispatch;

    public RestaurantDetailDelegateImpl(RestaurantDetailDispatch dispatch) {
        mRestaurantDetailDispatch = dispatch;
    }

    @Override
    public void onRestaurantDetailShown(long id) {
        mRestaurantDetailDispatch.onDisplayName(id);
        mRestaurantDetailDispatch.onDisplayLogo(id);
        mRestaurantDetailDispatch.onDisplayLink(id);
        mRestaurantDetailDispatch.onDisplayCuisine(id);
        mRestaurantDetailDispatch.onDisplayPayment(id);
        mRestaurantDetailDispatch.onDisplayShortDescription(id);
        mRestaurantDetailDispatch.onDisplayLongDescription(id);
    }
}
