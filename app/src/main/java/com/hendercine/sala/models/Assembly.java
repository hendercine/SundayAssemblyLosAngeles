/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 5:47 PM
 */

package com.hendercine.sala.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * sala created by hendercine on 6/14/18.
 */

//@SuppressWarnings("WeakerAccess")
@Parcel(Parcel.Serialization.BEAN)
public class Assembly implements Serializable {

    // Fields must be public for Parceler.
    @SerializedName("_id")
    public int mAssemblyId;
    @SerializedName("assembly_date")
    public String mAssemblyDate;
    @SerializedName("assembly_theme")
    public String mAssemblyTheme;
    @SerializedName("assembly_description")
    public String mAssemblyDescription;
    @SerializedName("assembly_location")
    public String mAssemblyLocation;
    @SerializedName("assembly_photo_url")
    public String mAssemblyPhotoUrl;
    @SerializedName("performers")
    public ArrayList<Performer> mPerformerList;
    @SerializedName("songs")
    public ArrayList<Song> mSongsList;

    public Assembly() {
        // Neccessary empty constructor for Parceler
    }

    public int getAssemblyId() {
        return mAssemblyId;
    }

    public void setAssemblyId(int assemblyId) {
        mAssemblyId = assemblyId;
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

    public String getAssemblyLocation() {
        return mAssemblyLocation;
    }

    public void setAssemblyLocation(String assemblyLocation) {
        mAssemblyLocation = assemblyLocation;
    }

    public String getAssemblyPhotoUrl() {
        return mAssemblyPhotoUrl;
    }

    public void setAssemblyPhotoUrl(String assemblyPhotoUrl) {
        mAssemblyPhotoUrl = assemblyPhotoUrl;
    }

    public ArrayList<Performer> getPerformerList() {
        return mPerformerList;
    }

    public void setPerformerList(ArrayList<Performer> performerList) {
        mPerformerList = performerList;
    }

    public ArrayList<Song> getSongsList() {
        return mSongsList;
    }

    public void setSongsList(ArrayList<Song> songsList) {
        mSongsList = songsList;
    }
}
