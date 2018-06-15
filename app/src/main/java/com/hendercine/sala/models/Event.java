/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 5:02 PM
 */

package com.hendercine.sala.models;

import org.parceler.Parcel;

/**
 * sala created by hendercine on 6/14/18.
 */

@SuppressWarnings("WeakerAccess")
@Parcel
public class Event {

    String mEventDate;
    String mEventTitle;
    String mEventLocation;
    String mEventContact;
    String mEventPhotoUrl;

    public Event() {
        // Neccessary empty constructor for Parceler
    }

    public Event(String eventDate, String eventTitle, String eventLocation, String eventContact, String eventPhotoUrl) {
        this.mEventDate = eventDate;
        this.mEventTitle = eventTitle;
        this.mEventLocation = eventLocation;
        this.mEventContact = eventContact;
        this.mEventPhotoUrl = eventPhotoUrl;
    }

    public String getEventDate() {
        return mEventDate;
    }

    public void setEventDate(String eventDate) {
        this.mEventDate = eventDate;
    }

    public String getEventTitle() {
        return mEventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.mEventTitle = eventTitle;
    }

    public String getEventLocation() {
        return mEventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.mEventLocation = eventLocation;
    }

    public String getEventContact() {
        return mEventContact;
    }

    public void setEventContact(String eventContact) {
        this.mEventContact = eventContact;
    }

    public String getEventPhotoUrl() {
        return mEventPhotoUrl;
    }

    public void setEventPhotoUrl(String eventPhotoUrl) {
        this.mEventPhotoUrl = eventPhotoUrl;
    }
}
