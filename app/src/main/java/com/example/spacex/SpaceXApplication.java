package com.example.spacex;

import android.app.Application;

import com.example.spacex.di.component.ApplicationComponent;
import com.example.spacex.di.component.DaggerApplicationComponent;
import com.example.spacex.di.module.ApplicationModule;

import io.reactivex.plugins.RxJavaPlugins;

import timber.log.Timber;

public class SpaceXApplication extends Application {

    private static SpaceXApplication instance;

    private ApplicationComponent applicationComponent;

    public static SpaceXApplication getInstance() {
        return instance;
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDagger();
        initRxJava();
        initTimber();
    }

    private void initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initRxJava() {
        RxJavaPlugins.setErrorHandler(Timber::w);
    }

    private void initTimber() {
        Timber.plant(new Timber.DebugTree());
    }
}
