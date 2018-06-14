/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 3:51 PM
 */

package com.hendercine.sala.models;

import org.parceler.Parcel;

/**
 * sala created by hendercine on 6/14/18.
 */

@SuppressWarnings("WeakerAccess")
@Parcel
public class Program {
    String mTheme;
    String mEmceeNameOne;
    String mEmceeNameTwo;
    String mBandName;
    String mCreativeType;
    String mCreativeName;
    String mTryingName;

    public Program() {
        // Neccessary empty constructor for Parceler
    }

    public Program(String theme, String emceeNameOne, String emceeNameTwo, String bandName, String creativeType, String creativeName, String tryingName) {
        this.mTheme = theme;
        this.mEmceeNameOne = emceeNameOne;
        this.mEmceeNameTwo = emceeNameTwo;
        this.mBandName = bandName;
        this.mCreativeType = creativeType;
        this.mCreativeName = creativeName;
        this.mTryingName = tryingName;
    }

    public String getTheme() {
        return mTheme;
    }

    public void setTheme(String theme) {
        this.mTheme = theme;
    }

    public String getEmceeNameOne() {
        return mEmceeNameOne;
    }

    public void setEmceeNameOne(String emceeNameOne) {
        this.mEmceeNameOne = emceeNameOne;
    }

    public String getEmceeNameTwo() {
        return mEmceeNameTwo;
    }

    public void setEmceeNameTwo(String emceeNameTwo) {
        this.mEmceeNameTwo = emceeNameTwo;
    }

    public String getBandName() {
        return mBandName;
    }

    public void setBandName(String bandName) {
        this.mBandName = bandName;
    }

    public String getCreativeType() {
        return mCreativeType;
    }

    public void setCreativeType(String creativeType) {
        this.mCreativeType = creativeType;
    }

    public String getCreativeName() {
        return mCreativeName;
    }

    public void setCreativeName(String creativeName) {
        this.mCreativeName = creativeName;
    }

    public String getTryingName() {
        return mTryingName;
    }

    public void setTryingName(String tryingName) {
        this.mTryingName = tryingName;
    }
}
