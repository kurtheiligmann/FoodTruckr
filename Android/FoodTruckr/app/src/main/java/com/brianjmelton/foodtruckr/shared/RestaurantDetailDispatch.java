package com.brianjmelton.foodtruckr.shared;

/**
 * Created by brianmelton on 1/16/15.
 */
public interface RestaurantDetailDispatch {

    public void onDisplayName(long id);

    public void onDisplayLogo(long id);

    public void onDisplayLink(long id);

    public void onDisplayCuisine(long id);

    public void onDisplayPayment(long id);

    public void onDisplayShortDescription(long id);

    public void onDisplayLongDescription(long id);

}
