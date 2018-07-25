package com.example.spacex.domain.repository;

import com.example.spacex.data.response.Flight;

import java.util.List;

import io.reactivex.Single;

public interface DataRepository {
    Single<List<Flight>> getFlights();
}
