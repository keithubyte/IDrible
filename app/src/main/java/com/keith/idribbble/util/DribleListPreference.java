package com.keith.idribbble.util;

import android.content.Context;
import android.preference.ListPreference;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by kaka on 2014/8/2.
 */
public class DribleListPreference extends ListPreference {

    public DribleListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DribleListPreference(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        PreferenceStyle.setStyle(getContext(), view);
    }

}
