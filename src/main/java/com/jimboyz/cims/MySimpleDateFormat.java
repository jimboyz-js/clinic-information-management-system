package com.jimboyz.cims;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MySimpleDateFormat {

    private static MySimpleDateFormat sdf;
    AppProperties appProperties = new AppProperties();

    private MySimpleDateFormat() {}

    public static MySimpleDateFormat getInstance() {
        if (sdf == null) {
            sdf = new MySimpleDateFormat();
        }

        return sdf;
    }

    public String format(Date date) {
       return new SimpleDateFormat(appProperties.dateFormatString).format(date);
    }

    public String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

//    public Date format(String date, String format) {
//        return null;
//    }

    public SimpleDateFormat formatter() {
        return new SimpleDateFormat(appProperties.dateFormatString);
    }
}
