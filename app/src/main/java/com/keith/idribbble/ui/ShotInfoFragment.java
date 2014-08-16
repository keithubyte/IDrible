package com.keith.idribbble.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keith.idribbble.R;
import com.keith.idribbble.bean.Shot;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class ShotInfoFragment extends Fragment implements View.OnClickListener {

    private Shot shot;

    public ShotInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        shot = (Shot) getActivity().getIntent().getSerializableExtra("shot");

        View view = inflater.inflate(R.layout.fragment_shot_info, container, false);
        CircularImageView avatarView = (CircularImageView) view.findViewById(R.id.detail_avatar);
        TextView playerView = (TextView) view.findViewById(R.id.detail_player);
        TextView titleView = (TextView) view.findViewById(R.id.detail_title);
        TextView likeView = (TextView) view.findViewById(R.id.detail_like);
        TextView viewView = (TextView) view.findViewById(R.id.detail_view);
        TextView commentView = (TextView) view.findViewById(R.id.detail_comment);

        avatarView.setOnClickListener(this);
        Picasso.with(getActivity()).load(shot.getPlayer().getAvatarUrl()).into(avatarView);
        playerView.setText(shot.getPlayer().getName());
        titleView.setText(shot.getTitle());
        likeView.setText(String.valueOf(shot.getViewsCount()));
        viewView.setText(String.valueOf(shot.getViewsCount()));
        commentView.setText(String.valueOf(shot.getCommentsCount()));

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), WorksActivity.class);
        intent.putExtra("shot", shot);
        startActivity(intent);
    }
}
