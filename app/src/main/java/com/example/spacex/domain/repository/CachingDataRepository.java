package com.example.spacex.domain.repository;

import com.example.spacex.data.response.Flight;
import com.example.spacex.domain.service.FlightService;
import com.example.spacex.persistence.dao.FlightDao;
import com.example.spacex.utils.rxjava.SchedulerProvider;

import java.util.List;

import io.reactivex.Single;

public class CachingDataRepository implements DataRepository {

    private final FlightService flightService;
    private final FlightDao flightDao;
    private final SchedulerProvider schedulerProvider;

    public CachingDataRepository(FlightService flightService, FlightDao flightDao,
                                 SchedulerProvider schedulerProvider) {
        this.flightService = flightService;
        this.flightDao = flightDao;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Single<List<Flight>> getFlights() {
        return Single.fromCallable(flightDao::getFlights)
                .flatMap(cachedFlights -> {
                    if (cachedFlights.isEmpty()) {
                        return flightService.getFlights()
                                .subscribeOn(schedulerProvider.io())
                                .observeOn(schedulerProvider.computation())
                                .map(flights -> {
                                    flightDao.setFlights(flights);
                                    return flights;
                                });
                    } else {
                        return Single.just(cachedFlights);
                    }
                })
                .subscribeOn(schedulerProvider.computation());
    }
}
