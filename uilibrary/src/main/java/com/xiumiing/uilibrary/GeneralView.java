package com.xiumiing.uilibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * ----------BigGod be here!----------/
 * ***┏┓******┏┓*********
 * *┏━┛┻━━━━━━┛┻━━┓*******
 * *┃             ┃*******
 * *┃     ━━━     ┃*******
 * *┃             ┃*******
 * *┃  ━┳┛   ┗┳━  ┃*******
 * *┃             ┃*******
 * *┃     ━┻━     ┃*******
 * *┃             ┃*******
 * *┗━━━┓     ┏━━━┛*******
 * *****┃     ┃神兽保佑*****
 * *****┃     ┃代码无BUG！***
 * *****┃     ┗━━━━━━━━┓*****
 * *****┃              ┣┓****
 * *****┃              ┏┛****
 * *****┗━┓┓┏━━━━┳┓┏━━━┛*****
 * *******┃┫┫****┃┫┫********
 * *******┗┻┛****┗┻┛*********
 * ━━━━━━神兽出没━━━━━━
 * 版权所有：个人
 * 作者：Created by a.wen.
 * 创建时间：2016/12/24
 * Email：13872829574@163.com
 * 内容描述： 订单详情通用组件
 * 修改人：a.wen
 * 修改时间：${DATA}
 * 修改备注：
 * 修订历史：1.0
 */
public class GeneralView extends RelativeLayout {

    ImageView mImgLeft;
    TextView mTvLeft;
    TextView mTvRight;
    ImageView mImgRight;
    private TextView mTvLeftMsg;

    public GeneralView(Context context) {
        super(context);
        init(context, null);
    }

    public GeneralView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GeneralView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.order_details_general_view, this);
        mImgLeft = (ImageView) inflate.findViewById(R.id.img_left);
        mTvLeft = (TextView) inflate.findViewById(R.id.tv_left);
        mTvLeftMsg = (TextView) inflate.findViewById(R.id.tv_left_msg);
        mTvRight = (TextView) inflate.findViewById(R.id.tv_right);
        mImgRight = (ImageView) inflate.findViewById(R.id.img_right);
        parseStyle(context, attrs);
    }

    private void parseStyle(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GeneralView);
            //左面文字
            mTvLeft.setTextColor(ta.getColor(R.styleable.GeneralView_left_text_textColor, Color.parseColor("#333333")));
            mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimension(R.styleable.GeneralView_left_textSize, sp2px(15)));
            if (!TextUtils.isEmpty(ta.getString(R.styleable.GeneralView_left_text))) {
                mTvLeft.setText(ta.getString(R.styleable.GeneralView_left_text));
            } else {
                mTvLeft.setText("");
            }

            //左面 msg 文字
            mTvLeftMsg.setTextColor(ta.getColor(R.styleable.GeneralView_left_msg_textColor, Color.parseColor("#999999")));
            mTvLeftMsg.setTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimension(R.styleable.GeneralView_left_msg_textSize, sp2px(13)));
            if (!TextUtils.isEmpty(ta.getString(R.styleable.GeneralView_left_msg_text))) {
                mTvLeftMsg.setText(ta.getString(R.styleable.GeneralView_left_msg_text));
            } else {
                mTvLeftMsg.setVisibility(GONE);
            }

            //右边文字
            mTvRight.setTextColor(ta.getColor(R.styleable.GeneralView_right_textColor, Color.parseColor("#333333")));
            mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimension(R.styleable.GeneralView_right_textSize, sp2px(15)));
            if (!TextUtils.isEmpty(ta.getString(R.styleable.GeneralView_right_text))) {
                mTvRight.setText(ta.getString(R.styleable.GeneralView_right_text));
            } else {
                mTvRight.setText("");
            }
            Drawable right_text_background = ta.getDrawable(R.styleable.GeneralView_right_text_background);
            if (null != right_text_background) {
                mTvRight.setBackground(right_text_background);
            }

            //左边图标
            int left_img_height = (int) ta.getDimension(R.styleable.GeneralView_left_img_height, dp2px(16));
            int left_img_width = (int) ta.getDimension(R.styleable.GeneralView_left_img_width, dp2px(16));
            int left_img_marginLeft = (int) ta.getDimension(R.styleable.GeneralView_left_img_marginLeft, dp2px(15));
            LinearLayout.LayoutParams params_left = new LinearLayout.LayoutParams(left_img_width, left_img_height);
            params_left.setMargins(left_img_marginLeft, 0, 0, 0);
            mImgLeft.setLayoutParams(params_left);

            //右边图标
            int right_img_height = (int) ta.getDimension(R.styleable.GeneralView_right_img_height, dp2px(9));
            int right_img_width = (int) ta.getDimension(R.styleable.GeneralView_right_img_width, dp2px(5));
            int right_img_marginRight = (int) ta.getDimension(R.styleable.GeneralView_right_img_marginRight, dp2px(15));
            LinearLayout.LayoutParams params_right = new LinearLayout.LayoutParams(right_img_width, right_img_height);
            params_right.setMargins(0, 0, right_img_marginRight, 0);
            mImgRight.setLayoutParams(params_right);


            Drawable leftDrawable = ta.getDrawable(R.styleable.GeneralView_left_img);
            if (null != leftDrawable) {
                mImgLeft.setBackground(leftDrawable);
            }
            Drawable rightDrawable = ta.getDrawable(R.styleable.GeneralView_right_img);
            if (null != rightDrawable) {
                mImgRight.setBackground(rightDrawable);
            }
            boolean left_img_visibility = ta.getBoolean(R.styleable.GeneralView_left_img_visibility, true);
            if (left_img_visibility) {
                mImgLeft.setVisibility(VISIBLE);
            } else {
                mImgLeft.setVisibility(GONE);
            }
            boolean right_img_visibility = ta.getBoolean(R.styleable.GeneralView_right_img_visibility, false);
            if (right_img_visibility) {
                mImgRight.setVisibility(VISIBLE);
            } else {
                mImgRight.setVisibility(GONE);
            }
            ta.recycle();
        }
    }

    private int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private float sp2px(float spValue) {
        final float scale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return spValue * scale;
    }

    public ImageView getmImgLeft() {
        return mImgLeft;
    }

    public void setmImgLeft(ImageView mImgLeft) {
        this.mImgLeft = mImgLeft;
    }

    public TextView getmTvLeft() {
        return mTvLeft;
    }

    public void setmTvLeft(TextView mTvLeft) {
        this.mTvLeft = mTvLeft;
    }

    public TextView getmTvRight() {
        return mTvRight;
    }

    public void setmTvRight(TextView mTvRight) {
        this.mTvRight = mTvRight;
    }

    public ImageView getmImgRight() {
        return mImgRight;
    }

    public void setmImgRight(ImageView mImgRight) {
        this.mImgRight = mImgRight;
    }
}
