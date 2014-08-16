package com.keith.idribbble.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.keith.idribbble.R;
import com.keith.idribbble.util.Font;
import com.keith.idribbble.util.TypefaceUtil;


public class DribbbleActivity extends Activity implements
        NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private ShotsFragment currentFragment = null;
    private SparseArray<ShotsFragment> fragments;
    private boolean fragmentOk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dribbble);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = TypefaceUtil.createStringInTypeface(this, getResources().getString(R.string.app_name), "AleoRegular.ttf");

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    private void initFragments() {
        ShotsFragment popularFragment = ShotsFragment.newInstance(ShotsFragment.ShotType.POPULAR, 0);
        ShotsFragment everyoneFragment = ShotsFragment.newInstance(ShotsFragment.ShotType.EVERYONE, 0);
        ShotsFragment debutsFragment = ShotsFragment.newInstance(ShotsFragment.ShotType.DEBUTS, 0);
        currentFragment = popularFragment;

        fragments = new SparseArray<ShotsFragment>(3);
        fragments.put(0, popularFragment);
        fragments.put(1, everyoneFragment);
        fragments.put(2, debutsFragment);

        getFragmentManager().beginTransaction().add(R.id.shots_container, popularFragment)
                .add(R.id.shots_container, everyoneFragment).hide(everyoneFragment)
                .add(R.id.shots_container, debutsFragment).hide(debutsFragment)
                .commit();

        fragmentOk = true;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (!fragmentOk) {
            initFragments();
        }

        getFragmentManager().beginTransaction().hide(currentFragment).show(fragments.get(position)).commit();
        currentFragment = fragments.get(position);
        onSectionAttached(position);
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = TypefaceUtil.createStringInTypeface(this, getString(R.string.title_section1), "AleoRegular.ttf");
                break;
            case 1:
                mTitle = TypefaceUtil.createStringInTypeface(this, getString(R.string.title_section2), "AleoRegular.ttf");
                break;
            case 2:
                mTitle = TypefaceUtil.createStringInTypeface(this, getString(R.string.title_section3), "AleoRegular.ttf");
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // getMenuInflater().inflate(R.menu.dribbble, menu); // do not use system's inflater
            menu.add(Menu.NONE, R.id.action_settings, 1, TypefaceUtil.createStringInTypeface(this, R.string.action_settings, Font.ALEO.getName()));
            menu.add(Menu.NONE, R.id.action_about, 2, TypefaceUtil.createStringInTypeface(this, R.string.action_about, Font.ALEO.getName()));
            restoreActionBar();
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about || id == R.id.action_settings) {
            Intent intent = new Intent();
            switch (item.getItemId()) {
                case R.id.action_about:
                    intent.setClass(this, AboutActivity.class);
                    break;
                case R.id.action_settings:
                    intent.setClass(this, SettingsActivity.class);
                    break;
            }
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
