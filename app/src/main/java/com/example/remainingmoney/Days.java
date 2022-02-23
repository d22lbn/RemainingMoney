package com.example.remainingmoney;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Days {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    public static int getRemainingDays() {
        Date dateNow = new Date();

        int yearNow = Integer.parseInt((new SimpleDateFormat("yyyy")).format(dateNow));
        int monthNow = Integer.parseInt((new SimpleDateFormat("MM")).format(dateNow));
        int dayNow = Integer.parseInt((new SimpleDateFormat("dd")).format(dateNow));

        int year = yearNow;
        int month = monthNow;
        int day = dayNow;

        if (dayNow >= 5 && dayNow < 20) {
            day = 20;
        } else {
            day = 5;
            month++;
            if (month >= 13) {
                month = 1;
                year++;
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(getForm(dayNow, monthNow, yearNow), formatter);
        LocalDate endDate = LocalDate.parse(getForm(day, month, year), formatter);
        Period period = Period.between(startDate, endDate);
        return period.getDays();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static int getTravelDays() {
        Date dateNow = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateNow);
        int num = (cal.get(Calendar.DAY_OF_WEEK) + 5) % 7;
        int count = 0;
        for (int i = 0; i < Days.getRemainingDays(); i++) {
            if (num != 5 && num != 6) {
                count++;
            }
            num = (num + 1) % 7;
        }
        return count;
    }

    private static String getForm(int a, int b, int c) {
        return getFormNum(a) + "." + getFormNum(b) + "." + getFormNum(c);
    }

    private static String getFormNum(int a) {
        if (a < 10) {
            return "0" + a;
        }
        return "" + a;
    }
}

