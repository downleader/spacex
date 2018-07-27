package com.example.spacex.di.module;

import android.app.Application;
import android.content.Context;

import com.example.spacex.domain.repository.CachingDataRepository;
import com.example.spacex.domain.repository.DataRepository;
import com.example.spacex.domain.service.FlightService;
import com.example.spacex.persistence.dao.FlightDao;
import com.example.spacex.persistence.dao.SQLiteFlightDao;
import com.example.spacex.persistence.sqlite.SQLiteHelper;
import com.example.spacex.utils.gson.GsonAdapterFactory;
import com.example.spacex.utils.rxjava.SchedulerProvider;
import com.example.spacex.utils.rxjava.SchedulerProviderImpl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import okhttp3.OkHttpClient;

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

    @Provides
    @Singleton
    OkHttpClient.Builder getOkHttpClientBuilder() {
        return new OkHttpClient.Builder();
    }

    @Provides
    @Singleton
    Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(GsonAdapterFactory.create())
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();
    }

    @Provides
    @Singleton
    SQLiteHelper getSQLiteHelper(Context context) {
        return new SQLiteHelper(context);
    }

    @Provides
    @Singleton
    FlightDao getFlightDao(SQLiteHelper helper, Gson gson) {
        return new SQLiteFlightDao(helper, gson);
    }

    @Provides
    @Singleton
    FlightService getFlightService(OkHttpClient.Builder okHttpClientBuilder, Gson gson) {
        return new FlightService(okHttpClientBuilder, gson);
    }

    @Provides
    @Singleton
    DataRepository getDataRepository(SchedulerProvider schedulerProvider,
                                     FlightService flightService, FlightDao flightDao) {
        return new CachingDataRepository(schedulerProvider, flightService, flightDao);
    }
}
