package com.example.administrator.shedule.course.data;

import com.example.administrator.shedule.bean.Course;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ParseCourseDataManager {

    public static List<Course> parseDataHtml(String html) {
        List<Course> list = new ArrayList<>();
        Elements trs = Jsoup.parse(html).select("table.CourseFormTable tr");
        // 去掉头尾无用信息
        for (int i = 1; i < trs.size() - 1; i++) {
            Element tr = trs.get(i);
            Elements tds = tr.children();
            for (int j = 0; j < tds.size(); j++) {
                Element td = tds.get(j);
                String rowspan = td.attr("rowspan");
                if ("2".equals(rowspan)) { // 该格子有课程
                    Course course = new Course();
                    course.setWeekDayStr(numWeekDayToStr(j - 2));
                    course.setWeekDayNum(j - 2);
                    String[] info = td.text().split(" ");
                    course.setTitle(info[0]);
                    course.setTeacher(info[3]);
                    course.setLocation(info[4] + " " + info[5]);
                    // 周数
                    String weekStr = info[1];
                    int[] startWeek;
                    int[] endWeek;
                    if (weekStr.contains(",")) { // 多阶段周课程
                        String[] weeks = weekStr.split(",");
                        startWeek = new int[weeks.length];
                        endWeek = new int[weeks.length];
                        for (int k = 0; k < weeks.length; k++) {
                            String week = weeks[k];
                            startWeek[k] = Integer.parseInt(week.substring(0, week.indexOf('-')));
                            endWeek[k] = Integer.parseInt(week.substring(week.indexOf('-') + 1, week.indexOf('周')));
                        }
                    } else { // 单阶段周课程
                        startWeek = new int[]{Integer.parseInt(weekStr.substring(0, weekStr.indexOf('-')))};
                        endWeek = new int[]{Integer.parseInt(weekStr.substring(weekStr.indexOf('-') + 1, weekStr.indexOf('周')))};
                    }
                    course.setStartWeek(startWeek);
                    course.setEndWeek(endWeek);
                    // 节数
                    String[] numStr = info[2].split("-");
                    course.setStartNum(Integer.parseInt(numStr[0].substring(2)));
                    course.setEndNum(Integer.parseInt(numStr[1].substring(0, numStr[1].indexOf('节'))));
                    // 单双周
                    if (weekStr.contains("单")) {
                        course.setSingleWeekCourse(true);
                    } else if (weekStr.contains("双")){
                        course.setDoubleWeekCourse(true);
                    } else {
                        course.setSingleWeekCourse(true);
                        course.setDoubleWeekCourse(true);
                    }
                    list.add(course);
                }
            }
        }
        return list;
    }

    private static String numWeekDayToStr(int num) {
        String[] weekDay = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        return weekDay[num];
    }
}
