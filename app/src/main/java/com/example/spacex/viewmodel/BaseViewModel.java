package com.example.spacex.viewmodel;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel {

    final CompositeDisposable disposable = new CompositeDisposable();

    public void onStart() {
    }

    public void onStop() {
        disposable.clear();
    }
}
