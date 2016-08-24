package com.wingsofts.custombehavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by wing on 16/8/24.
 */
public class DrawerBehavior extends CoordinatorLayout.Behavior<TextView> {
  private int mFrameMaxHeight = 100;
  private int mStartY;
  @Override
  public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
    return dependency instanceof Toolbar;
  }

  public DrawerBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child,
      View dependency) {

    if(mStartY == 0) {
      mStartY = (int) dependency.getY();
    }
    float percent = dependency.getY()/mStartY;

    child.setY(child.getHeight()*(1-percent) - child.getHeight());
    return true;
  }
}
