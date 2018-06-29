/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/26/18 1:19 AM
 */

package com.hendercine.sala.ui.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.hendercine.sala.models.Assembly;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import timber.log.Timber;

public class WidgetIntentService extends IntentService {

    private static final String ACTION_ADD_DATE = "com.hendercine.sala.data.action.UPDATE";
    private static final String ASSEMBLIES = "assemblies";
    // Base strings to run through Jsoup
    private static final String ASSEMBLIES_URL = "http://www.sundayassemblyla.org";
    private static final String LI_ELEMENT = "li";

    public WidgetIntentService() {
        super("WidgetIntentService");
    }

    public static void startActionAddDate(Context context) {
        Intent intent = new Intent(context, WidgetIntentService.class);
        intent.setAction(ACTION_ADD_DATE);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD_DATE.equals(action)) {
                handleActionAddDate();
            }
        }
    }

    private void handleActionAddDate() {

        Assembly mAssembly;
        ArrayList<Assembly> titleArray = new ArrayList<>();

        try {
            String assemblyDateLine;

            Document eventSummary = Jsoup.connect(ASSEMBLIES_URL).get();
            Element assemblies = eventSummary.tagName(LI_ELEMENT);
            Elements titles = assemblies.select("h4");

            for (Element title : titles) {
                mAssembly = new Assembly();
                assemblyDateLine = title.text();
                mAssembly.setAssemblyDate(assemblyDateLine);
                titleArray.add(mAssembly);
            }


        } catch (Exception e) {
            Timber.e("Something went wrong in the background", e);
            e.printStackTrace();
        }
        String dateStr = titleArray.get(0).getAssemblyDate();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new
                ComponentName(this, SalaWidgetProvider.class));
        SalaWidgetProvider.updateDateWidgets(this, appWidgetManager,
                dateStr, appWidgetIds);
    }

}
