package com.xiumiing.uilibrary;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

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
 * 创建时间：2017/9/1
 * Email：13872829574@163.com
 * 内容描述：
 * 修改人：a.wen
 * 修改时间：${DATA}
 * 修改备注：
 * 修订历史：1.0
 */

public class NotReusedListView extends LinearLayout {

    BaseAdapter mAdapter;

    public NotReusedListView(Context context) {
        super(context);
    }

    public NotReusedListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NotReusedListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        if (mAdapter != null) {
            for (int i = 0; i < mAdapter.getCount(); i++) {
                this.addView(mAdapter.getView(i, null, this));
            }
        }
    }

    public void setAdapter(BaseAdapter adapter) {
        this.removeAllViews();
        this.mAdapter = adapter;
        initView();
    }

}
