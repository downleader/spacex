package com.example.spacex.utils.arch;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

public class EventLiveData<Event> extends MutableLiveData<Event> {

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<Event> observer) {
        super.observe(owner, (event) -> {
            if (event == null) {
                return;
            }
            observer.onChanged(event);
            setValue(null);
        });
    }

    public void sendEvent(@NonNull Event event) {
        setValue(event);
    }
}
