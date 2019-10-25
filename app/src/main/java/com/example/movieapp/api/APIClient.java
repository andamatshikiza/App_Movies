package com.example.movieapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.movieapp.Constants.Constant.BASE_URL;

public class APIClient {

    private static Retrofit retrofit = null;
    private  static APIClient instance;

    public  APIClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


        }
    }

    public  static  synchronized  APIClient getInstance(){

        if (instance ==null){
            instance = new APIClient();
        }

        return instance;
    }

    public  apiService getapiService(){
        return retrofit.create(apiService.class);
    }
}
