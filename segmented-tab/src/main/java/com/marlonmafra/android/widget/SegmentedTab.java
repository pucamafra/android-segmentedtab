package com.marlonmafra.android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.marlonmafra.android.widget.utils.FontCache;
import com.marlonmafra.android.widget.utils.Utils;

import java.lang.reflect.Field;
import java.util.List;


public class SegmentedTab extends android.support.design.widget.TabLayout {

    private int tabSelectedColor;
    private int tabUnselectedColor;
    private ColorStateList titleColor;
    private int titleTextSize = 12;
    private Typeface typeface;
    private int borderColorSelected;
    private int borderColorUnselected;

    public SegmentedTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public SegmentedTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        setTabMode(TabLayout.MODE_FIXED);
        setTabGravity(TabLayout.GRAVITY_FILL);

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SegmentedTab);
            this.tabSelectedColor = typedArray.getColor(R.styleable.SegmentedTab_tabSelectedColor, ContextCompat.getColor(getContext(), R.color.cerulean));
            this.tabUnselectedColor = typedArray.getColor(R.styleable.SegmentedTab_tabUnselectedColor, ContextCompat.getColor(getContext(), android.R.color.white));

            if (typedArray.hasValue(R.styleable.SegmentedTab_titleColor)) {
                this.titleColor = typedArray.getColorStateList(R.styleable.SegmentedTab_titleColor);
            } else {
                this.titleColor = ContextCompat.getColorStateList(getContext(), R.color.segment_control_title);
            }

            this.titleTextSize = typedArray.getDimensionPixelSize(R.styleable.SegmentedTab_titleTextSize, this.titleTextSize);

            String path = typedArray.getString(R.styleable.SegmentedTab_titleFontPath);
            if (path != null) {
                this.typeface = FontCache.getInstance().put(path, getContext().getAssets());
            }

            this.borderColorSelected = typedArray.getColor(R.styleable.SegmentedTab_borderColorSelected, this.tabSelectedColor);
            this.borderColorUnselected = typedArray.getColor(R.styleable.SegmentedTab_borderColorUnselected, this.tabUnselectedColor);

            typedArray.recycle();
        }

        disablePadding("mTabPaddingStart");
        disablePadding("mTabPaddingEnd");
    }

    public void setup(List<String> titles) {

        if (titles.size() <= 1) {
            throw new IllegalArgumentException("You need at least two tabs");
        }

        for (int i = 0; i < getTabCount(); i++) {
            Tab tab = getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i, titles));
            }
        }

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(Utils.getValueByDensity(getContext(), 20), layoutParams.topMargin, Utils.getValueByDensity(getContext(), 20), layoutParams.bottomMargin);

        int backgroundTransparent = android.R.color.transparent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(AppCompatDrawableManager.get().getDrawable(getContext(), backgroundTransparent));
        }else{
            setBackgroundDrawable(
                    AppCompatDrawableManager.get().getDrawable(getContext(), backgroundTransparent));
        }
    }

    private View getTabView(int position, List<String> titles) {
        TabView tab;

        if (position == 0) {
            tab = new LeftTabView(getContext(), R.layout.left_tab);
            tab.setSelected(true);
        } else if (position == titles.size() - 1) {
            tab = new RightTabView(getContext(), R.layout.right_tab);
        } else {
            tab = new CenterTabView(getContext(), R.layout.center_tab);
        }

        tab.setBackground(this.tabSelectedColor, this.tabUnselectedColor, this.borderColorSelected, this.borderColorUnselected);
        tab.setTitle(titles.get(position));
        tab.setTextSize(this.titleTextSize);
        tab.setTextColorState(this.titleColor);

        if (this.typeface != null) {
            tab.setTitleTypeFace(this.typeface);
        }

        return tab.getView();
    }

    private void disablePadding(String name) {
        try {
            Field mTabPaddingStart = TabLayout.class.getDeclaredField(name);
            mTabPaddingStart.setAccessible(true);
            mTabPaddingStart.setInt(this, Utils.getValueByDensity(getContext(), -0.5));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
