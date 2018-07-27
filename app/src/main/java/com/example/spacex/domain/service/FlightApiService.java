package com.example.spacex.domain.service;

import com.example.spacex.data.response.Flight;

import java.util.List;

import io.reactivex.Single;

import retrofit2.http.GET;

public interface FlightApiService {
    @GET("/v2/launches")
    Single<List<Flight>> getFlights();
}
