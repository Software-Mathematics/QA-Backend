package com.commons.util.sequenceGenerator;

import com.google.common.base.CharMatcher;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
public class Db_sequenceDao {
    Random random = new Random();

    public String getSequenceNumber() {
        return UUID.randomUUID().toString();
    }

    public String getDateAndTime() {
        Date dateTime = new Date();
        String dateTimeFormat = "dd-MM-yyyy HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
        String dateTimeFormatted = dateFormat.format(dateTime);
        return dateTimeFormatted;
    }
    public String getDateAndTime(String dateTimeFormat) {
        Date dateTime = new Date();
        DateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
        String dateTimeFormatted = dateFormat.format(dateTime);
        return dateTimeFormatted;
    }

    public Date getDateTimeFormatter(String dateTimeFormat, String date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(dateTimeFormat);
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // Set the timezone explicitly
        return  formatter.parse(date);
    }
    public Date getDateTimeFormatterV2(String dateTimeFormat, Date date, String zone) throws ParseException {
        TimeZone timeZone = TimeZone.getTimeZone(zone);
        DateFormat formatter = new SimpleDateFormat(dateTimeFormat);
        formatter.setTimeZone(timeZone);
        return  getDateTimeFormatter(dateTimeFormat, formatter.format(date));
    }

    public Date getDateAndTime(String dateTimeFormat, Date date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
        String dateTimeFormatted = dateFormat.format(date);
        return getDateTimeFormatter(dateTimeFormat, dateTimeFormatted);
    }
    public Date getDate(String dateTimeFormat, Date date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(dateTimeFormat);
        return formatter.parse(formatter.format(date));

    }

    public Date getNewDateTimeFormatter(String dateTimeFormat) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(dateTimeFormat);
        return  formatter.parse(getDateAndTime());

    }


    public String idGenerator(String prefix, String mmuCode){
        String result = new String();
        if(prefix != null) {
            result += prefix;
        }
        if(mmuCode != null){
            result += mmuCode;
        }
        result += getDateAndTime("ddMMyyHHmmssSSS");
        return result;
    }

    public String sequenceGeneratorForCode(String name) {
        return generatePrefix(name) + random.nextInt(9999);
    }

    public String generateRandom(int randomSize){
        switch (randomSize){
            case 4:
                return ""+random.nextInt(9999);
            case 6:
                return ""+random.nextInt(999999);
            default:
                return ""+random.nextInt(9999);
        }

    }
    public static String generateOTP(int length) {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // Generate a random digit (0-9)
            otp.append(digit); // Append the digit to the OTP
        }

        return otp.toString();
    }

    public String generatePrefix(String name){
        StringBuilder stringBuilder = new StringBuilder();
        String rName = CharMatcher.anyOf("*#&!@$%").removeFrom(name);

        String characters = rName;
        String result = "";
        Boolean addValue = true;
        Boolean add2Char = true;
        for (int i = 0; i < characters.length(); i++) {
            char c = characters.charAt(i);
            if (characters.charAt(i) == ' ') {
                addValue = true;
            } else if (addValue) {
                stringBuilder.append(c);
                addValue = false;
            }
            if (add2Char) {
                stringBuilder.append(characters.charAt(i + 1));
                add2Char = false;
            }
        }

        result = stringBuilder.toString().toUpperCase();
        return result;
    }
    public String codeGenerator() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String randomString = "";
        int length = 3;

        Random rand = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }
        for (int i = 0; i < text.length; i++) {
            randomString += text[i];
        }
        System.out.println("text = " + text);
        return randomString;
    }

    public ZonedDateTime parseToZonedDateTime(String date, String dateFormat, String zone) {
        // use java.time from java 8
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        ZonedDateTime zonedDateTime = null;
        try {
            zonedDateTime = ZonedDateTime.parse(date, formatter);
        } catch (DateTimeException e) {
            // couldn't parse to a ZoneDateTime, try LocalDateTime
            LocalDateTime dt = LocalDateTime.parse(date, formatter);

            // convert to a timezone
            zonedDateTime = dt.atZone(ZoneId.of(zone));
        }
        return zonedDateTime;
    }

    public Pair<Date, Date> getDateRange() {
        Date begining, end;

        {
            Calendar calendar = getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            setTimeToBeginningOfDay(calendar);
            begining = calendar.getTime();
        }

        {
            Calendar calendar = getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            setTimeToEndofDay(calendar);
            end = calendar.getTime();
        }

        return Pair.of(begining, end);
    }

    public Pair<Date, Date> getDateRange(Date date) {

        Calendar calendar = getCalendarForNow(date);
        setTimeToBeginningOfDay(calendar);
        Date beginning = calendar.getTime();
        calendar = getCalendarForNow(date);
        setTimeToEndofDay(calendar);
        Date end = calendar.getTime();
        return Pair.of(beginning, end);


//        Date begining, end;
//
//        {
//            Calendar calendar = getCalendarForNow(date);
////            calendar.set(Calendar.DATE,
////                    calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
//            setTimeToBeginningOfDay(calendar);
//            begining = calendar.getTime();
//        }
//
//        {
//            Calendar calendar = getCalendarForNow(date);
////            calendar.set(Calendar.DATE,
////                    calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
//            setTimeToEndofDay(calendar);
//            end = calendar.getTime();
//        }
//
//        return Pair.of(begining, end);
    }

    private static Calendar getCalendarForNow() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }
    private static Calendar getCalendarForNow(Date date) {
       // Calendar calendar = GregorianCalendar.getInstance();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata")); // Set the timezone explicitly
        calendar.setTime(date);
        return calendar;
    }
    private static void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

}
