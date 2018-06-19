/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 5/21/18 1:21 PM
 */

package com.hendercine.sala.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;

/**
 * sala created by hendercine on 5/21/18.
 */

//@SuppressWarnings("WeakerAccess")
@Parcel(Parcel.Serialization.BEAN)
public class ChatMessage implements Serializable {

    // Fields must be public for Parceler.
    @SerializedName("chat_message")
    public String mChatMessage;
    @SerializedName("sender_name")
    public String mChatSenderName;
    @SerializedName("chat_user_avatar_url")
    public String mChatUserAvatarUrl;

    public ChatMessage() {
        // Necessary empty constructor for Parceler
    }

    public ChatMessage(String message, String senderName, String avatarUrl) {
        this.mChatMessage = message;
        this.mChatSenderName = senderName;
        this.mChatUserAvatarUrl = avatarUrl;
    }

    public String getChatMessage() {
        return mChatMessage;
    }

    public void setChatMessage(String message) {
        this.mChatMessage = message;
    }

    public String getChatSenderName() {
        return mChatSenderName;
    }

    public void setChatSenderName(String senderName) {
        this.mChatSenderName = senderName;
    }

    public String getChatUserAvatarUrl() {
        return mChatUserAvatarUrl;
    }

    public void setChatUserAvatarUrl(String avatarUrl) {
        this.mChatUserAvatarUrl = avatarUrl;
    }
}
