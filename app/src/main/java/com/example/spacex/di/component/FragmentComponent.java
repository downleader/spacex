package com.example.spacex.di.component;

import com.example.spacex.di.module.FragmentModule;
import com.example.spacex.di.scope.FragmentScope;
import com.example.spacex.ui.fragment.FlightDetailsFragment;
import com.example.spacex.ui.fragment.FlightListFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = {ApplicationComponent.class}, modules = {FragmentModule.class})
public interface FragmentComponent {
    void inject(FlightListFragment fragment);
    void inject(FlightDetailsFragment fragment);
}
