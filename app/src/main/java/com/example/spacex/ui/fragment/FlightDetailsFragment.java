package com.example.spacex.ui.fragment;

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
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FlightDetailsFragment extends BaseFragment {

    private static final String ARG_FLIGHT = "flight";

    @Inject
    FlightDetailsViewModel viewModel;

    @BindView(R.id.launch_photo)
    ImageView photoView;

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
        Bundle arguments = getArguments();
        if (arguments != null) {
            Flight flight = arguments.getParcelable(ARG_FLIGHT);
            if (flight != null) {
                viewModel.init(flight, new ModelListener());
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
        viewModel.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.onStop();
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

    private class ModelListener implements FlightDetailsViewModel.Listener {

        @Override
        public void loadPhoto(@NonNull String photoUrl) {
            Picasso.with(getContext())
                    .load(photoUrl)
                    .error(R.drawable.ic_launcher_background)
                    .into(photoView);
        }
    }
}
