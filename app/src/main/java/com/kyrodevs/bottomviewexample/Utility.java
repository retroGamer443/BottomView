package com.kyrodevs.bottomviewexample;

import android.content.Context;
import android.util.DisplayMetrics;

public class Utility {
    public static float dpToPixel(Context ctx, int dp) {
        return (dp * ((float) ctx.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
