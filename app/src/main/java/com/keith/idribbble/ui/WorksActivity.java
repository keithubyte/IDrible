package com.keith.idribbble.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.keith.idribbble.R;
import com.keith.idribbble.bean.Shot;
import com.keith.idribbble.util.Font;
import com.keith.idribbble.util.TypefaceUtil;

public class WorksActivity extends DribbbleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        getActionBar().setTitle(TypefaceUtil.createStringInTypeface(this, R.string.title_activity_works, Font.ALEO.getName()));

        Shot shot = (Shot) getIntent().getSerializableExtra("shot");

        getFragmentManager().beginTransaction()
                .add(R.id.collection_container, ShotsFragment.newInstance(ShotsFragment.ShotType.PLAYER, shot.getPlayer().getId()))
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
