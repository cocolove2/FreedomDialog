package com.cocolove2.library_freedomdialog.default_effects;

import android.view.View;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.ObjectAnimator;


public class FadeIn extends BaseAnimatorEffects {
    @Override
    protected void setupAnimator(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(700);
        animator.setInterpolator(new LinearInterpolator());
        getAnimatorSet().playTogether(animator);
    }
}
