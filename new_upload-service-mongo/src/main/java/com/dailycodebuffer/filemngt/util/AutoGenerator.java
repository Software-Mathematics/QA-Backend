package com.dailycodebuffer.filemngt.util;

import com.google.common.base.CharMatcher;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Component
public class AutoGenerator {

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

    public String sequenceGeneratorForCode(String name) {
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
        return result + random.nextInt(9999);

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
}
