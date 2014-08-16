package com.keith.idribbble.util;

import com.keith.idribbble.bean.DribbbleComment;
import com.keith.idribbble.bean.DribbbleShot;
import com.keith.idribbble.bean.Player;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by kaka on 2014/7/13.
 */
public class DribbbleApiClient {

    private static DribbbleApiInterface sDribbbleService;

    public static DribbbleApiInterface getDribbbleApiClient() {
        if (sDribbbleService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://api.dribbble.com").build();
            sDribbbleService = restAdapter.create(DribbbleApiInterface.class);
        }
        return sDribbbleService;
    }

    public interface DribbbleApiInterface {
        @GET("/shots/popular")
        void getPopularShots(@Query("page") int page, @Query("per_page") int per_page, Callback<DribbbleShot> callback);

        @GET("/shots/everyone")
        void getEveryoneShots(@Query("page") int page, @Query("per_page") int per_page, Callback<DribbbleShot> callback);

        @GET("/shots/debuts")
        void getDebutsShots(@Query("page") int page, @Query("per_page") int per_page, Callback<DribbbleShot> callback);

        @GET("/players/{id}/shots")
        void getPlayerShots(@Path("id") long id, @Query("page") int page, @Query("per_page") int per_page, Callback<DribbbleShot> callback);

        @GET("/shots/{id}/comments")
        void getComments(@Path("id") long id, @Query("page") int page, @Query("per_page") int per_page, Callback<DribbbleComment> callback);

        @GET("/players/{id}")
        void getPlayerProfile(@Path("id") long id, Callback<Player> callback);
    }
}
