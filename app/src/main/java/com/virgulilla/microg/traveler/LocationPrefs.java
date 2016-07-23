package com.virgulilla.microg.traveler;

import android.content.Context;

public class LocationPrefs extends Prefs {

    public static final double LATITUDE_ORIGIN_DEFAULT = 40.41687;
    public static final double LONGITUDE_ORIGIN_DEFAULT = -3.70339;
    public static final boolean WANDER_AROUND_DEFAULT = true;
    public static final double WANDERING_MAX_RADIUS_DEFAULT = 0.0001;

    public static final String LOG_LOCATION = "Latitude: %f | Longitude: %f";
    public static final String LOG_WANDERING = "Wandering: %f° lat | %fº lon";

    protected enum GeoKey {
        LATITUDE_ORIGIN, LONGITUDE_ORIGIN,
        WANDER_AROUND, WANDERING_MAX_RADIUS
    }

    private LocationPrefs(Context context) {
        super(context);
    }

    public static LocationPrefs getInstance(Context context) {
        if (instance == null) {
            instance = new LocationPrefs(context);
        }
        return (LocationPrefs) instance;
    }

    public double[] getOriginLocation() {
        double[] originLocation = {
                getDouble(GeoKey.LATITUDE_ORIGIN, LATITUDE_ORIGIN_DEFAULT),
                getDouble(GeoKey.LONGITUDE_ORIGIN, LONGITUDE_ORIGIN_DEFAULT)
        }; // Sol - Madrid
        Prefs.log(LOG_LOCATION, originLocation[0], originLocation[1]);
        return originLocation;
    }

    public void setOriginLocation(double[] location) {
        setOriginLocation(location[0], location[1]);
    }

    public void setOriginLocation(double latitude, double longitude) {
        put(GeoKey.LATITUDE_ORIGIN, latitude);
        put(GeoKey.LONGITUDE_ORIGIN, longitude);
    }

    public boolean isWanderingAroundEnabled() {
        return getBoolean(GeoKey.WANDER_AROUND, WANDER_AROUND_DEFAULT);
    }

    public void setWanderAround(boolean value) {
        put(GeoKey.WANDER_AROUND, value);
    }

    public double getWanderingMaxRadius() {
        return getDouble(GeoKey.WANDERING_MAX_RADIUS, WANDERING_MAX_RADIUS_DEFAULT);
    }

    public void setWanderingMaxRadius(double value) {
        put(GeoKey.WANDERING_MAX_RADIUS, value);
    }

}