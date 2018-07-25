package com.example.spacex.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.example.spacex.R;
import com.example.spacex.data.response.Flight;
import com.example.spacex.domain.datamodel.FlightDataModel;
import com.example.spacex.utils.rxjava.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

import timber.log.Timber;

public class FlightListViewModel extends BaseViewModel {

    private final FlightDataModel dataModel;
    private final SchedulerProvider schedulerProvider;

    private boolean flightsLoaded;
    private Listener listener;

    @Inject
    FlightListViewModel(FlightDataModel dataModel, SchedulerProvider schedulerProvider) {
        this.dataModel = dataModel;
        this.schedulerProvider = schedulerProvider;
    }

    public void init(@NonNull Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!flightsLoaded) {
            disposable.add(dataModel.getFlights()
                    .observeOn(schedulerProvider.ui())
                    .doAfterTerminate(() -> flightsLoaded = true)
                    .subscribeWith(new FlightsObserver()));
        }
    }

    private class FlightsObserver extends DisposableSingleObserver<List<Flight>> {

        @Override
        public void onSuccess(List<Flight> flights) {
            listener.onFlightsLoaded(flights);
        }

        @Override
        public void onError(Throwable throwable) {
            Timber.w(throwable);
            listener.onLoadingError(R.string.error_unexpected);
        }
    }

    public interface Listener {
        void onFlightsLoaded(@NonNull List<Flight> flights);
        void onLoadingError(@StringRes int errorMessageId);
    }
}
