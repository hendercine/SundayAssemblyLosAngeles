/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 4:52 PM
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
public class Song implements Serializable {

    // Fields must be public for Parceler.
    @SerializedName("song_id")
    public int mSongId;
    @SerializedName("song_title")
    public String mSongTitle;
    @SerializedName("song_by")
    public String mSongBy;
    @SerializedName("lyrics")
    public String mLyrics;

    public Song() {
        // Necessary empty constructor for Parceler
    }

    public Song(int songId, String songTitle, String songBy, String lyrics) {
        this.mSongId = songId;
        this.mSongTitle = songTitle;
        this.mSongBy = songBy;
        this.mLyrics = lyrics;
    }

    public int getSongId() {
        return mSongId;
    }

    public void setSongId(int songId) {
        this.mSongId = songId;
    }

    public String getSongTitle() {
        return mSongTitle;
    }

    public void setSongTitle(String songTitle) {
        this.mSongTitle = songTitle;
    }

    public String getSongBy() {
        return mSongBy;
    }

    public void setSongBy(String songBy) {
        this.mSongBy = songBy;
    }

    public String getLyrics() {
        return mLyrics;
    }

    public void setLyrics(String lyrics) {
        this.mLyrics = lyrics;
    }
}
