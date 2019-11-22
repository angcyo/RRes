package com.angcyo.library;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Email:angcyo@126.com
 *
 * @author angcyo
 * @date 2019/01/26
 */
public class RAnim {
    private static final int NO_INT = -0xFFFFFF;

    private View view;

    private Animation lastAnimation = null;

    private RAnim(View view) {
        this.view = view;
    }

    public static RAnim get(@NonNull View view) {
        return new RAnim(view);
    }

    public RAnim reset() {
        resetValue();
        resetAlphaValue();
        resetRotateValue();
        resetScaleValue();
        resetTranslateValue();
        return this;
    }

    //<editor-fold desc="Animation 基础相关属性和方法">

    private long duration = 0L;

    private boolean fillAfter = false;
    private boolean fillBefore = false;

    private Interpolator interpolator = new LinearInterpolator();

    private int repeatCount = 0;
    private int repeatMode = Animation.RESTART;

    private long startOffset = 0L;

    private Animation.AnimationListener animationListener = null;

    private Runnable onAnimationEnd = null;

    private void resetValue() {
        duration = 0L;
        fillAfter = false;
        fillBefore = false;
        interpolator = new LinearInterpolator();
        repeatCount = 0;
        repeatMode = Animation.RESTART;
        startOffset = 0L;
        animationListener = null;
        onAnimationEnd = null;
    }

    public RAnim duration(long durationMillis) {
        duration = durationMillis;
        return this;
    }

    public RAnim startOffset(long startOffset) {
        this.startOffset = startOffset;
        return this;
    }

    public RAnim fillAfter(boolean fillAfter) {
        this.fillAfter = fillAfter;
        return this;
    }

    public RAnim fillBefore(boolean fillBefore) {
        this.fillBefore = fillBefore;
        return this;
    }

    public RAnim interpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public RAnim animationListener(Animation.AnimationListener animationListener) {
        this.animationListener = animationListener;
        return this;
    }

    public RAnim animationEnd(Runnable runnable) {
        onAnimationEnd = runnable;
        return this;
    }

    public RAnim repeatMode(int repeatMode) {
        this.repeatMode = repeatMode;
        return this;
    }

    /**
     * 动画重复模式:重新开始动画
     */
    public RAnim restart() {
        repeatMode(Animation.RESTART);
        return this;
    }

    /**
     * 动画重复模式:反序开始动画
     */
    public RAnim reverse() {
        repeatMode(Animation.REVERSE);
        return this;
    }

    public RAnim repeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    /**
     * 无限循环
     */
    public RAnim infinite() {
        repeatCount(Animation.INFINITE);
        return this;
    }

    //</editor-fold desc="Animation 相关属性和方法">

    //<editor-fold desc="AlphaAnimation 相关属性和方法">

    private float fromAlpha = NO_INT;
    private float toAlpha = NO_INT;

    private void resetAlphaValue() {
        fromAlpha = NO_INT;
        toAlpha = NO_INT;
    }

    public RAnim alpha(float fromAlpha, float toAlpha) {
        this.fromAlpha = fromAlpha;
        this.toAlpha = toAlpha;
        return this;
    }

    //</editor-fold desc="AlphaAnimation 相关属性和方法">

    //<editor-fold desc="RotateAnimation 相关属性和方法">

    private float fromDegrees = NO_INT;
    private float toDegrees = NO_INT;

    /**
     * 以下四个属性, 同样适用于 ScaleAnimation
     */
    @PivotType
    private int pivotXType = Animation.RELATIVE_TO_SELF;

    //[0-1]
    private float pivotXValue = 0.5f;

    @PivotType
    private int pivotYType = Animation.RELATIVE_TO_SELF;
    //[0-1]
    private float pivotYValue = 0.5f;

    private void resetRotateValue() {
        fromDegrees = NO_INT;
        toDegrees = NO_INT;

        pivotXType = Animation.RELATIVE_TO_SELF;
        pivotXValue = 0.5f;

        pivotYType = Animation.RELATIVE_TO_SELF;
        pivotYValue = 0.5f;
    }

