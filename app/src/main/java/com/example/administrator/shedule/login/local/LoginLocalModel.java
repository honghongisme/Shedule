package com.example.administrator.shedule.login.local;

import android.content.Context;

import com.example.administrator.shedule.bean.User;
import com.google.gson.Gson;

import java.io.*;

public class LoginLocalModel {

    public void saveUser(Context context, User user) {
        File file = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/user.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            out.write(new Gson().toJson(user).getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User loadUser(Context context) {
        File file = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/user.txt");
        if(!file.exists()) return null;
        StringBuilder sb = null;
        try {
            FileInputStream in = new FileInputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            sb = new StringBuilder();
            while ((len = in.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sb == null) {
            return null;
        } else {
            return new Gson().fromJson(sb.toString(), User.class);
        }
    }
}
