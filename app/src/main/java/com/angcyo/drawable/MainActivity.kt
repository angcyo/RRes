package com.angcyo.drawable

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.SweepGradient
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.root_layout).background = CheckerboardDrawable.create()

//        findViewById<TextView>(R.id.text1).apply {
//            setCompoundDrawablesRelative(GradientDrawable().apply {
//                shape = GradientDrawable.LINE
//                setStroke(2, Color.RED)
////            setSize(20, 10)
//                setBounds(10, 0, 40, 20)
//            }, null, null, null)
//
//            background = GradientDrawable().apply {
//                shape = GradientDrawable.LINE
//                setStroke(10, Color.RED)
////                setSize(20, 40)
//                setBounds(0, 20, 20, 20)
//                gravity = Gravity.BOTTOM
//            }
//        }
//
//        findViewById<View>(R.id.view1).background = GradientDrawable().apply {
//            shape = GradientDrawable.LINE
//            setStroke(10, Color.RED)
//            setSize(20, 40)
//            setBounds(10, 10, 40, 20)
//        }
//
//        findViewById<View>(R.id.view3).background = LayerDrawable(emptyArray()).apply {
//            val layer = addLayer(GradientDrawable().apply {
//                shape = GradientDrawable.RECTANGLE
//                setStroke(10, Color.YELLOW)
//                cornerRadius = 10f
//                setColor(Color.BLACK)
//            })
//            setLayerInset(layer, -10, -10, 0, 0)
//        }

        findViewById<View>(R.id.view1).background = resources.getDrawable(R.drawable.shape1)

        findViewById<View>(R.id.view2_1).background = RDrawable.get(this)
            .strokeWidth(10)
            .strokeColor(Color.YELLOW)
            .solidColor(Color.RED)
            .cornerRadiiLeft(10f)
            .doIt()

        findViewById<View>(R.id.view2_2).background = RDrawable.get(this)
            .strokeWidth(10)
            .strokeColor(Color.YELLOW)
            .cornerRadiiRight(20f)
            .doIt()

        findViewById<View>(R.id.view2_3).background = RDrawable.get(this)
            .strokeColor(Color.YELLOW)
            .solidColor(Color.BLUE)
            .cornerRadius(20f)
            .doIt()

        findViewById<View>(R.id.view2_4).background = RDrawable.get(this)
            .solidColor(Color.RED)
            .cornerRadius(20f)
            .pressed(true)
            .andSelected(true)
            .andChecked(true)
            .reset()
            .stroke(4, Color.BLACK)
            .normal()
            .state()

        findViewById<View>(R.id.view2_5).background = RDrawable.get(this)
            .solidColor(Color.MAGENTA)
            .cornerRadius(20f)
            .andRippleContent()
            .rippleColor(Color.WHITE)
            .ripple()

        findViewById<View>(R.id.view3_1).background = RDrawable.get(this)
            .rippleColor(Color.YELLOW)
            .ripple()

        findViewById<View>(R.id.view3_2).background = RDrawable.get(this)
            .rippleColor(Color.YELLOW)
            .solidColor(Color.BLUE)
            .cornerRadius(120f)
            .pressed(true)
            //.andRippleContent()
            .andRippleMask()
            .ripple()


        findViewById<View>(R.id.view4_1).background = RDrawable.get(this)
            .circle(Color.RED)
            .doIt()

        findViewById<View>(R.id.view4_2).background = RDrawable.get(this)
            .circle()
            .stroke(30, Color.YELLOW, 6f, 2f)
            .normal()
            .stroke(60, Color.RED)
            .pressed(true)
            .state()

        findViewById<View>(R.id.view4_3).background = RDrawable.get(this)
            .circle()
            .stroke(50, Color.BLUE)
            .rippleColor(Color.RED)
            .andRippleContent()
            .ripple()


        findViewById<View>(R.id.view5_1).background = RDrawable.get(this)
            .lineColor(Color.GREEN)
            .rightLine(1)
            .get()

        findViewById<View>(R.id.view5_2).background = RDrawable.get(this)
            .lineColor(Color.BLUE)
            .topLine(2)
            .get()

        findViewById<View>(R.id.view5_3).background = RDrawable.get(this)
            .lineColor(Color.RED)
            .leftLine(4)
            .get()

        findViewById<View>(R.id.view5_4).background = RDrawable.get(this)
            .lineColor(Color.BLACK)
            .bottomLine(8)
            .get()

        findViewById<View>(R.id.view6_1).background = RDrawable.get(this)
            .circle(Color.RED)
            .addLayer()
            .lineColor(Color.GREEN)
            .rightLine(10)
            .addLayer()
