package com.example.spacex.di.module;

import android.app.Application;
import android.content.Context;

import com.example.spacex.utils.rxjava.SchedulerProviderImpl;
import com.example.spacex.utils.rxjava.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context getApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    SchedulerProvider getSchedulerProvider() {
        return new SchedulerProviderImpl();
    }
}
