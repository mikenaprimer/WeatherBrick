package com.naprimer.mike.weatherbrick.WeatherDatabase;

import android.provider.BaseColumns;

final class WeatherContract {

    // Private constructor
    private WeatherContract() {}

    // Inner class defines table contents
    static class WeatherTable implements BaseColumns {
        static final String TABLE_NAME = "weather";
        static final String COLUMN_DATE = "date";
        static final String COLUMN_CONDITION = "condition";
        static final String COLUMN_WINDDIR = "windDir";
        static final String COLUMN_WINDSPEED = "windSpeed";
        static final String COLUMN_TEMPDAY = "tempDay";
    }

    static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + WeatherTable.TABLE_NAME + " (" +
                    WeatherTable._ID + " INTEGER PRIMARY KEY," +
                    WeatherTable.COLUMN_DATE + " TEXT," +
                    WeatherTable.COLUMN_CONDITION + " TEXT," +
                    WeatherTable.COLUMN_WINDDIR + " TEXT," +
                    WeatherTable.COLUMN_WINDSPEED + " TEXT," +
                    WeatherTable.COLUMN_TEMPDAY + " TEXT," +
                    ")";

    static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + WeatherTable.TABLE_NAME;


}
