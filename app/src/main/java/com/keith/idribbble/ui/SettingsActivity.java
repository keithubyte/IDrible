package com.keith.idribbble.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.keith.idribbble.R;
import com.keith.idribbble.util.Font;
import com.keith.idribbble.util.TypefaceUtil;

public class SettingsActivity extends DribbbleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setTitle(TypefaceUtil.createStringInTypeface(this, R.string.title_activity_settings, Font.ALEO.getName()));
        getFragmentManager().beginTransaction().add(android.R.id.content, new SettingsFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
