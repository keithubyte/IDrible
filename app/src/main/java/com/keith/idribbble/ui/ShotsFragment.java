package com.keith.idribbble.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import com.keith.idribbble.R;
import com.keith.idribbble.adapter.CardAnimationAdapter;
import com.keith.idribbble.adapter.ShotAdapter;
import com.keith.idribbble.bean.Action;
import com.keith.idribbble.bean.DribbbleShot;
import com.keith.idribbble.bean.Shot;
import com.keith.idribbble.util.DribbbleApiClient;
import com.keith.idribbble.util.IUpdater;
import com.keith.idribbble.view.ShotListView;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ShotsFragment extends Fragment {

    private static final String ARG_SHOT_TYPE = "SHOT_TYPE";
    private static final String ARG_PLAYER_ID = "PLAYER_ID";
    private static final int PAGE_ITEMS = 8;
    private static final int INIT_PAGE = 1;
    private static final int THRESHOLD = 2;

    private ShotType shotType;
    private long playerId;
    private int mPage = INIT_PAGE;

    private SwipeRefreshLayout refresher;
    private ShotListView shotListView;
    private View loadingFooter;
    private View endFooter;
    private View networkFailedView;
    private ShotAdapter shotAdapter;
    private CardAnimationAdapter cardAdapter;

    private DribbbleApiClient.DribbbleApiInterface apiClient;
    private IUpdater refreshUpdater = new RefreshUpdater();
    private IUpdater loadmoreUpdater = new LoadmoreUpdater();

    private boolean loading = false;
    private boolean over = false;

    public static ShotsFragment newInstance(ShotType type, long playerId) {
        ShotsFragment fragment = new ShotsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SHOT_TYPE, type);
        args.putLong(ARG_PLAYER_ID, playerId);
        fragment.setArguments(args);
        return fragment;
    }

    public ShotsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            shotType = (ShotType) getArguments().get(ARG_SHOT_TYPE);
            playerId = getArguments().getLong(ARG_PLAYER_ID);
        }

        apiClient = DribbbleApiClient.getDribbbleApiClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shots, container, false);
        loadingFooter = inflater.inflate(R.layout.view_loading_footer, null);
        endFooter = inflater.inflate(R.layout.view_the_end, null);
        networkFailedView = inflater.inflate(R.layout.view_network_error, null);
        refresher = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        refresher.setColorScheme(R.color.dribbble_pink,
                R.color.dribbble_dark_pink,
                R.color.dribbble_pink,
                R.color.dribbble_dark_pink);
        refresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresher.setRefreshing(true);
                mPage = INIT_PAGE;
                over = false;
                loadShots(mPage++, shotType, playerId, Action.REFRESH);
            }
        });

        shotListView = (ShotListView) view.findViewById(R.id.shot_listview);
        shotAdapter = new ShotAdapter(getActivity(), 0, new ArrayList<Shot>());
        cardAdapter = new CardAnimationAdapter(shotAdapter);
        cardAdapter.setAbsListView(shotListView);
        shotListView.setAdapter(cardAdapter);
        shotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), CommentsActivity.class);
                intent.putExtra("shot", shotAdapter.getItem(i));
                startActivity(intent);
            }
        });
        shotListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // Log.i("onScroll at " + shotType.name(), totalItemCount + " - (" + firstVisibleItem + " + " + visibleItemCount + ")");
                if (!loading && !over) {
                    if (totalItemCount > shotListView.getHeaderViewsCount() && totalItemCount - (firstVisibleItem + visibleItemCount) < THRESHOLD) {
                        loadShots(mPage++, shotType, playerId, Action.LOADMORE);
                    }
                }
            }
        });

        shotListView.setOnPositionChangedListener(new ShotListView.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(ShotListView listView, int position, View scrollBarPanel) {
                ((TextView) scrollBarPanel).setText(String.valueOf(position + 1));
            }
        });

        loadShots(mPage++, shotType, playerId, Action.REFRESH);

        return view;
    }

    private void loadShots(int page, ShotType type, long playerId, Action action) {
        Callback<DribbbleShot> callback = new ShotCallback(action);

        switch (type) {
            case POPULAR:
                apiClient.getPopularShots(page, PAGE_ITEMS, callback);
                break;
            case EVERYONE:
                apiClient.getEveryoneShots(page, PAGE_ITEMS, callback);
                break;
            case DEBUTS:
                apiClient.getDebutsShots(page, PAGE_ITEMS, callback);
                break;
            case PLAYER:
                apiClient.getPlayerShots(playerId, page, PAGE_ITEMS, callback);
        }
    }

    private class ShotCallback implements Callback<DribbbleShot> {

        private IUpdater updater;

        private ShotCallback(Action action) {
            this.updater = action == Action.REFRESH ? refreshUpdater : loadmoreUpdater;
            this.updater.startLoadingUI();
        }

        @Override
        public void success(DribbbleShot dribbbleShot, Response response) {
            updater.stopLoadingUI();
            updater.updateData(dribbbleShot);
        }

        @Override
        public void failure(RetrofitError retrofitError) {
            updater.stopLoadingUI();
            updater.onError();
            Log.w("Error", retrofitError.toString());
        }

    }

    public static enum ShotType {
        POPULAR,
        EVERYONE,
        DEBUTS,
        PLAYER
    }

    private class RefreshUpdater implements IUpdater<DribbbleShot> {

        @Override
        public void startLoadingUI() {
            refresher.setRefreshing(true);
        }

        @Override
        public void stopLoadingUI() {
            refresher.setRefreshing(false);
        }

        @Override
        public void onError() {
            if (shotListView.getHeaderViewsCount() == 0) {
                shotListView.addHeaderView(networkFailedView);
            }
        }

        @Override
        public void updateData(DribbbleShot dribbbleShot) {
            if (shotListView.getHeaderViewsCount() > 0) {
                shotListView.removeHeaderView(networkFailedView);
            }
            shotAdapter.clear();
            shotAdapter.addAll(dribbbleShot.getShots());
            cardAdapter.notifyDataSetChanged();
            if (shotAdapter.getCount() == 0) {
                shotListView.addFooterView(endFooter);
            }
        }
    }

    private class LoadmoreUpdater implements IUpdater<DribbbleShot> {

        @Override
        public void startLoadingUI() {
            loading = true;
            if (shotListView.getFooterViewsCount() > 0 && shotListView.getAdapter() != null) {
                shotListView.addFooterView(loadingFooter);
            }
        }

        @Override
        public void stopLoadingUI() {
            loading = false;
            shotListView.removeFooterView(loadingFooter);
        }

        @Override
        public void onError() {

        }

        @Override
        public void updateData(DribbbleShot dribbbleShot) {
            if (!refresher.isRefreshing()) {
                if (dribbbleShot.getShots().size() == 0) {
                    over = true;
                    shotListView.addFooterView(endFooter);
                }
                shotAdapter.addAll(dribbbleShot.getShots());
                cardAdapter.notifyDataSetChanged();
            }
        }
    }

}
