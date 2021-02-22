package com.kurchavov.exchangeratestogif.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// Утилитный класс

public class Util {
    public static final SimpleDateFormat OXRDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");

    // Возвращает строковое представление даты, прошедшей "count" дней назад

    public static String getPreviousDate(int count) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -count);
        return Util.OXRDATEFORMAT.format(cal.getTime());
    }
}
