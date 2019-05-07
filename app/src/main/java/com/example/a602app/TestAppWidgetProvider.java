package com.example.a602app;
// This class manages all widgets

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

// Currently all hard coded. Need to find a way to add/remove when app closes (so it appears to update immediately)
public class TestAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //super.onUpdate(context, appWidgetManager, appWidgetIds);
        Intent intentUrl;
        Intent intentLocal;
        PendingIntent pendingIntentUrl;
        PendingIntent pendingIntentLocal;
        RemoteViews remoteViews;

        // There can be more than one widget, loop through them all
        for (int appWidgetId : appWidgetIds) {
            intentUrl = new Intent(context, YouTubeActivity.class);
            pendingIntentUrl = PendingIntent.getActivity(context, 0, intentUrl, 0);

            intentLocal = new Intent(context, PlayerActivity.class);
            pendingIntentLocal = PendingIntent.getActivity(context, 1, intentLocal, 0);

            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            remoteViews.setOnClickPendingIntent(R.id.url_button, pendingIntentUrl);
            remoteViews.setOnClickPendingIntent(R.id.local_button, pendingIntentLocal);

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }
}
