package com.brianjmelton.foodtruckr.io;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.converter.GsonConverter;

/**
 * Created by brianmelton on 1/16/15.
 */
public class RestAdapter {

    private static final String sEndpoint = "http://brianjmelton.com";

    private static retrofit.RestAdapter sRestAdapter;

    public static retrofit.RestAdapter getInstance() {
        if (null == sRestAdapter) {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

            sRestAdapter = new retrofit.RestAdapter.Builder().setEndpoint(sEndpoint)
                    .setConverter(new GsonConverter(gson)).build();
        }
        return sRestAdapter;
    }

}
