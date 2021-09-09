package com.hb.theme;


import android.content.Context;
import android.util.TypedValue;

/**
 * Created by HB on 3/30/2015.
 */
public class ThemeUtil {

  public static int dpToPx(Context context, int dp) {
    return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()) + 0.5f);
  }

  public static int spToPx(Context context, int sp) {
    return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics()) + 0.5f);
  }

}
