package com.example.spacex.persistence.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "spacex.db";

    private static final int DATABASE_VERSION_INITIAL = 1;
    private static final int DATABASE_VERSION = DATABASE_VERSION_INITIAL;

    private static final String CREATE_TABLE_FLIGHT
            = "CREATE TABLE IF NOT EXISTS " + SQLiteContract.Flight.TABLE_NAME + "("
            + SQLiteContract.Flight._ID + " INTEGER PRIMARY KEY, "
            + SQLiteContract.Flight.COLUMN_FLIGHT_NUMBER + " INTEGER, "
            + SQLiteContract.Flight.COLUMN_MISSION_NAME + " TEXT, "
            + SQLiteContract.Flight.COLUMN_LAUNCH_DATE + " INTEGER, "
            + SQLiteContract.Flight.COLUMN_LAUNCH_SITE + " TEXT, "
            + SQLiteContract.Flight.COLUMN_LAUNCH_SUCCESS + " INTEGER, "
            + SQLiteContract.Flight.COLUMN_ROCKET + " TEXT, "
            + SQLiteContract.Flight.COLUMN_LINKS + " TEXT, "
            + SQLiteContract.Flight.COLUMN_DETAILS + " TEXT"
            + ")";

    private static final String DROP_TABLE_FLIGHT
            = "DROP TABLE IF EXISTS " + SQLiteContract.Flight.TABLE_NAME;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_FLIGHT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(DROP_TABLE_FLIGHT);
        database.execSQL(CREATE_TABLE_FLIGHT);
    }
}
