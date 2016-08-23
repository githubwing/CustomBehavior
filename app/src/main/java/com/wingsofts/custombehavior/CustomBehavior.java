package com.wingsofts.custombehavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import de.hdodenhof.circleimageview.CircleImageView;


//泛型为child类型
public class CustomBehavior extends CoordinatorLayout.Behavior<CircleImageView> {
    private Context mContext;
    //头像的最终大小
    private float mCustomFinalHeight;

    //最终头像的Y
    private float mFinalAvatarY;

    private float mStartAvatarY;

    private float mStartAvatarX;

    private int mAvatarMaxHeight;

    private BounceInterpolator interpolator = new BounceInterpolator();

    public CustomBehavior(Context context, AttributeSet attrs) {
        mContext = context;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomBehavior);
            //获取缩小以后的大小
            mCustomFinalHeight = a.getDimension(R.styleable.CustomBehavior_finalHeight, 0);
            a.recycle();
        }
    }




    // 如果dependency为Toolbar
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }


    //当dependency变化的时候调用
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        //初始化属性
        //init(child, dependency);
        mFinalAvatarY = dependency.getHeight()/2;
        if(mStartAvatarY == 0){
            mStartAvatarY = dependency.getY();
        }
        if(mStartAvatarX == 0){
            mStartAvatarX = child.getX();
        }

        if(mAvatarMaxHeight == 0){
            mAvatarMaxHeight = child.getHeight();
        }


        //让ImageView跟随toolbar垂直移动
        //child.setY(dependency.getY());
        child.setY(dependency.getY()+dependency.getHeight()/2-mCustomFinalHeight/2);

        float percent = dependency.getY() / mStartAvatarY;

        //float x = mStartAvatarX*(1+percent);
        float x = mStartAvatarX * (1+ interpolator.getInterpolation(percent));

        //Log.e("wing","started x "+ mStartAvatarX + " currentX "+ x);

        //当toolbar 达到了位置，就不改变了。
        if(dependency.getY() > dependency.getHeight()/2) {
                child.setX(x);
        }else {
            child.setX(mStartAvatarX + ((mAvatarMaxHeight-mCustomFinalHeight))/2);
        }

        CoordinatorLayout.LayoutParams layoutParams =
            (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        layoutParams.height = (int) ((mAvatarMaxHeight-mCustomFinalHeight) * percent + mCustomFinalHeight);
        layoutParams.width =  (int) ((mAvatarMaxHeight-mCustomFinalHeight) * percent + mCustomFinalHeight);
        child.setLayoutParams(layoutParams);

        return true;
    }


}
