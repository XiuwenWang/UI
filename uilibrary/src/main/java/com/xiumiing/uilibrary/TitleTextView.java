package com.xiumiing.uilibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

import java.util.List;


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
 * 创建时间：2017/8/24
 * Email：13872829574@163.com
 * 内容描述：
 * 修改人：a.wen
 * 修改时间：${DATA}
 * 修改备注：
 * 修订历史：1.0
 */
public class TitleTextView extends android.support.v7.widget.AppCompatTextView {
    private String mTitle = "";
    private String mMsg = "";
    private int mTitleColor;
    private int mMsgColor;
    private List<Item> mList = null;


    public TitleTextView(Context context) {
        super(context);
    }

    public TitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public TitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray attrbutes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleTextView, 0, 0);
        mMsg = getText().toString();
        mTitle = attrbutes.getString(R.styleable.TitleTextView_tt_title);
        mTitleColor = attrbutes.getColor(R.styleable.TitleTextView_tt_title_color, getCurrentTextColor());
        mMsgColor = getCurrentTextColor();
        setContent();
    }

    private void setContent() {

        if (TextUtils.isEmpty(mTitle)) {
            super.setText(mMsg);
        } else {
            SpannableStringBuilder title = new SpannableStringBuilder(mTitle);
            title.setSpan(new ForegroundColorSpan(mTitleColor), 0, mTitle.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            super.setText(new SpannableStringBuilder().append(title).append(mMsg));
        }

        if (mList != null && mList.size() != 0) {
            SpannableStringBuilder builder = new SpannableStringBuilder();
            for (int i = 0; i < mList.size(); i++) {
                SpannableStringBuilder mTitle = new SpannableStringBuilder(mList.get(i).getmTitle());
                SpannableStringBuilder mMsg = new SpannableStringBuilder(mList.get(i).getmMsg());

                mTitle.setSpan(new ForegroundColorSpan(mTitleColor), 0, mTitle.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                mMsg.setSpan(new ForegroundColorSpan(mMsgColor), 0, mMsg.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

                builder.append(mTitle).append(mMsg);
            }
            super.setText(builder);
        }
    }

    public void setTitle(String title) {
        this.mTitle = title;
        setContent();
    }

    public void setMsg(String msg) {
        this.mMsg = msg;
        this.mList = null;
        setContent();
    }

    public void setmList(List<Item> mList) {
        this.mList = mList;
        mTitle = "";
        mMsg = "";
        setContent();
    }


    public void setTitleColor(int mTitleColor) {
        this.mTitleColor = mTitleColor;
        setContent();
    }

    public String getTitle() {
        return mTitle;
    }

    public String getMsg() {
        return mMsg;
    }


    public int getTitleColor() {
        return mTitleColor;
    }

    public static class Item {
        private String mTitle = "";
        private String mMsg = "";

        public Item(String mTitle, String mMsg) {
            this.mTitle = mTitle;
            this.mMsg = mMsg;
        }

        public String getmTitle() {
            return mTitle;
        }

        public void setmTitle(String mTitle) {
            this.mTitle = mTitle;
        }

        public String getmMsg() {
            return mMsg;
        }

        public void setmMsg(String mMsg) {
            this.mMsg = mMsg;
        }
    }
}
