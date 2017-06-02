package com.naprimer.mike.weatherbrick;

/**
 * Created by m on 20.05.17.
 */

public class WeatherEntry {

    final private String day;
    final private String w_cond;

    public WeatherEntry(String day, String w_cond) {
        this.day = day;
        this.w_cond = w_cond;
    }

    public String getDay() {
        return day;
    }

    public String getW_cond() {
        return w_cond;
    }
}
