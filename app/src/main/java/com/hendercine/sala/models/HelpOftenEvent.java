/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 5:02 PM
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
public class HelpOftenEvent implements Serializable {

    // Fields must be public for Parceler.
    @SerializedName("help_event_id")
    public String mHelpOftenEventId;
    @SerializedName("help_event_date")
    public String mHelpOftenEventDate;
    @SerializedName("help_event_title")
    public String mHelpOftenEventTitle;
    @SerializedName("help_event_location")
    public String mHelpOftenEventLocation;
    @SerializedName("help_event_contact")
    public String mHelpOftenEventContact;
    @SerializedName("help_event_photo_url")
    public String mHelpOftenEventPhotoUrl;

    public HelpOftenEvent() {
        // Necessary empty constructor for Parceler
    }

    public HelpOftenEvent(String helpOftenEventId, String helpOftenEventDate, String helpOftenEventTitle, String helpOftenEventLocation, String helpOftenEventContact, String helpOftenEventPhotoUrl) {
        this.mHelpOftenEventId = helpOftenEventId;
        this.mHelpOftenEventDate = helpOftenEventDate;
        this.mHelpOftenEventTitle = helpOftenEventTitle;
        this.mHelpOftenEventLocation = helpOftenEventLocation;
        this.mHelpOftenEventContact = helpOftenEventContact;
        this.mHelpOftenEventPhotoUrl = helpOftenEventPhotoUrl;
    }

    public String getHelpOftenEventId() {
        return mHelpOftenEventId;
    }

    public void setHelpOftenEventId(String helpOftenEventId) {
        this.mHelpOftenEventId = helpOftenEventId;
    }

    public String getHelpOftenEventDate() {
        return mHelpOftenEventDate;
    }

    public void setHelpOftenEventDate(String helpOftenEventDate) {
        this.mHelpOftenEventDate = helpOftenEventDate;
    }

    public String getHelpOftenEventTitle() {
        return mHelpOftenEventTitle;
    }

    public void setHelpOftenEventTitle(String helpOftenEventTitle) {
        this.mHelpOftenEventTitle = helpOftenEventTitle;
    }

    public String getHelpOftenEventLocation() {
        return mHelpOftenEventLocation;
    }

    public void setHelpOftenEventLocation(String helpOftenEventLocation) {
        this.mHelpOftenEventLocation = helpOftenEventLocation;
    }

    public String getHelpOftenEventContact() {
        return mHelpOftenEventContact;
    }

    public void setHelpOftenEventContact(String helpOftenEventContact) {
        this.mHelpOftenEventContact = helpOftenEventContact;
    }

    public String getHelpOftenEventPhotoUrl() {
        return mHelpOftenEventPhotoUrl;
    }

    public void setHelpOftenEventPhotoUrl(String helpOftenEventPhotoUrl) {
        this.mHelpOftenEventPhotoUrl = helpOftenEventPhotoUrl;
    }
}
