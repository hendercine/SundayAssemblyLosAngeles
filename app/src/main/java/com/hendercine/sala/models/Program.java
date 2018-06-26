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

//@SuppressWarnings("WeakerAccess")
@Parcel
public class Program {

    // Fields must be public for Parceler.
    public String mMilestoneHeader;
    public String mKidsHeader;
    public String mAudienceInteractionHeader;
    public String mQuietReflectionHeader;
    public String mCollectionHeader;

    Program() {
        // Necessary empty constructor for Parceler
    }

    public String getMilestoneHeader() {
        return mMilestoneHeader;
    }

    public void setMilestoneHeader(String milestoneHeader) {
        mMilestoneHeader = milestoneHeader;
    }

    public String getKidsHeader() {
        return mKidsHeader;
    }

    public void setKidsHeader(String kidsHeader) {
        mKidsHeader = kidsHeader;
    }

    public String getAudienceInteractionHeader() {
        return mAudienceInteractionHeader;
    }

    public void setAudienceInteractionHeader(String audienceInteractionHeader) {
        mAudienceInteractionHeader = audienceInteractionHeader;
    }

    public String getQuietReflectionHeader() {
        return mQuietReflectionHeader;
    }

    public void setQuietReflectionHeader(String quietReflectionHeader) {
        mQuietReflectionHeader = quietReflectionHeader;
    }

    public String getCollectionHeader() {
        return mCollectionHeader;
    }

    public void setCollectionHeader(String collectionHeader) {
        mCollectionHeader = collectionHeader;
    }
}
