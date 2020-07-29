package com.example.android.quakereport;

class Earthquake {

    private String  mMagnitude;
    private String mLocation;
    private String mTimestamp;

    public Earthquake(String mMagnitude, String mLocation, String mTimestamp) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mTimestamp = mTimestamp;
    }

    public String getmMagnitudeAsString() {
        return mMagnitude;
    }

    public void setmMagnitude(String mMagnitude) {
        this.mMagnitude = mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getmTimestamp() {
        return mTimestamp;
    }

    public void setmTimestamp(String mTimestamp) {
        this.mTimestamp = mTimestamp;
    }
}
