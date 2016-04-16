package com.cocolove2.library_freedomdialog.default_effects;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

public class FlipH extends BaseAnimatorEffects {


    @Override
    protected void setupAnimator(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "rotationY", -90, 0).setDuration(700)

        );
    }
}
