package com.marlonmafra.android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.marlonmafra.android.widget.utils.Utils;

abstract class TabView {

    private final int radius = 3;
    private final int strokeSize = 1;
    private final View view;
    private final Context context;
    private final TextView txtTitle;

    TabView(Context context, int layout) {
        this.context = context;
        this.view = View.inflate(context, layout, null);
        this.txtTitle = (TextView) this.view.findViewById(R.id.txtTitle);
    }

    public void setBackground(int colorSelected, int colorUnselected) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            this.view.setBackgroundDrawable(getDrawableStates(colorSelected, colorUnselected));
        } else {
            this.view.setBackground(getDrawableStates(colorSelected, colorUnselected));
        }
    }

    public void setSelected(boolean selected) {
        this.view.setSelected(selected);
    }

    public void setTitle(String title) {
        this.txtTitle.setText(title);
    }

    public void setTextSize(float size) {
        this.txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setTextColorState(ColorStateList colorState) {
        this.txtTitle.setTextColor(colorState);
    }

    public View getView() {
        return this.view;
    }

    public void setTitleTypeFace(Typeface typeFace) {
        this.txtTitle.setTypeface(typeFace);
    }

    private StateListDrawable getDrawableStates(int colorSelected, int colorUnselected) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, getDrawable(colorSelected, colorSelected));
        stateListDrawable.addState(new int[]{android.R.attr.state_focused}, getDrawable(colorSelected, colorSelected));
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, getDrawable(colorSelected, colorSelected));
        stateListDrawable.addState(new int[]{}, getDrawable(colorUnselected, colorSelected));

        return stateListDrawable;
    }

    private GradientDrawable getDrawable(int backgroundColor, int strokeColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(backgroundColor);
        gradientDrawable.setCornerRadii(getCornerRadii(Utils.getValueByDensity(context, this.radius)));
        gradientDrawable.setStroke(Utils.getValueByDensity(context, this.strokeSize), strokeColor);
        return gradientDrawable;
    }

    protected abstract float[] getCornerRadii(int roundRadius);
}
