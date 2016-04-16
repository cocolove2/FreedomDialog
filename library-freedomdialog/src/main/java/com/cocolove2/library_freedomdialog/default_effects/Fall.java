package com.cocolove2.library_freedomdialog.default_effects;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

public class Fall extends BaseAnimatorEffects {

    final int mDuration = 700;

    @Override
    protected void setupAnimator(View view) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "scaleX", 2, 1.5f, 1).setDuration(mDuration);
        getAnimatorSet().playTogether(
                animator1,
                ObjectAnimator.ofFloat(view, "scaleY", 2, 1.5f, 1).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration * 3 / 2)

        );
    }
}
