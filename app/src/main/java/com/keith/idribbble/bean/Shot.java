package com.keith.idribbble.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Shot implements Serializable {

    @Expose
    private int id;
    @Expose
    private String title;
    @Expose
    private String description;
    @Expose
    private int height;
    @Expose
    private int width;
    @SerializedName("likes_count")
    @Expose
    private int likesCount;
    @SerializedName("comments_count")
    @Expose
    private int commentsCount;
    @SerializedName("rebounds_count")
    @Expose
    private int reboundsCount;
    @Expose
    private String url;
    @SerializedName("short_url")
    @Expose
    private String shortUrl;
    @SerializedName("views_count")
    @Expose
    private int viewsCount;
    @SerializedName("rebound_source_id")
    @Expose
    private Object reboundSourceId;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("image_teaser_url")
    @Expose
    private String imageTeaserUrl;
    @SerializedName("image_400_url")
    @Expose
    private String image400Url;
    @Expose
    private Player player;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getReboundsCount() {
        return reboundsCount;
    }

    public void setReboundsCount(int reboundsCount) {
        this.reboundsCount = reboundsCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Object getReboundSourceId() {
        return reboundSourceId;
    }

    public void setReboundSourceId(Object reboundSourceId) {
        this.reboundSourceId = reboundSourceId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTeaserUrl() {
        return imageTeaserUrl;
    }

    public void setImageTeaserUrl(String imageTeaserUrl) {
        this.imageTeaserUrl = imageTeaserUrl;
    }

    public String getImage400Url() {
        return image400Url;
    }

    public void setImage400Url(String image400Url) {
        this.image400Url = image400Url;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
