package com.icanstudioz.bootstraper;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;

public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<LinearLayout> {

    private int height;

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, LinearLayout child, int layoutDirection) {
        height = child.getHeight();
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       LinearLayout child, @NonNull
                                               View directTargetChild, @NonNull View target,
                                       int axes, int type)
    {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull LinearLayout child,
               @NonNull View target, int dxConsumed, int dyConsumed,
               int dxUnconsumed, int dyUnconsumed, 
                @ViewCompat.NestedScrollType int type)
    {
       if (dyConsumed > 0) {
           slideDown(child);
       } else if (dyConsumed < 0) {
           slideUp(child);
       }
    }

    private void slideUp(LinearLayout child) {
        child.clearAnimation();
        child.animate().translationY(0).setInterpolator(new BounceInterpolator()).setDuration(100);
    }

    private void slideDown(LinearLayout child) {
        child.clearAnimation();
        child.animate().translationY(height).setInterpolator(new BounceInterpolator()).setDuration(100);
    }
}