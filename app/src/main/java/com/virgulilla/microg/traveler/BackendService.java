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
        coordinates = wanderAround(coordinates);

        Location location = LocationHelper.create(PROVIDER, coordinates[0], coordinates[1], 50);
        return location;
    }

    private double[] wanderAround(double[] coordinates) {
        if (!prefs.isWanderingAroundEnabled()) return coordinates;

        final double MAX_RADIUS = prefs.getWanderingMaxRadius();

        double offset_lat = (Math.random() - 0.5) * MAX_RADIUS;
        double offset_lon = (Math.random() - 0.5) * MAX_RADIUS;
        Prefs.log(LocationPrefs.LOG_WANDERING, offset_lat, offset_lon);

        coordinates[0] += offset_lat;
        coordinates[1] += offset_lon;

        return coordinates;
    }
}
