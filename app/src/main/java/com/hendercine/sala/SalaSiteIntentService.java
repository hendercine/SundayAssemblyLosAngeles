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

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SalaSiteIntentService extends IntentService {

    // Base Urls to run through Jsoup
    private static final String ASSEMBLIES_URL = "http://www.sundayassemblyla.org";
    private static final String HELP_OFTEN_URL = "http://www.sundayassemblyla.org/help_often";
    private static final String LIVE_BETTER_URL = "http://www.sundayassemblyla.org/live_better";

    // TODO: Rename parameters
    private static final String CLASS_NAME = "event-wrap";
    private static final String ELEMENT_NAME = "li";

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
            final String action = intent.getAction();
            if (ASSEMBLIES_URL.equals(action)) {
                final String className = intent.getStringExtra(CLASS_NAME);
                final String element = intent.getStringExtra(ELEMENT_NAME);
                handleParseAssebmlies(className, element);
            } else if (HELP_OFTEN_URL.equals(action)) {
                final String className = intent.getStringExtra(CLASS_NAME);
                final String element = intent.getStringExtra(ELEMENT_NAME);
                handleHelpOften(className, element);
            } else if (LIVE_BETTER_URL.equals(action)) {
                final String className = intent.getStringExtra(CLASS_NAME);
                final String element = intent.getStringExtra(ELEMENT_NAME);
                handleLiveBetter(className, element);
            }
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
