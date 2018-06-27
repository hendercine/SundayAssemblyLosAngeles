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
    public static final String ASSEMBLY_THEME = "assembly_theme";
    public static final String ASSEMBLY_PHOTO_URL = "assembly_photo_url";
    public static final String ASSEMBLY_DESCRIPTION = "assembly_description";
    public static final String ASSEMBLY_DATE = "assembly_date";
    public static final String ABS_SRC = "abs:src";
    public static final String IMG = "img";
    public static final String EVENT_WRAP = "event-wrap";
    public static final String SPAN = "span";
    public static final String STRONG = "strong";
    public static final String H_4 = "h4";
    public static final String REC = "rec";
    public static final String SALA_SITE_INTENT_SERVICE = "SalaSiteIntentService";

    public SalaSiteIntentService() {
        super(SALA_SITE_INTENT_SERVICE);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final ResultReceiver rec = intent.getParcelableExtra(REC);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseRef = firebaseDatabase.getReference()
                    .child(ASSEMBLIES);

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
                    Elements titles = assemblies.select(H_4);
                    Elements themes = assemblies.select(STRONG);
                    Elements descriptions = assemblies.select(SPAN);
                    Elements photoSources = assemblies.getElementsByClass
                            (EVENT_WRAP).select(IMG);

                    ArrayList<Assembly> assemblyArrayList = new ArrayList<>();
                    ArrayList<Assembly> titleArray = new ArrayList<>();
                    ArrayList<Assembly> themeArray = new ArrayList<>();
                    ArrayList<Assembly> descArray = new ArrayList<>();
                    ArrayList<Assembly> picsArray = new ArrayList<>();

                    Assembly assembly;
                    for (Element title : titles) {
                        assembly = new Assembly();
                        assemblyDateLine = title.text();
                        assembly.setAssemblyDate(assemblyDateLine);
                        titleArray.add(assembly);
                    }

                    for (Element theme : themes) {
                        assembly = new Assembly();
                        assemblyThemeLine = theme.text();
                        assembly.setAssemblyTheme(assemblyThemeLine);
                        themeArray.add(assembly);
                    }

                    for (Element description : descriptions) {
                        assembly = new Assembly();
                        assemblyDescriptionLine = description.text();
                        assembly.setAssemblyDescription(assemblyDescriptionLine);
                        descArray.add(assembly);
                    }

                    for (Element photoSource : photoSources) {
                        assembly = new Assembly();
                        assemblyPhotoUrl = photoSource.attr(ABS_SRC);
                        assembly.setAssemblyPhotoUrl(assemblyPhotoUrl);
                        picsArray.add(assembly);
                    }

                    Map<String, Object> assemblyMaps = new HashMap<>();
                    assemblyMaps.put(ASSEMBLY_DATE, titleArray);
                    assemblyMaps.put(ASSEMBLY_DESCRIPTION, descArray);
                    assemblyMaps.put(ASSEMBLY_PHOTO_URL, picsArray);
                    assemblyMaps.put(ASSEMBLY_THEME, themeArray);

                    assemblyArrayList.addAll(titleArray);
                    databaseRef.updateChildren(assemblyMaps);

                    Bundle args = new Bundle();
                    args.putParcelable(ASSEMBLIES, Parcels.wrap(assemblyArrayList));
                    rec.send(0, args);

                } catch (Exception e) {
                    Timber.e("Something went wrong in the background", e);
                    e.printStackTrace();
                }
            }
        }
    }

