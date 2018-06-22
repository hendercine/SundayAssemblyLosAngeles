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

import java.util.HashMap;
import java.util.Map;

/**
 * sala created by hendercine on 6/14/18.
 */

// [START assembly_class]
@IgnoreExtraProperties
public class Assembly {

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
        result.put("assembly_date", mAssemblyDate);
        result.put("assembly_theme", mAssemblyTheme);
        result.put("assembly_description", mAssemblyDescription);
        result.put("assembly_photo_url", mAssemblyPhotoUrl);

        return result;
    }
    // [END assembly_to_map]
}
// [END assembly_class]
