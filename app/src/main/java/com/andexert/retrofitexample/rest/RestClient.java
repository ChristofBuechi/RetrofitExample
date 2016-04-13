package com.andexert.retrofitexample.rest;

import com.andexert.retrofitexample.rest.service.WeatherService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Author :    Chutaux Robin
 * Date :      10/2/2014
 */
public class RestClient
{
    private static final String BASE_URL = "http://api.openweathermap.org/";
    private WeatherService apiService;
    private OkHttpClient okHttpClient;

    public RestClient()
    {

        okHttpClient = new OkHttpClient();


        Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
            .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(getOkHttpClient()))
                .build();

        apiService = restAdapter.create(WeatherService.class);
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setInterceptor(Interceptor interceptor) {
        okHttpClient.interceptors().add(interceptor);
    }

    public WeatherService getWeatherService()
    {
        return apiService;
    }
}
