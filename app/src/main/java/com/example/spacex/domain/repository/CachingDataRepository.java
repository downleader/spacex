package com.example.spacex.domain.repository;

import com.example.spacex.data.response.Flight;
import com.example.spacex.domain.service.FlightService;
import com.example.spacex.persistence.dao.FlightDao;
import com.example.spacex.utils.rxjava.SchedulerProvider;

import java.util.List;

import io.reactivex.Single;

public class CachingDataRepository implements DataRepository {

    private final SchedulerProvider schedulerProvider;
    private final FlightService flightService;
    private final FlightDao flightDao;

    public CachingDataRepository(SchedulerProvider schedulerProvider,
                                 FlightService flightService,
                                 FlightDao flightDao) {
        this.schedulerProvider = schedulerProvider;
        this.flightService = flightService;
        this.flightDao = flightDao;
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
