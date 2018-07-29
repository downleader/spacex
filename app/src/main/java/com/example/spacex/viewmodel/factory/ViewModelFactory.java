package com.example.spacex.viewmodel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.spacex.SpaceXApp;
import com.example.spacex.di.component.DaggerViewModelComponent;
import com.example.spacex.di.component.ViewModelComponent;
import com.example.spacex.viewmodel.FlightDetailsViewModel;
import com.example.spacex.viewmodel.FlightListViewModel;
import com.example.spacex.viewmodel.FlightSelectionViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.equals(FlightListViewModel.class)) {
            FlightListViewModel viewModel = new FlightListViewModel();
            createComponent().inject(viewModel);
            return (T) viewModel;
        }
        if (modelClass.equals(FlightDetailsViewModel.class)) {
            FlightDetailsViewModel viewModel = new FlightDetailsViewModel();
            createComponent().inject(viewModel);
            return (T) viewModel;
        }
        if (modelClass.equals(FlightSelectionViewModel.class)) {
            FlightSelectionViewModel viewModel = new FlightSelectionViewModel();
            createComponent().inject(viewModel);
            return (T) viewModel;
        }
        throw new IllegalArgumentException("Unknown viewmodel class: " + modelClass);
    }

    @NonNull
    private static ViewModelComponent createComponent() {
        return DaggerViewModelComponent.builder()
                .applicationComponent(SpaceXApp.getInstance().getComponent())
                .build();
    }
}
