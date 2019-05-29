package com.redbox.FourthLabRPP;

import android.app.PendingIntent;
import android.appwidget.AppWidgetProvider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Locale;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.redbox.FourthLabRPP.R;

public class MyWidget extends AppWidgetProvider {
    final String LOG_TAG = "myLogs";
    private static LocalDate localDate = LocalDate.now();
    private static LocalDate selDate;

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(LOG_TAG, "onEnabled");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(LOG_TAG, "onUpdate " + Arrays.toString(appWidgetIds));

        SharedPreferences sp = context.getSharedPreferences(
                Config.WIDGET_PREF, Context.MODE_PRIVATE);
        for (int id : appWidgetIds) {
            updateWidget(context, appWidgetManager, sp, id);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(LOG_TAG, "onDeleted " + Arrays.toString(appWidgetIds));

        // Удаляем Preferences
        SharedPreferences.Editor editor = context.getSharedPreferences(
                Config.WIDGET_PREF, Context.MODE_PRIVATE).edit();
        for (int widgetID : appWidgetIds) {
            editor.remove(Config.WIDGET_TEXT + widgetID);
        }
        editor.commit();
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(LOG_TAG, "onDisabled");
    }

    static void updateWidget(Context context, AppWidgetManager appWidgetManager,
                             SharedPreferences sp, int widgetID) {
        Log.d("T", "updateWidget " + widgetID);

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-M-yyyy", Locale.US);
        // Читаем параметры Preferences
        String widgetText = sp.getString(Config.WIDGET_TEXT + widgetID, null);
        if (widgetText == null) return;

        String widgetDate = sp.getString(Config.WIDGET_DATE+widgetID, "20-5-2019");
        selDate = LocalDate.parse(widgetDate, dateTimeFormatter);

        long days = 30 - ChronoUnit.DAYS.between( selDate, localDate);

        widgetText = days + " ДНЕЙ ОСТАЛОСЬ ДО " + widgetDate;

        RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget);

        Intent intent = new Intent(context, Config.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, widgetID, intent, 0);

        widgetView.setTextViewText(R.id.tv, widgetText);

        widgetView.setOnClickPendingIntent(R.id.r_layout, pendingIntent);

        // Обновляем виджет
        appWidgetManager.updateAppWidget(widgetID, widgetView);
    }

    public void onLayoutClicked(View v){
        Log.d("T", "onLayoutClicked: ");
    }
}