//            .doIt()
            .get()

        findViewById<View>(R.id.view6_2).background = RDrawable.get(this)
            .circle(Color.RED)
            .addLayer()
            .lineColor(Color.GREEN)
            .rightLine(10)
            .addLayer()
            .andPressed(true)
            .state()
//            .get()

        findViewById<View>(R.id.view6_3).background = RDrawable.get(this)
            .gradientColors(Color.RED, Color.BLUE)
            .cornerRadius(20f)
            .andRippleContent()
            .andRipple()
            .get()

        findViewById<View>(R.id.view6_4).background = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
//            setColor(Color.RED)
            gradientType = GradientDrawable.LINEAR_GRADIENT
            orientation = GradientDrawable.Orientation.LEFT_RIGHT
            colors = intArrayOf(Color.RED, Color.GREEN)
//            setGradientCenter(0.2f, 0.3f)
//            gradientRadius = 0.6f
            setStroke(10, Color.BLACK)
//            useLevel = false

//            cornerRadius = 6f
        }

        findViewById<View>(R.id.view5_5).background = RDrawable.get(this)
            .stroke(30, Color.BLACK)
            .circle(Color.BLUE)
            .fillShader(SweepGradient(0.2f, 0.2f, Color.RED, Color.WHITE))
            .doIt()

//        findViewById<View>(R.id.view5_5).background = RDrawable.get(this)
//            .stroke(30, Color.BLACK)
//            .circle(Color.BLUE)
//            .fillShader(SweepGradient(0.2f, 0.2f, Color.RED, Color.WHITE))
//            .doIt()

        findViewById<View>(R.id.view6_5).background = RDrawable.get(this)
            .stroke(30, Color.BLACK)
            .circle()
            .strokeShader(LinearGradient(0F, 0F, 200F, -1F, Color.RED, Color.GREEN, Shader.TileMode.CLAMP))
//            .gradientColors(Color.RED, Color.BLUE)
//            .cornerRadius(20f)
            .doIt()

        findViewById<View>(R.id.view7_1).background = RDrawable.get(this)
            .shape(GradientDrawable.OVAL)
            .gradientType(GradientDrawable.SWEEP_GRADIENT)
            .gradientOrientation(GradientDrawable.Orientation.TOP_BOTTOM)
            .gradientColors(intArrayOf("#004286FF".toColor(), "#224286FF".toColor(), "#884286FF".toColor()))
            .addLayer()
            .reset()
            .shape(GradientDrawable.OVAL)
            .solidColor("#224286FF".toColor())
            .addLayer()
            .layerInset(20)
            .solidColor("#444286FF".toColor())
            .addLayer()
            .layerInset(40)
            .solidColor("#664286FF".toColor())
            .addLayer()
            .get()
    }

    override fun onPostResume() {
        super.onPostResume()
        val drawable1 = resources.getDrawable(R.drawable.shape1)
        val drawable2 = resources.getDrawable(R.drawable.shape2)
        val drawable3 = resources.getDrawable(R.drawable.shape_line)
        val drawable4 = resources.getDrawable(R.drawable.shape_ring)

        findViewById<View>(R.id.view2).background = drawable4
        drawable1
    }
}

public fun String.toColor(): Int {
    return Color.parseColor(this)
}
