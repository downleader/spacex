package com.example.spacex.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.spacex.R;
import com.example.spacex.databinding.ActivityFlightDetailsBinding;
import com.example.spacex.di.component.ActivityComponent;
import com.example.spacex.viewmodel.FlightDetailsViewModel;

import javax.inject.Inject;

public class FlightDetailsActivity extends BaseActivity {

    public static final String KEY_FLIGHT = "flight";

    @Inject
    FlightDetailsViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFlightDetailsBinding flightDetailsBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_flight_details);
        flightDetailsBinding.setViewModel(viewModel);
        viewModel.init(getIntent().getParcelableExtra(KEY_FLIGHT));
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
}
