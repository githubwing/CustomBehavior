package com.wingsofts.custombehavior;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class EasyBehaviorActivity extends AppCompatActivity {
  private int mBtnX;
  private int mBtnY;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_easy_behavior);
    findViewById(R.id.btn).setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){

          case MotionEvent.ACTION_MOVE:
            v.setX(event.getRawX()-v.getWidth()/2);
            v.setY(event.getRawY()-v.getHeight()/2);
            break;
        }
        return false;
      }
    });

  }
}
