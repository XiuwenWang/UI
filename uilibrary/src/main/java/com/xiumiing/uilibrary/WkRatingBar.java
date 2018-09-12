package com.xiumiing.uilibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import static android.content.ContentValues.TAG;

/**
 * @author Jeffrey
 * Created on 2017/11/22.
 */

public class WkRatingBar extends LinearLayoutCompat {
    private TypedArray mTypedArray;
    private int mTotalStars;
    private int mChooseStars;
    private int mMinChooseStars = 0;
    private boolean isClickable;
    private Drawable mStarEmpty;
    private Drawable mStarFill;
    private int mStarPadding;


    public WkRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WkRatingBar);
        setUp();
    }

    public WkRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WkRatingBar);
        setUp();
    }

    @Override//事件拦截
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent ACTION_DOWN");
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent ACTION_MOVE");
                if (isClickable) {
                    float percentage = (event.getX() - getLeft()) / getWidth();
                    if (percentage > 1) {
                        percentage = 1;
                    } else if (percentage < 0) {
                        percentage = 0;
                    }
                    if (ratingMoveChangeListener != null) {
                        ratingMoveChangeListener.onMove(percentage);
                    }
                    Log.d(TAG, percentage + "");
                    int round = (int) Math.ceil(mTotalStars * percentage);
                    setChooseStars(round);
                    Log.d(TAG, round + "");
                    setStar();
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent ACTION_UP");
                break;
        }
        return true;
    }

    private void setStar() {
        for (int i = 0; i < getChildCount(); i++) {
            if (i <= mChooseStars - 1) {
                ((ImageView) getChildAt(i)).setBackground(mStarFill);
            } else {
                ((ImageView) getChildAt(i)).setBackground(mStarEmpty);
            }
        }

    }

    private void setUp() {
        mTotalStars = mTypedArray.getInt(R.styleable.WkRatingBar_rb_total_star_num, 5);
        mChooseStars = mTypedArray.getInt(R.styleable.WkRatingBar_rb_choose_star_num, 0);
        mMinChooseStars = mTypedArray.getInt(R.styleable.WkRatingBar_rb_min_choose_star_num, 1);
        isClickable = mTypedArray.getBoolean(R.styleable.WkRatingBar_rb_is_clickable, false);
        mStarFill = mTypedArray.getDrawable(R.styleable.WkRatingBar_rb_starFill);
        mStarEmpty = mTypedArray.getDrawable(R.styleable.WkRatingBar_rb_starEmpty);
        mStarPadding = (int) mTypedArray.getDimension(R.styleable.WkRatingBar_rb_star_padding, dp2px(5));
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        initView();
    }

    private void initView() {
        removeAllViews();
        for (int i = 0; i < mTotalStars; i++) {
            addView(getStar(i));
        }
        setStar();
    }

    private View getStar(int i) {
        ImageView star = new ImageView(getContext());
        int width = (int) mTypedArray.getDimension(R.styleable.WkRatingBar_rb_star_width, dp2px(25));
        int height = (int) mTypedArray.getDimension(R.styleable.WkRatingBar_rb_star_height, dp2px(25));
        LayoutParams params = new LayoutParams(width, height);
        params.setMargins(0, 0, i == mTotalStars - 1 ? 0 : mStarPadding, 0);
        star.setLayoutParams(params);
        if (i <= mChooseStars - 1) {
            star.setBackground(mStarFill);
        } else {
            star.setBackground(mStarEmpty);
        }
        return star;
    }


    private onRatingMoveChangeListener ratingMoveChangeListener;

    public onRatingMoveChangeListener getRatingMoveChangeListener() {
        return ratingMoveChangeListener;
    }

    public void setRatingMoveChangeListener(onRatingMoveChangeListener ratingMoveChangeListener) {
        this.ratingMoveChangeListener = ratingMoveChangeListener;
    }

    public interface onRatingMoveChangeListener {
        void onMove(float percentage);
    }

    public int getTotalStars() {
        return mTotalStars;
    }

    public void setTotalStars(int mTotalStars) {
        this.mTotalStars = mTotalStars;
        initView();
    }

    public int getChooseStars() {
        return mChooseStars;
    }

    public void setChooseStars(int mChooseStars) {
        if (mChooseStars >= mMinChooseStars) {
            this.mChooseStars = mChooseStars;
        }
        setStar();
    }

    @Override
    public boolean isClickable() {
        return isClickable;
    }

    @Override
    public void setClickable(boolean clickable) {
        isClickable = clickable;
    }

    public Drawable getStarEmpty() {
        return mStarEmpty;
    }

    public void setStarEmpty(Drawable mStarEmpty) {
        this.mStarEmpty = mStarEmpty;
    }

    public Drawable getStarFill() {
        return mStarFill;
    }

    public void setStarFill(Drawable mStarFill) {
        this.mStarFill = mStarFill;
    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public int dp2px(final float dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
