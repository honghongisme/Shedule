package com.example.administrator.shedule.bean;

import java.util.Arrays;

public class Course {

    // 课程名称
    private String title;
    // 开始周 (数组是是因为可能存在多个阶段周)
    private int[] startWeek;
    // 结束周
    private int[] endWeek;
    // 开始节数
    private int startNum;
    // 结束节数
    private int endNum;
    // 代课老师
    private String teacher;
    // 地点
    private String location;
    // 是否单周课程
    private boolean isSingleWeekCourse;
    // 是否双周课程
    private boolean isDoubleWeekCourse;
    // 周几(周一是0)
    private String weekDayStr;
    private int weekDayNum;
    private String color;

    public int getWeekDayNum() {
        return weekDayNum;
    }

    public void setWeekDayNum(int weekDayNum) {
        this.weekDayNum = weekDayNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int[] getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int[] startWeek) {
        this.startWeek = startWeek;
    }

    public int[] getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int[] endWeek) {
        this.endWeek = endWeek;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getEndNum() {
        return endNum;
    }

    public void setEndNum(int endNum) {
        this.endNum = endNum;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isSingleWeekCourse() {
        return isSingleWeekCourse;
    }

    public void setSingleWeekCourse(boolean singleWeekCourse) {
        isSingleWeekCourse = singleWeekCourse;
    }

    public boolean isDoubleWeekCourse() {
        return isDoubleWeekCourse;
    }

    public void setDoubleWeekCourse(boolean doubleWeekCourse) {
        isDoubleWeekCourse = doubleWeekCourse;
    }

    public String getWeekDayStr() {
        return weekDayStr;
    }

    public void setWeekDayStr(String weekDayStr) {
        this.weekDayStr = weekDayStr;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", startWeek=" + Arrays.toString(startWeek) +
                ", endWeek=" + Arrays.toString(endWeek) +
                ", startNum=" + startNum +
                ", endNum=" + endNum +
                ", teacher='" + teacher + '\'' +
                ", location='" + location + '\'' +
                ", isSingleWeekCourse=" + isSingleWeekCourse +
                ", isDoubleWeekCourse=" + isDoubleWeekCourse +
                ", weekDayStr='" + weekDayStr + '\'' +
                ", weekDayNum=" + weekDayNum +
                ", color='" + color + '\'' +
                '}';
    }
}
