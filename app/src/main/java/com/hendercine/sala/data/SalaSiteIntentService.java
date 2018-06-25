/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/24/18 10:54 AM
 */

package com.hendercine.sala;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.ResultReceiver;

import com.hendercine.sala.models.Assembly;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parceler.Parcels;

import java.util.ArrayList;

import timber.log.Timber;

public class SalaSiteIntentService extends IntentService {

    // Base Urls to run through Jsoup
    private static final String ASSEMBLIES_URL = "http://www.sundayassemblyla.org";
    private static final String HELP_OFTEN_URL = "http://www.sundayassemblyla.org/help_often";
    private static final String LIVE_BETTER_URL = "http://www.sundayassemblyla.org/live_better";

    private static final String CLASS_NAME = "event-wrap";
    private static final String ELEMENT_NAME = "li";
    private Assembly mAssembly;
    private ArrayList<String> mAssemblyStrArrayList;
    private ArrayList<Assembly> mAssemblyDetailsList;

    public SalaSiteIntentService() {
        super("SalaSiteIntentService");
    }

    public static void startParsingAssemblies(Context context, String className,
                                              String element) {
        Intent intent = new Intent(context, SalaSiteIntentService.class);
        intent.setAction(ASSEMBLIES_URL);
        intent.putExtra(CLASS_NAME, className);
        intent.putExtra(ELEMENT_NAME, element);
        context.startService(intent);
    }

    public static void startParsingHelpOften(Context context, String className,
                                       String element) {
        Intent intent = new Intent(context, SalaSiteIntentService.class);
        intent.setAction(HELP_OFTEN_URL);
        intent.putExtra(CLASS_NAME, className);
        intent.putExtra(ELEMENT_NAME, element);
        context.startService(intent);
    }

    public static void startParsingLiveBetter(Context context, String className,
                                          String element) {
        Intent intent = new Intent(context, SalaSiteIntentService.class);
        intent.setAction(LIVE_BETTER_URL);
        intent.putExtra(CLASS_NAME, className);
        intent.putExtra(ELEMENT_NAME, element);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            mAssembly = Parcels.unwrap(intent.getParcelableExtra("assembly"));
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
                Elements assemblies = eventSummary.getElementsByClass
                        (CLASS_NAME);
                mAssemblyStrArrayList = new ArrayList<>();
                for (Element assembly : assemblies) {
                    Element assemblyDetail = assembly.tagName(ELEMENT_NAME);
                    mAssemblyStrArrayList.add(assemblyDetail.text());
                }
                mAssemblyDetailsList = new ArrayList<>();

                if (mAssemblyStrArrayList != null) {
                    for (int i = 0; i < mAssemblyStrArrayList.size(); i++) {
                        mAssembly = new Assembly();
                        mAssembly.setAssemblyDate(mAssemblyStrArrayList.get(i));
                        mAssembly.setAssemblyTheme(mAssemblyStrArrayList.get(i));
                        mAssembly.setAssemblyDescription(mAssemblyStrArrayList.get(i));

                        mAssemblyDetailsList.add(mAssembly);
                    }

                }

//                mAssembliesList = new ArrayList<>();
//                if (assemblies != null) {
//                    for (int i = 0; i < assemblies.size(); i++) {

//                        mAssembly.setAssemblyDate(assemblies.get(i).getAssemblyDate());
//                        mAssembly.setAssemblyTheme(assemblies.get(i).getAssemblyTheme());
//                        mAssembly.setAssemblyDescription(assemblies.get(i).getAssemblyDescription());
//                        mAssembly.setAssemblyPhotoUrl(assemblies.get(i).getAssemblyPhotoUrl());
//
//                        mAssembliesList.add(mAssembly);
//                    }
//
//                }

            } catch (Exception e) {
                Timber.e("Something went wrong in the background", e);
                e.printStackTrace();
            }

//            final String action = intent.getAction();
//            if (ASSEMBLIES_URL.equals(action)) {
//                final String className = intent.getStringExtra(CLASS_NAME);
//                final String element = intent.getStringExtra(ELEMENT_NAME);
//                handleParseAssebmlies(className, element);
//            } else if (HELP_OFTEN_URL.equals(action)) {
//                final String className = intent.getStringExtra(CLASS_NAME);
//                final String element = intent.getStringExtra(ELEMENT_NAME);
//                handleHelpOften(className, element);
//            } else if (LIVE_BETTER_URL.equals(action)) {
//                final String className = intent.getStringExtra(CLASS_NAME);
//                final String element = intent.getStringExtra(ELEMENT_NAME);
//                handleLiveBetter(className, element);
//            }
        }
    }

    private void handleParseAssebmlies(String className, String element) {
        // TODO: Handle action
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleHelpOften(String className, String element) {
        // TODO: Handle action
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleLiveBetter(String className, String element) {
        // TODO: Handle action
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
