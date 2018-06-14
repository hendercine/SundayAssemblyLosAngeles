/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 4:28 PM
 */

package com.hendercine.sala.models;

import org.parceler.Parcel;

/**
 * sala created by hendercine on 6/14/18.
 */

@SuppressWarnings("WeakerAccess")
@Parcel
public class Speaker {

    String mSpeakerName;
    String mTalkTitle;
    String mSpeakerBio;
    String mSpeakerPhotoUrl;

    public Speaker() {
        // Neccessary empty constructor for Parceler
    }

    public Speaker(String speakerName, String talkTitle, String speakerBio, String speakerPhotoUrl) {
        this.mSpeakerName = speakerName;
        this.mTalkTitle = talkTitle;
        this.mSpeakerBio = speakerBio;
        this.mSpeakerPhotoUrl = speakerPhotoUrl;
    }

    public String getSpeakerName() {
        return mSpeakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.mSpeakerName = speakerName;
    }

    public String getTalkTitle() {
        return mTalkTitle;
    }

    public void setTalkTitle(String talkTitle) {
        this.mTalkTitle = talkTitle;
    }

    public String getSpeakerBio() {
        return mSpeakerBio;
    }

    public void setSpeakerBio(String speakerBio) {
        this.mSpeakerBio = speakerBio;
    }

    public String getSpeakerPhotoUrl() {
        return mSpeakerPhotoUrl;
    }

    public void setSpeakerPhotoUrl(String speakerPhotoUrl) {
        this.mSpeakerPhotoUrl = speakerPhotoUrl;
    }
}
