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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * sala created by hendercine on 6/14/18.
 */

// [START assembly_class]
@IgnoreExtraProperties
public class Assembly {

    public int mAssemblyId;
    public String mAssemblyDate;
    public String mAssemblyTheme;
    public String mAssemblyDescription;
    public String mAssemblyLocation;
    public String mAssemblyPhotoUrl;
    public ArrayList<Performer> mPerformerList;
    public ArrayList<Song> mSongsList;

    public Assembly() {
        // Neccessary empty constructor for Parceler
    }

    public Assembly(int assemblyId, String assemblyDate, String assemblyTheme, String assemblyDescription, String assemblyLocation, String assemblyPhotoUrl, ArrayList<Performer> performerList, ArrayList<Song> songsList) {
        mAssemblyId = assemblyId;
        mAssemblyDate = assemblyDate;
        mAssemblyTheme = assemblyTheme;
        mAssemblyDescription = assemblyDescription;
        mAssemblyLocation = assemblyLocation;
        mAssemblyPhotoUrl = assemblyPhotoUrl;
        mPerformerList = performerList;
        mSongsList = songsList;
    }

    // [START assembly_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("assembly_date", mAssemblyDate);
        result.put("assembly_description", mAssemblyDescription);
        result.put("assembly_location", mAssemblyLocation);
        result.put("assembly_photo_url", mAssemblyPhotoUrl);
        result.put("assembly_theme", mAssemblyTheme);
        result.put("performers", mPerformerList);
        result.put("songs", mSongsList);

        return result;
    }
    // [END assembly_to_map]
}
// [END assembly_class]
