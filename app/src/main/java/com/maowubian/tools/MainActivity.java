package com.maowubian.tools;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.maowubian.tools.view.SlidingScaleTitleView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new MyFragment();
        Fragment fragment1 = new MyFragment();
        Fragment fragment2 = new MyFragment();
        Fragment fragment3= new MyFragment();
        list.add(fragment);
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        SlidingScaleTitleView view = (SlidingScaleTitleView) findViewById(R.id.tv);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("标题1");
        strings.add("标题2");
        strings.add("标题3");
        strings.add("标题4");
        view.setTitles(strings);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp);
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {


            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }
        };
        view.setViewPagerAdapter(viewPager);
        viewPager.setAdapter(fragmentPagerAdapter);

    }
}
