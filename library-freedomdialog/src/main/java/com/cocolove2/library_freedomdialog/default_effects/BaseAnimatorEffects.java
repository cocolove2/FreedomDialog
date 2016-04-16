package com.cocolove2.library_freedomdialog.default_effects;

import android.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.view.ViewHelper;


public abstract class BaseAnimatorEffects {

    private AnimatorSet mAnimatorSet;

    {
        mAnimatorSet = new AnimatorSet();
    }

    protected abstract void setupAnimator(View view);

    public void start(View view) {
        reset(view);
        setupAnimator(view);
        Log.e("TAG", view.getMeasuredWidth() + "###" + view.getMeasuredHeight());
        mAnimatorSet.start();
    }

    public void reset(View view) {
//        Log.e("TAG", view.getWidth() + "###" + view.getHeight());
//
//        ViewHelper.setPivotX(view, view.getMeasuredWidth() / 2.0f);
//        ViewHelper.setPivotY(view, view.getMeasuredHeight() / 2.0f);
    }


    public AnimatorSet getAnimatorSet() {
        return mAnimatorSet;
    }


}
