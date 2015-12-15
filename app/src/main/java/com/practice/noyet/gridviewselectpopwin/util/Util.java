package com.practice.noyet.gridviewselectpopwin.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by noyet on 2015/11/1.
 */
public class Util {
    /**
     * 获取当前屏幕的尺寸大小
     * @param context
     * @return
     */
    public static DisplayMetrics getMetrics(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }
}
