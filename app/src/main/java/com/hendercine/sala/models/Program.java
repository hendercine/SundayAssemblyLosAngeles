/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 3:51 PM
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
public class Program implements Serializable {

    // Fields must be public for Parceler.
    @SerializedName("emcee_name_one")
    public String mEmceeNameOne;
    @SerializedName("emcee_name_two")
    public String mEmceeNameTwo;
    @SerializedName("band_name")
    public String mBandName;
    @SerializedName("creative_genre")
    public String mCreativeType;
    @SerializedName("creative_name")
    public String mCreativeName;
    @SerializedName("creative_title")
    public String mCreativeTitle;
    @SerializedName("trying_name")
    public String mTryingName;

    public Program() {
        // Necessary empty constructor for Parceler
    }

    public Program(String emceeNameOne, String emceeNameTwo,
                   String bandName, String creativeType, String creativeName,
                   String creativeTitle, String tryingName) {
        this.mEmceeNameOne = emceeNameOne;
        this.mEmceeNameTwo = emceeNameTwo;
        this.mBandName = bandName;
        this.mCreativeType = creativeType;
        this.mCreativeName = creativeName;
        this.mCreativeTitle = creativeTitle;
        this.mTryingName = tryingName;
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

    public String getCreativeTitle() {
        return mCreativeTitle;
    }

    public void setCreativeTitle(String creativeTitle) {
        this.mCreativeTitle = creativeTitle;
    }

    public String getTryingName() {
        return mTryingName;
    }

    public void setTryingName(String tryingName) {
        this.mTryingName = tryingName;
    }
}
