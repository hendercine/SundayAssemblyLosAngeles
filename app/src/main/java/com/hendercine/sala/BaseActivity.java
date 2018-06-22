/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/21/18 1:27 PM
 */

package com.hendercine.sala;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

import icepick.Icepick;

/**
 * sala created by hendercine on 6/21/18.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    public void showProgressBar() {
        if (mProgressBar == null) {
            mProgressBar = new ProgressBar(this);
        }

        mProgressBar.setVisibility(ProgressBar.VISIBLE);
    }

    public void hideProgressBar() {
        if (mProgressBar != null && mProgressBar.getVisibility() == ProgressBar
                .VISIBLE) {
            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

}
