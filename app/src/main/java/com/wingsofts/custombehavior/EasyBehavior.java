package com.wingsofts.custombehavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by wing on 16/8/23.
 */
public class EasyBehavior extends CoordinatorLayout.Behavior<TextView> {


  public EasyBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
    Log.e("wing","判断");
    return dependency instanceof Button;
  }

  @Override
  public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
    child.setX(dependency.getX()+200);
    child.setY(dependency.getY()+200);
    child.setText(dependency.getX()+","+dependency.getY());

    return true;
  }
}
