package com.keith.idribbble.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kaka on 2014/7/13.
 */

public class Player implements Serializable {

    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private String location;
    @SerializedName("followers_count")
    @Expose
    private int followersCount;
    @SerializedName("draftees_count")
    @Expose
    private int drafteesCount;
    @SerializedName("likes_count")
    @Expose
    private int likesCount;
    @SerializedName("likes_received_count")
    @Expose
    private int likesReceivedCount;
    @SerializedName("comments_count")
    @Expose
    private int commentsCount;
    @SerializedName("comments_received_count")
    @Expose
    private int commentsReceivedCount;
    @SerializedName("rebounds_count")
    @Expose
    private int reboundsCount;
    @SerializedName("rebounds_received_count")
    @Expose
    private int reboundsReceivedCount;
    @Expose
    private String url;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @Expose
    private String username;
    @SerializedName("twitter_screen_name")
    @Expose
    private Object twitterScreenName;
    @SerializedName("website_url")
    @Expose
    private String websiteUrl;
    @SerializedName("drafted_by_player_id")
    @Expose
    private int draftedByPlayerId;
    @SerializedName("shots_count")
    @Expose
    private int shotsCount;
    @SerializedName("following_count")
    @Expose
    private int followingCount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getDrafteesCount() {
        return drafteesCount;
    }

    public void setDrafteesCount(int drafteesCount) {
        this.drafteesCount = drafteesCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getLikesReceivedCount() {
        return likesReceivedCount;
    }

    public void setLikesReceivedCount(int likesReceivedCount) {
        this.likesReceivedCount = likesReceivedCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getCommentsReceivedCount() {
        return commentsReceivedCount;
    }

    public void setCommentsReceivedCount(int commentsReceivedCount) {
        this.commentsReceivedCount = commentsReceivedCount;
    }

    public int getReboundsCount() {
        return reboundsCount;
    }

    public void setReboundsCount(int reboundsCount) {
        this.reboundsCount = reboundsCount;
    }

    public int getReboundsReceivedCount() {
        return reboundsReceivedCount;
    }

    public void setReboundsReceivedCount(int reboundsReceivedCount) {
        this.reboundsReceivedCount = reboundsReceivedCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getTwitterScreenName() {
        return twitterScreenName;
    }

    public void setTwitterScreenName(Object twitterScreenName) {
        this.twitterScreenName = twitterScreenName;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public int getDraftedByPlayerId() {
        return draftedByPlayerId;
    }

    public void setDraftedByPlayerId(int draftedByPlayerId) {
        this.draftedByPlayerId = draftedByPlayerId;
    }

    public int getShotsCount() {
        return shotsCount;
    }

    public void setShotsCount(int shotsCount) {
        this.shotsCount = shotsCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
