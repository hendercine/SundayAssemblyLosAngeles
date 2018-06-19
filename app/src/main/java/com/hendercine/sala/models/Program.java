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

/**
 * sala created by hendercine on 6/14/18.
 */

//@SuppressWarnings("WeakerAccess")
@Parcel
public class Program {

    // Fields must be public for Parceler.
    @SerializedName("performance_category_header")
    public String mPerformanceCategoryHeader;
    @SerializedName("song_number_header")
    public String mSongNumberHeader;
    @SerializedName("milestone_header")
    public String mMilestoneHeader;
    @SerializedName("kids_header")
    public String mKidsHeader;
    @SerializedName("audience_interaction_header")
    public String mAudienceInteractionHeader;
    @SerializedName("quiet_reflection_header")
    public String mQuietReflectionHeader;
    @SerializedName("collection_announcements_header")
    public String mCollectionHeader;

    public Program() {
        // Necessary empty constructor for Parceler
    }

    public String getPerformanceCategoryHeader() {
        return mPerformanceCategoryHeader;
    }

    public void setPerformanceCategoryHeader(String performanceCategoryHeader) {
        mPerformanceCategoryHeader = performanceCategoryHeader;
    }

    public String getSongNumberHeader() {
        return mSongNumberHeader;
    }

    public void setSongNumberHeader(String songNumberHeader) {
        mSongNumberHeader = songNumberHeader;
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
