/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 5:42 PM
 */

package com.hendercine.sala.models;

import org.parceler.Parcel;

/**
 * sala created by hendercine on 6/14/18.
 */

@SuppressWarnings("WeakerAccess")
@Parcel
public class LiveBetterEvent {

    String mLiveBetterEventId;
    String mLiveBetterEventDate;
    String mLiveBetterEventTitle;
    String mLiveBetterEventLocation;
    String mLiveBetterEventContact;
    String mLiveBetterEventPhotoUrl;

    public LiveBetterEvent() {
        // Neccessary empty constructor for Parceler
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
