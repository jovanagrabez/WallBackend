package com.example.wallbackend.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {

    public String formattedDate;

    public String dateFormater(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       return formattedDate = myDateObj.format(myFormatObj);
    }
}
