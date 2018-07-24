package com.example.spacex.utils.rxjava;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
