/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 5/21/18 1:21 PM
 */

package com.hendercine.sala.models;

/**
 * sala created by hendercine on 5/21/18.
 */

public class SalamanderMessage {

    String mText;
    String mName;
    String mPhotoUrl;

    public SalamanderMessage() {
    }

    public SalamanderMessage(String text, String name, String photoUrl) {
        this.mText = text;
        this.mName = name;
        this.mPhotoUrl = photoUrl;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.mPhotoUrl = photoUrl;
    }
}
