package com.keith.idribbble.util;


import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kaka on 2014/8/2.
 */
public class PreferenceStyle {
    public static void setStyle(Context context, View view) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/AleoRegular.ttf");
        TextView titleView = (TextView) view.findViewById(android.R.id.title);
        TextView summaryView = (TextView) view.findViewById(android.R.id.summary);
        titleView.setTypeface(font);
        summaryView.setTypeface(font);
    }
}
