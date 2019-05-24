package com.example.administrator.shedule.course.local;

import android.content.Context;

import com.example.administrator.shedule.bean.Course;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class PersistenceStore {

    /**
     * 本地加载指定课程表
     * @param fileName 如2018-2019-2
     * @return
     */
    public List<Course> loadCourse(String fileName, Context context) {
        Gson gson = new Gson();
        File file = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/" + fileName + ".txt");
        if (!file.exists()) return null;
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
            Type type = new TypeToken<List<Course>>(){}.getType();
            return gson.fromJson(sb.toString(), type);
        }
    }

    /**
     * 把课表保存到本地
     * @param fileName 如2018-2019-2
     * @param list
     */
    public void saveCourse(String fileName, List<Course> list, Context context) {
        Gson gson = new Gson();
        String data = gson.toJson(list);
        File file = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/" + fileName + ".txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            out.write(data.getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
