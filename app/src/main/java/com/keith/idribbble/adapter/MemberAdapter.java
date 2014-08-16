package com.keith.idribbble.adapter;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.keith.idribbble.R;
import com.keith.idribbble.bean.Member;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kaka on 2014/7/14.
 */
public class MemberAdapter extends BaseAdapter {

    private List<Member> members;

    public MemberAdapter(List<Member> members) {
        this.members = members;
    }

    @Override
    public int getCount() {
        return members.size();
    }

    @Override
    public Object getItem(int i) {
        return members.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.item_about, null);
            holder = new ViewHolder();
            holder.avatar = (CircularImageView) view.findViewById(R.id.about_item_avatar);
            holder.name = (TextView) view.findViewById(R.id.about_item_name);
            holder.position = (TextView) view.findViewById(R.id.about_item_position);
            holder.desc = (TextView) view.findViewById(R.id.about_item_desc);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Member member = (Member) getItem(i);

        Picasso.with(viewGroup.getContext()).load(member.getAvatarId()).into(holder.avatar);
        holder.name.setText(member.getName());
        holder.position.setText(member.getPosition());
        holder.desc.setText(member.getDesc());

        return view;
    }

    private class ViewHolder {
        public CircularImageView avatar;
        public TextView name;
        public TextView position;
        public TextView desc;
    }
}
