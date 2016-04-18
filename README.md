# FreedomDialog

标签（空格分隔）： api

---
### 简介
freedomDialog是为了解决对话框弹出动画效果的自定义对话框。

---
###Thanks

* ######[NiftyDialogEffects](https://github.com/sd6352051/NiftyDialogEffects)

* ######[sweet-alert-dialog](https://github.com/pedant/sweet-alert-dialog)

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
---
###注意事项
* 在你的对话框布局的根布局最外层加一层ViewGroup,例子如下
```
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_gravity="center"
    android:orientation="vertical">

<!--对话框的根布局-->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="120dp"
        android:layout_marginLeft="16dp"
        android:layout_marginRight="16dp"
        android:layout_gravity="center"
        android:gravity="center"
        android:background="@drawable/bg_shap"
        android:orientation="vertical">

        <Button
            android:id="@+id/btn"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="这是基本的对话框界面,您可以任意添加布局"
            android:textColor="@android:color/white" />
    </LinearLayout>

</FrameLayout>


```





