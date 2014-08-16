package com.keith.idribbble.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaka on 2014/7/13.
 */
public class DribbbleShot {

    @Expose
    private String page;
    @SerializedName("per_page")
    @Expose
    private int perPage;
    @Expose
    private int pages;
    @Expose
    private int total;
    @Expose
    private List<Shot> shots = new ArrayList<Shot>();

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Shot> getShots() {
        return shots;
    }

    public void setShots(List<Shot> shots) {
        this.shots = shots;
    }

}
