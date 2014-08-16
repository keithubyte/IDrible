package com.keith.idribbble.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.keith.idribbble.R;
import com.keith.idribbble.bean.Comment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kaka on 2014/7/14.
 */
public class CommentAdapter extends ArrayAdapter<Comment> {

    private LayoutInflater inflater;
    private Context context;

    public CommentAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.item_comment, null);
            holder = new ViewHolder();
            holder.avatar = (ImageView) view.findViewById(R.id.comment_avatar);
            holder.author = (TextView) view.findViewById(R.id.comment_author);
            holder.content = (TextView) view.findViewById(R.id.comment_content);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Comment comment = getItem(i);

        Picasso.with(context).load(comment.getPlayer().getAvatarUrl()).into(holder.avatar);
        holder.author.setText(comment.getPlayer().getName());
        holder.content.setText(Html.fromHtml(comment.getBody()));

        return view;
    }

    private class ViewHolder {
        public ImageView avatar;
        public TextView author;
        public TextView content;
    }
}
