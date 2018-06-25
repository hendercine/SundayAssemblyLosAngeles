/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/24/18 4:49 PM
 */

package com.hendercine.sala.data;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.hendercine.sala.BaseActivity;
import com.hendercine.sala.models.Assembly;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parceler.Parcels;

import java.util.ArrayList;

import timber.log.Timber;

public class SalaSiteIntentService extends IntentService {

    // Base strings to run through Jsoup
    private static final String ASSEMBLIES_URL = "http://www.sundayassemblyla.org";
    private static final String CLASS_NAME = "event-wrap";
    private static final String LI_ELEMENT = "li";
    private static final String ASSEMBLIES = "assemblies";

    private Assembly mAssembly;
    private ArrayList<Assembly> mAssemblyArrayList;

    public SalaSiteIntentService() {
        super("SalaSiteIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            mAssembly = new Assembly();
            final ResultReceiver rec = intent.getParcelableExtra("rec");

            // Check for network
            if (BaseActivity.isNetworkAvailable(this))
            Timber.d("Get html");
            try {
                String assemblyDateLine;
                String assemblyThemeLine;
                String assemblyDescription;
                String assemblyPhotoUrl;

                Document eventSummary = Jsoup.connect(ASSEMBLIES_URL).get();
                Elements assemblyDetails = eventSummary.getElementsByClass(CLASS_NAME);
                Elements assemblies = assemblyDetails.tagName(LI_ELEMENT);
                mAssemblyArrayList = new ArrayList<>();
                for (Element assembly : assemblies) {
                    assemblyDateLine = assembly.tagName("h4").text();
                    assemblyThemeLine = assembly.tagName("strong").text();
                    assemblyDescription = assembly.tagName("span").text();
                    assemblyPhotoUrl = assembly.attr("src");

                    mAssembly.setAssemblyDate(assemblyDateLine);
                    mAssembly.setAssemblyTheme(assemblyThemeLine);
                    mAssembly.setAssemblyDescription(assemblyDescription);
                    mAssembly.setAssemblyPhotoUrl(assemblyPhotoUrl);

                    mAssemblyArrayList.add(mAssembly);
                }

                Bundle args = new Bundle();
                args.putParcelable(ASSEMBLIES, Parcels.wrap(mAssemblyArrayList));
                rec.send(0, args);

            } catch (Exception e) {
                Timber.e("Something went wrong in the background", e);
                e.printStackTrace();
            }

        }
    }

}
