/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 5:11 PM
 */

package com.hendercine.sala.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;

/**
 * sala created by hendercine on 6/14/18.
 */

//@SuppressWarnings("WeakerAccess")
@Parcel(Parcel.Serialization.BEAN)
public class Performer implements Serializable {

    // Fields must be public for Parceler.
//    @SerializedName("performance_date")
//    public String mPerformanceDate;
    @SerializedName("performance_category")
    public String mPerformanceCategory;
    @SerializedName("performance_title")
    public String mPerformanceTitle;
    @SerializedName("performer_name")
    public String mPerformerName;
    @SerializedName("performer_bio")
    public String mPerformerBio;
    @SerializedName("performer_photo_url")
    public String mPerformerPhotoUrl;

    public Performer() {
        // Necessary empty constructor for Parceler
    }

// TODO: Remove or uncomment commented code as required
//    public Performer(String performanceDate, String performanceCategory, String performanceTitle, String performerName, String performerBio, String performerPhotoUrl) {
//        this.mPerformanceDate = performanceDate;
//        this.mPerformanceCategory = performanceCategory;
//        this.mPerformanceTitle = performanceTitle;
//        this.mPerformerName = performerName;
//        this.mPerformerBio = performerBio;
//        this.mPerformerPhotoUrl = performerPhotoUrl;
//    }

//    public String getPerformanceDate() {
//        return mPerformanceDate;
//    }
//
//    public void setPerformanceDate(String performanceDate) {
//        this.mPerformanceDate = performanceDate;
//    }

    public String getPerformanceCategory() {
        return mPerformanceCategory;
    }

    public void setPerformanceCategory(String performanceCategory) {
        this.mPerformanceCategory = performanceCategory;
    }

    public String getPerformanceTitle() {
        return mPerformanceTitle;
    }

    public void setPerformanceTitle(String performanceTitle) {
        this.mPerformanceTitle = performanceTitle;
    }

    public String getPerformerName() {
        return mPerformerName;
    }

    public void setPerformerName(String performerName) {
        this.mPerformerName = performerName;
    }

    public String getPerformerBio() {
        return mPerformerBio;
    }

    public void setPerformerBio(String performerBio) {
        this.mPerformerBio = performerBio;
    }

    public String getPerformerPhotoUrl() {
        return mPerformerPhotoUrl;
    }

    public void setPerformerPhotoUrl(String performerPhotoUrl) {
        this.mPerformerPhotoUrl = performerPhotoUrl;
    }
}
