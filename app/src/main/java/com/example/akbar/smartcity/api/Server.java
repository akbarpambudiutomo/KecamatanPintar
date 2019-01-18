package com.example.akbar.smartcity.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    public static final String base_url = "http://192.168.100.13/smart/";

    public static Retrofit getUrl() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        // start converter json
        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create(gson)).client(httpClient.build()).build();
    }
}
