package com.xiumiing.uilibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by YoKeyword on 16/6/3.
 */
public class BottomBarTab extends FrameLayout {
    private ImageView mIcon;
    private TextView mTvTitle;
    private int mTabPosition = -1;

    private TextView mTvUnreadCount;
    private Builder mBuilder;

    public BottomBarTab(Builder builder) {
        super(builder.mContext);
        init(builder);
    }

    public BottomBarTab(Builder builder, AttributeSet attrs) {
        this(builder, attrs, 0);
        init(builder);
    }

    public BottomBarTab(Builder builder, AttributeSet attrs, int defStyleAttr) {
        super(builder.mContext, attrs, defStyleAttr);
        init(builder);
    }

    private void init(Builder builder) {
        this.mBuilder = builder;
        TypedArray typedArray = builder.mContext.obtainStyledAttributes(new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typedArray.getDrawable(0);
        setBackgroundDrawable(drawable);
        typedArray.recycle();

        LinearLayout lLContainer = new LinearLayout(builder.mContext);
        lLContainer.setOrientation(builder.mOrientation);
        lLContainer.setGravity(Gravity.CENTER);
        LayoutParams paramsContainer = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsContainer.gravity = Gravity.CENTER;
        lLContainer.setLayoutParams(paramsContainer);
        if (builder.mOrientation == LinearLayout.VERTICAL) {
            mIcon = new ImageView(builder.mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px(builder.mIconWidth + 2), dip2px(builder.mIconHeight));
            mIcon.setImageResource(builder.mUnSelectedIcon);
            mIcon.setLayoutParams(params);
//        mIcon.setColorFilter(ContextCompat.getColor(builder.mContext, R.color.tab_unselect));
            lLContainer.addView(mIcon);

            mTvTitle = new TextView(builder.mContext);
            mTvTitle.setText(builder.mTvTitle);
            LinearLayout.LayoutParams paramsTv = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsTv.topMargin = dip2px(5);
            mTvTitle.setTextSize(builder.mTvTitleSize);
            mTvTitle.setTextColor(ContextCompat.getColor(builder.mContext, builder.mUnSelectedTextColor));
            mTvTitle.setLayoutParams(paramsTv);
            lLContainer.addView(mTvTitle);
        } else {
            mTvTitle = new TextView(builder.mContext);
            mTvTitle.setText(builder.mTvTitle);
            LinearLayout.LayoutParams paramsTv = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsTv.rightMargin = dip2px(5);
            mTvTitle.setTextSize(builder.mTvTitleSize);
            mTvTitle.setTextColor(ContextCompat.getColor(builder.mContext, builder.mUnSelectedTextColor));
            mTvTitle.setLayoutParams(paramsTv);
            lLContainer.addView(mTvTitle);

            mIcon = new ImageView(builder.mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px(builder.mIconWidth), dip2px(builder.mIconHeight));
//            paramsTv.leftMargin = dip2px(5);
            mIcon.setBackgroundResource(builder.mUnSelectedIcon);
            mIcon.setLayoutParams(params);
//        mIcon.setColorFilter(ContextCompat.getColor(builder.mContext, R.color.tab_unselect));
            lLContainer.addView(mIcon);
        }


        addView(lLContainer);

//        int min = dip2px(20);
        int padding = dip2px(5);
        mTvUnreadCount = new TextView(builder.mContext);
        mTvUnreadCount.setBackgroundResource(R.drawable.shape_msg_count);
//        mTvUnreadCount.setMinWidth(min);
        mTvUnreadCount.setTextColor(Color.WHITE);
        mTvUnreadCount.setTextSize(13);
        mTvUnreadCount.setGravity(Gravity.CENTER);
        FrameLayout.LayoutParams tvUnReadParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dip2px(16));
        tvUnReadParams.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        tvUnReadParams.topMargin = dip2px(5);
        tvUnReadParams.leftMargin = dip2px(20);
        mTvUnreadCount.setLayoutParams(tvUnReadParams);
        mTvUnreadCount.setPadding(padding, 0, padding, 0);
        mTvUnreadCount.setVisibility(GONE);

        addView(mTvUnreadCount);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
//            mIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            if (mBuilder.mOrientation == LinearLayout.VERTICAL) {
                mIcon.setImageResource(mBuilder.mSelectedIcon);
            } else {
                mIcon.setBackgroundResource(mBuilder.mSelectedIcon);
            }
            mTvTitle.setTextColor(ContextCompat.getColor(getContext(), mBuilder.mSelectedTextColor));
        } else {
            if (mBuilder.mOrientation == LinearLayout.VERTICAL) {
                mIcon.setImageResource(mBuilder.mUnSelectedIcon);
            } else {
                mIcon.setBackgroundResource(mBuilder.mUnSelectedIcon);
            }
//            mIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.tab_unselect));
            mTvTitle.setTextColor(ContextCompat.getColor(getContext(), mBuilder.mUnSelectedTextColor));
        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }

    /**
     * 设置未读数量
     */
    public void setUnreadCount(int num) {
        if (num <= 0) {
//            mTvUnreadCount.setText(String.valueOf(0));
            mTvUnreadCount.setVisibility(GONE);
        } else {
            mTvUnreadCount.setVisibility(VISIBLE);
            if (num > 99) {
                mTvUnreadCount.setText("99+");
            } else {
                mTvUnreadCount.setText(String.valueOf(num));
            }
        }
    }

    /**
     * 获取当前未读数量
     */
    public int getUnreadCount() {
        int count = 0;
        if (TextUtils.isEmpty(mTvUnreadCount.getText())) {
            return count;
        }
        if (mTvUnreadCount.getText().toString().equals("99+")) {
            return 99;
        }
        try {
            count = Integer.valueOf(mTvUnreadCount.getText().toString());
        } catch (Exception ignored) {
        }
        return count;
    }

    private int dip2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    public ImageView getmIcon() {
        return mIcon;
    }

    public TextView getmTvTitle() {
        return mTvTitle;
    }

    public static class Builder {
        private int mSelectedIcon;
        private int mUnSelectedIcon;
        private int mUnSelectedTextColor = R.color.tab_unselect;
        private int mSelectedTextColor = R.color.colorPrimary;
        private int mIconHeight = 22;
        private int mIconWidth = 22;
        private CharSequence mTvTitle;
        private float mTvTitleSize = 11;
        private Context mContext;
        private int mOrientation = LinearLayout.VERTICAL;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public Builder setSelectedIcon(int mSelectedIcon) {
            this.mSelectedIcon = mSelectedIcon;
            return this;
        }

        public Builder setUnSelectedIcon(int mUnSelectedIcon) {
            this.mUnSelectedIcon = mUnSelectedIcon;
            return this;
        }

        public Builder setIconHeight(int iconHeight) {
            this.mIconHeight = iconHeight;
            return this;
        }

        public Builder setIconWidth(int iconWidth) {
            this.mIconWidth = iconWidth;
            return this;
        }

        public Builder setTitle(CharSequence mTvTitle) {
            this.mTvTitle = mTvTitle;
            return this;
        }

        public Builder setTitleSize(float mTvTitleSize) {
            this.mTvTitleSize = mTvTitleSize;
            return this;
        }

        public Builder setOrientation(int orientation) {
            this.mOrientation = orientation;
            return this;
        }

        public Builder setmUnSelectedTextColor(@ColorRes int mUnSelectedTextColor) {
            this.mUnSelectedTextColor = mUnSelectedTextColor;
            return this;
        }

        public Builder setmSelectedTextColor(@ColorRes int mSelectedTextColor) {
            this.mSelectedTextColor = mSelectedTextColor;
            return this;
        }

        public BottomBarTab create() {
            return new BottomBarTab(this);
        }
    }
}
