package com.keith.idribbble.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.keith.idribbble.R;
import com.keith.idribbble.bean.Player;
import com.keith.idribbble.bean.Shot;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class PlayerFragment extends Fragment {

    public PlayerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Shot shot = (Shot) getActivity().getIntent().getSerializableExtra("shot");
        Player player = shot.getPlayer();

        View view = inflater.inflate(R.layout.fragment_player, container, false);
        CircularImageView avatarView = (CircularImageView) view.findViewById(R.id.collection_player_avatar);
        TextView nameView = (TextView) view.findViewById(R.id.collection_player_name);
        TextView locationView = (TextView) view.findViewById(R.id.collection_player_location);
        TextView urlView = (TextView) view.findViewById(R.id.collection_website);
        TextView shotsView = (TextView) view.findViewById(R.id.profile_shots);
        TextView likesView = (TextView) view.findViewById(R.id.profile_likes);
        TextView commentsView = (TextView) view.findViewById(R.id.profile_comments);
        TextView reboundsView = (TextView) view.findViewById(R.id.profile_rebounds);
        TextView followsView = (TextView) view.findViewById(R.id.profile_follows);
        TextView drafteesView = (TextView) view.findViewById(R.id.profile_draftees);

        Picasso.with(getActivity()).load(player.getAvatarUrl()).into(avatarView);
        nameView.setText(player.getName());
        locationView.setText(player.getLocation());
        urlView.setText(player.getWebsiteUrl());
        shotsView.setText(String.valueOf(player.getShotsCount()));
        likesView.setText(String.valueOf(player.getLikesReceivedCount()));
        commentsView.setText(String.valueOf(player.getCommentsReceivedCount()));
        reboundsView.setText(String.valueOf(player.getReboundsReceivedCount()));
        followsView.setText(String.valueOf(player.getFollowersCount()));
        drafteesView.setText(String.valueOf(player.getDrafteesCount()));

        return view;
    }

}