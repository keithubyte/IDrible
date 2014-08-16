package com.keith.idribbble.util;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;

public class TypefaceUtil {

    /**
     * Apply some kind of font to a CharSequence object
     * @param context
     * @param content
     * @param font
     * @return
     */
    public static SpannableString createStringInTypeface(Context context, CharSequence content, String font) {
        SpannableString ss = new SpannableString(content);
        ss.setSpan(new TypefaceSpan(context, font), 0, ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    public static SpannableString createStringInTypeface(Context context, int contentId, String font) {
        String content = context.getString(contentId);
        return createStringInTypeface(context, content, font);
    }
}
