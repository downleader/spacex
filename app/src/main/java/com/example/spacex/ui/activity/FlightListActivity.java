package com.example.spacex.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;

import com.example.spacex.R;
import com.example.spacex.data.response.Flight;
import com.example.spacex.di.component.ActivityComponent;
import com.example.spacex.ui.fragment.FlightDetailsFragment;
import com.example.spacex.ui.fragment.FlightListFragment;
import com.example.spacex.utils.NavigationUtils;
import com.example.spacex.utils.UiUtils;
import com.example.spacex.utils.listener.OnFlightClickListener;
import com.example.spacex.viewmodel.FlightSelectionViewModel;

import javax.inject.Inject;

public class FlightListActivity extends BaseActivity implements OnFlightClickListener {

    @Inject
    FlightSelectionViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isTablet = UiUtils.isTablet();
        if (isTablet) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.activity_flight_list);
        viewModel.init(new ModelListener());
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.list_container, FlightListFragment.newInstance());
            if (isTablet) {
                transaction.replace(R.id.details_container, FlightDetailsFragment.newInstance());
            }
            transaction.commit();
        }
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
    public void onFlightClick(@NonNull Flight flight) {
        viewModel.selectFlight(flight);
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    private void openFlightDetails(@NonNull Flight flight) {
        if (UiUtils.isTablet()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.details_container, FlightDetailsFragment.newInstance(flight))
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        } else {
            NavigationUtils.openDetails(this, flight);
        }
    } 

    private class ModelListener implements FlightSelectionViewModel.Listener {

        @Override
        public void onFlightSelected(@NonNull Flight flight) {
            openFlightDetails(flight);
        }
    }
}
