package com.example.spacex.persistence.sqlite;

public final class SQLiteContract {

    public static final class Flight {
        public static final String TABLE_NAME = "flight";
        public static final String COLUMN_FLIGHT_NUMBER = "flight_number";
        public static final String COLUMN_MISSION_NAME = "mission_name";
        public static final String COLUMN_LAUNCH_DATE = "launch_date";
        public static final String COLUMN_LAUNCH_SITE = "launch_site";
        public static final String COLUMN_LAUNCH_SUCCESS = "launch_success";
        public static final String COLUMN_ROCKET = "rocket";
        public static final String COLUMN_DETAILS = "details";
    }

    private SQLiteContract() {
    }
}
