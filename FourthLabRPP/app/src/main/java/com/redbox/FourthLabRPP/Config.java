package com.redbox.FourthLabRPP;


import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.allyants.notifyme.NotifyMe;
import com.redbox.FourthLabRPP.R;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import java.time.LocalDate;
import java.util.Locale;


public class Config extends AppCompatActivity {
    int widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;
    Intent resultValue;

    final String LOG_TAG = "myLogs";

    public final static String WIDGET_PREF = "widget_pref";
    public final static String WIDGET_TEXT = "widget_text_";
    public final static String WIDGET_DATE = "widget_real_date";
    private Calendar now = Calendar.getInstance();

    private String date_str = " 0 0  0";
    private String realDate;
    private LocalDate localDate = LocalDate.now();
    private LocalDate selDate;

    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        datePicker = (DatePicker) findViewById(R.id.datepick);

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-M-yyyy", Locale.US);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        if (widgetID == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        now.set(Calendar.YEAR, now.get(Calendar.YEAR));
        now.set(Calendar.MONTH, now.get(Calendar.MONTH));
        now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));

        date_str = now.get(Calendar.DAY_OF_MONTH) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.YEAR);
        selDate = LocalDate.parse(date_str,dateTimeFormatter);
        realDate = date_str;
        date_str = 0 + " ДНЕЙ ОСТАЛОСЬ ДО " + now.get(Calendar.DAY_OF_MONTH) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.YEAR);

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                now.set(Calendar.YEAR, year);
                now.set(Calendar.MONTH, monthOfYear);
                now.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                date_str = dayOfMonth + "-" + (monthOfYear) + "-" + year;
                realDate = date_str;

                selDate = LocalDate.parse(date_str,dateTimeFormatter);

                long days = 30 - ChronoUnit.DAYS.between( selDate, localDate);

                date_str = days + " ДНЕЙ ОСТАЛОСЬ ДО " + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                Log.d("TT", "onDateChanged: " + (days));

            }
        });

        now.set(Calendar.HOUR_OF_DAY, 9);
        now.set(Calendar.MINUTE, 0);

        resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);

        setResult(RESULT_CANCELED, resultValue);

    }

    public void onClick(View v) {
        // Записываем значения с экрана в Preferences
        SharedPreferences sp = getSharedPreferences(WIDGET_PREF, MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(WIDGET_TEXT + widgetID, date_str);
        editor.putString(WIDGET_DATE + widgetID, realDate);
        editor.commit();

        NotifyMe.Builder notify = new NotifyMe.Builder(getApplicationContext());
        notify.title("СОБЫТИЕ НАСТУПИЛО").time(now).build();

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        MyWidget.updateWidget(this, appWidgetManager, sp, widgetID);

        // положительный ответ
        setResult(RESULT_OK, resultValue);

        Log.d(LOG_TAG, "finish config " + widgetID);
        finish();
    }
}
