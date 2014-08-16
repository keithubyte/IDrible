package com.keith.idribbble.util;

/**
 * Created by kaka on 2014/8/2.
 */
public enum Font {
    ALEO("AleoRegular.ttf");

    String name;

    public String getName() {
        return name;
    }

    Font(String name) {
        this.name = name;
    }
}
