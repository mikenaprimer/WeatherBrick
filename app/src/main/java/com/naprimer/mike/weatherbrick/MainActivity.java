package com.naprimer.mike.weatherbrick;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.naprimer.mike.weatherbrick.WeatherDatabase.WeatherDbHelper;
import com.naprimer.mike.weatherbrick.databinding.ActivityMainBinding;

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
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: start");
        //setContentView(R.layout.activity_main);

        //TODO check is database is uptodated:
        // yes - load data from DB
        // no -
        //
        WeatherDbHelper weatherDbHelper = new WeatherDbHelper(getApplicationContext());


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

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
            InputStream in = response.body().byteStream();
            try {


                ArrayList<WeatherEntry> entries = ParseUtils.parseXml(in);


                binding.setWeatherEntry(entries.get(0));


            } catch (XmlPullParserException e) {
                //TODO handle error
                e.printStackTrace();
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
            Log.d(TAG, "day: " + we.getDate());
            Log.d(TAG, "w_cond: " + we.getCondition());
        }
    }
}
