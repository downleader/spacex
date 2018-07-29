package com.example.spacex.viewmodel;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.spacex.data.event.FlightSelectionEvent;
import com.example.spacex.data.response.Flight;
import com.example.spacex.utils.UiUtils;
import com.example.spacex.utils.arch.EventLiveData;

public class FlightSelectionViewModel extends BaseViewModel {

    private final EventLiveData<FlightSelectionEvent> selectedFlight = new EventLiveData<>();

    private Flight currentFlight;

    @NonNull
    public LiveData<FlightSelectionEvent> getSelectedFlight() {
        return selectedFlight;
    }

    public void selectFlight(@NonNull Flight flight) {
        boolean isTablet = UiUtils.isTablet();
        if ((!isTablet) || (currentFlight == null) || (!currentFlight.equals(flight))) {
            currentFlight = flight;
            selectedFlight.sendEvent(new FlightSelectionEvent(flight, isTablet));
        }
    }
}
