/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/24/18 3:02 PM
 */

package com.hendercine.sala;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * sala created by hendercine on 6/24/18.
 */
public class SiteServiceReceiver extends ResultReceiver {

    Listener mListener;

    public SiteServiceReceiver(Handler handler) {
        super(handler);
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mListener != null) {
            mListener.onReceiveResult(resultCode, resultData);
        }
    }

    public static interface Listener {

        void onReceiveResult(int resultCode, Bundle resultData);

    }
}