    public RAnim rotate(float fromDegrees, float toDegrees) {
        this.fromDegrees = fromDegrees;
        this.toDegrees = toDegrees;
        return this;
    }

    public RAnim pivotType(@PivotType int pivotXType, @PivotType int pivotYType) {
        this.pivotXType = pivotXType;
        this.pivotYType = pivotYType;
        return this;
    }

    public RAnim pivotValue(float pivotXValue, float pivotYValue) {
        this.pivotYValue = pivotYValue;
        this.pivotXValue = pivotXValue;
        return this;
    }

    //</editor-fold desc="RotateAnimation 相关属性和方法">


    //<editor-fold desc="ScaleAnimation 相关属性和方法">

    private float fromX = NO_INT;
    private float toX = NO_INT;
    private float fromY = NO_INT;
    private float toY = NO_INT;

    private void resetScaleValue() {
        fromX = NO_INT;
        toX = NO_INT;
        fromY = NO_INT;
        toY = NO_INT;
    }

    public RAnim scaleX(float fromX, float toX) {
        scale(fromX, toX, 1, 1);
        return this;
    }

    public RAnim scaleY(float fromY, float toY) {
        scale(1, 1, fromY, toY);
        return this;
    }

    public RAnim scale(float from, float to) {
        scale(from, to, from, to);
        return this;
    }

    public RAnim scale(float fromX, float toX, float fromY, float toY) {
        this.fromX = fromX;
        this.toX = toX;
        this.fromY = fromY;
        this.toY = toY;
        return this;
    }

    //</editor-fold desc="ScaleAnimation 相关属性和方法">


    //<editor-fold desc="TranslateAnimation 相关属性和方法">

    @PivotType
    private int fromXType = Animation.RELATIVE_TO_SELF;
    private float fromXValue = NO_INT;
    @PivotType
    private int toXType = Animation.RELATIVE_TO_SELF;
    private float toXValue = NO_INT;
    @PivotType
    private int fromYType = Animation.RELATIVE_TO_SELF;
    private float fromYValue = NO_INT;
    @PivotType
    private int toYType = Animation.RELATIVE_TO_SELF;
    private float toYValue = NO_INT;

    private void resetTranslateValue() {
        fromXType = Animation.RELATIVE_TO_SELF;
        fromXValue = NO_INT;
        toXType = Animation.RELATIVE_TO_SELF;
        toXValue = NO_INT;
        fromYType = Animation.RELATIVE_TO_SELF;
        fromYValue = NO_INT;
        toYType = Animation.RELATIVE_TO_SELF;
        toYValue = NO_INT;
    }

    public RAnim translateVerticalType(@PivotType int fromYType, @PivotType int toYType) {
        this.fromYType = fromYType;
        this.toYType = toYType;
        return this;
    }

    public RAnim translateHorizontalType(@PivotType int fromXType, @PivotType int toXType) {
        this.fromXType = fromXType;
        this.toXType = toXType;
        return this;
    }

    public RAnim translateType(@PivotType int fromXType, @PivotType int toXType,
                               @PivotType int fromYType, @PivotType int toYType) {
        this.fromXType = fromXType;
        this.toXType = toXType;
        this.fromYType = fromYType;
        this.toYType = toYType;
        return this;
    }

    public RAnim translateValue(float fromXValue, float toXValue,
                                float fromYValue, float toYValue) {
        this.fromXValue = fromXValue;
        this.toXValue = toXValue;
        this.fromYValue = fromYValue;
        this.toYValue = toYValue;
        return this;
    }

    public RAnim translateVertical(float fromYValue, float toYValue) {
        translateValue(0, 0, fromYValue, toYValue);
        return this;
    }

    public RAnim translateHorizontal(float fromXValue, float toXValue) {
        translateValue(fromXValue, toXValue, 0, 0);
        return this;
    }

    //</editor-fold desc="TranslateAnimation 相关属性和方法">


