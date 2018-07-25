package com.example.spacex.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.example.spacex.R;
import com.example.spacex.data.response.Flight;
import com.example.spacex.di.component.ActivityComponent;
import com.example.spacex.viewmodel.FlightListViewModel;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    FlightListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel.init(new ModelListener());
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.onStop();
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    private class ModelListener implements FlightListViewModel.Listener {

        @Override
        public void onFlightsLoaded(@NonNull List<Flight> flights) {
        }

        @Override
        public void onLoadingError(@StringRes int errorMessageId) {
        }
    }
}
