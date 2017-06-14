package com.naprimer.mike.weatherbrick.WeatherDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.naprimer.mike.weatherbrick.WeatherEntry;

import java.util.ArrayList;

//TODO do i need two separate arrays (Arraylist<WeatherEntrys> and ContentValues)
public class WeatherDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WeatherBrick.db";



    public WeatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WeatherContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO consider rewrite this to make use
        db.execSQL(WeatherContract.SQL_DELETE_TABLE);
        onCreate(db);
    }
    
    //TODO rewrite this?
    public ContentValues[] createCtArray(ArrayList<WeatherEntry> entries) {
        ContentValues[] ct = new ContentValues[entries.size()];
        for (int i = 0; i < entries.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(WeatherContract.WeatherTable.COLUMN_DATE, entries.get(i).getDate());
            values.put(WeatherContract.WeatherTable.COLUMN_CONDITION, entries.get(i).getCondition());
            values.put(WeatherContract.WeatherTable.COLUMN_WINDDIR, entries.get(i).getWindDir());
            values.put(WeatherContract.WeatherTable.COLUMN_WINDSPEED, entries.get(i).getWindSpeed());
            values.put(WeatherContract.WeatherTable.COLUMN_TEMPDAY, entries.get(i).getTempDay());

            ct[i] = values;
        }
        return ct;
    }
    
}
