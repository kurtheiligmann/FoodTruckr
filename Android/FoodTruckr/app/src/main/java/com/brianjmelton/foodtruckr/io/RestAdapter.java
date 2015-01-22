package com.brianjmelton.foodtruckr.io;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit.converter.GsonConverter;

/**
 * Created by brianmelton on 1/16/15.
 *
 * Singleton representation of a {@link retrofit.RestAdapter}
 */
public class RestAdapter {

    private static final Logger log = LoggerFactory.getLogger(RestAdapter.class);

    private static final String sEndpoint = "http://brianjmelton.com";

    private static retrofit.RestAdapter sRestAdapter;

    public static retrofit.RestAdapter getInstance() {
        log.info("^getInstance()");
        if (null == sRestAdapter) {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

            sRestAdapter = new retrofit.RestAdapter.Builder().setEndpoint(sEndpoint)
                    .setConverter(new GsonConverter(gson)).build();
        }
        log.info("^getInstance() : {}", sRestAdapter);
        return sRestAdapter;
    }

}
