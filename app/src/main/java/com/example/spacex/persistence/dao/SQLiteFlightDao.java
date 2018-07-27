package com.example.spacex.persistence.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.spacex.data.response.Flight;
import com.example.spacex.persistence.sqlite.SQLiteContract;
import com.example.spacex.persistence.sqlite.SQLiteHelper;
import com.example.spacex.utils.BusinessUtils;
import com.example.spacex.utils.PersistenceUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SQLiteFlightDao implements FlightDao {

    private static final int BINDING_START_INDEX = 1;

    private static final String PREPARED_FLIGHT_INSERT
            = "INSERT INTO " + SQLiteContract.Flight.TABLE_NAME + "("
            + SQLiteContract.Flight.COLUMN_FLIGHT_NUMBER + ","
            + SQLiteContract.Flight.COLUMN_MISSION_NAME + ","
            + SQLiteContract.Flight.COLUMN_LAUNCH_DATE + ","
            + SQLiteContract.Flight.COLUMN_LAUNCH_SITE + ","
            + SQLiteContract.Flight.COLUMN_LAUNCH_SUCCESS + ","
            + SQLiteContract.Flight.COLUMN_ROCKET + ","
            + SQLiteContract.Flight.COLUMN_DETAILS
            + ") VALUES(?,?,?,?,?,?,?)";

    private final SQLiteHelper helper;
    private final Gson gson;

    public SQLiteFlightDao(SQLiteHelper helper, Gson gson) {
        this.helper = helper;
        this.gson = gson;
    }

    @NonNull
    @Override
    public List<Flight> getFlights() {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = database.query(SQLiteContract.Flight.TABLE_NAME,
                    null, null, null,
                    null, null, null);
            List<Flight> flights = new ArrayList<>();
            if ((cursor != null) && (cursor.getCount() > 0)) {
                while (cursor.moveToNext()) {
                    flights.add(PersistenceUtils.fromCursor(cursor, gson));
                }
            }
            return flights;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void setFlights(@NonNull List<Flight> flights) {
        SQLiteDatabase database = helper.getWritableDatabase();
        database.beginTransaction();
        try {
            SQLiteStatement statement = database.compileStatement(PREPARED_FLIGHT_INSERT);
            for (Flight flight : flights) {
                bindArguments(statement, flight);
                statement.executeInsert();
                statement.clearBindings();
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    private void bindArguments(@NonNull SQLiteStatement statement,@NonNull Flight flight) {
        int index = BINDING_START_INDEX;
        statement.bindLong(index++, BusinessUtils.safeUnbox(flight.flightNumber()));
        bindStringOrNull(statement, flight.missionName(), index++);
        statement.bindLong(index++, BusinessUtils.safeUnbox(flight.launchDateUnix()));
        statement.bindString(index++, gson.toJson(flight.launchSite()));
        statement.bindLong(index++, PersistenceUtils.fromBoolean(flight.launchSuccess()));
        statement.bindString(index++, gson.toJson(flight.rocket()));
        bindStringOrNull(statement, flight.details(), index);
    }

    private static void bindStringOrNull(@NonNull SQLiteStatement statement,
                                         @Nullable String value, int index) {
        if (value != null) {
            statement.bindString(index, value);
        } else {
            statement.bindNull(index);
        }
    }
}
