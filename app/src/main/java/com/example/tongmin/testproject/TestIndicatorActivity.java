package com.example.tongmin.testproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xhc on 2015/11/26.
 */
public class TestIndicatorActivity extends FragmentActivity{

    private FragmentPagerAdapter adapter ;
    private List<MyFragment> listFragment ;
    private ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);
        pager = (ViewPager)findViewById(R.id.mypage);
        listFragment =new ArrayList<MyFragment>();
        for(int i = 0 ;i < 10 ;i ++){
            MyFragment fragment = new MyFragment();
            listFragment.add(fragment);
        }
        adapter = new PageAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(4);


    }

    class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(FragmentManager fm) {
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
