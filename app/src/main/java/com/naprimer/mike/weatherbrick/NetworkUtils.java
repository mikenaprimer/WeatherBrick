package com.naprimer.mike.weatherbrick;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

final class NetworkUtils {

    private static final String TAG = "mTAG";

    private NetworkUtils() {
        throw new AssertionError();
    }

    @NonNull
    private static HttpUrl buildUrl() {
        return new HttpUrl.Builder()
                .scheme("http")
                .host("api.openweathermap.org")
                .addPathSegments("data/2.5/forecast/daily")
                .addQueryParameter("mode", "xml")
                .addQueryParameter("lat", "58.5988055")
                .addQueryParameter("lon", "49.6823645")
                .addQueryParameter("units", "metric")
                .addQueryParameter("cnt", "10")
                .addQueryParameter("appid", "c6bff50ef61dffe1e7f305095dd7ca31")
                .build();
    }

    static Request buildRequest() {
        return new Request.Builder()
                .url(buildUrl())
                .build();
    }

}
