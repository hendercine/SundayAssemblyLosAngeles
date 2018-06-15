/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 5/21/18 1:21 PM
 */

package com.hendercine.sala.models;

import org.parceler.Parcel;

/**
 * sala created by hendercine on 5/21/18.
 */

@SuppressWarnings("WeakerAccess")
@Parcel
public class ChatMessage {

    String mChatText;
    String mChatName;
    String mChatPhotoUrl;

    public ChatMessage() {
        // Neccessary empty constructor for Parceler
    }

    public ChatMessage(String text, String name, String photoUrl) {
        this.mChatText = text;
        this.mChatName = name;
        this.mChatPhotoUrl = photoUrl;
    }

    public String getChatText() {
        return mChatText;
    }

    public void setChatText(String text) {
        this.mChatText = text;
    }

    public String getChatName() {
        return mChatName;
    }

    public void setChatName(String name) {
        this.mChatName = name;
    }

    public String getChatPhotoUrl() {
        return mChatPhotoUrl;
    }

    public void setChatPhotoUrl(String photoUrl) {
        this.mChatPhotoUrl = photoUrl;
    }
}
