package com.marlon.android.widget;

import android.content.Context;

public class CenterTabView extends TabView {

    public CenterTabView(Context context, int layout) {
        super(context, layout);
    }

    @Override
    protected float[] getCornerRadii(int roundRadius) {
        return new float[]{0, 0, 0, 0, 0, 0, 0, 0};
    }
}
