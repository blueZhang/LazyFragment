package com.example.bluezhang.test.fragment;

import android.widget.GridView;
import java.util.ArrayList;
import java.util.List;

import mr_immortalz.com.lazefragment.R;

/**
 * Created by blueZhang on 2016/12/21.
 *
 * @Author: BlueZhang
 * @date: 2016/12/21
 */

public class FirstFragment extends BaseFragment{
    private GridView gridView;
    private ShowAdapter showAdapter;
    private List<String> mlist;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView() {
        gridView = findView(R.id.gridview);
    }

    @Override
    protected void initData() {
        mlist = new ArrayList<>();
        for (int i=0;i<30;i++){
            mlist.add("show "+i);
        }
        showAdapter = new ShowAdapter(getContext(),mlist);
        gridView.setAdapter(showAdapter);

    }
}
