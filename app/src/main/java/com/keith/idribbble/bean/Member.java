package com.keith.idribbble.bean;

import android.content.res.Resources;

import com.keith.idribbble.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaka on 2014/7/15.
 */
public class Member {

    private int avatarId;
    private String desc;
    private String name;
    private String position;

    public Member(int avatarId, String desc, String name, String position) {
        this.avatarId = avatarId;
        this.desc = desc;
        this.name = name;
        this.position = position;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public static List<Member> createMembers(Resources resources) {
        List<Member> members = new ArrayList<Member>(5);

        int[] avatars = {
                R.drawable.member_rich_thornett,
                R.drawable.member_dan_cederholm,
                R.drawable.member_tristan_dunn,
                R.drawable.member_patrick_byrne,
                R.drawable.member_susanna_baird
        };
        String[] names = resources.getStringArray(R.array.member_names);
        String[] positions = resources.getStringArray(R.array.member_positions);
        String[] descs = resources.getStringArray(R.array.member_descs);

        for (int i = 0; i < 5; i++) {
            members.add(new Member(avatars[i], descs[i], names[i], positions[i]));
        }

        return members;
    }
}
