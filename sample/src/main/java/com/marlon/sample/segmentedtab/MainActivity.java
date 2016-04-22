package com.marlon.sample.segmentedtab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.marlon.android.widget.SegmentedTab;
import com.marlon.sample.segmentedtab.adapter.SubTabAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SegmentedTab segmentedTab;
    private SubTabAdapter subTabAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        segmentedTab = (SegmentedTab) findViewById(R.id.segmentedTab);
        viewPager = (ViewPager) findViewById(R.id.viewPager);


        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titles = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            fragmentList.add(CustomFragment.createFragment("Tab " + (i + 1)));
            titles.add("Tab " + (i + 1));
        }

        this.subTabAdapter = new SubTabAdapter(getSupportFragmentManager(), this, fragmentList);
        this.viewPager.setAdapter(this.subTabAdapter);
        this.segmentedTab.setupWithViewPager(this.viewPager);
        this.segmentedTab.setup(titles);
    }
}
