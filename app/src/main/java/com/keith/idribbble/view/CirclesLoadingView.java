package com.keith.idribbble.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.keith.idribbble.R;


/**
 * Created by kaka on 2014/7/16.
 */
public class CirclesLoadingView extends View {

    private static final float DEFAULT_RADIUS = 16.0F;
    private static final float DEFAULT_SPACING = 8.0F;
    private static final int DEFAULT_COLOR = Color.DKGRAY;
    private static final int DEFAULT_DURATION = 500;
    private static final int DEFAULT_DELAY = 150;

    private Paint paint;
    private float radius;
    private int color;
    private int duration;
    private float spacing;
    private int delay;

    private ValueAnimator leftAnimator;
    private ValueAnimator centerAnimator;
    private ValueAnimator rightAnimator;

    private float leftRadius = 0.0f;
    private float centerRadius = 0.0f;
    private float rightRadius = 0.0f;

    private boolean loading = false;

    public CirclesLoadingView(Context context) {
        super(context);
    }

    public CirclesLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CirclesLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CirclesLoadingView);
        radius = array.getDimension(R.styleable.CirclesLoadingView_circle_radius, DEFAULT_RADIUS);
        color = array.getColor(R.styleable.CirclesLoadingView_circle_color, DEFAULT_COLOR);
        duration = array.getInt(R.styleable.CirclesLoadingView_circle_duration, DEFAULT_DURATION);
        spacing = array.getDimension(R.styleable.CirclesLoadingView_circle_spacing, DEFAULT_SPACING);
        delay = array.getInt(R.styleable.CirclesLoadingView_circle_delay, DEFAULT_DELAY);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);

        leftAnimator = ValueAnimator.ofFloat(0.0f, radius).setDuration(duration);
        centerAnimator = ValueAnimator.ofFloat(0.0f, radius).setDuration(duration);
        rightAnimator = ValueAnimator.ofFloat(0.0f, radius).setDuration(duration);

        leftAnimator.setRepeatCount(ValueAnimator.INFINITE);
        centerAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rightAnimator.setRepeatCount(ValueAnimator.INFINITE);

        leftAnimator.setRepeatMode(ValueAnimator.REVERSE);
        centerAnimator.setRepeatMode(ValueAnimator.REVERSE);
        rightAnimator.setRepeatMode(ValueAnimator.REVERSE);

        leftAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                leftRadius = (Float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        centerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                centerRadius = (Float) valueAnimator.getAnimatedValue();
            }
        });
        rightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                rightRadius = (Float) valueAnimator.getAnimatedValue();
            }
        });

        centerAnimator.setStartDelay(delay);
        rightAnimator.setStartDelay(delay * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawCircle(radius, radius, leftRadius, paint);
        canvas.drawCircle(radius * 3 + spacing, radius, centerRadius, paint);
        canvas.drawCircle(radius * 5 + spacing * 2, radius, rightRadius, paint);

        if (!loading) {
            loading = true;
            leftAnimator.start();
            centerAnimator.start();
            rightAnimator.start();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (int) (radius * 6 + spacing * 2) + getPaddingLeft() + getPaddingRight();
        int height = (int) (radius * 2) + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(width, height);
    }
}
