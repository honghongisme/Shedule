package com.example.administrator.shedule.course.data;

public class UrlParseManager {

    public static String parseQueryStr(String year, String semester) {
        String queryStr = year.substring(0, 4) + year.substring(5, 10);
        char s = semester.charAt(1);
        String temp = "";
        if (s == '一')  temp = "1";
        else if (s == '二') temp = "2";
        queryStr += ("-" + temp);
        return queryStr;
    }

    public static String parseToUrl(String fileName) {
        String url = "http://ssfw.tjut.edu.cn/ssfw/pkgl/kcbxx/4/";
        return url + fileName + ".do";
    }
}
