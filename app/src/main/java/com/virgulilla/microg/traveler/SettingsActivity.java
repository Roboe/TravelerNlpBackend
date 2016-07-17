package com.virgulilla.microg.traveler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    double[] coordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocationPrefs prefs = LocationPrefs.getInstance(getApplicationContext());
        coordinates = prefs.getOriginLocation();

        prefs.setOriginLocation(40.42412, -3.71733); // Templo de Debod
        coordinates = prefs.getOriginLocation();
    }
}
