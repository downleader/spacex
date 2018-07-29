package com.example.spacex.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.spacex.R;
import com.example.spacex.data.response.Flight;
import com.example.spacex.databinding.FragmentFlightDetailsBinding;
import com.example.spacex.di.component.FragmentComponent;
import com.example.spacex.viewmodel.FlightDetailsViewModel;
import com.example.spacex.viewmodel.factory.ViewModelFactory;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FlightDetailsFragment extends BaseFragment {

    private static final String ARG_FLIGHT = "flight";

    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.launch_photo)
    ImageView photoView;

    private FlightDetailsViewModel viewModel;
    private Unbinder unbinder;

    public static FlightDetailsFragment newInstance() {
        return newInstance(null);
    }

    public static FlightDetailsFragment newInstance(@Nullable Flight flight) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARG_FLIGHT, flight);
        FlightDetailsFragment fragment = new FlightDetailsFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViewModel();
        Bundle arguments = getArguments();
        if (arguments != null) {
            Flight flight = arguments.getParcelable(ARG_FLIGHT);
            if (flight != null) {
                viewModel.init(flight);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentFlightDetailsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_flight_details, container, false);
        binding.setViewModel(viewModel);
        View root = binding.getRoot();
        unbinder = ButterKnife.bind(this, root);
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FlightDetailsViewModel.class);
        viewModel.getPhotoUrl().observe(this, (photoUrl) -> {
            if (photoUrl != null) {
                Picasso.with(getContext())
                        .load(photoUrl)
                        .error(R.drawable.ic_launcher_background)
                        .into(photoView);
            }
        });
    }
}
