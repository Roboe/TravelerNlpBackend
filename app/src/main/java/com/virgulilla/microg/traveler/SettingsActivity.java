package com.virgulilla.microg.traveler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SettingsActivity extends AppCompatActivity {

    double[] coordinates;
    String TEST_STRING = "Latitude: %s | Longitud: %s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocationPrefs prefs = LocationPrefs.getInstance(this);
        coordinates = prefs.getOriginLocation();
        Log.i("test", String.format(TEST_STRING, coordinates[0], coordinates[1]));

        prefs.setOriginLocation(40.42371, -3.71578);
        coordinates = prefs.getOriginLocation();
        Log.i("test", String.format(TEST_STRING, coordinates[0], coordinates[1]));
    }
}
