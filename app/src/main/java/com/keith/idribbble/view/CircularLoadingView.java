package com.keith.idribbble.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.keith.idribbble.R;


/**
 * Created by kaka on 2014/7/16.
 */
public class CircularLoadingView extends View {

    private static final float DEFAULT_RADIUS = 64.0F;
    private static final int DEFAULT_BACK_COLOR = Color.LTGRAY;
    private static final int DEFAULT_FORE_COLOR = Color.DKGRAY;
    private static final int DEFAULT_DURATION = 500;

    private Paint backPaint;
    private Paint forePaint;

    private float radius;
    private float backRadius;
    private float foreRadius;

    private float pivotX;
    private float pivotY;

    private int backColor;
    private int foreColor;

    private ValueAnimator animator;

    private int duration;
    private boolean loading = false;


    public CircularLoadingView(Context context) {
        super(context);
    }

    public CircularLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircularLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircularLoadingView);

        radius = array.getDimension(R.styleable.CircularLoadingView_loading_radius, DEFAULT_RADIUS);
        backColor = array.getColor(R.styleable.CircularLoadingView_back_color, DEFAULT_BACK_COLOR);
        foreColor = array.getColor(R.styleable.CircularLoadingView_fore_color, DEFAULT_FORE_COLOR);
        duration = array.getInt(R.styleable.CircularLoadingView_circular_duration, DEFAULT_DURATION);
        pivotX = array.getFloat(R.styleable.CircularLoadingView_loading_pivotX, radius);
        pivotY = array.getFloat(R.styleable.CircularLoadingView_loading_pivotY, radius);

        backPaint = new Paint();
        forePaint = new Paint();

        backPaint.setAntiAlias(true);
        forePaint.setAntiAlias(true);

        backPaint.setColor(backColor);
        forePaint.setColor(foreColor);

        animator = ValueAnimator.ofFloat(radius / 2, radius).setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                backRadius = (Float) valueAnimator.getAnimatedValue();
                foreRadius = radius - backRadius;
                invalidate();
            }
        });

    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(pivotX, pivotY, backRadius, backPaint);
        canvas.drawCircle(pivotX, pivotY, foreRadius, forePaint);

        if (!loading) {
            loading = true;
            animator.start();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = Math.round(getPaddingLeft() + getPaddingRight() + radius * 2);
        int height = Math.round(getPaddingTop() + getPaddingBottom() + radius * 2);
        setMeasuredDimension(width, height);
    }
}
