package com.mag.ybuceng;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mag.ybuceng.Fragments.AnnouncementFragment;
import com.mag.ybuceng.Fragments.FoodMenuFragment;
import com.mag.ybuceng.Fragments.NewsFragment;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, FoodMenuFragment.OnFragmentInteractionListener, AnnouncementFragment.OnFragmentInteractionListener, NewsFragment.OnFragmentInteractionListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        TabLayout.Tab foodMenu = tabLayout.newTab();
        foodMenu.setText("Food Menu");
        foodMenu.setIcon(R.drawable.ic_food_menu);
        tabLayout.addTab(foodMenu);

        TabLayout.Tab announcements = tabLayout.newTab();
        announcements.setText("Announcements");
        announcements.setIcon(R.drawable.ic_announcements);
        tabLayout.addTab(announcements);

        TabLayout.Tab news = tabLayout.newTab();
        news.setText("News");
        news.setIcon(R.drawable.ic_news);
        tabLayout.addTab(news);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
