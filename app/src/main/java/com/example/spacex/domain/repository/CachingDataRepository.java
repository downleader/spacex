package com.example.spacex.domain.repository;

import com.example.spacex.data.response.Flight;
import com.example.spacex.domain.service.DataService;
import com.example.spacex.utils.rxjava.SchedulerProvider;

import java.util.List;

import io.reactivex.Single;

public class CachingDataRepository implements DataRepository {

    private final DataService dataService;
    private final SchedulerProvider schedulerProvider;

    public CachingDataRepository(DataService dataService, SchedulerProvider schedulerProvider) {
        this.dataService = dataService;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Single<List<Flight>> getFlights() {
        return dataService.getFlights().subscribeOn(schedulerProvider.io());
    }
}
