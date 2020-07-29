package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeAdapter extends BaseAdapter {

    private ArrayList<Earthquake> mList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;

    public EarthquakeAdapter(Context context) {
//        this.mList = (ArrayList<Earthquake>) earthquakes;
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        Earthquake earthquake = (Earthquake) getItem(position);
        if (view == null)
            view = inflater.inflate(R.layout.item_earthquake, null);

        TextView magnitude = view.findViewById(R.id.tv_magnitude);
        magnitude.setText((earthquake.getmMagnitudeAsString()));

        TextView location = view.findViewById(R.id.tv_location);
        location.setText((earthquake.getmLocation()));

        TextView timestamp = view.findViewById(R.id.tv_timestamp);
        timestamp.setText((earthquake.getmTimestamp()));

        return  view;
}
    public void setList(List<Earthquake> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }
}
