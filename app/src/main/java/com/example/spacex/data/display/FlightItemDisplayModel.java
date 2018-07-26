package com.example.spacex.data.display;

import android.support.annotation.NonNull;

import com.example.spacex.data.response.Flight;

public class FlightItemDisplayModel {

    private final Flight flight;

    public FlightItemDisplayModel(@NonNull Flight flight) {
        this.flight = flight;
    }

    @NonNull
    public Flight getFlight() {
        return flight;
    }
}
