package com.example.spacex.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.spacex.R;
import com.example.spacex.data.display.FlightItemDisplayModel;
import com.example.spacex.databinding.ActivityFlightListBinding;
import com.example.spacex.di.component.ActivityComponent;
import com.example.spacex.ui.adapter.FlightAdapter;
import com.example.spacex.viewmodel.FlightListViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlightListActivity extends BaseActivity {

    @Inject
    FlightListViewModel viewModel;

    @BindView(R.id.flight_list)
    RecyclerView flightList;

    private FlightAdapter flightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFlightListBinding flightListBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_flight_list);
        viewModel.init(new ModelListener());
        flightListBinding.setViewModel(viewModel);
        ButterKnife.bind(this);
        setupFlightList();
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

    private void setupFlightList() {
        flightAdapter = new FlightAdapter();
        flightList.setAdapter(flightAdapter);
        flightList.setHasFixedSize(true);
        flightList.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
    }

    private class ModelListener implements FlightListViewModel.Listener {

        @Override
        public void onFlightsLoaded(@NonNull List<FlightItemDisplayModel> flights) {
            flightAdapter.setItems(flights);
        }
    }
}
