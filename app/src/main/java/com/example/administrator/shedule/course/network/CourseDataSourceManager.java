package com.example.administrator.shedule.course.network;

import com.example.administrator.shedule.course.OnCourseDataReqListener;
import com.orhanobut.logger.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

public class CourseDataSourceManager {

    /**
     * 请求课程表
     */
    public void doRequest(final Map<String, String> cookie, final String url, final OnCourseDataReqListener listener) {
        // 个人课表url
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection.Response resp = null;
                try {
                    resp = Jsoup.connect(url).cookies(cookie).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onFailed();
                }
                if (resp != null) listener.onSuccess(resp.body());
                else listener.onFailed();
                Logger.d(resp.body());
            }
        }).start();
    }
}
