/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 5:47 PM
 */

package com.hendercine.sala.models;

import org.parceler.Parcel;

/**
 * sala created by hendercine on 6/14/18.
 */

@SuppressWarnings("WeakerAccess")
@Parcel
public class Assembly {

    String mAssemblyId;
    String mAssemblyDate;
    String mAssemblyTheme;
    String mAssemblyFeature;
    String mAssemblyDescription;
    String mAssemblyLocation;
    String mAssemblyPhotoUrl;

    public Assembly() {
        // Neccessary empty constructor for Parceler
    }

    public Assembly(String assemblyId, String assemblyDate, String assemblyTheme, String assemblyFeature, String assemblyDescription, String assemblyLocation, String assemblyPhotoUrl) {
        this.mAssemblyId = assemblyId;
        this.mAssemblyDate = assemblyDate;
        this.mAssemblyTheme = assemblyTheme;
        this.mAssemblyFeature = assemblyFeature;
        this.mAssemblyDescription = assemblyDescription;
        this.mAssemblyLocation = assemblyLocation;
        this.mAssemblyPhotoUrl = assemblyPhotoUrl;
    }

    public String getAssemblyId() {
        return mAssemblyId;
    }

    public void setAssemblyId(String assemblyId) {
        this.mAssemblyId = assemblyId;
    }

    public String getAssemblyDate() {
        return mAssemblyDate;
    }

    public void setAssemblyDate(String assemblyDate) {
        this.mAssemblyDate = assemblyDate;
    }

    public String getAssemblyTheme() {
        return mAssemblyTheme;
    }

    public void setAssemblyTheme(String assemblyTheme) {
        this.mAssemblyTheme = assemblyTheme;
    }

    public String getAssemblyFeature() {
        return mAssemblyFeature;
    }

    public void setAssemblyFeature(String assemblyFeature) {
        this.mAssemblyFeature = assemblyFeature;
    }

    public String getAssemblyDescription() {
        return mAssemblyDescription;
    }

    public void setAssemblyDescription(String assemblyDescription) {
        this.mAssemblyDescription = assemblyDescription;
    }

    public String getAssemblyLocation() {
        return mAssemblyLocation;
    }

    public void setAssemblyLocation(String assemblyLocation) {
        this.mAssemblyLocation = assemblyLocation;
    }

    public String getAssemblyPhotoUrl() {
        return mAssemblyPhotoUrl;
    }

    public void setAssemblyPhotoUrl(String assemblyPhotoUrl) {
        this.mAssemblyPhotoUrl = assemblyPhotoUrl;
    }
}
