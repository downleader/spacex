package com.example.spacex.di.component;

import com.example.spacex.di.module.ViewModelModule;
import com.example.spacex.di.scope.ViewModelScope;
import com.example.spacex.viewmodel.FlightDetailsViewModel;
import com.example.spacex.viewmodel.FlightListViewModel;
import com.example.spacex.viewmodel.FlightSelectionViewModel;

import dagger.Component;

@ViewModelScope
@Component(dependencies = {ApplicationComponent.class}, modules = {ViewModelModule.class})
public interface ViewModelComponent {

    void inject(FlightListViewModel viewModel);

    void inject(FlightDetailsViewModel viewModel);

    void inject(FlightSelectionViewModel viewModel);
}
