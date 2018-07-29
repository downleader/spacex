package com.example.spacex.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spacex.R;
import com.example.spacex.databinding.FragmentFlightListBinding;
import com.example.spacex.di.component.FragmentComponent;
import com.example.spacex.ui.adapter.FlightAdapter;
import com.example.spacex.utils.listener.OnFlightClickListener;
import com.example.spacex.viewmodel.FlightListViewModel;
import com.example.spacex.viewmodel.factory.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FlightListFragment extends BaseFragment {

    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.flight_list)
    RecyclerView flightList;

    private FlightListViewModel viewModel;
    private FlightAdapter flightAdapter;
    private Unbinder unbinder;

    public static FlightListFragment newInstance() {
        Bundle arguments = new Bundle();
        FlightListFragment fragment = new FlightListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentFlightListBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_flight_list, container, false);
        binding.setViewModel(viewModel);
        View root = binding.getRoot();
        unbinder = ButterKnife.bind(this, root);
        setupFlightList();
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FlightListViewModel.class);
        viewModel.getFlights().observe(this, (items) -> {
            if (items != null) {
                flightAdapter.setItems(items);   
            }
        });
    }

    private void setupFlightList() {
        flightAdapter = new FlightAdapter();
        flightAdapter.setOnFlightClickListener((flight) -> {
            FragmentActivity activity = getActivity();
            if (activity instanceof OnFlightClickListener) {
                OnFlightClickListener listener = (OnFlightClickListener) activity;
                listener.onFlightClick(flight);
            }
        });
        flightList.setAdapter(flightAdapter);
        flightList.setHasFixedSize(true);
        flightList.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
    }
}
