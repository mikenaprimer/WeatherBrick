package com.naprimer.mike.weatherbrick;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by m on 19.05.17.
 */

// TODO: 20.05.17 rewrite parser
class ParseUtils {

    private static final String TAG = "mTAG";

    //this is for namespace
    private static final String ns = null;

    private ParseUtils() {
        throw new AssertionError();
    }

    public static ArrayList<WeatherEntry> parseXml(InputStream in) throws XmlPullParserException, IOException {
        ArrayList<WeatherEntry> weatherEntryList = new ArrayList<>();
        //initialize parser
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(in, null);

        //int event = parser.getEventType();

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                switch (parser.getName()) {
                    case "location":
                        Log.d(TAG, "parseXml: location");
                        break;

                    case "time":
                        weatherEntryList.add(parseWeather(parser));
                }
            }
        }
        return weatherEntryList;

    }


    //put here require just for lulz
    private static WeatherEntry parseWeather(XmlPullParser parser) throws IOException, XmlPullParserException {
        String day = "";
        String w_cond = "";
        //i can leave only time, can i?
        while (parser.next() != XmlPullParser.END_TAG && !parser.getName().equals("time")) {
            if (parser.getName().equals("time")) {
                day = parser.getAttributeValue(0);
            } else if (parser.getName().equals("symbol")) {
                w_cond = parser.getAttributeValue(0);
            }

        }
        return new WeatherEntry(day, w_cond);
    }










}
