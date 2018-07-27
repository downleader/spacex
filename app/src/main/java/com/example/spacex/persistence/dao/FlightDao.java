package com.example.spacex.persistence.dao;

import android.support.annotation.NonNull;

import com.example.spacex.data.response.Flight;

import java.util.List;

public interface FlightDao {

    @NonNull
    List<Flight> getFlights();

    void setFlights(@NonNull List<Flight> flights);
}
