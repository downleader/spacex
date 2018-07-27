package com.example.spacex.utils;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.spacex.data.response.Flight;
import com.example.spacex.data.response.LaunchSite;
import com.example.spacex.data.response.Links;
import com.example.spacex.data.response.Rocket;

import com.google.gson.Gson;

import static com.example.spacex.persistence.sqlite.SQLiteContract.Flight.*;

public final class PersistenceUtils {

    @NonNull
    public static Flight fromCursor(@NonNull Cursor cursor, @NonNull Gson gson) {
        return Flight.builder()
                .setFlightNumber(cursor.getInt(cursor.getColumnIndex(COLUMN_FLIGHT_NUMBER)))
                .setMissionName(cursor.getString(cursor.getColumnIndex(COLUMN_MISSION_NAME)))
                .setLaunchDateUnix(cursor.getLong(cursor.getColumnIndex(COLUMN_LAUNCH_DATE)))
                .setRocket(gson.fromJson(cursor.getString(cursor
                        .getColumnIndex(COLUMN_ROCKET)), Rocket.class))
                .setLaunchSite(gson.fromJson(cursor.getString(cursor
                        .getColumnIndex(COLUMN_LAUNCH_SITE)), LaunchSite.class))
                .setLaunchSuccess(toBoolean(cursor.getInt(cursor
                        .getColumnIndex(COLUMN_LAUNCH_SUCCESS))))
                .setLinks(gson.fromJson(cursor.getString(cursor
                        .getColumnIndex(COLUMN_LINKS)), Links.class))
                .setDetails(cursor.getString(cursor.getColumnIndex(COLUMN_DETAILS)))
                .build();
    }

    public static int fromBoolean(@Nullable Boolean value) {
        return BusinessUtils.safeUnbox(value) ? 1 : 0;
    }

    private static boolean toBoolean(int value) {
        return (value != 0);
    }

    private PersistenceUtils() {
    }
}
