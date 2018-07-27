package com.example.spacex.utils.listener;

import android.support.annotation.NonNull;

import com.example.spacex.data.response.Flight;

public interface OnFlightClickListener {
    void onFlightClick(@NonNull Flight flight);
}
