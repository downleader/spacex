package com.example.spacex.data.event;

import android.support.annotation.NonNull;

import com.example.spacex.data.response.Flight;

public class FlightSelectionEvent {

    private final Flight flight;
    private final boolean sameScreen;

    public FlightSelectionEvent(@NonNull Flight flight, boolean sameScreen) {
        this.flight = flight;
        this.sameScreen = sameScreen;
    }

    @NonNull
    public Flight getFlight() {
        return flight;
    }

    public boolean isSameScreen() {
        return sameScreen;
    }
}
