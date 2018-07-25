package com.example.spacex.domain.service;

import com.example.spacex.data.constant.NetworkConstants;
import com.example.spacex.data.response.Flight;

import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Single;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataService {

    private final FlightService flightService;

    public DataService(OkHttpClient.Builder okHttpClientBuilder, Gson gson) {
        flightService = new Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClientBuilder.build())
                .build()
                .create(FlightService.class);
    }

    public Single<List<Flight>> getFlights() {
        return flightService.getFlights();
    }
}
