package com.example.administrator.shedule.course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PickerDataManager {

    private String mYear;

    public PickerDataManager(String account) {
        this.mYear = account.substring(0, 4);
    }

    public List<String> getYears() {
        List<String> data = new ArrayList<>();
        int year = Integer.parseInt(mYear);
        data.add(year + "年-" + (year + 1) + "年");
        data.add((year + 1) + "年-" + (year + 2) + "年");
        data.add((year + 2) + "年-" + (year + 3) + "年");
        return data;
    }

    public List<String> getSemesters() {
        String[] semester = {"第一学期", "第二学期"};
        return Arrays.asList(semester);
    }

    /**
     * 根据选择的学期，拼接部分查询url
     * @param year 如：2016年-2017年
     * @param semester 如：第一学期
     * @return 2016-2017-1.do
     */
    public String getQueryStringInfo(String year, String semester) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(year.substring(0, 4));
        stringBuffer.append(year.substring(5, 10));
        stringBuffer.append("-");
        String temp = semester.substring(1, 2);
        if ("一".equals(temp)) {
            temp = "1";
        } else if ("二".equals(temp)) {
            temp = "2";
        }
        stringBuffer.append(temp);
        stringBuffer.append(".do");
        return stringBuffer.toString();
    }
}
