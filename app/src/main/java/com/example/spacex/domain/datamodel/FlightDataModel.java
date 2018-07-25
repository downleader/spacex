package com.example.spacex.domain.datamodel;

import com.example.spacex.data.response.Flight;
import com.example.spacex.domain.repository.DataRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class FlightDataModel {

    private final DataRepository dataRepository;

    @Inject
    FlightDataModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public Single<List<Flight>> getFlights() {
        return dataRepository.getFlights();
    }
}
