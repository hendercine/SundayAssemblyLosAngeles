/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 5/20/18 1:58 PM
 */

package com.hendercine.sala;

import org.parceler.Parcel;

/**
 * sala created by hendercine on 5/20/18.
 */

@SuppressWarnings("WeakerAccess")
@Parcel
public class User {
    // Fields must not be private for Parceler
    String firstName;
    String lastName;
    String username;
    String email;

    public User() {
        // Neccessary empty constructor for Parceler
    }

    public User(String firstName, String lastName, String username, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

