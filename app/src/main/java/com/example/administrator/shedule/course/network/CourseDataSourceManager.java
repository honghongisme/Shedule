package com.example.administrator.shedule.course.network;

import com.example.administrator.shedule.bean.User;
import com.example.administrator.shedule.course.OnCourseDataReqListener;
import com.example.administrator.shedule.login.network.LoginNetManager;
import com.example.administrator.shedule.login.network.OnLoginReqListener;
import com.orhanobut.logger.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

public class CourseDataSourceManager {

    /**
     * 请求课程表
     */
    public void doRequest(User user, final String url, final OnCourseDataReqListener listener) {
        LoginNetManager manager = new LoginNetManager();
        manager.setLoginReqListener(new OnLoginReqListener() {
            @Override
            public void onSuccess(final User user) {
                // 个人课表url
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Connection.Response resp = null;
                        try {
                            resp = Jsoup.connect(url).cookies(user.getCookies()).execute();
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

            @Override
            public void onAccountOrPasswordError() {

            }

            @Override
            public void onNotKnownFailed() {

            }
        });
        manager.requestLogin(user.getAccount(), user.getPassword());
    }
}
