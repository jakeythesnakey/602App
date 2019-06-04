package com.example.a602app

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class WidgetActivity : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // Perform this loop procedure for each App Widget that belongs to this provider
        appWidgetIds.forEach { appWidgetId ->
            val pendingIntent1: PendingIntent = Intent(context, ActivityRdr1::class.java)
                .let { intent -> PendingIntent.getActivity(context, 0, intent, 0)}

            val pendingIntent2: PendingIntent = Intent(context, ActivityRdr2::class.java)
                .let { intent -> PendingIntent.getActivity(context, 0, intent, 0)}
            val pendingIntent3: PendingIntent = Intent(context, ActivityRdr3::class.java)
                .let { intent -> PendingIntent.getActivity(context, 0, intent, 0)}
            val pendingIntent4: PendingIntent = Intent(context, ActivityRdr4::class.java)
                .let { intent -> PendingIntent.getActivity(context, 0, intent, 0)}
            val pendingIntent5: PendingIntent = Intent(context, ActivityRdr5::class.java)
                .let { intent -> PendingIntent.getActivity(context, 0, intent, 0)}
            val pendingIntent6: PendingIntent = Intent(context, ActivityRdr6::class.java)
                .let { intent -> PendingIntent.getActivity(context, 0, intent, 0)}
            val pendingIntent7: PendingIntent = Intent(context, ActivityRdr7::class.java)
                .let { intent -> PendingIntent.getActivity(context, 0, intent, 0)}
            val pendingIntent8: PendingIntent = Intent(context, ActivityRdr8::class.java)
                .let { intent -> PendingIntent.getActivity(context, 0, intent, 0)}
            val pendingIntent9: PendingIntent = Intent(context, ActivityRdr9::class.java)
                .let { intent -> PendingIntent.getActivity(context, 0, intent, 0)}
            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            val views: RemoteViews = RemoteViews(context.packageName, R.layout.widget).apply {
                setOnClickPendingIntent(R.id.widgetButton1, pendingIntent1)
                setOnClickPendingIntent(R.id.widgetButton2, pendingIntent2)
                setOnClickPendingIntent(R.id.widgetButton3, pendingIntent3)
                setOnClickPendingIntent(R.id.widgetButton4, pendingIntent4)
                setOnClickPendingIntent(R.id.widgetButton5, pendingIntent5)
                setOnClickPendingIntent(R.id.widgetButton6, pendingIntent6)
                setOnClickPendingIntent(R.id.widgetButton7, pendingIntent7)
                setOnClickPendingIntent(R.id.widgetButton8, pendingIntent8)
                setOnClickPendingIntent(R.id.widgetButton9, pendingIntent9)
            }

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}