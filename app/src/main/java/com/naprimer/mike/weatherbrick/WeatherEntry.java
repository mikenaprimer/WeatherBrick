package com.naprimer.mike.weatherbrick;

public class WeatherEntry {

    final private String date;
    final private String condition;
    final private String windDir;
    final private String windSpeed;
    final private String tempDay;

    WeatherEntry(
                        String date,
                        String condition,
                        String windDir,
                        String windSpeed,
                        String tempDay) {

        this.date = date;
        this.condition = condition;
        this.windDir = windDir;
        this.windSpeed = windSpeed;
        this.tempDay = tempDay;
    }

    public String getDate() {
        return date;
    }

    public String getCondition() {
        return condition;
    }

    public String getWindDir() {
        return windDir;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getTempDay() {
        return tempDay;
    }


}
