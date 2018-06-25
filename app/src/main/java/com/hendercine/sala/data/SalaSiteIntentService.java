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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hendercine.sala.BaseActivity;
import com.hendercine.sala.models.Assembly;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class SalaSiteIntentService extends IntentService {

    // Base strings to run through Jsoup
    private static final String ASSEMBLIES_URL = "http://www.sundayassemblyla.org";
    private static final String LI_ELEMENT = "li";
    private static final String ASSEMBLIES = "assemblies";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseRef;

    private Assembly mAssembly;
    private ArrayList<Assembly> mAssemblyArrayList;
    private ArrayList<Assembly> titleArray;
    private ArrayList<Assembly> themeArray;
    private ArrayList<Assembly> descArray;
    private ArrayList<Assembly> picsArray;

    public SalaSiteIntentService() {
        super("SalaSiteIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final ResultReceiver rec = intent.getParcelableExtra("rec");
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDatabaseRef = mFirebaseDatabase.getReference();

            // Check for network
            if (!BaseActivity.isNetworkAvailable(this))
                Timber.d("Get html");

                try {
                    String assemblyDateLine;
                    String assemblyThemeLine;
                    String assemblyDescriptionLine;
                    String assemblyPhotoUrl;

                    Document eventSummary = Jsoup.connect(ASSEMBLIES_URL).get();
                    Element assemblies = eventSummary.tagName(LI_ELEMENT);
                    Elements titles = assemblies.select("h4");
                    Elements themes = assemblies.select("strong");
                    Elements descriptions = assemblies.select("span");
                    Elements photoSources = assemblies.getElementsByClass
                            ("event-wrap").select("img");

                    mAssemblyArrayList = new ArrayList<>();
                    titleArray = new ArrayList<>();
                    themeArray = new ArrayList<>();
                    descArray = new ArrayList<>();
                    picsArray = new ArrayList<>();

                    for (Element title : titles) {
                        mAssembly = new Assembly();
                        assemblyDateLine = title.text();
                        mAssembly.setAssemblyDate(assemblyDateLine);
                        titleArray.add(mAssembly);
                    }

                    for (Element theme : themes) {
                        mAssembly = new Assembly();
                        assemblyThemeLine = theme.text();
                        mAssembly.setAssemblyTheme(assemblyThemeLine);
                        themeArray.add(mAssembly);
                    }

                    for (Element description : descriptions) {
                        mAssembly = new Assembly();
                        assemblyDescriptionLine = description.text();
                        mAssembly.setAssemblyDescription(assemblyDescriptionLine);
                        descArray.add(mAssembly);
                    }

                    for (Element photoSource : photoSources) {
                        mAssembly = new Assembly();
                        assemblyPhotoUrl = photoSource.attr("abs:src");
                        mAssembly.setAssemblyPhotoUrl(assemblyPhotoUrl);
                        picsArray.add(mAssembly);
                    }

                    Map<String, Object> assemblyMaps = new HashMap<>();
                    assemblyMaps.put("assembly_date", titleArray);
                    assemblyMaps.put("assembly_description", descArray);
                    assemblyMaps.put("assembly_photo_url", picsArray);
                    assemblyMaps.put("assembly_theme", themeArray);

                    mAssemblyArrayList.addAll(titleArray);
                    mDatabaseRef.updateChildren(assemblyMaps);
//                    mAssemblyArrayList.addAll(themeArray);
//                    mAssemblyArrayList.addAll(descArray);*-+

//                    mAssemblyArrayList.addAll(picsArray);

                    Timber.i(
                            "Is there a title string here in svc: '%s'",
                            titleArray.get(0).getAssemblyDate()
                    );
                    Timber.i(
                            "Is there a title string here in svc: '%s'",
                            mAssemblyArrayList.get(1).getAssemblyDate()
                    );
                    Timber.i(
                            "Is there a title string here in svc: '%s'",
                            mAssemblyArrayList.get(2).getAssemblyDate()
                    );
                    Timber.i(
                            "Is there a title string here in svc: '%s'",
                            mAssemblyArrayList.get(3).getAssemblyDate()
                    );
                    Timber.i(
                            "Is there a title string here in svc: '%s'",
                            mAssemblyArrayList.get(4).getAssemblyDate()
                    );
                    Timber.i(
                            "Is there a theme string here in svc: '%s'",
                            themeArray.get(0).getAssemblyTheme()
                    );
                    Timber.i(
                            "Is there a description string here in svc: '%s'",
                            descArray.get(0).getAssemblyDescription()
                    );
                    Timber.i(
                            "Is there a string photo url here in svc: '%s'",
                            picsArray.get(0).getAssemblyPhotoUrl()
                    );

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

