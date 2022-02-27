package com.alkemy.disney.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

    public static LocalDate stringToLocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

}
