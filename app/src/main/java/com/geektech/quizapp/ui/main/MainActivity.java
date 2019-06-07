package com.geektech.quizapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.geektech.quizapp.App;
import com.geektech.quizapp.R;
import com.geektech.quizapp.data.IQuizRepository;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.ui.widgets.PageChangeListener;
import com.geektech.quizapp.ui.history.HistoryFragment;

import java.util.List;

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

        App.quizRepository.getQuestions(10, new IQuizRepository.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                for (Question item : questions) {
                    Log.d("ololo", item.toString());
                }
            }

            @Override
            public void onFailure(String message) {
                Log.d("ololo", message);
            }
        });
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
                default: fragment = HistoryFragment.newInstance();
            }
            //TODO: Return fragments
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
