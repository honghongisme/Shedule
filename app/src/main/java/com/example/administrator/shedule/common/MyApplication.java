package com.example.administrator.shedule.common;

import android.app.Application;
import com.example.administrator.shedule.bean.User;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MyApplication extends Application {

    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
   //     Logger.addLogAdapter(new DiskLogAdapter());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
