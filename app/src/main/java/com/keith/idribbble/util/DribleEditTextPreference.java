package com.keith.idribbble.util;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by kaka on 2014/8/2.
 */
public class DribleEditTextPreference extends EditTextPreference {

    public DribleEditTextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public DribleEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DribleEditTextPreference(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        PreferenceStyle.setStyle(getContext(), view);
    }

}
