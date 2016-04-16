package com.cocolove2.library_freedomdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

import com.cocolove2.library_freedomdialog.default_effects.BaseAnimatorEffects;
import com.cocolove2.library_freedomdialog.listener.OnAnimListener;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;


public class FreedomDialog extends Dialog {
    private Builder builder;


    public FreedomDialog(final Builder builder) {
        super(builder.activity, R.style.FreedomDialog);
        this.builder = builder;
        setCanceledOnTouchOutside(builder.isCancelTouchOutSide);
        setCancelable(builder.isCancelable);
        initInListener();
        initOutListener();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup parent = (ViewGroup) builder.rootView.getParent();
        if (parent != null)
            parent.removeAllViews();
        setContentView(builder.rootView);
        //顺序不能颠倒,不然就不能全屏
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(layoutParams);

        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                startInAnim();
            }
        });
    }


    private void initInListener() {
        if (builder.inEffects != null) {
            builder.inEffects.getAnimatorSet().addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    if (builder.inAnimListener != null)
                        builder.inAnimListener.onAnimStart();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    if (builder.inAnimListener != null)
                        builder.inAnimListener.onAnimCancel();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (builder.inAnimListener != null)
                        builder.inAnimListener.onAnimEnd();
                }
            });
        }

        if (builder.inAnimation != null) {
            builder.inAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (builder.inAnimListener != null)
                        builder.inAnimListener.onAnimStart();
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (builder.inAnimListener != null)
                        builder.inAnimListener.onAnimEnd();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }

    private void initOutListener() {
        builder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (builder.isCancelable && builder.isCancelTouchOutSide) {
                    startOutAnim();
                }
            }
        });
        if (builder.outEffects != null) {
            builder.outEffects.getAnimatorSet().addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    if (builder.outAnimListener != null)
                        builder.outAnimListener.onAnimStart();
                }
                @Override
                public void onAnimationCancel(Animator animation) {
                    if (builder.outAnimListener != null)
                        builder.outAnimListener.onAnimCancel();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    realDismiss();
                    if (builder.outAnimListener != null)
                        builder.outAnimListener.onAnimEnd();
                }
            });
        }


        if (builder.outAnimation != null) {
            builder.outAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (builder.outAnimListener != null)
                        builder.outAnimListener.onAnimStart();
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    realDismiss();
                    if (builder.outAnimListener != null)
                        builder.outAnimListener.onAnimEnd();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }

    @Override
    public void cancel() {
        startOutAnim();
    }

    @Override
    public void dismiss() {
        startOutAnim();
    }

    private void realDismiss() {
        builder.rootView.post(new Runnable() {
            @Override
            public void run() {
                FreedomDialog.super.dismiss();
            }
        });
    }

    private void startAnimator(BaseAnimatorEffects animator) {
        if (animator != null)
            animator.start(builder.rootView);
    }

    private void startAnimation(AnimationSet animationSet) {
        if (animationSet != null) {
            builder.rootView.startAnimation(animationSet);
        }
    }


    private void startInAnim() {
        if (builder.inEffects != null) {
            startAnimator(builder.inEffects);
        } else if (builder.inAnimation != null) {
            startAnimation(builder.inAnimation);
        }
    }

    private void startOutAnim() {
        if (builder.outEffects != null) {
            startAnimator(builder.outEffects);
        } else if (builder.outAnimation != null) {
            startAnimation(builder.outAnimation);
        } else {
            super.dismiss();
        }
    }


    public static class Builder {
        private Activity activity;
        private View rootView;
        private boolean isCancelTouchOutSide = true;
        private boolean isCancelable;

        private BaseAnimatorEffects inEffects;
        private BaseAnimatorEffects outEffects;

        private AnimationSet inAnimation;
        private AnimationSet outAnimation;

        private OnAnimListener inAnimListener;
        private OnAnimListener outAnimListener;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder setCancelable(boolean cancelable) {
            isCancelable = cancelable;
            return this;
        }

        public Builder setContentView(View view) {
            if (null == view)
                throw new NullPointerException("doalog's view is null,place setContentView");

            if (view instanceof ViewGroup && ((ViewGroup) view).getChildCount() > 0)
                ((ViewGroup) view).getChildAt(0).setClickable(true);
            this.rootView = view;

            return this;
        }


        public Builder setAnimator(BaseAnimatorEffects inEffects, BaseAnimatorEffects outEffects) {
            this.inEffects = inEffects;
            this.outEffects = outEffects;
            return this;
        }

        public Builder setAnimation(AnimationSet inAnimation, AnimationSet outAnimation) {
            this.inAnimation = inAnimation;
            this.outAnimation = outAnimation;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean isCancelTouchOutSide) {
            this.isCancelTouchOutSide = isCancelTouchOutSide;
            return this;
        }

        public Builder addInAnimListener(OnAnimListener inAnimListener) {
            this.inAnimListener = inAnimListener;
            return this;
        }

        public Builder addOutAnimListener(OnAnimListener outAnimListener) {
            this.outAnimListener = outAnimListener;
            return this;
        }

        public FreedomDialog build() {
            return new FreedomDialog(this);
        }
    }

}
