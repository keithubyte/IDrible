package com.keith.idribbble.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.keith.idribbble.R;
import com.keith.idribbble.adapter.MemberAdapter;
import com.keith.idribbble.bean.Member;
import com.keith.idribbble.util.Font;
import com.keith.idribbble.util.TypefaceUtil;
import com.nirhart.parallaxscroll.views.ParallaxListView;

public class AboutActivity extends DribbbleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getActionBar().setTitle(TypefaceUtil.createStringInTypeface(this, R.string.title_activity_about, Font.ALEO.getName()));

        ParallaxListView listView = (ParallaxListView) findViewById(R.id.about_listview);
        View header = View.inflate(this, R.layout.view_about_header, null);
        listView.addParallaxedHeaderView(header);
        listView.setAdapter(new MemberAdapter(Member.createMembers(getResources())));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
