package com.angcyo.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shape 动态创建类
 * Email:angcyo@126.com
 *
 * @author angcyo
 * @date 2019/01/23
 * Copyright (c) 2019 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class RDrawable {
    public static final int NO_INT = -999;

    //<editor-fold desc="内部成员">

    private Context context;

    private LinkedHashMap<Integer, Drawable> drawableLinkedHashMap = new LinkedHashMap<>();

    private Drawable normalDrawable;

    /**
     * 没次调用doIt, 都将结果保存起来备用
     */
    private Drawable lastDrawable;

    //</editor-fold desc="内部成员">

    private RDrawable(Context context) {
        this.context = context;
    }

    public static RDrawable get(@NonNull Context context) {
        return new RDrawable(context);
    }

    /**
     *
     * 默认使用 GradientDrawable 创建所需要的 Drawable
     *
     * 某些效果, 可能需要 LayerDrawable 配合使用
     *
     * 波纹效果, 必须使用 RippleDrawable 且 API>=21
     *
     * */

    //<editor-fold desc="GradientDrawable 相关属性">

    /**
     * 在设置TextView的leftDrawable等时, 大小位置属性, 就很重要.
     */
    private int width = NO_INT;
    private int height = NO_INT;
    private int left = NO_INT;
    private int right = NO_INT;

    /**
     * 四个角, 8个设置点的圆角信息
     * 从 左上y轴->左上x轴->右上x轴->右上y轴..., 开始设置.
     */
    private float[] radii = new float[8];

    /**
     * 形状
     */
    @Shape
    private int shape = GradientDrawable.RECTANGLE;

    /**
     * 通常用来设置画笔的宽度
     */
    private int strokeWidth = NO_INT;

    /**
     * 边框颜色
     */
    private int strokeColor = Color.TRANSPARENT;

    /**
     * 填充颜色
     */
    private int solidColor = NO_INT;

    //</editor-fold>


    //<editor-fold desc="LayerDrawable 相关属性">

    /**
     * 重力属性, 通常需要配合 LayerDrawable 才能生效.
     * <p>
     * 此属性无法控制 shape为line时的位置 的位置
     * 请使用 inset的方式, 让 rectangle 偏移到视图外, 障眼法
     */
    @Deprecated
    private int gravity = NO_INT;

    private int layerInsetLeft = NO_INT;
    private int layerInsetRight = NO_INT;
    private int layerInsetBottom = NO_INT;
    private int layerInsetTop = NO_INT;

    //</editor-fold>

    //<editor-fold desc="操作属性">

    /**
     * 重置所有值到初始化状态
     */
    public RDrawable reset() {
        resetGradientValue();
        resetLayoutValue();
        resetRippleValue();

        //lastDrawable 为null时, 调用normal()方法, 会调用一次doIt()
        lastDrawable = null;
        return this;
    }

    private void resetGradientValue() {
        //重新分配内存, 否则会覆盖原来的值
        radii = new float[8];
        shape = GradientDrawable.RECTANGLE;
        width = NO_INT;
        height = NO_INT;
        left = NO_INT;
        right = NO_INT;
        strokeWidth = NO_INT;
        strokeColor = Color.TRANSPARENT;
        solidColor = NO_INT;
    }

    private void resetLayoutValue() {
        gravity = NO_INT;
        layerInsetLeft = NO_INT;
        layerInsetRight = NO_INT;
        layerInsetBottom = NO_INT;
        layerInsetTop = NO_INT;
    }

    /**
     * 边框的宽度
     */
    public RDrawable strokeWidth(int width /*px*/) {
        strokeWidth = width;
        return this;
    }

    public RDrawable stroke(int width /*px*/, int color) {
        strokeWidth(width);
        strokeColor(color);
        return this;
    }

    public RDrawable strokeColor(int color) {
        strokeColor = color;
        return this;
    }

    public RDrawable shape(@Shape int shape) {
        this.shape = shape;
        return this;
    }

    /**
     * 填充颜色
     */
    public RDrawable solidColor(int color) {
        solidColor = color;
        return this;
    }

    //</editor-fold desc="操作属性">

    //<editor-fold desc="圆角相关配置">

    /**
     * 4个角, 8个点 圆角配置
     */
    public RDrawable cornerRadii(float[] radii) {
        this.radii = radii;
        return this;
    }

    public RDrawable cornerRadius(float radii) {
        Arrays.fill(this.radii, radii);
        return this;
    }

    /**
     * 只配置左边的圆角
     */
    public RDrawable cornerRadiiLeft(float radii) {
        this.radii[0] = radii;
        this.radii[1] = radii;
        this.radii[6] = radii;
        this.radii[7] = radii;
        return this;
    }

    public RDrawable cornerRadiiRight(float radii) {
        this.radii[2] = radii;
        this.radii[3] = radii;
        this.radii[4] = radii;
        this.radii[5] = radii;
        return this;
    }

    public RDrawable cornerRadiiTop(float radii) {
        this.radii[0] = radii;
        this.radii[1] = radii;
        this.radii[2] = radii;
        this.radii[3] = radii;
        return this;
    }

    public RDrawable cornerRadiiBottom(float radii) {
        this.radii[4] = radii;
        this.radii[5] = radii;
        this.radii[6] = radii;
        this.radii[7] = radii;
        return this;
    }

    //</editor-fold desc="圆角相关配置">


    //<editor-fold desc="Drawable状态配置">

    /**
     * 将当前的配置信息产生的Drawable, 当做 press状态
     */
    public RDrawable pressed(boolean enable) {
        stateSet(android.R.attr.state_pressed, enable, doIt());
        return this;
    }

    /**
     * 焦点状态
     */
    public RDrawable focused(boolean enable) {
        stateSet(android.R.attr.state_focused, enable, doIt());
        return this;
    }

    /**
     * 激活状态
     */
    public RDrawable enabled(boolean enable) {
        stateSet(android.R.attr.state_enabled, enable, doIt());
        return this;
    }

    /**
     * 选中状态
     */
    public RDrawable selected(boolean enable) {
        stateSet(android.R.attr.state_selected, enable, doIt());
        return this;
    }

    /**
     * 勾选状态
     */
    public RDrawable checked(boolean enable) {
        stateSet(android.R.attr.state_checked, enable, doIt());
        return this;
    }

    /**
     * 使用上一次的状态, 同时设置其他状态
     */
    public RDrawable andPressed(boolean enable) {
        stateSet(android.R.attr.state_pressed, enable, lastDrawable);
        return this;
    }


    public RDrawable andFocused(boolean enable) {
        stateSet(android.R.attr.state_focused, enable, lastDrawable);
        return this;
    }

    public RDrawable andEnabled(boolean enable) {
        stateSet(android.R.attr.state_enabled, enable, lastDrawable);
        return this;
    }


    public RDrawable andSelected(boolean enable) {
        stateSet(android.R.attr.state_selected, enable, lastDrawable);
        return this;
    }

    public RDrawable andChecked(boolean enable) {
        stateSet(android.R.attr.state_checked, enable, lastDrawable);
        return this;
    }

    /**
     * 开放对外设置方法
     */
    public RDrawable stateSet(int attr, boolean enable, Drawable drawable) {
        if (enable) {
            drawableLinkedHashMap.put(attr, drawable);
        } else {
            drawableLinkedHashMap.put(-attr, drawable);
        }
        return this;
    }

    /**
     * 设置正常状态的drawable
     */
    public RDrawable normal() {
        if (lastDrawable == null) {
            doIt();
        }
        normalDrawable = lastDrawable;
        return this;
    }


    //</editor-fold desc="Drawable状态配置">

    //<editor-fold desc="Ripple 相关方法和属性">

    private int rippleColor = Color.WHITE;

    private Drawable rippleMaskDrawable;
    private Drawable rippleContentDrawable;

    private void resetRippleValue() {
        rippleColor = Color.WHITE;
    }

    public RDrawable rippleColor(int color) {
        this.rippleColor = color;
        return this;
    }

    public RDrawable andRippleContent() {
        if (lastDrawable == null) {
            doIt();
        }
        rippleContentDrawable = lastDrawable;
        return this;
    }

    public RDrawable andRippleMask() {
        if (lastDrawable == null) {
            doIt();
        }
        rippleMaskDrawable = lastDrawable;
        return this;
    }

    /**
     * 创建具有Ripple效果的drawable.
     * <p>
     * 如果版本不支持时, 返回 state()
     */
    public Drawable ripple() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            lastDrawable = new RippleDrawable(ColorStateList.valueOf(rippleColor), rippleContentDrawable, rippleMaskDrawable);
        } else {
            lastDrawable = state();
        }
        return lastDrawable;
    }

    //</editor-fold desc="Ripple 相关方法和属性">

    //<editor-fold desc="生成可用的Drawable">

    /**
     * 返回具有选择状态的Drawable
     */
    public Drawable state() {
        StateListDrawable listDrawable = new StateListDrawable();
        for (Map.Entry<Integer, Drawable> entry : drawableLinkedHashMap.entrySet()) {
            listDrawable.addState(new int[]{entry.getKey()}, entry.getValue());
        }
        listDrawable.addState(new int[]{}, normalDrawable);
        return listDrawable;
    }

    /**
     * 返回最后一次创建的有效Drawable, 作用类似于 doIt()
     */
    public Drawable get() {
        if (lastDrawable == null) {
            doIt();
        }
        return lastDrawable;
    }

    /**
     * 使用此方法, 获取最终的Drawable
     */
    public Drawable doIt() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(shape);
        if (strokeWidth != NO_INT) {
            gradientDrawable.setStroke(strokeWidth, strokeColor);
        }
        if (solidColor != NO_INT) {
            gradientDrawable.setColor(solidColor);
        }
        gradientDrawable.setCornerRadii(radii);

        lastDrawable = gradientDrawable;
        return lastDrawable;
    }

    //</editor-fold desc="生成可用的Drawable">

    @IntDef({GradientDrawable.RECTANGLE, GradientDrawable.OVAL, GradientDrawable.LINE, GradientDrawable.RING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Shape {
    }
}
