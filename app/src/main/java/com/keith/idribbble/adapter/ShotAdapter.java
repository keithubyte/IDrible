package com.keith.idribbble.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.keith.idribbble.R;
import com.keith.idribbble.bean.Shot;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kaka on 2014/7/13.
 */
public class ShotAdapter extends ArrayAdapter<Shot> {

    private LayoutInflater inflater;
    private Context context;

    public ShotAdapter(Context context, int resource, List<Shot> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_shot, null);
            holder = new ViewHolder();
            holder.shotImage = (ImageView) convertView.findViewById(R.id.shot_image);
            holder.shotPlayer = (TextView) convertView.findViewById(R.id.shot_player);
            holder.shotAvatar = (ImageView) convertView.findViewById(R.id.shot_avatar);
            holder.shotName = (TextView) convertView.findViewById(R.id.shot_name);
            holder.shotLike = (TextView) convertView.findViewById(R.id.shot_like);
            holder.shotComment = (TextView) convertView.findViewById(R.id.shot_comment);
            holder.shotView = (TextView) convertView.findViewById(R.id.shot_view);
            holder.shotDate = (TextView) convertView.findViewById(R.id.shot_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Shot shot = getItem(position);

        if (shot.getImage400Url() == null || shot.getImage400Url().length() == 0) {
            Picasso.with(context).load(shot.getImageUrl()).into(holder.shotImage);
        } else {
            Picasso.with(context).load(shot.getImage400Url()).into(holder.shotImage);
        }

        Picasso.with(context).load(shot.getPlayer().getAvatarUrl().split("\\?")[0]).into(holder.shotAvatar);

        holder.shotPlayer.setText(shot.getPlayer().getName());
        holder.shotName.setText(shot.getTitle());
        holder.shotLike.setText(String.valueOf(shot.getLikesCount()));
        holder.shotComment.setText(String.valueOf(shot.getCommentsCount()));
        holder.shotView.setText(String.valueOf(shot.getViewsCount()));
        holder.shotDate.setText(shot.getCreatedAt().substring(0, 10));

        return convertView;
    }

    private class ViewHolder {
        public ImageView shotImage;
        public ImageView shotAvatar;
        public TextView shotPlayer;
        public TextView shotName;
        public TextView shotLike;
        public TextView shotView;
        public TextView shotComment;
        public TextView shotDate;
    }
}
