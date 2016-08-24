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
    //记录开始的Y坐标  也就是toolbar起始Y坐标
    if(mStartY == 0) {
      mStartY = (int) dependency.getY();
    }

    //计算toolbar从开始移动到最后的百分比
    float percent = dependency.getY()/mStartY;

    //改变child的坐标(从消失，到可见)
    child.setY(child.getHeight()*(1-percent) - child.getHeight());
    return true;
  }
}
