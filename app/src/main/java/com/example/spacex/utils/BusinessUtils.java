package com.example.spacex.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.spacex.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class BusinessUtils {

    private static final long MILLIS_IN_SECOND = TimeUnit.SECONDS.toMillis(1);
    private static final SimpleDateFormat launchDateFormat = new SimpleDateFormat(
            "HH:mm:ss MM/dd/yyyy", Locale.getDefault());

    @NonNull
    public static String formatMissionName(@Nullable Integer flightNumber, @Nullable String missionName) {
        return UiUtils.getString(R.string.mission_name_format,
                safeUnbox(flightNumber), missionName);
    }

    @NonNull
    public static String formatLaunchDate(@Nullable Long launchDateUnix) {
        long timestamp = safeUnbox(launchDateUnix) * MILLIS_IN_SECOND;
        return launchDateFormat.format(new Date(timestamp));
    }

    private static long safeUnbox(@Nullable Long value) {
        return (value != null) ? value : 0;
    }

    private static int safeUnbox(@Nullable Integer value) {
        return (value != null) ? value : 0;
    }

    private BusinessUtils() {
    }
}
