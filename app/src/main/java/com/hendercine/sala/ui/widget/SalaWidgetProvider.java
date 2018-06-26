/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/14/18 1:05 PM
 */

package com.hendercine.sala.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.hendercine.sala.R;

/**
 * Implementation of App Widget functionality.
 */
public class SalaWidgetProvider extends AppWidgetProvider {

    private static final String ACTION_CLICK = "ACTION_CLICK";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                String dateStr, int appWidgetId) {

        Intent intent = new Intent(context, WidgetIntentService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                intent, 0
        );
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.sala_widget);
        views.setTextViewText(R.id.sala_widget_date_text, dateStr);
        // Construct the RemoteViews object
        views.setOnClickPendingIntent(R.id.sala_widget_date_text, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

       WidgetIntentService.startActionAddDate(context);
    }

    public static void updateDateWidgets(Context context, AppWidgetManager
            appWidgetManager, String dateStr, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, dateStr, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

