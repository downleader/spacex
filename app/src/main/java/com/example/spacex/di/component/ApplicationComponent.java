package com.example.spacex.di.component;

import android.content.Context;

import com.example.spacex.di.module.ApplicationModule;
import com.example.spacex.domain.repository.DataRepository;
import com.example.spacex.domain.service.DataService;
import com.example.spacex.utils.rxjava.SchedulerProvider;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Context getApplicationContext();

    SchedulerProvider getSchedulerProvider();

    OkHttpClient.Builder getOkHttpClientBuilder();

    Gson getGson();

    DataService getDataService();

    DataRepository getDataRepository();
}
