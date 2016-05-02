package com.marlonmafra.android.widget;

import android.content.Context;

public class RightTabView extends TabView {

    public RightTabView(Context context, int layout) {
        super(context, layout);
    }

    @Override
    protected float[] getCornerRadii(int roundRadius) {
        return new float[]{0, 0, roundRadius, roundRadius, roundRadius, roundRadius, 0, 0};
    }
}
