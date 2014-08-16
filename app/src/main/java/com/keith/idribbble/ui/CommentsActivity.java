package com.keith.idribbble.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

import com.keith.idribbble.R;
import com.keith.idribbble.bean.Shot;
import com.keith.idribbble.util.Font;
import com.keith.idribbble.util.TypefaceUtil;

public class CommentsActivity extends DribbbleBaseActivity {

    private Shot shot;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        getActionBar().setTitle(TypefaceUtil.createStringInTypeface(this, R.string.title_activity_comments, Font.ALEO.getName()));

        shot = (Shot) getIntent().getSerializableExtra("shot");
        getFragmentManager().beginTransaction().add(R.id.comments_container, CommentsFragment.newInstance(shot)).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) shareItem.getActionProvider();
        mShareActionProvider.setShareIntent(createShareIntent());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private Intent createShareIntent() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "「" + shot.getTitle() + "」by " + shot.getPlayer().getName() + " on Dribbble " + shot.getUrl());
        intent.setType("text/plain");
        return intent;
    }

}
