package com.izv.dam.newquip.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Paquete de funciones sueltas que adaptan los datos.
 */
public class UtilFecha {

    public static long dateToLong(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("es", "ES"));
            Date fecha = sdf.parse(date);
            return fecha.getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    public static String formatDate(Date date) {
        return formatStringDate("yyyy-MM-dd HH:mm:ss", date);
    }

    public static String formatStringDate(String formatStr, Date date) {
        if (date == null) {
            return null;
        }
        return android.text.format.DateFormat.format(formatStr, date).toString();
    }


    /*
     * Formato de jue. 1 sept 2016 12:43:00 a 2016-9-1 12:43:00
     */
    public static String cambiarFormato(String fecha_recordatorio, int formato) {
        String formato1 = "E',' d MMM yyyy HH:mm:ss";
        String formato2 = "yyyy-MM-dd HH:mm:ss";
        switch (formato) {
            case 1:
                formato1 = "yyyy-MM-dd HH:mm:ss";
                formato2 = "E',' d MMM yyyy HH:mm:ss";
                break;
            case 2:
                formato1 = "E',' d MMM yyyy HH:mm:ss";
                formato2 = "yyyy-MM-dd HH:mm:ss";
                break;
        }
        String original = fecha_recordatorio.trim();
        DateFormat originalFormat = new SimpleDateFormat(formato1, new Locale("es", "ES"));
        DateFormat otherFormat = new SimpleDateFormat(formato2, new Locale("es", "ES"));
        Date date = null;
        try {
            date = originalFormat.parse(original);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String formattedDate;
        if (date != null) {
            formattedDate = otherFormat.format(date);
        } else {
            formattedDate = "";
        }

        return formattedDate;
    }


    public static String[] cortarFormato(String fecha_a_cortar) {
        String[] re = {"", ""};
        String fecha;
        // fecha = "mié., 19 oct. 2016 19:52:00";
        fecha = fecha_a_cortar;
        int mitad = fecha.lastIndexOf(" ");

        re[0] = fecha.substring(0, mitad).trim();
        re[1] = fecha.substring(mitad).trim();
        return re;
    }

    public static String fechaHoy() {
        SimpleDateFormat formato = new SimpleDateFormat("E',' d MMM yyyy HH:mm:ss", new Locale("es", "ES"));
        Calendar c = Calendar.getInstance();

        return formato.format(c.getTime());
    }

    public static String fechaHoyDia() {
        SimpleDateFormat formato = new SimpleDateFormat("E',' d MMM yyyy", new Locale("es", "ES"));
        Calendar c = Calendar.getInstance();

        return formato.format(c.getTime());
    }

    public static String fechaHoyHora() {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss", new Locale("es", "ES"));
        Calendar c = Calendar.getInstance();

        return formato.format(c.getTime());
    }

    public static Long stringToLongTime(String alarm) {
        SimpleDateFormat string_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        Long longDate;
        try {
            date = string_date.parse(alarm);
            longDate = date.getTime();
        } catch (ParseException ex) {
            longDate = null;
            ex.printStackTrace();
        }

        return longDate;
    }
}