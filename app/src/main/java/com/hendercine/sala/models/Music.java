/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 4:10 PM
 */

package com.hendercine.sala.models;

import org.parceler.Parcel;

/**
 * sala created by hendercine on 6/14/18.
 */

@SuppressWarnings("WeakerAccess")
@Parcel
public class Music {
    String mSongTitleOne;
    String mSongByOne;
    String mSongTitleTwo;
    String mSongByTwo;
    String mSongTitleThree;
    String mSongByThree;
    String mSongTitleFour;
    String mSongByFour;

    public Music() {
        // Neccessary empty constructor for Parceler
    }

    public Music(String songTitleOne, String songByOne, String songTitleTwo, String songByTwo, String songTitleThree, String songByThree, String songTitleFour, String songByFour) {
        this.mSongTitleOne = songTitleOne;
        this.mSongByOne = songByOne;
        this.mSongTitleTwo = songTitleTwo;
        this.mSongByTwo = songByTwo;
        this.mSongTitleThree = songTitleThree;
        this.mSongByThree = songByThree;
        this.mSongTitleFour = songTitleFour;
        this.mSongByFour = songByFour;
    }

    public String getSongTitleOne() {
        return mSongTitleOne;
    }

    public void setSongTitleOne(String songTitleOne) {
        this.mSongTitleOne = songTitleOne;
    }

    public String getSongByOne() {
        return mSongByOne;
    }

    public void setSongByOne(String songByOne) {
        this.mSongByOne = songByOne;
    }

    public String getSongTitleTwo() {
        return mSongTitleTwo;
    }

    public void setSongTitleTwo(String songTitleTwo) {
        this.mSongTitleTwo = songTitleTwo;
    }

    public String getSongByTwo() {
        return mSongByTwo;
    }

    public void setSongByTwo(String songByTwo) {
        this.mSongByTwo = songByTwo;
    }

    public String getSongTitleThree() {
        return mSongTitleThree;
    }

    public void setSongTitleThree(String songTitleThree) {
        this.mSongTitleThree = songTitleThree;
    }

    public String getSongByThree() {
        return mSongByThree;
    }

    public void setSongByThree(String songByThree) {
        this.mSongByThree = songByThree;
    }

    public String getSongTitleFour() {
        return mSongTitleFour;
    }

    public void setSongTitleFour(String songTitleFour) {
        this.mSongTitleFour = songTitleFour;
    }

    public String getSongByFour() {
        return mSongByFour;
    }

    public void setSongByFour(String songByFour) {
        this.mSongByFour = songByFour;
    }
}
