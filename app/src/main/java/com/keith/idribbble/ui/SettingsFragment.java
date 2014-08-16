package com.keith.idribbble.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.keith.idribbble.R;


public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
