package com.virgulilla.microg.traveler;

import android.content.Context;

public class LocationPrefs extends Prefs {

    protected enum GeoKey {
        LATITUDE_ORIGIN, LONGITUDE_ORIGIN
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
                getDouble(GeoKey.LATITUDE_ORIGIN, 40.41687),
                getDouble(GeoKey.LONGITUDE_ORIGIN, -3.70339)
        };
        return originLocation;
    }

    public void setOriginLocation(double[] location) {
        setOriginLocation(location[0], location[1]);
    }

    public void setOriginLocation(double latitude, double longitude) {
        put(GeoKey.LATITUDE_ORIGIN, latitude);
        put(GeoKey.LONGITUDE_ORIGIN, longitude);
    }

}