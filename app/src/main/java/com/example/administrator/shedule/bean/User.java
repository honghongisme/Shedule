package com.example.administrator.shedule.bean;

import java.io.Serializable;
import java.util.HashMap;

public class User {

    private String account;
    private String password;
    private HashMap<String, String> cookies;
    // 正显示的课表学期
    private String showingSemester;
    // 正显示的课表周数
    private int showingWeek = 1;

    public User(String account, String password, HashMap<String, String> cookies) {
        this.account = account;
        this.password = password;
        this.cookies = cookies;
    }

    public String getAccount() {
        return account;
    }

    public String getShowingSemester() {
        return showingSemester;
    }

    public void setShowingSemester(String showingSemester) {
        this.showingSemester = showingSemester;
    }

    public int getShowingWeek() {
        return showingWeek;
    }

    public void setShowingWeek(int showingWeek) {
        this.showingWeek = showingWeek;
    }

    public HashMap<String, String> getCookies() {
        return cookies;
    }
    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", cookies=" + cookies +
                ", showingSemester='" + showingSemester + '\'' +
                ", showingWeek=" + showingWeek +
                '}';
    }
}
