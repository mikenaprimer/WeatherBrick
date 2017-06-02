package com.naprimer.mike.weatherbrick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
        implements Callback {


    private static final String TAG = "mTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: start");
        //NetworkUtils networkUtils = new NetworkUtils();
        //networkUtils.fetchData();

        OkHttpClient client = new OkHttpClient();
        client.newCall(NetworkUtils.buildRequest()).enqueue(this);
    }

    //OKHttp3 Callback interface methods
    @Override
    public void onFailure(Call call, IOException e) {
        e.printStackTrace();
        Log.d(TAG, "onFailure: oops");
        //TODO handle errors
    }
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if(response.isSuccessful()) {
            //Log.d(TAG, "onResponse: " + NetworkUtils.buildRequest().toString());
            //Log.d(TAG, "onResponse() returned: " + response.body().string());
            InputStream in = response.body().byteStream();
            try {
                printRes(ParseUtils.parseXml(in));
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                //TODO handle errors
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        } else {
            //TODO handle errors
            Log.d(TAG, "error " + response);
            //throw new IOException("Unexpected code " + response);

        }

    }

    private void printRes(ArrayList<WeatherEntry> res) {
        for(WeatherEntry we : res) {
            Log.d(TAG, "printRes: " + we.getDay());
        }
    }
}
