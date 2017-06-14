package com.naprimer.mike.weatherbrick;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

//TODO get one free day to understand xml parser and rewrite like in google
class ParseUtils {

    private static final String TAG = "mTAG";

    // We don't use namespaces
    private static final String ns = null;


    private ParseUtils() {
        throw new AssertionError();
    }

    static ArrayList<WeatherEntry> parseXml(InputStream in) throws XmlPullParserException, IOException {

        ArrayList<WeatherEntry> weatherEntryList = new ArrayList<>();

        //initialize parser
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(in, null);

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                switch (parser.getName()) {
                    case "location":
                        //TODO parse location
                        break;

                    case "time":
                        weatherEntryList.add(parseWeather(parser));
                        break;
                }
            }
        }
        return weatherEntryList;

    }


    //TODO put here require just for lulz
    private static WeatherEntry parseWeather(XmlPullParser parser) throws IOException, XmlPullParserException {

        String day = "";
        String condition = "";
        String windDir = "";
        String windSpeed = "";
        String tempDay = "";

        parser.require(XmlPullParser.START_TAG, ns, "time");

        do {
            if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName().equals("time")) {
                day = parser.getAttributeValue(0);
            } else if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName().equals("symbol")) {
                condition = parser.getAttributeValue(1);
            } else if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName().equals("windDirection")) {
                windDir = parser.getAttributeValue(1);
            } else if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName().equals("windSpeed")) {
                windSpeed = parser.getAttributeValue(0);
            } else if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName().equals("temperature")) {
                tempDay = parser.getAttributeValue(4);
            }
            parser.next();
        } while (!parser.getName().equals("time"));


        return new WeatherEntry(day, condition, windDir, windSpeed, tempDay);
    }




    //TODO do i need this?
    static void decode_weather(String code) {
        int intCode = Integer.parseInt(code);

        if (intCode < 200) {
            // 0 - 199
            // default
        } else if (intCode < 300) {
            // 200 - 299
            // thunderstorm
        } else if (intCode < 400) {
            // 300 - 399
            // drizzle
        } else if (intCode < 500) {
            // 400 - 499
            // nothing
        } else if (intCode < 600) {
            // 500 - 599
        } else if (intCode < 700) {
            // 600 - 699
        } else if (intCode < 800) {
            // 700 - 799
        } else if (intCode < 900) {
            // 800- 899
        } else if (intCode < 1000) {
            // 900 - 999
        }
    }


}
