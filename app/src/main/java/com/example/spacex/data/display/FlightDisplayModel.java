package com.example.spacex.data.display;

import com.example.spacex.data.response.Flight;

public class FlightDisplayModel {

    private final Flight flight;

    public FlightDisplayModel(Flight flight) {
        this.flight = flight;
    }

    public Flight getFlight() {
        return flight;
    }
}
