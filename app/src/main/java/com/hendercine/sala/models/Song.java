/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 4:52 PM
 */

package com.hendercine.sala.models;

import org.parceler.Parcel;

/**
 * sala created by hendercine on 6/14/18.
 */

@SuppressWarnings("WeakerAccess")
@Parcel
public class Song {

    int mSongId;
    String mSongTitle;
    String mSongBy;
    String mLyrics;

    public Song() {
        // Neccessary empty constructor for Parceler
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
