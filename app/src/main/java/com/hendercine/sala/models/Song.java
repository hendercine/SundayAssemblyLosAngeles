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

    public Song() {
        // Neccessary empty constructor for Parceler
    }

    public Song(int songId, String songTitle, String songBy) {
        this.mSongId = songId;
        this.mSongTitle = songTitle;
        this.mSongBy = songBy;
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
}
