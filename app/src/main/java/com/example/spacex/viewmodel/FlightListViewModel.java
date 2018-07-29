package com.example.spacex.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.spacex.R;
import com.example.spacex.data.display.FlightItemDisplayModel;
import com.example.spacex.data.response.Flight;
import com.example.spacex.domain.datamodel.FlightDataModel;
import com.example.spacex.utils.rxjava.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

import timber.log.Timber;

import static com.example.spacex.utils.UiUtils.getString;

public class FlightListViewModel extends BaseViewModel {

    public final ObservableBoolean isLoading = new ObservableBoolean();
    public final ObservableBoolean isListVisible = new ObservableBoolean();
    public final ObservableField<String> message = new ObservableField<>();

    @Inject
    FlightDataModel dataModel;

    @Inject
    SchedulerProvider schedulerProvider;

    private final MutableLiveData<List<FlightItemDisplayModel>> flights = new MutableLiveData<>();

    private boolean flightsLoaded;

    @NonNull
    public LiveData<List<FlightItemDisplayModel>> getFlights() {
        return flights;
    }

    public void loadData() {
        if (!flightsLoaded) {
            isLoading.set(true);
            disposable.add(dataModel.getFlights()
                    .map(this::mapFlights)
                    .observeOn(schedulerProvider.ui())
                    .doAfterTerminate(() -> {
                        isLoading.set(false);
                        flightsLoaded = true;
                    })
                    .subscribeWith(new FlightsObserver()));
        }
    }

    @NonNull
    private List<FlightItemDisplayModel> mapFlights(@NonNull List<Flight> flights) {
        List<FlightItemDisplayModel> models = new ArrayList<>(flights.size());
        for (Flight flight : flights) {
            models.add(mapFlight(flight));
        }
        return models;
    }

    @NonNull
    private FlightItemDisplayModel mapFlight(@NonNull Flight flight) {
        return new FlightItemDisplayModel(flight);
    }

    private class FlightsObserver extends DisposableSingleObserver<List<FlightItemDisplayModel>> {

        @Override
        public void onSuccess(List<FlightItemDisplayModel> flightList) {
            if (flightList.isEmpty()) {
                isListVisible.set(false);
                message.set(getString(R.string.error_no_data));
            } else {
                isListVisible.set(true);
                flights.setValue(flightList);
            }
        }

        @Override
        public void onError(Throwable throwable) {
            isListVisible.set(false);
            message.set(getString(R.string.error_unexpected));
            Timber.w(throwable);
        }
    }
}
