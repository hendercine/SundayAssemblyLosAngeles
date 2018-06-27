/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 5:47 PM
 */

package com.hendercine.sala.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import org.parceler.Parcel;

import java.util.HashMap;
import java.util.Map;

/**
 * sala created by hendercine on 6/14/18.
 */

// [START assembly_class]
@IgnoreExtraProperties
@SuppressWarnings("WeakerAccess")
@Parcel
public class Assembly {

    public static final String ASSEMBLY_PHOTO_URL = "assembly_photo_url";
    public static final String ASSEMBLY_DESCRIPTION = "assembly_description";
    public static final String ASSEMBLY_THEME = "assembly_theme";
    public static final String ASSEMBLY_DATE = "assembly_date";
    public String mAssemblyDate;
    public String mAssemblyTheme;
    public String mAssemblyDescription;
    public String mAssemblyPhotoUrl;

    public Assembly() {
        // Neccessary empty constructor for Parceler
    }

    public Assembly(String assemblyDate, String assemblyTheme, String assemblyDescription, String assemblyPhotoUrl) {
        this.mAssemblyDate = assemblyDate;
        this.mAssemblyTheme = assemblyTheme;
        this.mAssemblyDescription = assemblyDescription;
        this.mAssemblyPhotoUrl = assemblyPhotoUrl;
    }

    public String getAssemblyDate() {
        return mAssemblyDate;
    }

    public void setAssemblyDate(String assemblyDate) {
        mAssemblyDate = assemblyDate;
    }

    public String getAssemblyTheme() {
        return mAssemblyTheme;
    }

    public void setAssemblyTheme(String assemblyTheme) {
        mAssemblyTheme = assemblyTheme;
    }

    public String getAssemblyDescription() {
        return mAssemblyDescription;
    }

    public void setAssemblyDescription(String assemblyDescription) {
        mAssemblyDescription = assemblyDescription;
    }

    public String getAssemblyPhotoUrl() {
        return mAssemblyPhotoUrl;
    }

    public void setAssemblyPhotoUrl(String assemblyPhotoUrl) {
        mAssemblyPhotoUrl = assemblyPhotoUrl;
    }

    // [START assembly_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(ASSEMBLY_DATE, mAssemblyDate);
        result.put(ASSEMBLY_THEME, mAssemblyTheme);
        result.put(ASSEMBLY_DESCRIPTION, mAssemblyDescription);
        result.put(ASSEMBLY_PHOTO_URL, mAssemblyPhotoUrl);

        return result;
    }
    // [END assembly_to_map]
}
// [END assembly_class]
