package com.marlon.sample.segmentedtab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marlon.sample.segmentedtab.R;

public class CustomFragment extends Fragment {

    private String title;

    public static CustomFragment createFragment(String title) {
        CustomFragment customFragment = new CustomFragment();
        customFragment.title = title;
        return customFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.title)).setText(title);
    }
}
