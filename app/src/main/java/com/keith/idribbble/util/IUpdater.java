package com.keith.idribbble.util;

/**
 * responsible for data loading related operation
 * @param <T>
 */
public interface IUpdater<T> {

    /**
     * start the loading animation or change some views' state to
     * indicate there is an operation running at background to load data
     */
    void startLoadingUI();

    /**
     * stop the loading animation or resume views' state
     * to indicate the background operation finished its work
     */
    void stopLoadingUI();

    /**
     * do something when error appears
     */
    void onError();

    /**
     * change the views' data
     * @param t
     */
    void updateData(T t);
}
