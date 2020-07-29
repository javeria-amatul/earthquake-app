package com.example.android.quakereport;

import android.content.Context;
import android.util.Log;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private static final String  TAG      = EarthquakeLoader.class.getCanonicalName();
    private              String  mUrl     = "";
    private              Context mContext = null;

    public EarthquakeLoader(@NonNull Context context, String url) {
        super(context);
        this.mContext = context;
        this.mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {

        if (mUrl == null || mUrl.length() < 1)
            return null;

        URL url = QueryUtils.createUrl(mUrl);
        List<Earthquake> earthquakeList = new ArrayList<>();

        try {
            String response = QueryUtils.makeHttpRequest(url);
            if (response != null && response.length() > 0) {
                earthquakeList = QueryUtils.parseEarthquakesResponse(response);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
        return earthquakeList;
    }
}
