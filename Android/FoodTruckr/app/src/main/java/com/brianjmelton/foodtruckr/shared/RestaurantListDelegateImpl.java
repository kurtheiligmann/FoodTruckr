package com.brianjmelton.foodtruckr.shared;

/**
 * Created by brianmelton on 1/16/15.
 *
 * Behavioral Business Object
 */
public class RestaurantListDelegateImpl implements RestaurantListDelegate {

    final RestaurantListDispatch mRestaurantListDispatch;

    public RestaurantListDelegateImpl(RestaurantListDispatch restaurantListDispatch) {
        mRestaurantListDispatch = restaurantListDispatch;
    }

    @Override
    public void onRestaurantClicked(long id) {
        mRestaurantListDispatch.onShowRestaurantDetail(id);
    }
}
