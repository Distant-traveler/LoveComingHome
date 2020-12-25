package com.example.lovecominghome;

import android.content.Context;
import android.content.res.Resources;

public class ConvertUtils {
    public static int dp2px(int dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }
    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources ().getDisplayMetrics ().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
