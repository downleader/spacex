package com.example.spacex.di.component;

import android.content.Context;

import com.example.spacex.di.module.ApplicationModule;
import com.example.spacex.domain.repository.DataRepository;
import com.example.spacex.utils.rxjava.SchedulerProvider;
import com.example.spacex.viewmodel.factory.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Context getApplicationContext();

    ViewModelFactory getViewModelFactory();

    SchedulerProvider getSchedulerProvider();

    DataRepository getDataRepository();
}
