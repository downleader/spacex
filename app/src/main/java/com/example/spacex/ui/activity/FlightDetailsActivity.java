package com.example.spacex.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.spacex.R;
import com.example.spacex.di.component.ActivityComponent;
import com.example.spacex.ui.fragment.FlightDetailsFragment;

public class FlightDetailsActivity extends BaseActivity {

    public static final String KEY_FLIGHT = "flight";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.details_container, FlightDetailsFragment.newInstance(
                            getIntent().getParcelableExtra(KEY_FLIGHT)))
                    .commit();
        }
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
