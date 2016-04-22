package com.marlon.android.widget;

import android.content.Context;

public class LeftTabView extends TabView {

    public LeftTabView(Context context, int layout) {
        super(context, layout);
    }

    @Override
    public float[] getCornerRadii(int roundRadius) {
        return new float[]{roundRadius, roundRadius, 0, 0, 0, 0, roundRadius, roundRadius};
    }
}
