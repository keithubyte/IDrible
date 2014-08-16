package com.keith.idribbble.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by kaka on 2014/7/13.
 */
public class CardAnimationAdapter extends AnimationAdapter {

    private float mTranslationY = 150;
    private float mRotationX = 8;
    private long mDelay;

    public CardAnimationAdapter(BaseAdapter baseAdapter) {
        super(baseAdapter);
        mDelay = 500;
    }

    @Override
    public Animator[] getAnimators(ViewGroup viewGroup, View view) {
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "translationY", mTranslationY, 0),
                ObjectAnimator.ofFloat(view, "rotationX", mRotationX, 0)
        };
    }

    @Override
    protected long getAnimationDurationMillis() {
        return 500;
    }

    @Override
    protected long getAnimationDelayMillis() {
        return mDelay;
    }
}
