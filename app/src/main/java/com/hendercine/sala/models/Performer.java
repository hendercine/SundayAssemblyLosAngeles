/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 5:11 PM
 */

package com.hendercine.sala.models;

import org.parceler.Parcel;

/**
 * sala created by hendercine on 6/14/18.
 */

@SuppressWarnings("WeakerAccess")
@Parcel
public class Performer {

    String mPerformerId;
    String mPerformerName;
    String mPerformanceTitle;
    String mPerformerBio;
    String mPerformerPhotoUrl;

    public Performer() {
        // Neccessary empty constructor for Parceler
    }

    public Performer(String performerId, String performerName, String performanceTitle, String performerBio, String performerPhotoUrl) {
        this.mPerformerId = performerId;
        this.mPerformerName = performerName;
        this.mPerformanceTitle = performanceTitle;
        this.mPerformerBio = performerBio;
        this.mPerformerPhotoUrl = performerPhotoUrl;
    }

    public String getPerformerId() {
        return mPerformerId;
    }

    public void setPerformerId(String performerId) {
        this.mPerformerId = performerId;
    }

    public String getPerformerName() {
        return mPerformerName;
    }

    public void setPerformerName(String performerName) {
        this.mPerformerName = performerName;
    }

    public String getPerformanceTitle() {
        return mPerformanceTitle;
    }

    public void setPerformanceTitle(String performanceTitle) {
        this.mPerformanceTitle = performanceTitle;
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
