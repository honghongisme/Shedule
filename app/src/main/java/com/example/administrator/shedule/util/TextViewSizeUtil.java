package com.example.administrator.shedule.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.example.administrator.shedule.course.GridConfig;

/**
 * 当gridview里子控件为textview时，textview过长不会换行，通过权重计算最大宽度
 */
public class TextViewSizeUtil {

    public static int getTextViewWidth(Activity activity, GridConfig config) {
        int width = getDisplayWidth(activity);
        // 已确定的宽度,一行8分
        int determined = config.getLeftHeaderLeftRightMargin() * 2 + (config.getSingleCourseLeftRightMargin() * 2) * 7;
        // 未确定的宽度用weight处理
        return (int) ((width - determined) / 7.5);
    }

    public static int getTextViewHeight(Activity activity, GridConfig config) {
        int height = getDisplayHeight(activity);
        // 已确定的宽度  一列5分
        int determined =  config.getSingleCourseTopBottomMargin() * 2 * 5;
        // 未确定的宽度用weight处理
        return (int) ((height - determined) / 5);
    }

    private static int getDisplayWidth(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        return width;
    }

    private static int getDisplayHeight(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int height = outMetrics.heightPixels;
        return height;
    }
}
