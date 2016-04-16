package com.cocolove2.library_freedomdialog.default_effects;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

public class FlipV extends BaseAnimatorEffects{


    @Override
    protected void setupAnimator(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "rotationX", -90, 0).setDuration(700)

        );
    }
}
