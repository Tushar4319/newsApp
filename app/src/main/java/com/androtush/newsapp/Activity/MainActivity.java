package com.androtush.newsapp.Activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androtush.newsapp.Fragment.EverythingFragment;
import com.androtush.newsapp.Fragment.SourceFragment;
import com.androtush.newsapp.Fragment.TopHeadlinesFragment;
import com.androtush.newsapp.R;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

public class MainActivity extends AppCompatActivity {

    //2f5d94003b614fc29af38e4356e4364f

    private NavigationTabStrip mNavTabs;
    private ViewPager mViewPager;
    private MyPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* mainActivityModel = new EveryThingModel(this);
        mainActivityModel.getEverything("Apple","2019-03-06","popularity","2f5d94003b614fc29af38e4356e4364f");*/

       init();


    }

    public void init(){

        mNavTabs = (NavigationTabStrip) findViewById(R.id.nav_tabs);
        mNavTabs.setTabIndex(0, true);
        mNavTabs.setTitleSize(30);
        mNavTabs.setStripColor(Color.RED);
        mNavTabs.setStripWeight(6);
        mNavTabs.setStripFactor(2);
        mNavTabs.setStripType(NavigationTabStrip.StripType.LINE);
        mNavTabs.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
        mNavTabs.setTypeface("fonts/typeface.ttf");
        mNavTabs.setCornersRadius(3);
        mNavTabs.setAnimationDuration(300);
        mNavTabs.setInactiveColor(Color.BLACK);
        mNavTabs.setActiveColor(Color.RED);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(2);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapterViewPager);

        mNavTabs.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                mViewPager.setCurrentItem(index);
            }

            @Override
            public void onEndTabSelected(String title, int index) {

            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                mNavTabs.setTabIndex(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return TopHeadlinesFragment.newInstance("0", "Page # 1");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return EverythingFragment.newInstance("1", "Page # 2");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return SourceFragment.newInstance("2", "Page # 3");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }

}
