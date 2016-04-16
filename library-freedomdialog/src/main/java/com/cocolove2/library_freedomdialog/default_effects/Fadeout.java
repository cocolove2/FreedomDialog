package com.cocolove2.library_freedomdialog.default_effects;

import android.view.View;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.ObjectAnimator;


public class Fadeout extends BaseAnimatorEffects {
    @Override
    protected void setupAnimator(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1, 0).setDuration(700);
        animator.setInterpolator(new LinearInterpolator());
        getAnimatorSet().playTogether(animator);
    }

}
