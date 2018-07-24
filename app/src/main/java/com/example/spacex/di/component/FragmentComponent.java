package com.example.spacex.di.component;

import com.example.spacex.di.module.FragmentModule;
import com.example.spacex.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = {ApplicationComponent.class}, modules = {FragmentModule.class})
public interface FragmentComponent {
}
