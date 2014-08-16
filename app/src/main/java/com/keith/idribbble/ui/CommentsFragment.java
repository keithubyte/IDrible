package com.keith.idribbble.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.keith.idribbble.R;
import com.keith.idribbble.adapter.CommentAdapter;
import com.keith.idribbble.bean.Action;
import com.keith.idribbble.bean.Comment;
import com.keith.idribbble.bean.DribbbleComment;
import com.keith.idribbble.bean.Shot;
import com.keith.idribbble.util.DribbbleApiClient;
import com.keith.idribbble.util.IUpdater;
import com.nirhart.parallaxscroll.views.ParallaxListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class CommentsFragment extends Fragment {

    private static final String ARG_SHOT = "shot";
    private static final int INIT_PAGE = 1;
    private static final int PAGE_ITEMS = 15;
    private static final int THRESHOLD = 4;

    private SwipeRefreshLayout refresher;
    private ParallaxListView commentListView;
    private CommentAdapter commentAdapter;
    private View loadingFooter;
    private View endFooter;
    private View networkFailedView;

    private IUpdater refreshUpdater = new RefreshUpdater();
    private IUpdater loadmoreUpdater = new LoadmoreUpdater();

    private int mPage = INIT_PAGE;
    private boolean loading = false;
    private boolean over = false;
    private Shot shot;

    public static CommentsFragment newInstance(Shot shot) {
        CommentsFragment fragment = new CommentsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SHOT, shot);
        fragment.setArguments(args);
        return fragment;
    }

    public CommentsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            shot = (Shot) getArguments().getSerializable(ARG_SHOT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comments, container, false);

        ImageView image = new ImageView(getActivity());
        image.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, getImageHeight()));
        image.setBackgroundColor(getResources().getColor(R.color.dribbble_background));
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (shot.getImage400Url() == null || shot.getImage400Url().length() == 0) {
            Picasso.with(getActivity()).load(shot.getImageUrl()).into(image);
        } else {
            Picasso.with(getActivity()).load(shot.getImage400Url()).into(image);
        }

        loadingFooter = inflater.inflate(R.layout.view_loading_footer, null);
        endFooter = inflater.inflate(R.layout.view_the_end, null);
        networkFailedView = inflater.inflate(R.layout.view_network_error, null);

        refresher = (SwipeRefreshLayout) view.findViewById(R.id.detail_refresher);
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
                loadComments(mPage++, Action.REFRESH);
            }
        });

        commentListView = (ParallaxListView) view.findViewById(R.id.comments_listview);
        commentListView.addParallaxedHeaderView(image);
        commentAdapter = new CommentAdapter(getActivity(), 0, new ArrayList<Comment>());
        commentListView.setAdapter(commentAdapter);
        commentListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (!loading && !over) {
                    if (totalItemCount > commentListView.getHeaderViewsCount() && totalItemCount - (firstVisibleItem + visibleItemCount) < THRESHOLD) {
                        loadComments(mPage++, Action.LOADMORE);
                    }
                }
            }
        });

        loadComments(mPage++, Action.REFRESH);

        return view;
    }

    private int getImageHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels / 4 * 3; // dribbble image default ratio is 4 : 3
    }

    private void loadComments(int page, Action action) {
        Callback<DribbbleComment> callback = new CommentCallback(action);
        DribbbleApiClient.getDribbbleApiClient().getComments(shot.getId(), page, PAGE_ITEMS, callback);
    }

    private class CommentCallback implements Callback<DribbbleComment> {

        private IUpdater updater;

        private CommentCallback(Action action) {
            this.updater = action == Action.REFRESH ? refreshUpdater : loadmoreUpdater;
            this.updater.startLoadingUI();
        }

        @Override
        public void success(DribbbleComment dribbbleComment, Response response) {
            updater.stopLoadingUI();
            updater.updateData(dribbbleComment);
        }

        @Override
        public void failure(RetrofitError retrofitError) {
            updater.stopLoadingUI();
            updater.onError();
            Log.w("Error", retrofitError.toString());
        }

    }

    private class RefreshUpdater implements IUpdater<DribbbleComment> {

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
            if (commentListView.getFooterViewsCount() == 0) {
                commentListView.addFooterView(networkFailedView);
            }
        }

        @Override
        public void updateData(DribbbleComment dribbbleComment) {
            if (commentListView.getFooterViewsCount() > 0) {
                commentListView.removeFooterView(networkFailedView);
            }
            commentAdapter.clear();
            commentAdapter.addAll(dribbbleComment.getComments());
            commentAdapter.notifyDataSetChanged();
            if (commentAdapter.getCount() == 0) {
                commentListView.addFooterView(endFooter);
            }
        }
    }

    private class LoadmoreUpdater implements IUpdater<DribbbleComment> {

        @Override
        public void startLoadingUI() {
            loading = true;
            commentListView.addFooterView(loadingFooter);
        }

        @Override
        public void stopLoadingUI() {
            loading = false;
            if (commentListView.getFooterViewsCount() > 0 && commentListView.getAdapter() != null) {
                commentListView.removeFooterView(loadingFooter);
            }
        }

        @Override
        public void onError() {

        }

        @Override
        public void updateData(DribbbleComment dribbbleComment) {
            if (!refresher.isRefreshing()) {
                if (dribbbleComment.getComments().size() == 0) {
                    over = true;
                    commentListView.addFooterView(endFooter);
                }
                commentAdapter.addAll(dribbbleComment.getComments());
                commentAdapter.notifyDataSetChanged();
            }
        }
    }

}
