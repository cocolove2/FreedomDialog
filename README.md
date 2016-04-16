# FreedomDialog

标签（空格分隔）： api

---
### 简介
freedomDialog是为了解决对话框弹出动画效果的自定义对话框。

---

###特性
* 对话框弹出和退出支持补间动画
* 对话框弹出和退出支持属性动画
* 自定义对话框布局样式

---
### 效果图
![pic](https://github.com/cocolove2/FreedomDialog/blob/master/app/screenshot/freedomdialog-screen.gif)
---
### 使用方式

* 创建对话框
```
    builder = new FreedomDialog.Builder(this)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .setContentView(view)
                //弹出动画的监听
                .addInAnimListener(inAnimListener)
                //退出动画的监听
                .addOutAnimListener(outAnimListener)
                //设置弹出和退出补间动画效果
                .setAnimation(inAnim, outAnim)
                //设置弹出和退出的属性动画
                .setAnimator(new FadeIn(), new Fadeout())
        ;
        dialog=builder.build();
```
* 动画监听
```
使用OnAnimListener  或者使用缺省 AnimListenerAdapter

void onAnimStart();
void onAnimEnd();
void onAnimCancel();
```

* 自定义属性动画

```
//例子如下

public class FadeIn extends BaseAnimatorEffects {
    @Override
    protected void setupAnimator(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(700);
        animator.setInterpolator(new LinearInterpolator());
        getAnimatorSet().playTogether(animator);
    }
}
```





