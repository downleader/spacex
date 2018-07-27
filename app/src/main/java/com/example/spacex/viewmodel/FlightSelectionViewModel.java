package com.example.spacex.viewmodel;

import android.support.annotation.NonNull;

import com.example.spacex.data.response.Flight;

import javax.inject.Inject;

public class FlightSelectionViewModel extends BaseViewModel {

    private Flight currentFlight;
    private Listener listener;

    @Inject
    FlightSelectionViewModel() {
    }

    public void init(@NonNull Listener listener) {
        this.listener = listener;
    }

    public void selectFlight(@NonNull Flight flight) {
        if ((currentFlight == null) || (!currentFlight.equals(flight))) {
            currentFlight = flight;
            listener.onFlightSelected(flight);
        }
    }

    public interface Listener {
        void onFlightSelected(@NonNull Flight flight);
    }
}
