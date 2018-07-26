package com.example.spacex.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.spacex.data.response.Flight;
import com.example.spacex.ui.activity.FlightDetailsActivity;

public final class NavigationUtils {

    public static void openDetails(@NonNull Context context, @NonNull Flight flight) {
        Intent intent = new Intent(context, FlightDetailsActivity.class);
        intent.putExtra(FlightDetailsActivity.KEY_FLIGHT, flight);
        context.startActivity(intent);
    }

    private NavigationUtils() {
    }
}
