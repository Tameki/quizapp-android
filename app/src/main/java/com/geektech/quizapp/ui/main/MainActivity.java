package com.geektech.quizapp.ui.main;

import android.content.Context;
import android.content.Intent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.geektech.quizapp.R;
import com.geektech.quizapp.ui.settings.SettingsFragment;
import com.geektech.quizapp.ui.widgets.PageChangeListener;
import com.geektech.quizapp.ui.history.HistoryFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MainPagerAdapter mAdapter;
    private BottomNavigationView mNavigation;


    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.main_view_pager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new PageChangeListener() {
            @Override
            public void onPageSelected(int i) {
                int selectedItemId = R.id.nav_main;

                switch (i) {
                    case 1: selectedItemId = R.id.nav_history;
                        break;
                    case 2: selectedItemId = R.id.nav_settings;
                        break;
                }

                mNavigation.setSelectedItemId(selectedItemId);
            }
        });

        mNavigation = findViewById(R.id.main_bottom_nav_bar);
        mNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.nav_main:
                    mViewPager.setCurrentItem(0);
                    break;
                case R.id.nav_history:
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.nav_settings:
                    mViewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment;
            switch (i) {
                case 0: fragment = MainFragment.newInstance();
                    break;
                case 1: fragment = HistoryFragment.newInstance();
                    break;
                default: fragment = SettingsFragment.newInstance();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
