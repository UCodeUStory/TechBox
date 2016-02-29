package com.sim.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.test2.R;




public class MyTabActivity extends ActionBarActivity {
    private TabLayout mTagLayout;
    private ViewPager mViewPager;
    private PagerAdapter adapter;
    private String [] mTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_tab_activity);
        //实例化ViewPager
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        //初始化TabLayout
        mTagLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTitles=getResources().getStringArray(R.array.news_tags);
        //根据新闻标题添加Tab
        for (int i = 0; i <mTitles.length; i++) {
            mTagLayout.addTab(mTagLayout.newTab().setText(mTitles[i]));
        }
        adapter = new MyPagerAdapter(getSupportFragmentManager(), mTitles);
        mViewPager.setAdapter(adapter);
        //给ViewPager添加监听(这里我们直接使用TabLayout里面提供的TabLayoutOnPageChangeListener无需自己再编写)
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTagLayout));
        //设置setOnTabSelectedListener
        mTagLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //切换到制定的item
                mViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    /**
     * 自定义一个PagerAdapter
     */
    class MyPagerAdapter extends FragmentPagerAdapter {
        String[] newsTags = null;
        public MyPagerAdapter(FragmentManager fm, String[] newsTags) {
            super(fm);
            this.newsTags = newsTags;
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fg=new NewsFragment();
            Bundle bundle=new Bundle();
            //实例化新闻Fragment
            bundle.putString(NewsFragment.NEWS_KEY,newsTags[position]);
            fg.setArguments(bundle);
            return fg;
        }

        @Override
        public int getCount() {
            return (newsTags != null) ? newsTags.length : 0;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