    //</editor-fold desc="AnimationSet 相关属性和方法">

    private AnimationSet animationSet = null;

    public RAnim setAndReset() {
        set();
        reset();
        return this;
    }

    public RAnim set() {
        return set(true);
    }

    public RAnim doSet() {
        config(animationSet);
        if (animationSet != null) {
            animationSet.setStartOffset(startOffset);
        }
        return this;
    }

    public RAnim set(boolean shareInterpolator) {
        ensureAnimationSet(shareInterpolator);
        doIt();
        addSet(shareInterpolator, lastAnimation);
        return this;
    }

    public RAnim addSet(boolean shareInterpolator, Animation animation) {
        ensureAnimationSet(shareInterpolator);
        if (animation != null) {
            animationSet.addAnimation(animation);
            config(animationSet);
        }
        return this;
    }

    private void ensureAnimationSet(boolean shareInterpolator) {
        if (animationSet == null) {
            animationSet = new AnimationSet(shareInterpolator);
        }
    }

    //</editor-fold desc="AnimationSet 相关属性和方法">


    //<editor-fold desc="获取Animation">

    private void configListener(Animation animation) {
        if (animationListener != null || onAnimationEnd != null) {
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (animationListener != null) {
                        animationListener.onAnimationStart(animation);
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (animationListener != null) {
                        animationListener.onAnimationEnd(animation);
                    }
                    if (onAnimationEnd != null) {
                        onAnimationEnd.run();
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    if (animationListener != null) {
                        animationListener.onAnimationRepeat(animation);
                    }
                }
            });
        }
    }

    private void config(Animation animation) {
        if (animation != null) {
            if (interpolator != null) {
                animation.setInterpolator(interpolator);
            }

            configListener(animation);

            if (animation instanceof AnimationSet) {
            } else {
                animation.setStartOffset(startOffset);
            }
            //Repeat 属性, 设置在AnimationSet 中无效, 需要单独设置在Animation中
            animation.setRepeatCount(repeatCount);
            animation.setRepeatMode(repeatMode);
            animation.setDuration(duration);
            animation.setFillAfter(fillAfter);
            animation.setFillBefore(fillBefore);
        }
    }

    public Animation get() {
        if (animationSet != null) {
            return animationSet;
        }
        doIt();
        return lastAnimation;
    }

    /**
     * 获取生成的animation
     */
    public Animation doIt() {
        Animation animation = null;
        if (fromAlpha != NO_INT && toAlpha != NO_INT) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
            animation = alphaAnimation;
        } else if (fromDegrees != NO_INT && toDegrees != NO_INT) {
            RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, toDegrees,
                    pivotXType, pivotXValue, pivotYType, pivotYValue);
            animation = rotateAnimation;
        } else if (fromX != NO_INT && toX != NO_INT && fromY != NO_INT && toY != NO_INT) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(fromX, toX,
                    fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue);
            animation = scaleAnimation;
        } else if (fromXValue != NO_INT && toXValue != NO_INT && fromYValue != NO_INT && toYValue != NO_INT) {
            TranslateAnimation translateAnimation = new TranslateAnimation(fromXType, fromXValue,
                    toXType, toXValue, fromYType, fromYValue, toYType, toYValue);
            animation = translateAnimation;
        }
        config(animation);
        lastAnimation = animation;
        return lastAnimation;
    }

    /**
     * 直接成功, 并执行动画
     */
    public Animation start() {
        Animation animation = get();
        if (animation != null) {
            view.startAnimation(animation);
        }
        return animation;
    }

    public Animation start(long durationMillis) {
        duration(durationMillis);
        if (animationSet != null) {
            animationSet.setDuration(durationMillis);
            configListener(animationSet);
        }
        return start();
    }
    //</editor-fold desc="获取Animation">


    //<editor-fold desc="Animator 相关属性和方法">


    //</editor-fold desc="Animator 相关属性和方法">


    //</editor-fold desc="注解">

    @IntDef({Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_PARENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PivotType {
    }
    //</editor-fold desc="注解">

}
