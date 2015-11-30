package com.example.tongmin.testproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xhc on 2015/11/26.
 */
public class TestIndicatorActivity extends FragmentActivity implements MyIndicator.CustomeTextViewInter {

    private MyPageAdapter adapter ;
    private List<MyFragment> listFragment ;
    private ViewPager pager;
    private MyIndicator indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);
        pager = (ViewPager)findViewById(R.id.mypage);
        indicator = (MyIndicator) findViewById(R.id.myindicator);
        indicator.setCustomeTextViewInter(this);
        listFragment =new ArrayList<>();
        for(int i = 0 ;i < 10 ;i ++){
            Bundle bundle = new Bundle();
            bundle.putString("who","hello world "+ i);
            MyFragment fragment = MyFragment.getInstance(bundle);
            listFragment.add(fragment);
        }
        adapter = new MyPageAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(4);
        indicator.setViewPager(pager);
    }

    @Override
    public TextView selected(TextView tv) {
        tv.setBackgroundColor(Color.parseColor("#8FFF58"));
        return tv;
    }

    @Override
    public TextView unSelect(TextView tv) {
        tv.setBackgroundColor(Color.parseColor("#FFFFFF"));
        return tv;
    }

    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "title - "+position;
        }
    }
}
