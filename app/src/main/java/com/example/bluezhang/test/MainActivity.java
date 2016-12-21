package com.example.bluezhang.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.bluezhang.test.fragment.FirstFragment;
import com.example.bluezhang.test.fragment.SecondFragment;
import com.example.bluezhang.test.fragment.ThirdFragment;
import com.example.bluezhang.test.fragment.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;

import mr_immortalz.com.lazefragment.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerIndicator indicator;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mList;
    private List<String> mDatas;
    private int itemCount = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.vp);
        indicator = (ViewPagerIndicator) findViewById(R.id.indicator);

        mList = new ArrayList<>();
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        ThirdFragment thirdFragment = new ThirdFragment();
        mList.add(firstFragment);
        mList.add(secondFragment);
        mList.add(thirdFragment);

        mDatas = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            mDatas.add("i=" + i);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mList.get(position);
            }

            @Override
            public int getCount() {
                return mList.size();
            }
        };

        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(itemCount);

        //viewpager bind with indicator
        indicator.setDatas(mDatas);
        indicator.setViewPager(viewPager);
    }

}
