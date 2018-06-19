/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 5:42 PM
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
public class LiveBetterEvent implements Serializable {

    // Fields must be public for Parceler.
    @SerializedName("better_event_id")
    public String mLiveBetterEventId;
    @SerializedName("better_event_date")
    public String mLiveBetterEventDate;
    @SerializedName("better_event_title")
    public String mLiveBetterEventTitle;
    @SerializedName("better_event_location")
    public String mLiveBetterEventLocation;
    @SerializedName("better_event_contact")
    public String mLiveBetterEventContact;
    @SerializedName("better_event_photo_url")
    public String mLiveBetterEventPhotoUrl;

    public LiveBetterEvent() {
        // Necessary empty constructor for Parceler
    }

    public LiveBetterEvent(String liveBetterEventId, String liveBetterEventDate, String liveBetterEventTitle, String liveBetterEventLocation, String liveBetterEventContact, String liveBetterEventPhotoUrl) {
        this.mLiveBetterEventId = liveBetterEventId;
        this.mLiveBetterEventDate = liveBetterEventDate;
        this.mLiveBetterEventTitle = liveBetterEventTitle;
        this.mLiveBetterEventLocation = liveBetterEventLocation;
        this.mLiveBetterEventContact = liveBetterEventContact;
        this.mLiveBetterEventPhotoUrl = liveBetterEventPhotoUrl;
    }

    public String getLiveBetterEventId() {
        return mLiveBetterEventId;
    }

    public void setLiveBetterEventId(String liveBetterEventId) {
        this.mLiveBetterEventId = liveBetterEventId;
    }

    public String getLiveBetterEventDate() {
        return mLiveBetterEventDate;
    }

    public void setLiveBetterEventDate(String liveBetterEventDate) {
        this.mLiveBetterEventDate = liveBetterEventDate;
    }

    public String getLiveBetterEventTitle() {
        return mLiveBetterEventTitle;
    }

    public void setLiveBetterEventTitle(String liveBetterEventTitle) {
        this.mLiveBetterEventTitle = liveBetterEventTitle;
    }

    public String getLiveBetterEventLocation() {
        return mLiveBetterEventLocation;
    }

    public void setLiveBetterEventLocation(String liveBetterEventLocation) {
        this.mLiveBetterEventLocation = liveBetterEventLocation;
    }

    public String getLiveBetterEventContact() {
        return mLiveBetterEventContact;
    }

    public void setLiveBetterEventContact(String liveBetterEventContact) {
        this.mLiveBetterEventContact = liveBetterEventContact;
    }

    public String getLiveBetterEventPhotoUrl() {
        return mLiveBetterEventPhotoUrl;
    }

    public void setLiveBetterEventPhotoUrl(String liveBetterEventPhotoUrl) {
        this.mLiveBetterEventPhotoUrl = liveBetterEventPhotoUrl;
    }
}
