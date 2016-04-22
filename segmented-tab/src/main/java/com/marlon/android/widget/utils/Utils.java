package com.marlon.android.widget.utils;

import android.content.Context;

public class Utils {

    public static int getValueByDensity(Context context, int value) {
        return (int) (context.getResources().getDisplayMetrics().density * value);
    }

    public static int getValueByDensity(Context context, double value) {
        return (int) (context.getResources().getDisplayMetrics().density * value);
    }
}
