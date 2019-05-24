package com.example.administrator.shedule.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.example.administrator.shedule.course.GridConfig;

/**
 * 当gridview里子控件为textview时，textview过长不会换行，通过权重计算最大宽度
 */
public class TextViewWidthUtil {

    public static int getTextViewWidth(Activity activity, GridConfig config) {
        int width = getDisplayWidth(activity);
        // 已确定的宽度
        int determined = config.getLeftHeaderLeftMargin() + config.getLeftHeaderRightMargin()
                + (config.getSingleCourseLeftMargin() + config.getSingleCourseRightMargin()) * 7;
        // 未确定的宽度用weight处理
        return (int) ((width - determined) / (config.getLeftHeaderWeight() + config.getSingleCourseWeight() * 7) * config.getSingleCourseWeight());
    }

    private static int getDisplayWidth(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        return width;
    }
}
