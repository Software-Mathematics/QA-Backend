package com.commons.util.adapter.util;

import com.commons.data.dao.commonDao.BaseDaoImpl;
import com.commons.util.model.dto.BaseDtoI;
import com.commons.util.sequenceGenerator.Db_sequenceDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class Utility {

    @Autowired
    private BaseDaoImpl baseDao;
    @Autowired
    private Db_sequenceDao dbseq;

    public static long calculateTimeDifferenceInMinutes(String startTime, String endTime) {
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);

        // Calculate the difference directly if end time is after start time
        if (!end.isBefore(start)) {
            return ChronoUnit.MINUTES.between(start, end);
        } else {
            // If end time is before start time, calculate difference across midnight
            long minutesUntilMidnight = ChronoUnit.MINUTES.between(start, LocalTime.MAX) + 1;
            long minutesFromMidnight = ChronoUnit.MINUTES.between(LocalTime.MIDNIGHT, end);
            return minutesUntilMidnight + minutesFromMidnight;
        }
    }
    public static String dateFormatter(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return sdf.format(date);
    }
    public long getMonthBetweenTwoDates(Date sDate, Date eDate){
        Period diff = Period.between(
                LocalDate.parse(getDate(sDate, "yyyy-MM-dd")).withDayOfMonth(1),
                LocalDate.parse(getDate(eDate, "yyyy-MM-dd")).withDayOfMonth(1));
        return diff.toTotalMonths();
    }
    public long getDaysBetweenTwoDates(Date sDate, Date eDate){
        long diff = eDate.getTime() - sDate.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
//        Duration diff = Duration.between(
//                LocalDate.parse(getDate(sDate, "yyyy-MM-dd")),
//                LocalDate.parse(getDate(eDate, "yyyy-MM-dd")));
//        return diff.toDays();
    }

    public String getDate(Date date, String formatter){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
        return simpleDateFormat.format(date);
    }
    public static String getDateV3(Date date, String formatter){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
        return simpleDateFormat.format(date);
    }
    public static Date getDateV2(Date date, String formatter) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
        return simpleDateFormat.parse(simpleDateFormat.format(date));
    }
    public static Date selectTimeZone(String timeZone, String formatter, Date date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
        String strDate= simpleDateFormat.format(date);

        DateFormat fmt2 = new SimpleDateFormat(formatter);
        fmt2.setTimeZone(TimeZone.getTimeZone(timeZone));
        return fmt2.parse(strDate);

    }

    public static Date converterLongToDate(Long date){
        return new Date(date);
    }
    public static Long converterStringToLong(String data){
        return Long.valueOf(data);
    }

    public static BaseDtoI convertObjectToDTO(Object object, BaseDtoI baseDtoI){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(object, baseDtoI.getClass());
    }

    public static void callingPOSTAPI(String url, Object object){
        RestTemplate restTemplate = new RestTemplate();
        try {
            System.out.println("connecting........");
            ResponseEntity<String> result = restTemplate.postForEntity(url, object, String.class);
            System.out.println("Connected !!");
            System.out.println("result = " + result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String arrayConcat(List<String> stringList, String del){
        String response = "";
        if (stringList != null){
            for (int i=0; i<stringList.size(); i++){
                if (i == stringList.size() -1){
                    response = response + stringList.get(i);
                }else {
                    response = response + stringList.get(i) + del;
                }
            }
        }
        return response;
    }

    public static Object dynamicCasting(Object something, String dataType) throws ClassNotFoundException {
        Object response = new Object();
        if(dataType == null || something == null){
            return null;
        }
        String theType = dataType;
        switch (theType){
            case "java.lang.Long":
               response =  Long.valueOf(something.toString());
               break;
            default:
                Class<?> theClass = Class.forName(theType);
                response =  theClass.cast(something);
        }
        return response;
    }

    public Object dynamicGenerator(String type, String name){
        String typeUpper = type.toUpperCase();
        switch (typeUpper){
            case "LONG":
                return baseDao.generateSequence(name);
            case "GENERATE_WITH_NAME":
                return dbseq.sequenceGeneratorForCode(name);
            case "DATE":
                return new Date();
            default:
                return baseDao.generateUUID();
        }
    }

    public static Pair<List<Date>, List<String>> getLastDates(int days){
        List<Date> dateList = new ArrayList<>();
        List<String> stringDateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
// get starting date
        cal.add(Calendar.DAY_OF_YEAR, -days);

// loop adding one day in each iteration
        for(int i = 0; i< days; i++){
            cal.add(Calendar.DAY_OF_YEAR, 1);
            dateList.add(cal.getTime());
            stringDateList.add(sdf.format(cal.getTime()));
        }
        return Pair.of(dateList, stringDateList);
    }


    public static Pair<List<Date>, List<String>> getLastDates(int days, Date date){
        List<Date> dateList = new ArrayList<>();
        List<String> stringDateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = getCalendar(date);
// get starting date
        cal.add(Calendar.DAY_OF_YEAR, -days);

// loop adding one day in each iteration
        for(int i = 0; i< days; i++){
            cal.add(Calendar.DAY_OF_YEAR, 1);
            dateList.add(cal.getTime());
            stringDateList.add(sdf.format(cal.getTime()));
        }
        return Pair.of(dateList, stringDateList);
    }


    public static Calendar getCalendar(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Calendar setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar;
    }

    public static Calendar setTimeToEndofDay(Calendar calendar) {
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar;
    }

    public static Pair<Date, Date> getDateRange(Date date) {
        Date begining, end;

        {
            Calendar calendar = getCalendar(date);
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            setTimeToBeginningOfDay(calendar);
            begining = calendar.getTime();
        }

        {
            Calendar calendar = getCalendar(date);
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            setTimeToEndofDay(calendar);
            end = calendar.getTime();
        }

        return Pair.of(begining, end);
    }

    public static Date getBegningDate(Date date){
        Calendar calendar = getCalendar(date);
        setTimeToBeginningOfDay(calendar);
        return calendar.getTime();
    }
    public static Date getEndDate(Date date){
        Calendar calendar = getCalendar(date);
        setTimeToEndofDay(calendar);
        return calendar.getTime();
    }
    public static Pair<Date, Date> getDayRange(Date date) {
        Date begining, end;

        {
            Calendar calendar = getCalendar(date);
            setTimeToBeginningOfDay(calendar);
            begining = calendar.getTime();
        }

        {
            Calendar calendar = getCalendar(date);
            setTimeToEndofDay(calendar);
            end = calendar.getTime();
        }

        return Pair.of(begining, end);
    }

    public static Pair<Date, Date> getDateRange(int year, int month, int day) throws ParseException {

            LocalDate localDate = LocalDate.of(year, month, day);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(localDate.toString());
            return getDateRange(date);

    }

    public static Pair<Date, Date> getDayRange(int year, int month, int day) throws ParseException {

        LocalDate localDate = LocalDate.of(year, month, day);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(localDate.toString());
        return getDayRange(date);

    }
    public static int getMonth(Calendar c){
        c.add(Calendar.MONTH, -1);
        return c.get(Calendar.MONTH) + 1;
    }
    public static int getYear(Calendar c){
        return c.get(Calendar.YEAR);
    }

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }
    public static Pair<List<String>, List<Integer>> getMonth(int months){
        List<String> monthList = new ArrayList<>();
        List<Integer> monthCountList = new ArrayList<>();
        Calendar calendar = getCalendar(new Date());
        calendar.add(Calendar.MONTH, -months);
        for(int i = 0; i< months; i++){
            calendar.add(Calendar.MONTH, 1);
            int mm = calendar.get(Calendar.MONTH);
            monthCountList.add(mm + 1);
            monthList.add(getMonthForInt(mm));
        }
        return Pair.of(monthList, monthCountList);
    }

    public static Date getDate(int year, int month, int day){

        Calendar c = getCalendar(new Date());
        c.set(year, month - 1, day, 0, 0);
        return c.getTime();
    }

    public static int getMonths(Date startDate, Date endDate){
        Calendar stCalender = getCalendar(startDate);
        Calendar edCalender = getCalendar(endDate);
        return edCalender.get(Calendar.MONTH) - stCalender.get(Calendar.MONTH);
    }

    public static Long getDays(Date startDate, Date endDate){
        long dateBeforeInMs = startDate.getTime();
        long dateAfterInMs = endDate.getTime();

        long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);

        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        System.out.println(" The number of days between dates: " + daysDiff);
        return daysDiff;
    }

    public static Date getDate(String stDate, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = formatter.parse(stDate);
        return date;
    }

    public static int getDaysCount(int year, int month){
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }
}
