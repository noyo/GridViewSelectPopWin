package com.practice.noyet.gridviewselectpopwin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.practice.noyet.gridviewselectpopwin.view.GridViewSelectPopWin;
import com.practice.noyet.gridviewselectpopwin.adapter.GridViewSelectPopWinAdapter;
import com.practice.noyet.gridviewselectpopwin.R;
import com.practice.noyet.gridviewselectpopwin.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private GridViewSelectPopWin mPopWin;
    private GridViewSelectPopWinAdapter mAdapter;
    private List<Integer> mList;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.content);
        mPopWin = new GridViewSelectPopWin(this);
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            mList.add(1000 + i);
        }
        mAdapter = new GridViewSelectPopWinAdapter(this,
                Util.getMetrics(this).widthPixels / 6,
                mList);
    }

    private void initEvent() {
        findViewById(R.id.text).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text:
                mPopWin.showWin(mPopWin.getContentView(), Gravity.BOTTOM, 0, 0, new GridViewSelectPopWin.onGridViewClickListener() {
                    @Override
                    public void onClick() {
                        mTextView.setText("选中位置：" + mAdapter.getSelectIndex() +  "   选中位置内容：" + mList.get(mAdapter.getSelectIndex()));
                        mPopWin.dismiss();
                    }
                }, mAdapter);
                break;
        }
    }
}
