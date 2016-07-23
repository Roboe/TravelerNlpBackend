package com.virgulilla.microg.traveler;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Locale;

public abstract class Prefs {

    protected static String TAG = "traveler_log";
    protected static Prefs instance;

    protected SharedPreferences mPref;
    protected SharedPreferences.Editor mEditor;
    protected boolean mBulkUpdate = false;

    protected Prefs(Context context) {
        mPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Prefs getInstance() {
        if (instance != null) {
            return instance;
        }

        throw new IllegalArgumentException("Should use getInstance(Context) at least once before using this method.");
    }


    public void put(Enum key, String val) {
        doEdit();
        mEditor.putString(key.name(), val);
        doCommit();
    }

    public void put(Enum key, int val) {
        doEdit();
        mEditor.putInt(key.name(), val);
        doCommit();
    }

    public void put(Enum key, boolean val) {
        doEdit();
        mEditor.putBoolean(key.name(), val);
        doCommit();
    }

    public void put(Enum key, float val) {
        doEdit();
        mEditor.putFloat(key.name(), val);
        doCommit();
    }

    public void put(Enum key, double val) {
        doEdit();
        mEditor.putString(key.name(), String.valueOf(val));
        doCommit();
    }

    public void put(Enum key, long val) {
        doEdit();
        mEditor.putLong(key.name(), val);
        doCommit();
    }


    public String getString(Enum key, String defaultValue) {
        return mPref.getString(key.name(), defaultValue);
    }

    public String getString(Enum key) {
        return mPref.getString(key.name(), null);
    }

    public int getInt(Enum key) {
        return mPref.getInt(key.name(), 0);
    }

    public int getInt(Enum key, int defaultValue) {
        return mPref.getInt(key.name(), defaultValue);
    }

    public long getLong(Enum key) {
        return mPref.getLong(key.name(), 0);
    }

    public long getLong(Enum key, long defaultValue) {
        return mPref.getLong(key.name(), defaultValue);
    }

    public float getFloat(Enum key) {
        return mPref.getFloat(key.name(), 0);
    }

    public float getFloat(Enum key, float defaultValue) {
        return mPref.getFloat(key.name(), defaultValue);
    }

    public double getDouble(Enum key) {
        return getDouble(key, 0);
    }

    public double getDouble(Enum key, double defaultValue) {
        try {
            return Double.valueOf(mPref.getString(key.name(), String.valueOf(defaultValue)));
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public boolean getBoolean(Enum key, boolean defaultValue) {
        return mPref.getBoolean(key.name(), defaultValue);
    }

    public boolean getBoolean(Enum key) {
        return mPref.getBoolean(key.name(), false);
    }


    public void remove(Enum... keys) {
        doEdit();
        for (Enum key : keys) {
            mEditor.remove(key.name());
        }
        doCommit();
    }


    public void clear() {
        doEdit();
        mEditor.clear();
        doCommit();
    }

    public void edit() {
        mBulkUpdate = true;
        mEditor = mPref.edit();
    }

    public void commit() {
        mBulkUpdate = false;
        mEditor.commit();
        mEditor = null;
    }

    protected void doEdit() {
        if (!mBulkUpdate && mEditor == null) {
            mEditor = mPref.edit();
        }
    }

    protected void doCommit() {
        if (!mBulkUpdate && mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }


    protected static void log(String message, Object... args) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, String.format(Locale.getDefault(), message, args));
        }
    }
}