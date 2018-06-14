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

    public String getmTheme() {
        return mTheme;
    }

    public void setmTheme(String mTheme) {
        this.mTheme = mTheme;
    }

    public String getmEmceeNameOne() {
        return mEmceeNameOne;
    }

    public void setmEmceeNameOne(String mEmceeNameOne) {
        this.mEmceeNameOne = mEmceeNameOne;
    }

    public String getmEmceeNameTwo() {
        return mEmceeNameTwo;
    }

    public void setmEmceeNameTwo(String mEmceeNameTwo) {
        this.mEmceeNameTwo = mEmceeNameTwo;
    }

    public String getmBandName() {
        return mBandName;
    }

    public void setmBandName(String mBandName) {
        this.mBandName = mBandName;
    }

    public String getmCreativeType() {
        return mCreativeType;
    }

    public void setmCreativeType(String mCreativeType) {
        this.mCreativeType = mCreativeType;
    }

    public String getmCreativeName() {
        return mCreativeName;
    }

    public void setmCreativeName(String mCreativeName) {
        this.mCreativeName = mCreativeName;
    }

    public String getmTryingName() {
        return mTryingName;
    }

    public void setmTryingName(String mTryingName) {
        this.mTryingName = mTryingName;
    }
}
