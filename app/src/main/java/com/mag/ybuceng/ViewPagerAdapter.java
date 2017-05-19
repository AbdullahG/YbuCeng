package com.mag.ybuceng;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mag.ybuceng.Fragments.AnnouncementFragment;
import com.mag.ybuceng.Fragments.FoodMenuFragment;
import com.mag.ybuceng.Fragments.NewsFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int numberOfTabs;
    private FoodMenuFragment foodMenuFragment;
    private AnnouncementFragment announcementFragment;
    private NewsFragment newsFragment;
    public ViewPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.foodMenuFragment = new FoodMenuFragment();
        this.announcementFragment = new AnnouncementFragment();
        this.newsFragment = new NewsFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return foodMenuFragment;
            case 1: return announcementFragment;
            case 2: return newsFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
