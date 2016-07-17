package com.virgulilla.microg.traveler;

import android.location.Location;

import org.microg.nlp.api.LocationBackendService;
import org.microg.nlp.api.LocationHelper;

public class BackendService extends LocationBackendService {

    private static final String PROVIDER = "traveler";

    private LocationPrefs prefs;

    @Override
    protected void onOpen() {
        prefs = LocationPrefs.getInstance(getApplicationContext());
    }

    @Override
    protected Location update() {
        double[] coordinates = prefs.getOriginLocation();
        Location location = LocationHelper.create(PROVIDER, coordinates[0], coordinates[1], 50);
        return location;
    }

}
