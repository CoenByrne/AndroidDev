package com.coffeeshopdevelopment.eatcheap;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Coen Byrne on 16/02/2018.
 */

public class Shop {

    private String mTitle;
    private String mDescription;
    private float mRating;
    private int mImage;
    private String mLocation;
    private LatLng mLatLng;

    public Shop(String title, String description, String location, float rating, int image, LatLng latLng) {
        // constructor function used to create instances of the Shop class
        mTitle = title;
        mDescription = description;
        mRating = rating;
        mLocation = location;
        mImage = image;
        mLatLng = latLng;
    }

    public LatLng getLatLng() {
        return mLatLng;
    }

    public void setLatLng(LatLng mLatLng) {
        this.mLatLng = mLatLng;
    }

    String getLocation() {
        return mLocation;
    }

    String getTitle() {
        return mTitle;
    }

    String getDescription() {
        return mDescription;
    }

    float getRating() {
        return mRating;
    }

    int getImage() {
        return mImage;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    void setTitle(String title) {
        mTitle = title;
    }

    void setDescription(String description) {
        mDescription = description;
    }

    void setRating(float rating) {
        mRating = rating;
    }

    void setImage(int image) {
        mImage = image;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "mTitle='" + mTitle + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mRating=" + mRating +
                ", mImage=" + mImage +
                '}';
    }
}
