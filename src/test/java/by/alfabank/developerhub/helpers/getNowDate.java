package by.alfabank.developerhub.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class getNowDate {

    public static String getNowDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate now = LocalDate.now();
        String nowDate = dtf.format(now);
        return nowDate;
    }
}
