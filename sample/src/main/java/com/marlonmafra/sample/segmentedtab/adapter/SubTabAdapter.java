package com.marlonmafra.sample.segmentedtab.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class SubTabAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList;
    private final Context context;

    public SubTabAdapter(FragmentManager manager, Context context, List<Fragment> fragmentList) {
        super(manager);
        this.context = context;
        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return this.mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return this.mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}
