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

// [START assembly_class]
/*@IgnoreExtraProperties*/
@SuppressWarnings("WeakerAccess")
@Parcel
public class AssemblyListing {

    public String mAssemblyHeadline;
    public String mAssemblyTheme;
    public String mAssemblyDescription;
    public String mAssemblyPhotoUrl;

    public AssemblyListing() {
        // Neccessary empty constructor for Parceler
    }

    public AssemblyListing(String assemblyHeadline, String assemblyTheme, String assemblyDescription, String assemblyPhotoUrl) {
        this.mAssemblyHeadline = assemblyHeadline;
        this.mAssemblyTheme = assemblyTheme;
        this.mAssemblyDescription = assemblyDescription;
        this.mAssemblyPhotoUrl = assemblyPhotoUrl;
    }

    public String getAssemblyHeadline() {
        return mAssemblyHeadline;
    }

    public void setAssemblyHeadline(String assemblyHeadline) {
        mAssemblyHeadline = assemblyHeadline;
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
//    @Exclude
//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("assembly_date", mAssemblyHeadline);
//        result.put("assembly_theme", mAssemblyTheme);
//        result.put("assembly_description", mAssemblyDescription);
//        result.put("assembly_photo_url", mAssemblyPhotoUrl);
//
//        return result;
//    }
    // [END assembly_to_map]
}
// [END assembly_class]
