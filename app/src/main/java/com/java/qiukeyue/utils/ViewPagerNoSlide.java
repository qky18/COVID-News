package com.java.qiukeyue.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerNoSlide extends ViewPager {

    public ViewPagerNoSlide(@NonNull Context context) {
        super(context);
    }

    public ViewPagerNoSlide(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
