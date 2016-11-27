package ru.mgpy.Network;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Request {

    private static API instance;

    public static API getAPI() {
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://mydelivery.96.lt/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
           return instance = retrofit.create(API.class);
        }
        return instance;
    }
}
