package com.brianjmelton.foodtruckr.io;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by brianmelton on 1/16/15.
 */
public interface Calendar {

    @GET("/food-truckr/static/trucks.json")
    public void getCalendar(Callback<com.brianjmelton.foodtruckr.shared.Calendar> days);
}
