/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String            LOG_TAG = EarthquakeActivity.class.getName();
    private             EarthquakeAdapter earthquakeAdapter;
    private             TextView          tvEmptyData;
    private             ProgressBar       progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        tvEmptyData = findViewById(R.id.empty_state);
        progressBar = findViewById(R.id.loading_spinner);
        updateUI(this);
        getSupportLoaderManager().initLoader(1, null, this).forceLoad();
//        EarthquakeTask earthquakeTask = new EarthquakeTask(this);
//        earthquakeTask.execute(Constants.USGS_REQUEST_URL);
    }


    private void updateUI(Context context) {
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) ((Activity) context).findViewById(R.id.list);
        earthquakeAdapter = new EarthquakeAdapter(context);
        earthquakeListView.setAdapter(earthquakeAdapter);
    }

    @NonNull
    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, @Nullable Bundle args) {
        progressBar.setVisibility(View.VISIBLE);
        return new EarthquakeLoader(this, Constants.USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Earthquake>> loader, List<Earthquake> data) {
        progressBar.setVisibility(View.GONE);
        if (!QueryUtils.isInternetAvailable(this)) {
            tvEmptyData.setText("No internet connection");
            tvEmptyData.setVisibility(View.VISIBLE);
        } else if (loader.isStarted() && data != null && data.size() > 0)
            earthquakeAdapter.setList(data);
        else {
            tvEmptyData.setText("No earthquakes occurred");
            tvEmptyData.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Earthquake>> loader) {
        earthquakeAdapter.setList(new ArrayList<Earthquake>());
    }

    //    private static class EarthquakeTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {
//
//        private WeakReference<Context> mContext;
//
//        public EarthquakeTask(Context context) {
//            mContext = new WeakReference<>(context);
//        }
//
//        @Override
//        protected ArrayList<Earthquake> doInBackground(String... urls) {
//
//            if (urls.length < 1 || urls[0] == null) {
//                return null;
//            }
//
//            URL url = QueryUtils.createUrl(urls[0]);
//            String response = "";
//            ArrayList<Earthquake> list = new ArrayList<>();
//            try {
//                response = QueryUtils.makeHttpRequest(url);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (response != null && response.length() > 0) {
//                list = QueryUtils.parseEarthquakesResponse(response);
//            }
//            return list;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
//            if (earthquakes == null)
//                return;
//            updateUI(earthquakes, mContext.get());
//        }
//    }
}
